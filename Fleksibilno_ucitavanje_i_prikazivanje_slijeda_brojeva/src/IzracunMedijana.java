import java.util.ArrayList;
import java.util.Collections;

public class IzracunMedijana implements Observer {

    public Subject s;

    public IzracunMedijana(Subject sub) {
        this.s = sub;
        this.s.attach(this);
    }

    @Override
    public void update() {
        ArrayList<Integer> numbers = s.getListOfNumbs();
        Collections.sort(numbers);

        System.out.println("Izracun medijana: " + numbers.get(numbers.size()/2));
    }
}
