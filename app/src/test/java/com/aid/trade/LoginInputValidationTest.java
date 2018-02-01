package com.aid.trade;

import com.aid.trade.util.Validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * The login input validation.
 */
public class LoginInputValidationTest {
    @Test
    public void validate_email() {
        assertTrue(Validator.getInstance().validateEmail("user@gmail.com"));
    }

    @Test
    public void validate_invalid_email() {
        assertFalse(Validator.getInstance().validateEmail("usergmail.com"));
    }

    @Test
    public void validate_password_length() {
        assertFalse(Validator.getInstance().validatePassword("123456"));
    }

    @Test
    public void validate_password_without_uppercase() {
        assertFalse(Validator.getInstance().validatePassword("user@test"));
    }

    @Test
    public void validate_password() {
        assertTrue(Validator.getInstance().validatePassword("User@test123"));
    }
}
