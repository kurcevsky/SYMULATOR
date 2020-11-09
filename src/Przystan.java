public class Przystan {
    static int PRZYSTAN=1;
    static int START=2;
    static int REJS=3;
    static int KONIEC_REJSU=4;
    static int KATASTROFA=5;
    static int DODAWANIE=6;
    int ilosc_miejsc;
    int ilosc_zajetych;
    int ilosc_statkow;
    Przystan(int ilosc_miejsc,int ilosc_statkow){
        this.ilosc_miejsc=ilosc_miejsc;
        this.ilosc_statkow=ilosc_statkow;
        this.ilosc_zajetych=0;
    }
    synchronized int start(int numer){
        ilosc_zajetych--;
        System.out.println("Statek "+numer+" dostal pozwolenie na wyplyniecie");
        return START;
    }
    synchronized int cumowanie(){
        try{
            Thread.sleep(30);
        }
        catch(Exception ie){ System.out.println("Exception"+ie);}
        if(ilosc_zajetych<ilosc_miejsc){
            ilosc_zajetych++;
            System.out.println("Pozwolenie na cumowanie, ilosc statkow w porcie "+ilosc_zajetych);
            return PRZYSTAN;
        }
        else
        {return KONIEC_REJSU;}
    }
    synchronized void zmniejsz(){
        ilosc_statkow--;
        System.out.println("Statek nie mial paliwa, jest niedostepny");
        if(ilosc_statkow==ilosc_miejsc) System.out.println("Ilosc statkow jaka sama jak miejsc");
    }
}