import java.awt.*;

public class G2DRenderImpl implements Renderer {
    private Graphics2D g2d;

    public G2DRenderImpl(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void drawLine(Point s, Point e) {
        // Postavi boju na plavu
        g2d.setColor(Color.BLUE);
        // Nacrtaj linijski segment od S do E
        g2d.drawLine(s.getX(), s.getY(), e.getX(), e.getY());
        // (sve to uporabom g2d dobivenog u konstruktoru)
    }

    @Override
    public void fillPolygon(Point[] points) {
        // Postavi boju na plavu
        g2d.setColor(Color.RED);
        // Popuni poligon definiran danim točkama
        int[] xS = new int[points.length];
        int[] yS = new int[points.length];

        int i = 0;
        while (i < points.length) {
            xS[i] = points[i].getX();
            yS[i] = points[i].getY();
            i++;
        }
        g2d.fillPolygon(xS, yS, points.length);
        // Postavi boju na crvenu
        g2d.setColor(Color.RED);
        // Nacrtaj rub poligona definiranog danim točkama
        g2d.drawPolygon(xS, yS, points.length);
        // (sve to uporabom g2d dobivenog u konstruktoru)
    }
}
