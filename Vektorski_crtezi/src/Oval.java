public class Oval extends AbstractGraphicalObject {

    private Point[] ovalPoints;

    public Oval(Point desni, Point donji) {

        super(new Point[]{ desni, donji });
        this.ovalPoints = this.getOvalPoints(50);
    }

    public Oval() {
        //                              desni                   donji
        super(new Point[]{ new Point(10,0), new Point(0,10)});
        this.ovalPoints = this.getOvalPoints(50);
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        //  provjera nalazi li se tocka unutar ovala
        Point desni = this.getHotPoint(0);
        Point donji = this.getHotPoint(1);
        int a = desni.getX() - donji.getX();
        int b = donji.getY() - desni.getY();

        double elipseEquation = Math.pow(mousePoint.getX() - donji.getX(), 2)/Math.pow(a, 2) + Math.pow(mousePoint.getY() - desni.getY(), 2)/Math.pow(b, 2);

        if(elipseEquation <= 1) {
            //  ako je unutar
            return 0;
        }

        Point[] points = this.ovalPoints;
        //  vracam udaljenost najblize tocke na krivulji i zadane tocke
        double minPoint = GeometryUtil.distanceFromPoint(points[0], mousePoint);

        for (int i = 1; i < points.length; i++) {
            double distance = GeometryUtil.distanceFromPoint(points[i], mousePoint);
            if(distance < minPoint) {
                minPoint = distance;
            }
        }
        return minPoint;
    }

    //  random tocke na krivlji
    /*
     x = a*sin(t)
     y = b*cos(t)
     */
    private Point[] getOvalPoints(int n) {

        Point desni = this.getHotPoint(0);
        Point donji = this.getHotPoint(1);
        Point center = new Point(donji.getX(), desni.getY());
        Point[] ovalPoints = new Point[n];

        int a = desni.getX() - donji.getX();
        int b = donji.getY() - desni.getY();

        for (int i = 0; i < n; i++) {
            double t = (2*Math.PI/n) * i;
            int x = (int)(a * Math.cos(t)) + center.getX();
            int y = (int)(b * Math.sin(t)) + center.getY();
            ovalPoints[i] = new Point(x, y);
        }
        return ovalPoints;
    }

    @Override
    public Rectangle getBoundingBox() {

        Point desni = this.getHotPoint(0);
        Point donji = this.getHotPoint(1);

        int width = 2 * (desni.getX() - donji.getX());
        int height = 2 * (donji.getY() - desni.getY());
        int x = donji.getX() - (desni.getX() - donji.getX());
        int y = desni.getY() - (donji.getY() - desni.getY());

        return new Rectangle(x, y, width, height);

    }

    @Override
    public String getShapeName() {
        return "Oval";
    }

    @Override
    public GraphicalObject duplicate() {

        return new Oval(this.getHotPoint(0), this.getHotPoint(1));
    }

    @Override
    public void render(Renderer r) {
        r.fillPolygon(this.getOvalPoints(50));
    }

    @Override
    public void translate(Point delta) {
        //  delta sada postaje moje centar
        Point noviDesni = new Point(delta.getX() + (this.hotPoints[0].getX() - this.hotPoints[1].getX()), delta.getY());

        this.hotPoints[1].x = delta.getX();
        this.hotPoints[1].y = delta.getY() - (this.hotPoints[0].getY() - this.hotPoints[1].getY());

        this.hotPoints[0].x = noviDesni.getX();
        this.hotPoints[0].y = noviDesni.getY();

        this.ovalPoints = this.getOvalPoints(50);

        this.notifyListeners();
    }

}
