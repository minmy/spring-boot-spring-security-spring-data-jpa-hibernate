package com.xinmy.springbootbase.helper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @desc
 */
@ApiModel(value = "操作结果")
public class Result<T> {

    public static final Result  SUCCESS = new Result(true, "操作成功", null);
    public static final Result  FAIL    = new Result(false);
    @ApiModelProperty(value = "结果: true=操作成功, false=操作失败", allowableValues = "true,false", required = true)
    private             boolean success;
    @ApiModelProperty(value = "提示信息")
    private String message;
    @ApiModelProperty(value = "操作失败时的异常信息")
    private String errorMsg;
    @ApiModelProperty(value = "操作结果：附带的数据")
    private             T       data;

    public Result(final boolean success) {
        this.setSuccess(success);
    }

    public Result(final boolean success, final T data) {
        this.setSuccess(success);
        this.setData(data);
    }

    public Result(final boolean success, final String message, final String errorMsg, final T data) {
        this.setSuccess(success);
        this.setData(data);
        this.setMessage(message);
        this.setErrorMsg(errorMsg);
    }

    public Result(final boolean success, final String message, final String errorMsg) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setErrorMsg(errorMsg);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(final String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public int getCode() {
        return success ? 0 : 502;
    }
}
