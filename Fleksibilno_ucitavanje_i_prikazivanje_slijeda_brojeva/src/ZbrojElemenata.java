import java.util.ArrayList;

public class ZbrojElemenata implements Observer {

    public Subject s;

    public ZbrojElemenata(Subject sub) {
        this.s = sub;
        this.s.attach(this);
    }

    @Override
    public void update() {
        ArrayList<Integer> numbers = this.s.getListOfNumbs();
        int suma = 0;
        int i;

        for (i=0; i<numbers.size(); i++){
            suma += numbers.get(i);
        }
        System.out.println("Trenutna suma ucitanih brojeva: " + suma);
    }
}
