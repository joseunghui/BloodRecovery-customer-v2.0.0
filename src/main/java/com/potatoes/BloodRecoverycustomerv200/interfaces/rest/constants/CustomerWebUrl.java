package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants;

public class CustomerWebUrl {

    // 회원 기본
    public static final String CUSTOMER = "/user";

    // 가입
    public static final String CUSTOMER_ADD = "/add";

    // 아이디 중복체크
    public static final String CUSTOMER_ID_CHECK = "/check/id";

    // 닉네임 중복체크
    public static final String CUSTOMER_NICK_CHECK = "/check/nickname";

    // 실명인증 문자 전송 전 확인
    public static final String CUSTOMER_BEFORE_PERSONAL_CHECK = "/user/before/check/personal";

    // 실명인증 문자 획인
    public static final String CUSTOMER_PERSONAL_CHECK = "/check/personal";

    // 로그인
    public static final String CUSTOMER_LOGIN = "/login";

    // 아이디, 비번, 목록 찾기
    public static final String CUSTOMER_FIND = "/find";

    // 탈퇴
    public static final String CUSTOMER_DELETE = "/delete";

    // 휴면 상태로 변경
    public static final String CUSTOMER_STATUS = "/status";

    // 사용자 목록
    public static final String CUSTOMER_LIST = "/list";

    // 사용자 정보 상세
    public static final String CUSTOMER_EDIT = "/edit";
}
