package com.example.peselverifier;

public class Verification {

    private int[] tabPesel = new int[11];
    String enteredPesel;

    public Verification(String enteredPesel) {
        this.enteredPesel = enteredPesel;
    }

    public String startVerification(){

        String result = "pesel zawiera 11 znaków";

        if (!isLongInt()){
            result = "dozwolone tlko cyfry";
            return result;
        }

        if (enteredPesel.length() < 11){
            result = "pesel za krótki";
            return result;

        } else if (enteredPesel.length() > 11)  {
            result = "pesel za długi";
            return result;
        }else {
            strToIntPesel();
            verByPattern(tabPesel);
            System.out.println(verByPattern(tabPesel));
            return result;

        }


    }






    public boolean isLongInt(){
        try {
            Long.parseLong(enteredPesel);
            System.out.println("Wprowadzony ciąg znaków jest liczbą zmiennoprzecinkową: " + enteredPesel);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Wprowadzony ciąg znaków nie jest liczbą zmiennoprzecinkową.");
            return false;
        }
    }







    private void strToIntPesel() {

        for (int i = 0; i < enteredPesel.length(); i++) {
            char znak = enteredPesel.charAt(i);                      //tworzymy z każdego znaku stringa 1 char/znak
            int cyfra = Character.getNumericValue(znak);         // w/w char/znak zamieniamy na int
            tabPesel[i] = cyfra;                                 // wygenerowany int przypisujemy do tablicy. pętla przechodzi całego stringa dając całą tablice
        }
    }//KONIEC METODY strToIntPesel


    public boolean verByPattern(int[] tabPesel){ //weryfikacja nr PESEL za pomocą wzoru dostępnego na https://www.gov.pl/web/gov/czym-jest-numer-pesel

        int check;
        int lastCheck;
        check = tabPesel[0] + 3*tabPesel[1] + 7*tabPesel[2] + 9*tabPesel[3] + tabPesel[4] + 3*tabPesel[5] + 7*tabPesel[6] + 9*tabPesel[7] + tabPesel[8] + 3*tabPesel[9];
        lastCheck = check % 10;

        if((lastCheck - 10) * (-1) == tabPesel[10] && (tabPesel[4] * 10 + tabPesel[5]) < 32 ){
            // weryfikacja cyfry kontrolnej. ((tabPesel[4] * 10 + tabPesel[5]) < 32) - dodatkowo sprawdza czy podany pesel ma miesiąc urodzenia < 13 oraz dzień urodzenia < 32. Gdyby nie ten warunek pesel 48954584584 byłby poprawny, choć w rzeczyistości nie jest.
            return true; //pesel poprawny
        }else {
            return false;//niepoprawny
        }
    }//KONIEC METODY verification Pesel

}
