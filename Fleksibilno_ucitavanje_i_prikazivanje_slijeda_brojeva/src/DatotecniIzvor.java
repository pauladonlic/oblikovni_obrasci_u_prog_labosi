import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class DatotecniIzvor implements Izvor {

    public Scanner sc;
    public Path path;

    public DatotecniIzvor(Path p) throws IOException {
        this.path = p;
        this.sc = new Scanner(path);
    }

    @Override
    public int ucitajBroj() {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            return -1;
        }
    }

}
