package ac.kr.kw.judge.challenge.domain;

public enum ProgrammingLanguage {
    CPP("CPP"), C("C"), JAVA("JAVA");
    private String value;

    ProgrammingLanguage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
