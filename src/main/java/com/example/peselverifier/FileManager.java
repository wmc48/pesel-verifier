package com.example.peselverifier;

import javafx.scene.control.ListView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private final File fileToVer = new File("do_weryfikacji.txt");
    private final File fileToSave = new File("po_weryfikacji.txt");
    List<String> peselFromFile = new ArrayList<>();// do weryfikacji pliku,

    void checkFile() {

        if (!fileToVer.exists() || !fileToSave.exists()) {            // jeśli jeden lub drugiplik nie itsnieje - tworzy go
            try {
                FileWriter writer = new FileWriter("do_weryfikacji.txt");
                System.out.println("Plik do_weryfikacji utworzony.");
                writer = new FileWriter("po_weryfikacji.txt");
                System.out.println("Plik po_weryfikacji utworzony.");

            } catch (IOException e) {
                System.out.println("Błąd przy tworzeniu pliku DoWeryfikacji.txt");
            }
        }
    }

    void readFile() {
        try {
            Scanner fileScanner = new Scanner(fileToVer); // odczytuje wskazany plik(file)
            int i = 0;
            while (fileScanner.hasNext()) { // sprawdza kazdą linie pliku, jeśli nie ma następnej lini - koniec pętli
                peselFromFile.add(fileScanner.nextLine()); // przypisuje do listy kolejną linie. = kolejny index listy = kolejna linia pliku
                i++;
            }
        } catch (IOException e) { //komunikat do niespodziewanego błędu
            System.out.println("Błąd w odczycie pliku do_weryfikacji.txt");
        }
    }

    void saveFile(ListView<String> listView) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("po_weryfikacji.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pozycje wypisane w pliku: " + peselFromFile.size());
        List<String> verificatedList = new ArrayList<>();//lista na wyniki weryfikacji

        for (String row : peselFromFile) { // pętla pozwala zweryfikować każdą linię pliku
            // a następnie zapisać wynik weryfikacji jako każdy nowy wiersz do pliku, oraz dodać wynik o listy weryfikacji aby wyświetlić to w listView
            String strPesel = row.trim(); // strPesel jako niezbędny argument do metody verificationPesel. w pętli przypisuje do siebie element listy a w następnej itereacji kolejny aż do końca listy
            Pesel pesel = new Pesel(strPesel);
            Verification verification = new Verification();
            String result;
            if (strPesel.length() > 11) {
                result = String.format("%-25s| błędny pesel, powód: za długi", strPesel);
                verificatedList.add(result);
                writer.println(result);
            } else if (strPesel.length() < 11) {
                result = String.format("%-25s| błędny pesel, powód: za krótki", strPesel);
                verificatedList.add(result);
                writer.println(result);
            } else if (verification.verByPattern(pesel.getEnteredPesel())) {
                result = String.format("%-25s| PESEL poprawny", strPesel);
                verificatedList.add(result);
                writer.println(result);
                //peselFromFile.set(i, pesel.verByPattern()); // przypisanie do listy nowo utworzonych, finalnych stringów
            } else {
                result = String.format("%-25s| błędny pesel", strPesel);
                verificatedList.add(result);
                writer.println(result);
            }
        }

        listView.getItems().addAll(verificatedList);
        writer.close(); // zamyka zapis pliku. niezbędne do udanego zapisu
    }

    public File getFileToSave() {
        return fileToSave;
    }
}
