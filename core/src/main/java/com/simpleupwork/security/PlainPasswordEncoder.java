package com.simpleupwork.security;

import com.simpleupwork.auth.EncryptionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence charSequence) {
		return EncryptionUtils.encodeSHA1(charSequence.toString());
	}

	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return EncryptionUtils.encodeSHA1(charSequence.toString()).equals(s);
	}
}
