package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.validation;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;

public class TwilioValidation {
    // Install the Java helper library from twilio.com/docs/java/install

    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("AC0adb6b489f90f8f44cd241528957faa3");
    public static final String AUTH_TOKEN = System.getenv("24ec56404bb097e764907f670c57e5e5");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Service service = Service.creator("My First Verify Service").create();

        System.out.println(service.getSid());
    }
}
