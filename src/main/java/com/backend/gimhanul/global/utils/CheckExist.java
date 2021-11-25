package com.backend.gimhanul.global.utils;

import java.util.regex.Pattern;

public class CheckExist {

    private final static String CHECK_KR = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";
//    private final static String ONLY_ENG = "^[a-zA-Z]*$";
//    private final static String ONLY_SPECIAL = "^[`~!@#$%^&*()_+=-]*$";
//    private final static String ONLY_NUM = "^[0-9]*$";

    private static boolean checkKR(String message) {
        return Pattern.matches(CHECK_KR, message);
    }

//    private static boolean checkOnlyEng (String message) {
//        return Pattern.matches(ONLY_ENG, message);
//    }
//
//    private static boolean checkOnlySpecial (String message) {
//        return Pattern.matches(ONLY_SPECIAL, message);
//    }
//
//    private static boolean checkOnlyNum (String message) {
//        return Pattern.matches(ONLY_NUM, message);
//    }

    public static boolean checkIsOnlyEng(String message) {
        return !checkKR(message);
    }
}
