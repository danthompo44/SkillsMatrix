package com.university.skillsmatrix.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class PasswordEncoderImpl {
    private enum Encoders {BCRYPT, PBKDF2, SCRYPT, ARGON2}
    public static PasswordEncoder createDelegatingPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        final int STRENGTH = 10;
        encoders.put(Encoders.BCRYPT.name(), new BCryptPasswordEncoder(STRENGTH, new SecureRandom()));

        String PEPPER = "1Ab$2";  		//Secret key for encoding
        int ITERATIONS = 200000;  		//Number of hash iteration
        int HASH_WIDTH = 256;      		//Hash width in bits
        encoders.put(Encoders.PBKDF2.name(), new Pbkdf2PasswordEncoder(PEPPER, ITERATIONS, HASH_WIDTH));

        int CPU_COST = (int) Math.pow(2, 14); //Factor to increase CPU costs
        int MEMORY_COST = 8;     		 //Increases memory usage
        int PARALLELIZATION = 1; 		//Currently not supported
        int KEY_LENGTH = 32;      		//Key length in bytes
        int SALT_LENGTH = 64;     		//Salt length in bytes
        encoders.put(Encoders.SCRYPT.name(), new SCryptPasswordEncoder(CPU_COST, MEMORY_COST, PARALLELIZATION, KEY_LENGTH, SALT_LENGTH));
        encoders.put(Encoders.ARGON2.name(),new Argon2PasswordEncoder(CPU_COST, MEMORY_COST, PARALLELIZATION, KEY_LENGTH, SALT_LENGTH));

        return encoders.get(Encoders.BCRYPT.name());
    }
}
