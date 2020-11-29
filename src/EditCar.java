import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class EditCar
{
        public static void Edit(JFrame f,Vector<Automobile> v,JList lista)
        {

                UIManager.put("Panel.background", Color.DARK_GRAY);
                Automobile a = v.elementAt(lista.getSelectedIndex());
                ImageIcon i = new ImageIcon("assets/auto.jpg");

                JTextField marca1 = new JTextField(); marca1.setText(a.getMarca());
                JTextField modello1 = new JTextField(); modello1.setText(a.getModello());
                JTextField cilindrata1 = new JTextField(); cilindrata1.setText(String.valueOf(a.getCilindrata()));
                JTextField potenza1 = new JTextField(); potenza1.setText(String.valueOf(a.getPotenza()));
                JTextField euro1 = new JTextField(); euro1.setText(String.valueOf(a.getEuro()));
                JTextField posti1 = new JTextField(); posti1.setText(String.valueOf(a.getPosti()));
                JTextField porte1 = new JTextField(); porte1.setText(String.valueOf(a.getPorte()));
                Object[] autoObj = {
                        "Marca:", marca1,
                        "Modello:", modello1,
                        "Cilindrata: ", cilindrata1,
                        "Potenza: ", potenza1,
                        "Euro: ", euro1,
                        "Posti: ", posti1,
                        "Porte: ", porte1
                };
                int j = JOptionPane.showConfirmDialog(f,autoObj,"Info",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,i);

                if(j == JOptionPane.OK_OPTION){
                        v.elementAt(lista.getSelectedIndex()).setMarca(marca1.getText());
                        v.elementAt(lista.getSelectedIndex()).setModello(modello1.getText());
                        v.elementAt(lista.getSelectedIndex()).setCilindrata(Integer.parseInt(cilindrata1.getText()));
                        v.elementAt(lista.getSelectedIndex()).setPotenza(Integer.parseInt(potenza1.getText()));
                        v.elementAt(lista.getSelectedIndex()).setEuro(Integer.parseInt(euro1.getText()));
                        v.elementAt(lista.getSelectedIndex()).setPosti(Integer.parseInt(posti1.getText()));
                        v.elementAt(lista.getSelectedIndex()).setPorte(Integer.parseInt(porte1.getText()));
                }
        }
}
