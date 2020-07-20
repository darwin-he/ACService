package com.admin.vo;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: hxl
 * \* Date: 2018/12/3
 * \* Time: 13:00
 * \* Description:
 * \
 */
public enum CodeEnum {
    SUCCESS(0, "成功"),
    SYSTEM_OK(1000, "保留码"),
    SYSTEM_BUSY(1001, "系统繁忙"),
    SYSTEM_ERROR(1002, "系统错误"),

    SYSTEM_NULL(1002, "系统空指针异常"),
    SYSTEM_PARAM_NULL(1110, "含有空参数"),
    SYSTEM_PARAM_ERROR(1120, "参数结构错误"),
    SYSTEM_PARAM_TIME_ERROR(1130, "参数时间结构错误"),
    SYSTEM_PARAM_DATA_ERROR(1140, "参数数据错误"),

    SYSTEM_UNLOGIN(1200, "请从微信菜单重新进入"),


    WORD_INTERVAL_ERROR(20000, "学习间隙时间不足3分钟，请认真学习"),
    WORD_TASK_OVER(20001, "今日的学习任务已经完成了"),
    SYSTEM_PERMISSIONS(1200, "你的权限不够");


    private final int code;
    private final String msg;

    CodeEnum(int _code, String _msg) {
        this.code = _code;
        this.msg = _msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsgByCode(int code) {
        for (CodeEnum _enum : values()) {
            if (_enum.getCode() == code) {
                return _enum.getMsg();
            }
        }
        return null;
    }

}