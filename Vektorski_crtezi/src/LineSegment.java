import java.awt.*;

public class LineSegment extends AbstractGraphicalObject {

    public LineSegment(Point start, Point end) {
        super(new Point[]{ start, end });
    }

    public LineSegment() {
        super(new Point[]{ new Point(0,0), new Point(10,0)});
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        return GeometryUtil.distanceFromLineSegment(this.getHotPoint(0), this.getHotPoint(1), mousePoint);
    }

    @Override
    public Rectangle getBoundingBox() {
        Point p1 = this.getHotPoint(0);
        Point p2 = this.getHotPoint(1);

        //  najlijeviju tocku uziam kao pocetnu
        int x = p1.x < p2.x ? p1.x : p2.x;
        int y = p1.y < p2.y ? p1.y : p2.y;

        return new Rectangle(x, y, Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
    }

    @Override
    public String getShapeName() {
        return "Linija";
    }

    @Override
    public GraphicalObject duplicate() {
        return new LineSegment(this.getHotPoint(0), this.getHotPoint(1));
    }

    @Override
    public void render(Renderer r) {
        r.drawLine(this.getHotPoint(1), this. getHotPoint(0));
    }

    @Override
    public void translate(Point delta) {
        Point centar = new Point((this.hotPoints[0].getX()+this.hotPoints[1].getX())/2, (this.hotPoints[0].getY()+this.hotPoints[1].getY())/2);

        int razlikaXstart = centar.getX() - this.hotPoints[0].getX();
        int razlikaYstart = this.hotPoints[0].getY() - centar.getY();
        //  delta je novi centar
        this.hotPoints[0].x = delta.getX() - razlikaXstart;
        this.hotPoints[0].y = delta.getY() + razlikaYstart;

        int razlikaXend = this.hotPoints[1].getX() - centar.getX();
        int razlikaYend = centar.getY() - this.hotPoints[1].getY();
        //  delta je novi centar
        this.hotPoints[1].x = delta.getX() + razlikaXstart;
        this.hotPoints[1].y = delta.getY() - razlikaYstart;

        this.notifyListeners();
    }
}
