package unitl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import exception.BaseException;
import exception.ErrorType;
import exception.SystemErrorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author levy
 * @description  返回体
 * @date 2021年1月12日 10:56:13
 * @param <T>
 */
@ApiModel(
        description = "rest请求的返回模型，所有rest正常都返回该类的对象"
)
public class Result<T> implements Serializable {
    public static final String SUCCESSFUL_CODE = "0";
    public static final String SUCCESSFUL_msg = "处理成功";
    @ApiModelProperty(
            value = "处理结果code",
            required = true
    )
    private String code;
    @ApiModelProperty("处理结果描述信息")
    private String msg;
    @ApiModelProperty("处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
    }

    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }

    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.msg = data.toString();
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result("0", "处理成功", data);
    }

    public static Result success(String msg, Object data) {
        return new Result("0", msg, data);
    }

    public static Result success() {
        return success((Object)null);
    }

    public static Result fail() {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }

    public static Result fail(BaseException baseException) {
        return fail((BaseException)baseException, (Object)null);
    }

    public static Result fail(BaseException baseException, Object data) {
        return new Result(baseException.getErrorType(), data);
    }

    public static Result fail(ErrorType errorType, Object data) {
        return new Result(errorType, data);
    }

    public static Result fail(ErrorType errorType) {
        return fail((ErrorType)errorType, (Object)null);
    }

    public static Result fail(String msg) {
        return new Result(SystemErrorType.SYSTEM_ERROR, msg);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return "0".equals(this.code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !this.isSuccess();
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label47;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Result;
    }
/*
    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $code = this.getCode();
        int result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }*/

    public String toString() {
        return "Result(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }
}
