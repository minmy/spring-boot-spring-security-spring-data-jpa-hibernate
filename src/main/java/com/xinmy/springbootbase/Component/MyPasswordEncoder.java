package com.xinmy.springbootbase.Component;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lijianxin
 * @date 2019/9/26 17:13
 * @desc
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s == null ? false : s.equals(charSequence);
    }
}
