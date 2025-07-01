package org.example.entity;

import lombok.Data;

@Data
public class Result {
    private Integer code; // 编码： 1成功，0为失败
    private String message;// 错误信息
    private Object data;// 数据

    public static Result success(){
        Result result = new Result();
        result.code = 1;
        result.message = "success";
        return result;
    }
    public static Result success(Object object){
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.message = "success";
        return result;
    }
    public static Result error(String message){
        Result result = new Result();
        result.code = 0;
        result.message = message;
        return result;
    }
}
