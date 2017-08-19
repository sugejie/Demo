package com.example.handle;

import com.example.entity.Girl;
import com.example.entity.Result;
import com.example.exception.GirlException;
import com.example.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        } else {
            logger.error("[系统异常]{}", e.getClass().getName()); //在控制台输出异常
            return ResultUtil.error(-1, "未知异常：" + e.getClass().getName());
        }
    }
}
