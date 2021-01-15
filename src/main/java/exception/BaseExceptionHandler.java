package exception;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import unitl.Result;

/**
 * @author qiuw
 * @date 2018/10/11 10:59
 * 全局异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(BaseException.class);
    /**
     * @author qiuw
     *  拦截未知的运行时异常
     * @param e e
     * @return java.lang.Object
     * @date 2019/8/1  14:23
     */
    @ExceptionHandler(value = BaseException.class)
    public Object allExceptionHandler(BaseException e){

        return Result.fail(e.getMessage());
    }

    /**
     * @author qiuw
     *  拦截未知的运行时异常
     * @param e e
     * @return java.lang.Object
     * @date 2019/8/1  14:23
     */
    @ExceptionHandler(RuntimeException.class)
    public Object notFount(RuntimeException e) {
        log.error("运行时异常:{}",e.getMessage());
        return Result.fail(SystemErrorType.SYSTEM_ERROR.getCode());
    }
}
