package com.aid.trade.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The validator class, used to validate email and password.
 */
public class Validator {
    /**
     * The email regex pattern.
     */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * The password regex pattern.
     */
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    /**
     * The email emailPattern
     */
    private Pattern emailPattern;
    /**
     * The password emailPattern.
     */
    private Pattern pwdPattern;
    /**
     * The matcher.
     */
    private Matcher matcher;
    /**
     * The instance of this class.
     */
    private static Validator sInstance;

    /**
     * The Private Constructor.
     */
    private Validator() {
        emailPattern = Pattern.compile(EMAIL_PATTERN);
        pwdPattern = Pattern.compile(PASSWORD_PATTERN);
    }

    /**
     * Gets the instance
     *
     * @return The instance of Validator
     */
    public static Validator getInstance() {
        if (sInstance == null) {
            sInstance = new Validator();
        }
        return sInstance;
    }

    /**
     * Returns TRUE if email is valid.
     *
     * @param email The email has to be validated
     * @return TRUE if email is valid.
     */
    public boolean validateEmail(String email) {
        if (!TextUtils.isEmpty(email)) {
            matcher = emailPattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    /**
     * Returns TRUE if password is valid.
     *
     * @param password The password has to be validated
     * @return TRUE if password is valid.
     */
    public boolean validatePassword(String password) {
        if (!TextUtils.isEmpty(password)) {
            matcher = pwdPattern.matcher(password);
            return matcher.matches();
        }
        return false;
    }

}
