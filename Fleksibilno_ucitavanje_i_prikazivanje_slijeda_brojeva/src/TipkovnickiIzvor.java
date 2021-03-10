import java.util.ArrayList;
import java.util.Scanner;

public class TipkovnickiIzvor implements Izvor {

    public Scanner sc;

    public TipkovnickiIzvor() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public int ucitajBroj() {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            return -1;
        }
    }

}
