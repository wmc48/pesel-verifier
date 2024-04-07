package com.example.peselverifier;

public abstract class PeselInfo {

    int[] weight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    int controlDigitCalculate(StringBuilder peselNumber, int peselLength){
        //przekazujemy rożną długośc numeru PESEL w zależności czy sprawdzamy czy dopiero generujemy
        int checkDigitSum = 0; // suma kontrolna
        for (int i = 0; i < peselLength; i++) {
            int j = Character.getNumericValue(peselNumber.charAt(i));
            checkDigitSum += j * weight[i];
        }
        //ostatnia cyfra sumy kontrolnej
        int controlDigit = (checkDigitSum % 10 == 0) ? 0 : (10 - (checkDigitSum % 10));
        return controlDigit;
    }
}

