package com.example.absence.Functions;

import java.security.SecureRandom;
import java.util.Random;

public class Functions {
    public static String generateRandomPassword(int length) {
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_-+={}[]:;<>,.?/";

        String allChars = upperChars + lowerChars + numbers + symbols;

        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        sb.append(upperChars.charAt(random.nextInt(upperChars.length())));
        sb.append(lowerChars.charAt(random.nextInt(lowerChars.length())));
        sb.append(numbers.charAt(random.nextInt(numbers.length())));
        sb.append(symbols.charAt(random.nextInt(symbols.length())));

        for (int i = 4; i < length; i++) {
            sb.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return sb.toString();
    }
}
