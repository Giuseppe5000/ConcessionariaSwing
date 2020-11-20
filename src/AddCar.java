import javax.swing.*;
import java.util.Vector;

public class AddCar
{
        public static void Add(JFrame f, Vector<Automobile> v)
        {
                JTextField marca1 = new JTextField();
                JTextField modello1 = new JTextField();
                JTextField cilindrata1 = new JTextField();
                JTextField potenza1 = new JTextField();
                JTextField euro1 = new JTextField();
                JTextField posti1 = new JTextField();
                JTextField porte1 = new JTextField();
                Object[] autoObj = {
                        "Marca:", marca1,
                        "Modello:", modello1,
                        "Cilindrata: ", cilindrata1,
                        "Potenza: ", potenza1,
                        "Euro: ", euro1,
                        "Posti: ", posti1,
                        "Porte: ", porte1
                };
                int j = JOptionPane.showConfirmDialog(f,autoObj,"Aggiungi auto",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

                if(j == JOptionPane.OK_OPTION) {
                        Automobile a1 = new Automobile();
                        int cilindrata = Integer.parseInt(String.valueOf(cilindrata1.getText()));
                        int potenza = Integer.parseInt(String.valueOf(potenza1.getText()));
                        int euro = Integer.parseInt(String.valueOf(euro1.getText()));
                        int posti = Integer.parseInt(String.valueOf(posti1.getText()));
                        int porte = Integer.parseInt(String.valueOf(porte1.getText()));

                        a1.setMarca(marca1.getText());
                        a1.setModello(modello1.getText());
                        a1.setCilindrata(cilindrata);
                        a1.setPotenza(potenza);
                        a1.setEuro(euro);
                        a1.setPosti(posti);
                        a1.setPorte(porte);
                        a1.setTarga("##@@@##");

                        v.add(a1);
                }
        }
}
