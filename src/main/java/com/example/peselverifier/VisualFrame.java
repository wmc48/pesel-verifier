package com.example.peselverifier;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class VisualFrame extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        Button btnVerify, btnByTyping;

        //Label
        Label startInfo = new Label("Wybierz w jaki sposób chcesz zweryfikować nr PESEL:");
        startInfo.setLayoutY(10);
        startInfo.setLayoutX(100);

        Label peselVerificationInfo = new Label("Wprowadzony PESEL jest za krótki");
        peselVerificationInfo.setLayoutY(85);
        peselVerificationInfo.setLayoutX(320);
        peselVerificationInfo.setVisible(false);
        peselVerificationInfo.setTextFill(Color.GRAY);


        //Buttons
        //btnByTyping
        btnByTyping = new Button("Weryfikuj wpisując PESEL");
        btnByTyping.setLayoutX(50);
        btnByTyping.setLayoutY(50);


        //btnVerify
        btnVerify = new Button("Weryfikuj");
        btnVerify.setLayoutX(250);
        btnVerify.setLayoutY(80);
        btnVerify.setVisible(false);
        btnVerify.setDisable(false);

        //TextFiled
        TextField enteringPesel = new TextField();
        enteringPesel.setLayoutY(80);
        enteringPesel.setLayoutX(80);
        enteringPesel.setPromptText("Wprowadź numer PESEL...");
        enteringPesel.setVisible(false);


        Controller controller = new Controller(enteringPesel, btnVerify, peselVerificationInfo);

        btnByTyping.setOnAction(event -> controller.handleBtnByTypping());
        btnVerify.setOnAction(event -> controller.handleBtnVerify());


        Group group = new Group();
        group.getChildren().add(startInfo);
        group.getChildren().add(btnByTyping);
        group.getChildren().add(enteringPesel);
        group.getChildren().add(btnVerify);
        group.getChildren().add(peselVerificationInfo);

        Scene scene = new Scene(group, 520, 440);
        stage.setTitle("PeselVerifer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}