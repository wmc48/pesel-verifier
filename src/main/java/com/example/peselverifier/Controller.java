package com.example.peselverifier;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    private TextField enteringPesel;
    private String enteredPesel;
    private Button btnVerify;
    private Label peselVerificationInfo;


    public Controller(TextField enteringPesel, Button btnVerify, Label peselVerificationInfo) {
        this.enteringPesel = enteringPesel;
        this.btnVerify = btnVerify;
        this.peselVerificationInfo = peselVerificationInfo;
    }

    public void handleBtnByTypping() {// Obsługa kliknięcia przycisku btnByTyping
        enteringPesel.setVisible(true);
        btnVerify.setVisible(true);
        verificationLabel();
    }

    public void handleBtnVerify() {// Obsługa kliknięcia przycisku BtnVerify
        enteredPesel = enteringPesel.getText(); // przypisanie do zmiennej tekstu wproadzonego w Label przez usera
        Verification verification = new Verification(enteredPesel);
        String result = verification.startVerification();
        peselVerificationInfo.setVisible(true);
        peselVerificationInfo.setText(result);



        //System.out.println(result);

    }

    public void verificationLabel(){


        enteringPesel.textProperty().addListener((observable, oldValue, newValue) -> { // możliwość weryfikacji tylko 11 znakowego stringa
            if (newValue.length() < 11){
                btnVerify.setDisable(true);
                peselVerificationInfo.setVisible(true);
            } else if (newValue.length() > 11)  {
                btnVerify.setDisable(true);
                peselVerificationInfo.setVisible(true);
                peselVerificationInfo.setText("Wprowadzony PESEL jest za długi");
            }else {
                btnVerify.setDisable(false);
                peselVerificationInfo.setVisible(false);
            }
        });
    }
}
