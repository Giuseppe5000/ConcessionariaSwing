import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Init
{
        public static void InitCar(Vector<Automobile> v)
        {
                JFileChooser filec = new JFileChooser();
                filec.setFileFilter(new FileNameExtensionFilter("CSV files","csv"));
                filec.setCurrentDirectory(new File("."));
                File elenco = null;
                int returnVal = filec.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION)
                        elenco = filec.getSelectedFile();


                Vector<String> arrayauto = new Vector<>();
                FileInputStream autof;
                Scanner in;
                int i=0;

                try {
                        assert elenco != null;
                        autof = new FileInputStream(elenco.getAbsoluteFile());
                        in = new Scanner(autof);

                        while(in.hasNextLine()) {
                                Automobile a = new Automobile();
                                arrayauto.add(in.nextLine());

                                String[] a2;
                                a2 = arrayauto.elementAt(i).split(",");


                                a.setMarca(a2[0]);
                                a.setModello(a2[1]);
                                a.setCilindrata(Integer.parseInt(a2[2]));
                                a.setPotenza(Integer.parseInt(a2[3]));
                                a.setEuro(Integer.parseInt(a2[4]));
                                a.setPosti(Integer.parseInt(a2[5]));
                                a.setPorte(Integer.parseInt(a2[6]));
                                a.setTarga("##@@@##");

                                v.add(a);

                                i++;
                        }
                } catch (Exception e) {
                        System.out.println("File non trovato");
                        System.exit(1);
                }
        }
}
