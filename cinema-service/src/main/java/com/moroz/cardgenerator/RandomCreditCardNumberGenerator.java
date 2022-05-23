package com.moroz.cardgenerator;

import java.util.Random;

public class RandomCreditCardNumberGenerator {
    public static String getRandomCard() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            builder.append(new Random().nextInt(9999 - 1111) + 1111);
        }

        return builder.toString();
    }
}

