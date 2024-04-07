package com.example.peselverifier;

import java.util.concurrent.ThreadLocalRandom;

public class Generator extends PeselInfo {

    public StringBuilder generate(int day, int month, int year) {

        StringBuilder peselNumber = new StringBuilder();//"yymmddxxxcc"
        int serialNumber = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);

        String formattedYear = dateFormat(year % 100); //formatujemy tylko ostatnie 2cyfry z całego roku
        String formattedMonth = dateFormat(month);
        String formattedDay = dateFormat(day);

        peselNumber.append(formattedYear).append(formattedMonth).append(formattedDay).append(serialNumber);

        int checkDigit = controlDigitCalculate(peselNumber, peselNumber.length());
        peselNumber.append(checkDigit);
        return peselNumber;

    }
    private String dateFormat(int date) {
        return String.format("%02d", date);

    }

}
