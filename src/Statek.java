import java.util.Random;
public class Statek extends Thread {
    //definicja stanu samolotu
    static int PRZYSTAN=1;
    static int START=2;
    static int REJS=3;
    static int KONIEC_REJSU=4;
    static int KATASTROFA=5;
    static int TANKUJ=50;
    static int REZERWA=20;
    //zmienne pomocnicze
    int numer;
    int paliwo;
    int stan;
    Przystan p;
    Random rand;
    public Statek(int numer, int paliwo, Przystan p){
        this.numer=numer;
        this.paliwo=paliwo;
        this.stan=REJS;
        this.p=p;
        rand=new Random();
    }
    public void run(){
        while(true){
            if(stan==PRZYSTAN){
                if(rand.nextInt(2)==1){
                    stan=START;
                    paliwo=TANKUJ;
                    System.out.println("Statek "+numer+" chce wyplynac w rejs");
                            stan=p.start(numer);
                }
                else{
                    System.out.println("Postoje sobie jeszcze troche");
                }
            }
            else if(stan==START){
                System.out.println("Wyruszylem, statek "+numer);
                stan=REJS;
            }
            else if(stan==REJS){
                paliwo-=rand.nextInt(15);
                System.out.println("Statek "+numer+" w drodze!");
                if(paliwo<=REZERWA){
                    stan=KONIEC_REJSU;
                }
                else if(paliwo<=0)
                {
                    System.out.println("BRAK PALIWA, KATASTROFA");
                    stan=KATASTROFA;
                }
                else try{
                        Thread.sleep(rand.nextInt(30));
                }
                catch (Exception e){System.out.println("Error "+e);
                }
            }
            else if(stan==KONIEC_REJSU){
                System.out.println("Prosze o pozowolenie na cumowanie statku "+numer+" ilosc paliwa "+paliwo);
                stan=p.cumowanie();
                if(stan==KONIEC_REJSU){
                    paliwo-=rand.nextInt(10);
                    System.out.println("REZERWA "+paliwo);
                    if(paliwo<=0) stan=KATASTROFA;
                }
            }
            else if(stan==KATASTROFA){
                System.out.println("Statek "+numer+" nie ma paliwa, potrzebna pomoc");
                p.zmniejsz();
            }
        }} }