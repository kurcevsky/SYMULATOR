public class Main {
    static int ilosc_statkow=20;
    static int ilosc_miejsc=2;
    static Przystan lotnisko;
    public Main(){ }
    public static void main(String[] args) {
        lotnisko=new Przystan(ilosc_miejsc, ilosc_statkow);
        for(int i=0;i<ilosc_statkow;i++)
            new Statek(i,2000,lotnisko).start();
    }
}