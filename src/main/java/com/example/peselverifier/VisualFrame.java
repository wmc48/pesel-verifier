package com.example.peselverifier;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class VisualFrame extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Button btnVerify, btnByTyping, btnFile, btnVerFile;
        Label startInfo, verificationInfo, validTextField, verFileInfo;
        TextField enteringPesel;
        ListView<String> list;

        //Label
        startInfo = new Label("Wybierz w jaki sposób chcesz zweryfikować nr PESEL:");
        startInfo.setLayoutX(100);
        startInfo.setLayoutY(10);

        verFileInfo = new Label();
        verFileInfo.setLayoutX(70);
        verFileInfo.setLayoutY(90);

        validTextField = new Label();
        validTextField.setLayoutX(270);
        validTextField.setLayoutY(85);
        validTextField.setVisible(false);
        validTextField.setTextFill(Color.GRAY);

        verificationInfo = new Label();
        verificationInfo.setVisible(false);
        verificationInfo.setLayoutX(55);
        verificationInfo.setLayoutY(105);


        //Buttons
        //btnByTyping
        btnByTyping = new Button("Weryfikuj wpisując PESEL");
        btnByTyping.setLayoutX(50);
        btnByTyping.setLayoutY(50);
        //btnFile
        btnFile = new Button("Weryfikuj numer PESEL z pliku");
        btnFile.setLayoutX(250);
        btnFile.setLayoutY(50);
        //btnVerify
        btnVerify = new Button("Weryfikuj");
        btnVerify.setLayoutX(200);
        btnVerify.setLayoutY(80);
        btnVerify.setVisible(false);
        btnVerify.setDisable(false);

        btnVerFile = new Button("Weryfikuj");
        btnVerFile.setLayoutX(200);
        btnVerFile.setLayoutY(130);
        btnVerFile.setVisible(false);

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

        Controller controller = new Controller(enteringPesel, btnVerify, btnFile, validTextField, verificationInfo, btnVerFile, verFileInfo, list);

        btnByTyping.setOnAction(event -> controller.handleBtnByTypping());
        btnVerify.setOnAction(event -> controller.handleBtnVerify());
        btnFile.setOnAction(actionEvent -> controller.handleBtnFile());

        Group group = new Group();
        group.getChildren().add(startInfo);
        group.getChildren().add(btnByTyping);
        group.getChildren().add(btnFile);
        group.getChildren().add(enteringPesel);
        group.getChildren().add(btnVerify);
        group.getChildren().add(btnVerFile);
        group.getChildren().add(verFileInfo);
        group.getChildren().add(validTextField);
        group.getChildren().add(verificationInfo);
        group.getChildren().add(list);

        Scene scene = new Scene(group, 520, 440);
        stage.setTitle("PeselVerifer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}