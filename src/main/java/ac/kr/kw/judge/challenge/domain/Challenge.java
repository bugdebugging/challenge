package ac.kr.kw.judge.challenge.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ElementCollection
    @CollectionTable(name = "challenge_authors", joinColumns = @JoinColumn(name = "challenge_id"))
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> challengeQuestions = new ArrayList<>();

    @Embedded
    private ChallengeDateTime challengeDateTime;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Challenge() {
    }

    public Challenge(String name, List<Author> authors, List<Question> challengeQuestions, ChallengeDateTime challengeDateTime) {
        this.name = name;
        this.authors = authors;
        this.challengeQuestions = challengeQuestions;
        this.challengeDateTime = challengeDateTime;

        this.verifyAuthorNumberLargerOrEqualThanOne();
        this.verifyChallengeQuestionNumberLargerOrEqualThanOne();
        this.checkExistDuplicateAuthor();

        this.mappingChallengeWithQuestion();
    }
    private void mappingChallengeWithQuestion(){
        this.challengeQuestions.stream().forEach(challengeQuestion->challengeQuestion.setChallenge(this));
    }

    private void verifyAuthorNumberLargerOrEqualThanOne() {
        if (authors.isEmpty()) {
            throw new IllegalArgumentException("적어도 한명의 출제진은 존재해야합니다.");
        }
    }

    private void verifyChallengeQuestionNumberLargerOrEqualThanOne() {
        if (challengeQuestions.isEmpty()) {
            throw new IllegalArgumentException("적어도 하나 이상의 문제는 존재해야합니다.");
        }
    }

    public void participateInChallenge(Long userId, String name, LocalDateTime timestamp) {
        this.verifyCanParticipateIn(userId, timestamp);
        this.addParticipation(userId, name);
    }

    private void addParticipation(Long userId, String name) {
        this.participations.add(Participation.of(userId, name, this));
    }

    private void verifyCanParticipateIn(Long userId, LocalDateTime timestamp) {
        this.participations.stream()
                .filter(participation -> participation.getUserId().equals(userId))
                .findFirst().ifPresent((participation) -> {
            throw new IllegalStateException("이미 대회에 참여했습니다.");
        });
        if (!this.challengeDateTime.isBeforeChallenge(timestamp)) {
            throw new IllegalStateException("대회 시작전에만 참여가 가능합니다.");
        }
    }

    public void cancelParticipate(Long participationId) {
        if (!this.challengeDateTime.isBeforeChallenge(LocalDateTime.now())) {
            throw new IllegalArgumentException("대회참여 취소는 시작전에만 가능합니다.");
        }
        Participation willDeletedParticipation = this.participations.stream()
                .filter(participation -> participation.getId().equals(participationId))
                .findFirst().orElseThrow(() -> {
                    throw new IllegalArgumentException("참여하지 않은 대회의 참여취소를 할 수 없습니다.");
                });
        this.participations.remove(willDeletedParticipation);
    }

    public void changeQuestions(List<Question> questions) {
        this.challengeQuestions.clear();
        this.challengeQuestions.addAll(questions);
        this.verifyChallengeQuestionNumberLargerOrEqualThanOne();
        this.mappingChallengeWithQuestion();
    }

    public void changeAuthors(List<Author> authors) {
        this.authors = authors;
        checkExistDuplicateAuthor();
        this.verifyAuthorNumberLargerOrEqualThanOne();
    }

    private void checkExistDuplicateAuthor() {
        if (this.authors.stream().distinct()
                .collect(Collectors.toList()).size() != this.authors.size()) {
            throw new IllegalArgumentException("중복된 저자가 존재할 수 없습니다.");
        }
    }

    public void changeChallengeInfo(String name, ChallengeDateTime challengeDateTime) {
        if (!this.challengeDateTime.isBeforeChallenge(LocalDateTime.now())) {
            throw new IllegalStateException("대회 시작전에만 정보를 수정할 수 있습니다.");
        }
        this.name = name;
        this.challengeDateTime = challengeDateTime;
    }

    public void submitSolutionOfQuestion(Long participationId, Long problemId, ProgrammingLanguage programmingLanguage, String sourceCode) {
        verifyCanSubmit(problemId);
        Participation participator = this.participations.stream()
                .filter(participation -> participation.getId().equals(participationId))
                .findFirst().orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 참여자가 존재하지 않습니다.");
                });
        participator.submitSolutionOfQuestion(problemId, programmingLanguage, sourceCode);
    }

    private void verifyCanSubmit(Long problemId) {
        if (!this.challengeDateTime.isOnProgressChallenge(LocalDateTime.now())) {
            throw new IllegalStateException("대회가 진행중인 경우만 제출할 수 있습니다.");
        }
        this.verifyProblemExistInChallenge(problemId);
    }

    private void verifyProblemExistInChallenge(Long problemId) {
        this.challengeQuestions.stream()
                .filter(question -> question.getProblemId().equals(problemId))
                .findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("해당 문제는 대회에 출제된 문제가 아닙니다.");
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(id, challenge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public List<Question> getChallengeQuestions() {
        return challengeQuestions;
    }

    public ChallengeDateTime getChallengeDateTime() {
        return challengeDateTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
