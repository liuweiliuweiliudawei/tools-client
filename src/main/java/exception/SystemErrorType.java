package exception;

public enum SystemErrorType implements ErrorType{
    SYSTEM_ERROR("1", "系统异常"),
    SYSTEM_BUSY("1", "系统繁忙,请稍候再试"),
    GATEWAY_NOT_FOUND_SERVICE("1", "服务未找到"),
    GATEWAY_ERROR("1", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("1", "网关超时"),
    ARGUMENT_NOT_VALID("1", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("1", "上传文件大小超过限制"),
    DUPLICATE_PRIMARY_KEY("1", "唯一键冲突");

    private String code;
    private String msg;

    private SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
