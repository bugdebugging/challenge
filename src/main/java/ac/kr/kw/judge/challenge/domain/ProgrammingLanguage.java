package ac.kr.kw.judge.challenge.domain;

public enum ProgrammingLanguage {
    CPP("C++"), C("C"), JAVA("JAVA"), PYTHON("PYTHON"), JAVASCRIPT("JAVASCRIPT");
    private String value;

    ProgrammingLanguage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
