package com.jiang.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    
    private Integer code;
    private String msg;
    private T data;
    
    public Result(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public  Result(Integer code, T data){
        this.code = code;
        this.data = data;
    }
}
