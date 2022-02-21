package ac.kr.kw.judge.challenge.dto.in;

import ac.kr.kw.judge.challenge.domain.Author;

import java.util.List;

public class AuthorsModifyWrapper {
    private List<Author> authors;

    public AuthorsModifyWrapper() {
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
