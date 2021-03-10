public class IzracunProsjeka implements  Observer {

    public Subject s;

    public IzracunProsjeka(Subject sub) {
        this.s = sub;
        this.s.attach(this);
    }

    @Override
    public void update() {
        double prosjek;
        int zbroj = 0;
        for (int i:s.getListOfNumbs()) {
            zbroj+=i;
        }
        prosjek = zbroj/s.getListOfNumbs().size();
        System.out.println("Prosjek unesenih brojeva: " + prosjek);
    }
}
