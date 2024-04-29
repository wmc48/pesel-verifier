package com.example.peselverifier;

public class Verification extends PeselInfo {

    //numer błędny 45646545641 - błędna data urodzin
    public boolean verByPattern(String peselNumber) {
        // ostatnia cyfra sumy kontrolnej
        int controlDigit = controlDigitCalculate(peselNumber, peselNumber.length() - 1);
        int checkDigit = Character.getNumericValue(peselNumber.charAt(10)); //ostatnia cyfra numeru pesel jest kontrolna
        //pobieramy date dnia urodzin aby wyeliminowac przypadek PESEL 45646545641
        int dayOfBirth = Character.getNumericValue(peselNumber.charAt(4)) * 10 + Character.getNumericValue(peselNumber.charAt(5));
        //sprawdzenie cyfry kontrolnej oraz dnia urodzenia
        return (controlDigit == checkDigit) && (dayOfBirth < 32 && dayOfBirth > 0);

    }
}
