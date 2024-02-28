package com.example.peselverifier;

public class Pesel {

    private static final int[] tabPesel = new int[11];
    private final String enteredPesel;

    public Pesel(String enteredPesel) {
        this.enteredPesel = enteredPesel;
    }

    private void strToIntPesel() {
        for (int i = 0; i < enteredPesel.length(); i++) {
            char znak = enteredPesel.charAt(i);                      //tworzymy z każdego znaku stringa 1 char/znak
            int cyfra = Character.getNumericValue(znak);         // w/w char/znak zamieniamy na int
            tabPesel[i] = cyfra;                                 // wygenerowany int przypisujemy do tablicy. pętla przechodzi całego stringa dając całą tablice
        }
    }

    public boolean verByPattern(){ //weryfikacja nr PESEL za pomocą wzoru dostępnego na https://www.gov.pl/web/gov/czym-jest-numer-pesel
        strToIntPesel();
        int check;
        int lastCheck;
        check = tabPesel[0] + 3*tabPesel[1] + 7*tabPesel[2] + 9*tabPesel[3] + tabPesel[4] + 3*tabPesel[5] + 7*tabPesel[6] + 9*tabPesel[7] + tabPesel[8] + 3*tabPesel[9];
        lastCheck = check % 10;
        // weryfikacja cyfry kontrolnej. ((tabPesel[4] * 10 + tabPesel[5]) < 32) - dodatkowo sprawdza czy podany pesel ma miesiąc urodzenia < 13 oraz dzień urodzenia < 32. Gdyby nie ten warunek pesel 48954584584 byłby poprawny, choć w rzeczyistości nie jest.
        //niepoprawny
        return (lastCheck - 10) * (-1) == tabPesel[10] && (tabPesel[4] * 10 + tabPesel[5]) < 32; //pesel poprawny
    }//KONIEC METODY verification Pesel
}
