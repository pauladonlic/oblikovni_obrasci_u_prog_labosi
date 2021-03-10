import javax.swing.*;
import java.awt.*;

public class MyCanvas extends JComponent {

    private DocumentModel documentModel;
    private GUI host;

    public MyCanvas(GUI window) {
        this.documentModel = window.getDocumentModel();
        this.host = window;
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Renderer r = new G2DRenderImpl(g2d);
        //za svaki objekt o modela:
        //System.out.println(this.documentModel.list().size());
        for (GraphicalObject o : this.documentModel.list()) {
            System.out.println(o.getHotPoint(0).getX());
            o.render(r);
            // Poziva se nakon što je platno nacrtalo grafički objekt predan kao argument
            this.host.getCurrentState().afterDraw(r, o);
        }
        // Poziva se nakon što je platno nacrtalo čitav crtež
        this.host.getCurrentState().afterDraw(r);
        requestFocusInWindow();
    }

}
