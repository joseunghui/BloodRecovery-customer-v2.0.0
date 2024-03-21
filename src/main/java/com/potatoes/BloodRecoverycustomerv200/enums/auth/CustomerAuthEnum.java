package com.potatoes.BloodRecoverycustomerv200.enums.auth;

/**
 * 회원의 인증과 관련된 상태를 나타내는 ENUM
 */
public enum CustomerAuthEnum {
    // 신규회원(0), 기존회원(1)
    NEW_CUSTOMER("0"), OLD_CUSTOMER("1");

    // 해당 값이 아닌 구분 코드로 사용하는 경우
    public enum otherCode {
        OK, NOT_OK
    }


    private final String text;
    private CustomerAuthEnum(final String text) { this.text = text; }
}
