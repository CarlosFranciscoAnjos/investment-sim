package org.investmentsimspring.domain.security;

import com.google.common.annotations.Beta;
import com.google.common.hash.Hashing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author Carlos Anjos
 */
public class HashHelper {

    public static final int STRENGTH = 10;
    private static final String SALT = "98489730328817783267";

    /**
     * @param password
     * @return the hashed password
     */
    @Beta
    private static String SHA512(String password) {
        return Hashing.sha512().hashString(SALT + password, StandardCharsets.UTF_8).toString();
    }

    public static String encode(String password) {
        PasswordEncoder encoder = getPasswordEncoder();
        return encoder.encode(password);
    }

    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(STRENGTH);
    }
}
