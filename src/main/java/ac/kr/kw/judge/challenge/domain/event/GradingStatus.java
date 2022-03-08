package ac.kr.kw.judge.challenge.domain.event;

public enum GradingStatus {
    SUCCESS("SUCCESS"),FAILED("FAILED"),COMPILE_ERROR("COMPILE_ERROR")
    , TIME_LIMIT("TIME_LIMIT"),RUNTIME_ERROR("RUNTIME_ERROR");
    private String value;

    GradingStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
