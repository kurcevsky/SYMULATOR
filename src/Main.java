import java.util.ArrayList;

public class Main {
    static int ilosc_statkow = 20;
    static int ilosc_miejsc = 2;
    static Przystan lotnisko;

    public Main() {
    }

    public static void main(String[] args) {
        String[] nazwy_statkow={"Baltyk","Batory","Heweliusz","Iskra","Kania","Jastrzab","Ustka","Warszawa","Szczecin","Szybki","Zbyszko","Nurek","Dzik","Bizon","Kurp","Sprawny","Albatros","Hetman","Orlik","Wilk"};
        System.out.println(nazwy_statkow.length);
        lotnisko = new Przystan(ilosc_miejsc, ilosc_statkow);
        for (int i = 0; i < ilosc_statkow; i++)
            new Statek(nazwy_statkow[i], i, 2000, 30, lotnisko).start();
    }
}