import java.util.Random;
public class Statek extends Thread {
    //definicja stanu samolotu
    static int PRZYSTAN=1;
    static int START=2;
    static int REJS=3;
    static int KONIEC_REJSU=4;
    static int KATASTROFA=5;
    static int DODAWANIE=6;
    static int TANKUJ=50;
    static int REZERWA=20;
    //zmienne pomocnicze
    String nazwa;
    int numer;
    int paliwo;
    int stan;
    int ilosc_pasazerow;
    Przystan p;
    Random rand;
    public Statek(String nazwa, int numer, int paliwo, int ilosc_pasazerow, Przystan p){
        this.nazwa=nazwa;
        this.numer=numer;
        this.paliwo=paliwo;
        this.stan=REJS;
        this.ilosc_pasazerow=ilosc_pasazerow;
        this.p=p;
        rand=new Random();
    }
    public void run(){
        while(true){
            if(stan==PRZYSTAN){
                if(rand.nextInt(2)==1){
                    stan=START;
                    paliwo=TANKUJ;
                    ilosc_pasazerow+=rand.nextInt(50);
                    System.out.println("Statek "+nazwa+"["+numer+"] chce wyplynac w rejs");
                            stan=p.start(numer);
                }
                else{
                    System.out.println("Postoje sobie jeszcze troche");
                }
            }
            else if(stan==START){
                if(ilosc_pasazerow>70)
                {
                    System.out.println("Statek "+nazwa+" ma za duzo pasazerow! "+"["+ilosc_pasazerow+"]");
                    stan=PRZYSTAN;
                }
                System.out.println("Wyruszylem, statek "+nazwa+"["+numer+"]");
                stan=REJS;
            }
            else if(stan==REJS){
                paliwo-=rand.nextInt(15);
                System.out.println("Statek "+nazwa+"["+numer+"] w drodze!");
                if(paliwo<=REZERWA){
                    stan=KONIEC_REJSU;
                }
                else if(paliwo<=0)
                {
                    System.out.println("BRAK PALIWA, KATASTROFA");
                    stan=KATASTROFA;
                }
                else try{
                        Thread.sleep(rand.nextInt(100));
                }
                catch (Exception e){System.out.println("Error "+e);
                }
            }
            else if(stan==KONIEC_REJSU){
                System.out.println("Prosze o pozowolenie na cumowanie statku "+numer+" ilosc paliwa "+paliwo);
                stan=p.cumowanie();
                if(stan==KONIEC_REJSU){
                    paliwo-=rand.nextInt(30);
                    System.out.println("REZERWA "+paliwo);
                    if(paliwo<=0) stan=KATASTROFA;
                }
            }
            else if(stan==KATASTROFA){
                System.out.println("Statek "+nazwa+"["+numer+"] nie ma paliwa, potrzebna pomoc");
                p.zmniejsz();
            }
        }
    }
}