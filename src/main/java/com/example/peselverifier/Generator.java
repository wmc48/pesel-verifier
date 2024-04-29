package com.example.peselverifier;

import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator extends PeselInfo {

    public String generate(LocalDate getDate) {
        int day, month, year;

        if (getDate == null) { //
            // Generowanie losowej daty
            day = (int)(Math.random() * 28 + 1);
            month = (int)(Math.random() * 12 + 1);
            year = (int)(Math.random() * 2024 + 1800);
        } else {
            day = getDate.getDayOfMonth();
            month = getDate.getMonthValue();
            year = getDate.getYear();
        }

        String peselNumber;//"yymmddxxxcc"
        int serialNumber = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        String formattedYear = dateFormat(year % 100); //formatujemy tylko ostatnie 2cyfry z całego roku
        String formattedMonth = dateFormat(month);
        String formattedDay = dateFormat(day);
        peselNumber = formattedYear + formattedMonth + formattedDay + serialNumber;
        int checkDigit = controlDigitCalculate(peselNumber, peselNumber.length());
        peselNumber += checkDigit;
        return peselNumber;

    }

    public void generatedList(int quantity, LocalDate getDate, ListView<String> listView){
        List<String> generatedList = new ArrayList<>();//lista na wyniki weryfikacji
        for (int i = 0; i < quantity; i++) {
            generatedList.add(generate(getDate));
        }
        listView.getItems().addAll(generatedList);
    }

    private String dateFormat(int date) {
        return String.format("%02d", date);

    }

}
