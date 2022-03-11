package ac.kr.kw.judge.challenge.dto.in;

public class ProblemDto {
    private Long id;
    private String name;


    public ProblemDto() {
    }

    public ProblemDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
