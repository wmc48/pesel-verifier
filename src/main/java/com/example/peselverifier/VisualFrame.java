package com.example.peselverifier;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class VisualFrame extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Button btnVerify, btnByTyping, btnFile, btnVerFile, btnChoseGenerate, btnGenerate;
        Label startInfo, verificationInfo, validTextField, verFileInfo, generateInfo, dayOfBirthInfo, genderInfo, quantityInfo;
        TextField enteringPesel, enteringQuantity;
        ListView<String> list;
        DatePicker datePicker;
        RadioButton btnGenderM, btnGenderF;

        //dataPicker
        datePicker = new DatePicker();
        datePicker.setLayoutX(20);
        datePicker.setLayoutY(130);

        //Labels ****************************
        startInfo = new Label("Wybierz, co chcesz zrobić:");
        startInfo.setLayoutX(100);
        startInfo.setLayoutY(10);

        verFileInfo = new Label();
        verFileInfo.setLayoutX(70);
        verFileInfo.setLayoutY(90);

        generateInfo = new Label("uzupełnij dane aby wygenerować pesel");
        generateInfo.setLayoutX(150);
        generateInfo.setLayoutY(90);

        validTextField = new Label();
        validTextField.setLayoutX(270);
        validTextField.setLayoutY(85);
        validTextField.setVisible(false);
        validTextField.setTextFill(Color.GRAY);

        verificationInfo = new Label();
        verificationInfo.setVisible(false);
        verificationInfo.setLayoutX(55);
        verificationInfo.setLayoutY(105);

        dayOfBirthInfo = new Label("Data urodzin:");
        dayOfBirthInfo.setLayoutX(20);
        dayOfBirthInfo.setLayoutY(110);

        genderInfo = new Label("Płeć:");
        genderInfo.setLayoutX(230);
        genderInfo.setLayoutY(110);

        quantityInfo = new Label("Ilość numerów do wygenerowania:");
        quantityInfo.setLayoutX(20);
        quantityInfo.setLayoutY(180);

        //Buttons
        //btnByTyping
        btnByTyping = new Button("Weryfikuj wpisując PESEL");
        btnByTyping.setLayoutX(10);
        btnByTyping.setLayoutY(50);
        //btnFile
        btnFile = new Button("Weryfikuj numer PESEL z pliku");
        btnFile.setLayoutX(170);
        btnFile.setLayoutY(50);
        //btnChoseGenerate
        btnChoseGenerate = new Button("Wygeneruj numer PESEL");
        btnChoseGenerate.setLayoutX(360);
        btnChoseGenerate.setLayoutY(50);
        //btnVerify
        btnVerify = new Button("Weryfikuj");
        btnVerify.setLayoutX(200);
        btnVerify.setLayoutY(80);
        btnVerify.setVisible(false);
        btnVerify.setDisable(true);

        btnVerFile = new Button("Weryfikuj");
        btnVerFile.setLayoutX(200);
        btnVerFile.setLayoutY(130);
        btnVerFile.setVisible(false);

        btnGenerate = new Button("Generuj");
        btnGenerate.setLayoutX(280);
        btnGenerate.setLayoutY(180);
        btnGenerate.setVisible(true);

        btnGenderM = new RadioButton("Mężczyzna");
        btnGenderM.setLayoutX(230);
        btnGenderM.setLayoutY(130);

        btnGenderF = new RadioButton("Kobieta");
        btnGenderF.setLayoutX(230);
        btnGenderF.setLayoutY(150);

        //list
        list = new ListView<String>();
        list.setPrefWidth(450);
        list.setPrefHeight(200);
        list.setLayoutX(40);
        list.setLayoutY(160);
        list.setVisible(false);

        //TextFiled
        enteringPesel = new TextField();
        enteringPesel.setLayoutX(50);
        enteringPesel.setLayoutY(80);
        enteringPesel.setPromptText("Wprowadź numer PESEL...");
        enteringPesel.setVisible(false);

        enteringQuantity = new TextField();
        enteringQuantity.setLayoutX(210);
        enteringQuantity.setLayoutY(180);
        enteringQuantity.setPrefColumnCount(4);

        Controller controller = new Controller(
                enteringPesel,
                btnVerify,
                btnFile,
                btnChoseGenerate,
                btnGenerate,
                btnGenderF,
                btnGenderM,
                validTextField,
                verificationInfo,
                btnVerFile,
                verFileInfo,
                list,
                dayOfBirthInfo,
                datePicker,
                generateInfo,
                genderInfo,
                quantityInfo,
                enteringQuantity);

        btnByTyping.setOnAction(event -> controller.handleBtnByTypping());
        btnVerify.setOnAction(event -> controller.handleBtnVerify());
        btnFile.setOnAction(actionEvent -> controller.handleBtnFile());
        btnChoseGenerate.setOnAction(actionEvent -> controller.handleBtnChoseGenerate());

        Group group = new Group();
        group.getChildren().add(startInfo);
        group.getChildren().add(btnByTyping);
        group.getChildren().add(btnFile);
        group.getChildren().add(btnChoseGenerate);
        group.getChildren().add(enteringPesel);
        group.getChildren().add(btnVerify);
        group.getChildren().add(btnVerFile);
        group.getChildren().add(verFileInfo);
        group.getChildren().add(validTextField);
        group.getChildren().add(verificationInfo);
        group.getChildren().add(list);
        group.getChildren().add(datePicker);
        group.getChildren().add(generateInfo);
        group.getChildren().add(dayOfBirthInfo);
        group.getChildren().add(genderInfo);
        group.getChildren().add(quantityInfo);
        group.getChildren().add(btnGenderF);
        group.getChildren().add(btnGenderM);
        group.getChildren().add(enteringQuantity);
        group.getChildren().add(btnGenerate);


        Scene scene = new Scene(group, 520, 440);
        stage.setTitle("PeselVerifer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}