package ac.kr.kw.judge.challenge.domain;

public enum SubmitStatus {
    PENDING("PENDING"),SUCCESS("SUCCESS"),FAILED("FAILED");
    private String value;

    SubmitStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
