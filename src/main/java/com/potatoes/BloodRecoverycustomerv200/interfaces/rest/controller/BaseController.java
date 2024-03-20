package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BaseController {

    // 초기 실행을 위한 페이지 연결
    @RequestMapping("/")
    public String defaultStart() {
        return "실행 성공!";
    }

    // Http header 이용 에러 설정
    protected HttpHeaders getSuccessHeader(String value) {

        HttpHeaders headers = new HttpHeaders();

        headers.set("resultCode", "200");
        headers.set("resultMessage", "청상 처리 하였습니다.");
        headers.set("userStatus", "R");
        headers.set("cid", value);

        return headers;
    }
}
