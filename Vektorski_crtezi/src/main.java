import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
        List objects = new ArrayList<>();
        Point p1 = new Point(200, 200);
        Point p2 = new Point(350, 350);

        Point p3 = new Point(100, 100);
        Point p4 = new Point(200, 200);

        objects.add(new LineSegment(p1, p2));
        objects.add(new Oval(p3, p4));

        GUI gui = new GUI(objects);
        gui.setVisible(true);
    }

}
