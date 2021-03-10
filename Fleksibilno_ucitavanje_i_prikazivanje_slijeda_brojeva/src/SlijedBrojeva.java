import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SlijedBrojeva implements Subject{

    public Izvor izvor;
    public ArrayList<Integer> listaBrojeva;
    public ArrayList<Observer> observers;

    public SlijedBrojeva(Izvor source) {
        this.izvor = source;
        this.listaBrojeva = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void dettach(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs: this.observers) {
            obs.update();
        }
    }

    @Override
    public ArrayList<Integer> getListOfNumbs() {
        return this.listaBrojeva;
    }

    public void kreni() {
        int num = 0;

        System.out.println("Kraj unosa oznacava -1; unesite brojeve/zeljenu datoteku: ");

        while (num != -1) {
            num = izvor.ucitajBroj();
            if (num == -1) { break; }
            System.out.println("Ucitan broj ------------------------------> " + num);

            listaBrojeva.add(num);
            notifyObservers();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch(InterruptedException ex) {
                System.out.println("Pricekajte istek vremena!");
            }

        }
    }

}
