package com.fan.Exception;

import com.fan.vo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(VRabbitException.class)
    public ResponseResult ytExceptionHandler(VRabbitException e) {
        ResponseResult responseResult = new ResponseResult(false);
        ErrorCode errorcode = e.getErrorcode();
        responseResult.setCode(errorcode.getCode());
        responseResult.setMsg(errorcode.getDesc());
        responseResult.setData(((VRabbitException) e).getExtraInfo());
        return responseResult;
    }

}
