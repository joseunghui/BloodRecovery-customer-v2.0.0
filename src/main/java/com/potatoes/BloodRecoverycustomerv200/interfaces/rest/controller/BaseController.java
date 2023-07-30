package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    // 초기 실행을 위한 페이지 연결
    @RequestMapping("/home")
    public String defaultStart() {
        return "실행 성공!";
    }

    // Http header 이용 에러 설정
    protected HttpHeaders getSuccessHeader() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("resultCode", "200");
        headers.set("resultMessage", "청상 처리 하였습니다.");
        headers.set("userStatus", "R");

        return headers;
    }
}
