package com.primeton.ygq.demo.exception;

/**
 * @Description: 响应码
 * @author 杨功强 
 */

public enum ResponseCode {
	SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    SYSTEM_ERROR(500,"系统异常"),
    PARAM_NOT_NULL(4001, "参数不能为空"),
    USERNAME_EXIST(4002, "用户名已存在"),
    REGISTER_REEOR(4003, "注册失败"),
    USER_NOT_EXIST(4004, "用户不存在"),
    USERNAME_PASS_ERROR(4005, "用户名或密码错误"),
    ORGNAME_EXIST(4006, "该组已存在"),
    ORG_NOT_EXIST(4007, "该组织不存在"),
    ORG_CREAT_REEOR(4008, "添加分组失败"),
    UPDATE_ERROR(4009, "更新失败"),
    SELECT_ERROR(4010, "查询失败"),
    ORG_USERS_ERROR(4011, "该组织存在成员"),
    LOGIN_ERROR(4012, "用户未登录"),
    REMOVE_ERROR(4013, "删除失败"),
    ORGCHILD_ERROR(4014, "请删除子机构");


    private final int code;
    private final String dec;

    ResponseCode(int code, String dec) {
        this.code = code;
        this.dec = dec;
    }

    public int getCode() {
        return code;
    }

    public String getDec() {
        return dec;
    }
}
