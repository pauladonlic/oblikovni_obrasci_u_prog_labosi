import java.util.ArrayList;

public interface Subject {

    public void attach(Observer obs);
    public void dettach(Observer obs);
    public void notifyObservers();

    ArrayList<Integer> getListOfNumbs();

}
