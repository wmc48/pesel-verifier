package com.example.peselverifier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private File fileToVer = new File("do_weryfikacji.txt");
    private File fileToSave = new File("po_weryfikacji.txt");
    List<String> peselFromFile = new ArrayList<>();// do weryfikacji pliku,
    private String strPesel;

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

    void saveFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("po_weryfikacji.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pozycje wypisane w pliku: " + peselFromFile.size());

        for (int i = 0; i < peselFromFile.size(); i++) { // pętla pozwala zweryfikować każdą linię pliku

            // a następnie zapisać wynik weryfikacji jako każdy nowy wiersz do pliku
            strPesel = peselFromFile.get(i).trim(); // strPesel jako niezbędny argument do metody verificationPesel. w pętli przypisuje do siebie element listy a w następnej itereacji kolejny aż do końca listy
            Pesel pesel = new Pesel(strPesel);
            if (strPesel.length() > 11) {
                writer.println(String.format("%-25s| błędny pesel, powód: za długi", strPesel));
            } else if (strPesel.length() < 11) {
                writer.println(String.format("%-25s| błędny pesel, powód: za krótki", strPesel));
            } else if (pesel.verByPattern()){
                writer.println(String.format("%-25s| PESEL poprawny", strPesel));
                //peselFromFile.set(i, pesel.verByPattern()); // przypisanie do listy nowo utworzonych, finalnych stringów
            } else {
                writer.println(String.format("%-25s| błędny pesel", strPesel));
            }
        }
        writer.close(); // zamyka zapis pliku. niezbędne do udanego zapisu
    }

    public File getFileToSave() {
        return fileToSave;
    }
}
