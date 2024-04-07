package com.example.peselverifier;

public class Pesel {

    private final String enteredPesel;

    public Pesel(String enteredPesel) {
        this.enteredPesel = enteredPesel;
    }

    public StringBuilder getEnteredPesel() {
        return new StringBuilder(enteredPesel);
    }
}
