package com.backend.gimhanul.global.utils;

import java.util.regex.Pattern;

public class CheckExist {

    public static boolean checkIsOnlyEng (String message) {
        return !Pattern.matches("^[a-zA-Z]*$", message);
    }
}
