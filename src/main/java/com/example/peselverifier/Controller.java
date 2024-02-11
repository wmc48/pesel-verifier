package com.example.peselverifier;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {

    private TextField enteringPesel;
    private String enteredPesel;
    private Button btnVerify, btnFile;
    private Label validTextField, verificatinInfo;


    public Controller(TextField enteringPesel, Button btnVerify, Button btnFile, Label validTextField, Label verificatinInfo) {
        this.enteringPesel = enteringPesel;
        this.btnVerify = btnVerify;
        this.btnFile = btnFile;
        this.validTextField = validTextField;
        this.verificatinInfo = verificatinInfo;

    }

    public void handleBtnByTypping() {// Obsługa kliknięcia przycisku btnByTyping
        enteringPesel.setVisible(true);
        btnVerify.setVisible(true);
        verificationTextField();
    }

    public void handleBtnVerify() {// Obsługa kliknięcia przycisku BtnVerify
        enteredPesel = enteringPesel.getText(); // przypisanie do zmiennej tekstu wproadzonego w Label przez usera
        Pesel pesel = new Pesel(enteredPesel);
        boolean result = pesel.verByPattern();
        verificatinInfo.setVisible(true);
        if (result){
            verificatinInfo.setText("PESEL " + enteredPesel + " poprawny");
            verificatinInfo.setTextFill(Color.GREEN);
        }else {
            verificatinInfo.setText("PESEL " + enteredPesel + " niepoprawny");
            verificatinInfo.setTextFill(Color.RED);
        }
    }


    public void handleBtnFile() {
        enteringPesel.setVisible(false);
        verificatinInfo.setVisible(false);

        FileManager fileManager = new FileManager();
        fileManager.checkFile();
        fileManager.readFile();
        fileManager.saveFile();
        System.out.println("sciezka pliku to " + fileManager.getFileToSave().getAbsolutePath());

    }

    public void verificationTextField() {
        enteringPesel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 11) {
                btnVerify.setDisable(true);
                validTextField.setVisible(true);
                validTextField.setText("Wprowadzony PESEL jest za krótki");
            } else if (newValue.length() > 11) {
                btnVerify.setDisable(true);
                validTextField.setVisible(true);
                validTextField.setText("Wprowadzony PESEL jest za długi");
            } else if (!isLongInt(newValue)) {
                btnVerify.setDisable(true);
                validTextField.setVisible(true);
                validTextField.setText("Dozwolone tylko cyfry");
            } else {
                btnVerify.setDisable(false);
                validTextField.setVisible(false);
            }
        });
    }


    private boolean isLongInt(String value) { // sprawdzenie czy String to tylko cyfry
        try {
            Long.parseLong(value); // jesli uda sie rzutować na Long to true
            return true;
        } catch (NumberFormatException e) { // w innym przypadku false
            return false;
        }
    }
}
