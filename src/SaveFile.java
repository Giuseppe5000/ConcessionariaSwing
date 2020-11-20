import java.io.FileWriter;
import java.util.Vector;

public class SaveFile
{
        public static void Save(Vector<Automobile> v, Vector<Automobile> aVendute)
        {
                FileWriter f;
                try {
                        f = new FileWriter("auto.csv");

                        for (Automobile automobile : v) {
                                f.write(automobile.getMarca() + "," + automobile.getModello() + "," + automobile.getCilindrata() + "," + automobile.getPotenza() +
                                        "," + automobile.getEuro() + "," + automobile.getPosti() + "," + automobile.getPorte() + "," + automobile.getTarga() + "\n");
                                f.flush();
                        }

                } catch (Exception e) {
                        System.out.println("Errore");
                        System.exit(1);
                }

                FileWriter f2;
                try {
                        f2 = new FileWriter("autovendute.csv",true);

                        for (Automobile automobile : aVendute) {
                                f2.write(automobile.getMarca() + "," + automobile.getModello() + "," + automobile.getCilindrata() + "," + automobile.getPotenza() +
                                        "," + automobile.getEuro() + "," + automobile.getPosti() + "," + automobile.getPorte() + "," + automobile.getTarga() + "\n");
                                f2.flush();
                        }

                } catch (Exception e2) {
                        System.out.println("Errore");
                        System.exit(1);
                }
        }
}
