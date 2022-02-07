package ac.kr.kw.judge.challenge.service.command;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ParticipationRegisterCommand {
    private Long userId;
    private String name;

    public ParticipationRegisterCommand(Long userId, String name) {
        checkArgument(userId != null, "user_id는 필수입니다.");
        checkArgument(isNotEmpty(name), "name은 필수입니다.");
        this.userId = userId;
        this.name = name;
    }
    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
