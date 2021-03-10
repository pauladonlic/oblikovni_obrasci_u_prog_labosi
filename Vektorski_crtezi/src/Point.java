public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Point translate(Point dp) {
        // vraća NOVU točku translatiranu za argument tj. THIS+DP...
        int newX = this.x + dp.getX();
        int newY = this.y + dp.getY();
        return new Point(newX, newY);
    }

    public Point difference(Point p) {
        // vraća NOVU točku koja predstavlja razliku THIS-P...
        int newX = this.x - p.getX();
        int newY = this.y - p.getY();
        return new Point(newX, newY);
    }

}
