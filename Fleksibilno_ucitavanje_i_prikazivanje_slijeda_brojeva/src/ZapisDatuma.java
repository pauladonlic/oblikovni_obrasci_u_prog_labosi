import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ZapisDatuma implements Observer {

    public Path path;
    public Subject s;
    public Scanner sc;

    public ZapisDatuma(Subject sub, Path p) throws IOException {
        this.s = sub;
        this.path = p;
        this.sc = new Scanner(path);
        this.s.attach(this);
    }

    public void update() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date) + "  ---->  dodan broj: " + s.getListOfNumbs().get(s.getListOfNumbs().size()-1)); //2016/11/16 12:08:43

        String datE = dateFormat.format(date).toString() + "=> " + s.getListOfNumbs().toString();
        byte[] strToBytes = datE.getBytes();
        try {
            Files.write(this.path, strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
