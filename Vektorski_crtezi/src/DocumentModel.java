import java.util.*;

public class DocumentModel {

    public final static double SELECTION_PROXIMITY = 10;

    // Kolekcija svih grafičkih objekata:
    private List<GraphicalObject> objects;
    // Read-Only proxy oko kolekcije grafičkih objekata:
    private List<GraphicalObject> roObjects;
    // Kolekcija prijavljenih promatrača:
    private List<DocumentModelListener> listeners;
    // Kolekcija selektiranih objekata:
    private List<GraphicalObject> selectedObjects;
    // Read-Only proxy oko kolekcije selektiranih objekata:
    private List<GraphicalObject> roSelectedObjects;

    // Konstruktor...
    public DocumentModel() {
        this.objects = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.selectedObjects = new ArrayList<>();
        this.roObjects = Collections.unmodifiableList(objects);
        this.roSelectedObjects = Collections.unmodifiableList(selectedObjects);
    }

    // Promatrač koji će biti registriran nad svim objektima crteža...
    private final GraphicalObjectListener goListener = new GraphicalObjectListener() {
        @Override
        public void graphicalObjectChanged(GraphicalObject go) {

            notifyListeners();
        }

        @Override
        public void graphicalObjectSelectionChanged(GraphicalObject go) {
            // ...
            notifyListeners();
        }
    };
    // Brisanje svih objekata iz modela (pazite da se sve potrebno odregistrira)
    // i potom obavijeste svi promatrači modela
    public void clear() {
        for (GraphicalObject obj : objects) {
            obj.removeGraphicalObjectListener(goListener);
        }
        selectedObjects.clear();
        objects.clear();
        notifyListeners();
    }

    // Dodavanje objekta u dokument (pazite je li već selektiran; registrirajte model kao promatrača)
    public void addGraphicalObject(GraphicalObject obj) {
        objects.add(obj);
        obj.addGraphicalObjectListener(goListener);
        if (obj.isSelected()) {
            this.selectedObjects.add(obj);
        }
        notifyListeners();
    }

    // Uklanjanje objekta iz dokumenta (pazite je li već selektiran; odregistrirajte model kao promatrača)
    public void removeGraphicalObject(GraphicalObject obj) {
        objects.remove(obj);
        obj.removeGraphicalObjectListener(goListener);
        obj.setSelected(false);
        notifyListeners();
    }

    // Vrati nepromjenjivu listu postojećih objekata (izmjene smiju ići samo kroz metode modela)
    public List<GraphicalObject> list() {
        return this.roObjects;
    }

    // Prijava...
    public void addDocumentModelListener(DocumentModelListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    // Odjava...
    public void removeDocumentModelListener(DocumentModelListener l) {
        listeners.remove(l);
    }

    // Obavještavanje...
    public void notifyListeners() {
        for (DocumentModelListener each : listeners) {
            each.documentChange();
        }
    }

    // Vrati nepromjenjivu listu selektiranih objekata
    public List getSelectedObjects() {
        return this.selectedObjects;
    }

    // Pomakni predani objekt u listi objekata na jedno mjesto kasnije...
    // Time će se on iscrtati kasnije (pa će time možda veći dio biti vidljiv)
    public void increaseZ(GraphicalObject go) {
        int i = objects.indexOf(go);

        if (i < objects.size()-1 && i != -1) {
            //  na moj mjesto stavi onog poslije mene
            objects.set(i, objects.get(i+1));
            // a mene na jedno mjesto kasnije
            objects.set(i+1, go);
        }
        notifyListeners();
    }

    // Pomakni predani objekt u listi objekata na jedno mjesto ranije...
    public void decreaseZ(GraphicalObject go) {
        int i = objects.indexOf(go);

        if (i > 0) {
            //  na moj mjesto stavi onog prije mene
            objects.set(i, objects.get(i-1));
            // a mene na jedno mjesto prije
            objects.set(i-1, go);
        }
        notifyListeners();
    }

    // Pronađi postoji li u modelu neki objekt koji klik na točku koja je
    // predana kao argument selektira i vrati ga ili vrati null. Točka selektira
    // objekt kojemu je najbliža uz uvjet da ta udaljenost nije veća od
    // SELECTION_PROXIMITY. Status selektiranosti objekta ova metoda NE dira.
    public GraphicalObject findSelectedGraphicalObject(Point mousePoint) {
        Map<Double, GraphicalObject> selections = new HashMap<>();
        double distance;

        //  izracunaj za svaku tocku udaljenost od klika misa
        for (GraphicalObject obj : objects) {
            distance = obj.selectionDistance(mousePoint);
            if (this.SELECTION_PROXIMITY >= distance) {
                selections.put(distance, obj);
            }
        }

        double minDistance = SELECTION_PROXIMITY;
        if(selections.isEmpty()) {
            for (Map.Entry<Double, GraphicalObject> e : selections.entrySet()) {
                if (minDistance >= e.getKey()) {
                    minDistance = e.getKey();
                }
            }
            return selections.get(minDistance);
        }
        return null;
    }

    // Pronađi da li u predanom objektu predana točka miša selektira neki hot-point.
    // Točka miša selektira onaj hot-point objekta kojemu je najbliža uz uvjet da ta
    // udaljenost nije veća od SELECTION_PROXIMITY. Vraća se indeks hot-pointa
    // kojeg bi predana točka selektirala ili -1 ako takve nema. Status selekcije
    // se pri tome NE dira.
    public int findSelectedHotPoint(GraphicalObject object, Point mousePoint) {
        for (int p = 0; p < object.getNumberOfHotPoints(); p++) {
            if (this.SELECTION_PROXIMITY >= object.getHotPointDistance(p, mousePoint)) {
                return p;
            }
        }
        return -1;
    }

}
