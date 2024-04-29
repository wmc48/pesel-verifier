package com.example.peselverifier;

import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private final TextField enteringPesel, enteringQuantity;
    private final Button btnVerify, btnFile, btnVerFile, btnChoseGenerate, btnGenerate;
    private RadioButton btnGenderM, btnGenderF;
    private Label validTextField, verificatinInfo, verFileInfo, dayOfBirthInfo, generateInfo, quantityInfo, genderInfo;
    DatePicker datePicker;
    private final ListView<String> list;


    public Controller(
            TextField enteringPesel,
            Button btnVerify,
            Button btnFile, Button btnChoseGenerate, Button btnGenerate, RadioButton btnGenderF, RadioButton btnGenderM, Label validTextField,
            Label verificatinInfo, Button btnVerFile,
            Label verFileInfo,
            ListView<String> list,
            Label dayOfBirthInfo, DatePicker datePicker, Label generateInfo, Label genderInfo, Label quantityInfo, TextField enteringQuantity) {
        this.enteringPesel = enteringPesel;
        this.btnVerify = btnVerify;
        this.btnFile = btnFile;
        this.validTextField = validTextField;
        this.verificatinInfo = verificatinInfo;
        this.btnVerFile = btnVerFile;
        this.verFileInfo = verFileInfo;
        this.list = list;
        this.btnChoseGenerate = btnChoseGenerate;
        this.btnGenerate = btnGenerate;
        this.btnGenderF = btnGenderF;
        this.btnGenderM = btnGenderM;
        this.enteringQuantity = enteringQuantity;
        this.dayOfBirthInfo = dayOfBirthInfo;
        this.generateInfo = generateInfo;
        this.genderInfo = genderInfo;
        this.quantityInfo = quantityInfo;
        this.datePicker = datePicker;
    }

    public void handleBtnByTypping() {// Obsługa kliknięcia przycisku btnByTyping
        verificationTextField();
        visibleGenerateInfo(false);
        visibleFileInfo(false);
        visibleVerInfo(true);
    }

    public void handleBtnVerify() {// Obsługa kliknięcia przycisku BtnVerify
        String enteredPesel = enteringPesel.getText(); // przypisanie do zmiennej tekstu wproadzonego w Label przez usera
        Pesel pesel = new Pesel(enteredPesel);
        Verification verification = new Verification();
        boolean result = verification.verByPattern(pesel.getEnteredPesel());

        verificatinInfo.setVisible(true);
        if (result) {
            verificatinInfo.setText("PESEL " + enteredPesel + " poprawny");
            verificatinInfo.setTextFill(Color.GREEN);
        } else {
            verificatinInfo.setText("PESEL " + enteredPesel + " niepoprawny");
            verificatinInfo.setTextFill(Color.RED);
        }
    }

    public void handleBtnGenerate(){

        LocalDate getDate = datePicker.getValue();

        int quantity = Integer.parseInt(enteringQuantity.getText());

        Generator generator = new Generator();
        generator.generatedList(quantity, getDate, list);

        }

    public void handleBtnFile() {
        FileManager fileManager = new FileManager();
        visibleGenerateInfo(false);
        visibleVerInfo(false);
        visibleFileInfo(true);

        verFileInfo.setText("Aby zweryfikować dane z pliku, plik powinien znajdować się katalogu: \n " +
                fileManager.getFileToSave().getAbsolutePath());

        btnVerFile.setOnAction(actionEvent -> handleBtnVerFile());//obśługa przycisku "weryfikuj" do weryfikowania z pliku

    }

    public void handleBtnChoseGenerate() {
        visibleVerInfo(false);
        visibleFileInfo(false);
        list.getItems().clear();
        visibleGenerateInfo(true);

    }

    private void visibleGenerateInfo(boolean visible) {
        btnGenerate.setVisible(visible);
        btnGenderF.setVisible(visible);
        btnGenderM.setVisible(visible);
        dayOfBirthInfo.setVisible(visible);
        datePicker.setVisible(visible);
        generateInfo.setVisible(visible);
        genderInfo.setVisible(visible);
        quantityInfo.setVisible(visible);
        enteringQuantity.setVisible(visible);
        list.setVisible(visible);


        validTextField.setVisible(false);
    }

    private void visibleFileInfo(boolean visible) {
        verFileInfo.setVisible(visible);
        btnVerFile.setVisible(visible);
        list.getItems().clear();
        list.setVisible(visible);
        validTextField.setVisible(false);
    }

    private void visibleVerInfo(boolean visible) {
        enteringPesel.setVisible(visible);
        verificatinInfo.setVisible(visible);
        btnVerify.setVisible(visible);
        validTextField.setVisible(false);
    }


    public void handleBtnVerFile() {
        FileManager fileManager = new FileManager();
        fileManager.checkFile();
        fileManager.readFile();
        fileManager.saveFile(list); // zapisanie do pliku oraz dodanie wyników do listView
        list.setVisible(true);
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
