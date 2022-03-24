package ac.kr.kw.judge.commons.exception;

import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiResult> handleBusinessException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiUtils.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResult> handleInvalidRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiUtils.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler({UnAuthorizedException.class})
    public ResponseEntity<ApiResult> handleForbiddenRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiUtils.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }
}
