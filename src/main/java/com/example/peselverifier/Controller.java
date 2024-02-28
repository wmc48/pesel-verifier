package com.example.peselverifier;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {

    private final TextField enteringPesel;
    private final Button btnVerify, btnFile, btnVerFile;
    private final Label validTextField, verificatinInfo, verFileInfo;
    private ListView list;

    public Controller(TextField enteringPesel, Button btnVerify, Button btnFile, Label validTextField, Label verificatinInfo, Button btnVerFile, Label verFileInfo, ListView list) {
        this.enteringPesel = enteringPesel;
        this.btnVerify = btnVerify;
        this.btnFile = btnFile;
        this.validTextField = validTextField;
        this.verificatinInfo = verificatinInfo;
        this.btnVerFile = btnVerFile;
        this.verFileInfo = verFileInfo;
        this.list = list;

    }

    public void handleBtnByTypping() {// Obsługa kliknięcia przycisku btnByTyping
        enteringPesel.setVisible(true);
        btnVerify.setVisible(true);
        verFileInfo.setVisible(false);
        btnVerFile.setVisible(false);
        list.setVisible(false);
        verificationTextField();
    }

    public void handleBtnVerify() {// Obsługa kliknięcia przycisku BtnVerify
        String enteredPesel = enteringPesel.getText(); // przypisanie do zmiennej tekstu wproadzonego w Label przez usera
        Pesel pesel = new Pesel(enteredPesel);
        boolean result = pesel.verByPattern();
        verificatinInfo.setVisible(true);
        if (result) {
            verificatinInfo.setText("PESEL " + enteredPesel + " poprawny");
            verificatinInfo.setTextFill(Color.GREEN);
        } else {
            verificatinInfo.setText("PESEL " + enteredPesel + " niepoprawny");
            verificatinInfo.setTextFill(Color.RED);
        }
    }


    public void handleBtnFile() {
        FileManager fileManager = new FileManager();
        enteringPesel.setVisible(false);
        verificatinInfo.setVisible(false);
        btnVerify.setVisible(false);
        verFileInfo.setVisible(true);
        verFileInfo.setText("Aby zweryfikować dane z pliku, plik powinien znajdować się katalogu: \n " + fileManager.getFileToSave().getAbsolutePath());
        btnVerFile.setVisible(true);
        btnVerFile.setOnAction(actionEvent -> handleBtnVerFile()); //obśługa przycisku "weryfikuj" do weryfikowania z pliku
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
