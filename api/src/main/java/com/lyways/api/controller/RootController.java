package com.lyways.api.controller;

import com.lyways.api.response.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author austin
 * @createDate 2017/11/25.
 */
@RestController
@RequestMapping("/")
public class RootController {
    @RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseModel root(){
        return new ResponseModel().success();
    }
}
