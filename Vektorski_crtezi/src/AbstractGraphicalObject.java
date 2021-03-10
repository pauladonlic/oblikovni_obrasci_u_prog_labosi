import java.util.ArrayList;
import java.util.List;

public class AbstractGraphicalObject implements GraphicalObject {

    public Point[] hotPoints;
    private boolean[] hotPointSelected;
    private boolean selected;
    private List<GraphicalObjectListener> listeners;

    public AbstractGraphicalObject(Point[] hotPoints) {
        this.hotPoints = hotPoints;
        this.hotPointSelected = new boolean[hotPoints.length];
        this.listeners = new ArrayList<>();
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int getNumberOfHotPoints() {
        return hotPoints.length;
    }

    @Override
    public Point getHotPoint(int index) {
        if (index < hotPoints.length) {
            return this.hotPoints[index];
        } else {
            return null;
        }
    }

    @Override
    public void setHotPoint(int index, Point point) {
        if (index < hotPoints.length) {
            this.hotPoints[index] = point;
        }
    }

    @Override
    public boolean isHotPointSelected(int index) {
        if (index < hotPointSelected.length) {
            return this.hotPointSelected[index];
        } else {
            return false;
        }
    }

    @Override
    public void setHotPointSelected(int index, boolean selected) {
        if (index < hotPointSelected.length) {
            this.hotPointSelected[index] = selected;
        }
    }

    @Override
    public double getHotPointDistance(int index, Point mousePoint) throws IndexOutOfBoundsException {
        if (index >= hotPoints.length) {
            throw new IndexOutOfBoundsException("Preveliki index!");
        } else {
            return GeometryUtil.distanceFromPoint(this.hotPoints[index], mousePoint);
        }
    }

    @Override
    public void translate(Point delta) {
        for (Point p : hotPoints) {
            p.x = p.getX() + delta.getX();
            p.y = p.getY() + delta.getY();
        }
        notifyListeners();
    }

    @Override
    public Rectangle getBoundingBox() {
        return null;
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        return 0;
    }

    @Override
    public String getShapeName() {
        return null;
    }

    @Override
    public GraphicalObject duplicate() {
        return null;
    }

    @Override
    public void render(Renderer r) {

    }

    @Override
    public void addGraphicalObjectListener(GraphicalObjectListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    @Override
    public void removeGraphicalObjectListener(GraphicalObjectListener l) {
        listeners.remove(l);
    }

    public void notifyListeners() {
        for (GraphicalObjectListener each : listeners) {
            each.graphicalObjectChanged(this);
        }
    }

    private  void notifySelectionListeners() {
        for (GraphicalObjectListener each : listeners) {
            each.graphicalObjectSelectionChanged(this);
        }
    }

    public Point[] getHotPoints() {
        return hotPoints;
    }
}
