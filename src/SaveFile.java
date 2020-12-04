/*
 * Funzione che salva i 2 file contenenti le auto e le auto vendute.
 * Viene richiamata in Frame e in DnD*/
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class SaveFile
{
        public static void Save(Vector<Automobile> v, Vector<Automobile> aVendute)
        {

                for(int i=0; i<2; i++){
                        FileWriter f;
                        try {
                                if(i==0){
                                        f = new FileWriter("auto.csv");
                                        Write(v,f);
                                }

                                if(i==1){
                                        f = new FileWriter("autovendute.csv");
                                        Write(aVendute,f);
                                }

                        } catch (Exception e) {
                                System.out.println("Errore");
                                System.exit(1);
                        }
                }

        }

        private static void Write(Vector<Automobile> v,FileWriter f) throws IOException
        {
                for (Automobile automobile : v) {
                        f.write(automobile.getMarca() + "," + automobile.getModello() + "," + automobile.getCilindrata() + "," + automobile.getPotenza() +
                                "," + automobile.getEuro() + "," + automobile.getPosti() + "," + automobile.getPorte() + "," + automobile.getTarga() + "\n");
                        f.flush();
                }
        }
}
