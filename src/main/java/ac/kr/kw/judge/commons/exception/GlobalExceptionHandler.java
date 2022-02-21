package ac.kr.kw.judge.commons.exception;

import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ApiResult handleBusinessException(Exception e) {
        return ApiUtils.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
