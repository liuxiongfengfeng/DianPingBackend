package com.xiongfeng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 刘雄锋
 * @version 1.0
 * @Date 2022/11/28 18:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    public static Result ok(){
        return new Result(true,null,null,null);
    }

    public static Result ok(Object data){
        return new Result(true,null,data,null);
    }

    public static Result ok(List<Object> data,Long total){
        return new Result(true,null,data,null);
    }

    public static Result fail(String errorMsg){
        return new Result(false,errorMsg,null,null);
    }
}
