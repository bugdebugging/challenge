package ac.kr.kw.judge.challenge.domain;

import java.util.Arrays;

public enum ProgrammingLanguage {
    CPP("CPP"), C("C"), JAVA("JAVA");
    private String value;

    ProgrammingLanguage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static void checkLanguageIsSupported(String language) {
        Arrays.stream(ProgrammingLanguage.values())
                .filter(programmingLanguage -> language.equals(programmingLanguage.toString()))
                .findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("해당 language는 지원하지 않는 언어입니다.");
        });
    }

    public static ProgrammingLanguage ofSupportedLanguage(String language) {
        checkLanguageIsSupported(language);
        return ProgrammingLanguage.valueOf(language);
    }
}
