package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BaseController {

    // Http header 이용 에러 설정
    protected HttpHeaders getSuccessHeader() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("resultCode", "200");
        headers.set("resultMessage", "청상 처리 하였습니다.");
        headers.set("userStatus", "R");

        return headers;
    }
}
