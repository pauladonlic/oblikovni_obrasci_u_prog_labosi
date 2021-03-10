import java.lang.Math;
import java.awt.geom.Line2D;

public class GeometryUtil {

    public static double distanceFromPoint(Point point1, Point point2) {
        // izračunaj euklidsku udaljenost između dvije točke ...
        double distance = java.lang.Math.sqrt(java.lang.Math.pow((point1.getX()+point2.getX()), 2) + java.lang.Math.pow((point1.getY()+point2.getY()), 2));
        return distance;
    }

    public static double distanceFromLineSegment(Point s, Point e, Point p) {
        // Izračunaj koliko je točka P udaljena od linijskog segmenta određenog
        // početnom točkom S i završnom točkom E. Uočite: ako je točka P iznad/ispod
        // tog segmenta, ova udaljenost je udaljenost okomice spuštene iz P na S-E.
        // Ako je točka P "prije" točke S ili "iza" točke E, udaljenost odgovara
        // udaljenosti od P do početne/konačne točke segmenta.
        return Line2D.ptSegDist(s.getX(), s.getY(), e.getX(), e.getY(), p.getX(), p.getY());
    }

}
