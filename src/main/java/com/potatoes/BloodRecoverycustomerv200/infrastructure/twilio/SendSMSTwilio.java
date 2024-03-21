package com.potatoes.BloodRecoverycustomerv200.infrastructure.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SendSMSTwilio {
    // Install the Java helper library from twilio.com/docs/java/install

    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("AC0adb6b489f90f8f44cd241528957faa3");
    public static final String AUTH_TOKEN = System.getenv("24ec56404bb097e764907f670c57e5e5");

    /**
     * 문자 발송 API
     *
     * @param phone
     * @return
     */
    public static String sendMessage(String phone) {
        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Math.random() 함수로 6자리 인증번호 생성
        int authNumber = makeAuthNumber(100000, 999999);

        // 전달 받은 문자 보낼 phone number (국가코드 +82, 대한민국만)
        String targetPhone = "+82" + phone;

        // 전송할 메시지
        String message = "[피로회복]\n본인 확인을 위해 인증 번호\n[" + targetPhone + "]를 입력해주세요!";

        // 대상 단말기에 본인인증용 메시지 전송
        Message msg = Message.creator(
                // to
                new PhoneNumber(targetPhone),
                // from
                new PhoneNumber("+16179970580"),
                message).create();

        return String.valueOf(authNumber);
    }

    /**
     * 인증번호 생성 메소드
     *
     * @param fromNumber
     * @param toNumber
     * @return
     */
    public static int makeAuthNumber(int fromNumber, int toNumber) {
        return (int) (Math.random() * (toNumber - fromNumber + 1) + toNumber);
    }
}
