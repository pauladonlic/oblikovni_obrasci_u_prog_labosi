import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClass {
    public static void main (String[] args) throws IOException {
        Path path1 = Paths.get(".\\zad5data.txt");
        Path path2 = Paths.get(".\\zapisnik.txt");

        DatotecniIzvor datotecni = new DatotecniIzvor(path1);
        TipkovnickiIzvor tipkovnicki = new TipkovnickiIzvor();

        //SlijedBrojeva slijed = new SlijedBrojeva(datotecni);
        SlijedBrojeva slijed = new SlijedBrojeva(tipkovnicki);

        IzracunProsjeka rp = new IzracunProsjeka(slijed);
        ZbrojElemenata z = new ZbrojElemenata(slijed);
        IzracunMedijana tm = new IzracunMedijana(slijed);
        ZapisDatuma dz = new ZapisDatuma(slijed, path2);

        slijed.kreni();
    }
}
