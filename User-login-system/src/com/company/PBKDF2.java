package com.company;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PBKDF2 {
    private PBKDF2() {}

    private static String generateHashPassword(String password) throws InvalidKeySpecException,
            NoSuchAlgorithmException {
        byte[] salt = new byte[16];
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        StringBuilder hashString = new StringBuilder();
        for (byte b : hash) {
            hashString.append(Math.abs(b));
        }
        return hashString.toString();
    }
    public static String returnHashed(String pass){
        String result=null;
        try {
            result= generateHashPassword(pass);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}