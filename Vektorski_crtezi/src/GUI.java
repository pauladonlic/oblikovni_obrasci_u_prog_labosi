import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GUI extends JFrame {

    private DocumentModel documentModel;
    //  platno za crtanje
    private MyCanvas canvas;
    private State currentState;

    public State getCurrentState() {
        return currentState;
    }
    public DocumentModel getDocumentModel() {
        return documentModel;
    }

    public GUI (List<GraphicalObject> objects) {
        this.documentModel = new DocumentModel();
        this.canvas = new MyCanvas(this);
        this.currentState = new IdleState();

        for (GraphicalObject each : objects) {
            this.documentModel.addGraphicalObject(each);
        }

        this.addToolbar(objects);
        this.setListeners();
        this.add(canvas, BorderLayout.CENTER);

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setListeners() {

        this.documentModel.addDocumentModelListener(new DocumentModelListener() {
            @Override
            public void documentChange() {
                canvas.repaint();
            }
        });
        this.canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
                    currentState.onLeaving();
                    currentState = new IdleState();
                } else {
                    // poziva se kad program registrira da je korisnik pritisnuo tipku na tipkovnici
                    currentState.keyPressed(e.getKeyCode());
                }
            }
        });
        this.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentState.mouseDown(new Point(e.getX(), e.getY()), e.isShiftDown(), e.isControlDown());
            }
        });
    }

    private void addToolbar(List<GraphicalObject> objects){
        JToolBar toolBar = new JToolBar();
        JButton button;

        for (GraphicalObject obj : objects) {
            button = new JButton(obj.getShapeName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    //System.out.println("E stisnula si " + obj.getShapeName());
                    currentState.onLeaving();
                    currentState = new AddShapeState(documentModel, obj);
                }
            });
            toolBar.add(button);
        }
        this.add(toolBar, BorderLayout.NORTH);
    }
}
