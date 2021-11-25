package com.backend.gimhanul.global.utils;

import java.util.regex.Pattern;

public class CheckExist {

    private final static String ONLY_ENG = "^[a-zA-Z]*$";
    private final static String ONLY_SPECIAL = "^[`~!@#$%^&*()_+=-]*$";

    private static boolean checkOnlyEng (String message) {
        return !Pattern.matches(ONLY_ENG, message);
    }

    private static boolean checkOnlySpecial (String message) {
        return !Pattern.matches(ONLY_SPECIAL, message);
    }

    public static boolean checkIsOnlyEng (String message) {
        return checkOnlyEng(message)
                || checkOnlySpecial(message);
    }
}
