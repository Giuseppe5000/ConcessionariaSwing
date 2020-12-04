/*
 * Funzione che cerca una automobile.
 * Viene richiamata in Frame*/
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SearchCar
{
        public static void Search(JFrame f,Vector<Automobile> v,Vector<Automobile> aVendute)
        {
                UIManager.put("Panel.background", Color.DARK_GRAY);
                JTextField marca1 = new JTextField();
                JTextField modello1 = new JTextField();
                JTextField cilindrata1 = new JTextField();
                JTextField potenza1 = new JTextField();
                JCheckBox check = new JCheckBox();
                check.setBackground(Color.DARK_GRAY);
                Object[] autoObj = {
                        "Marca:", marca1,
                        "Modello:", modello1,
                        "Cilindrata: ", cilindrata1,
                        "Potenza: ", potenza1,
                        "Vendute: ", check
                };
                int j = JOptionPane.showConfirmDialog(f,autoObj,"Cerca auto",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

                if (j == JOptionPane.OK_OPTION){
                        if(check.isSelected()){
                                v = aVendute;
                        }

                        String marca = marca1.getText();
                        String modello = modello1.getText();
                        String cilindrata = cilindrata1.getText();
                        String potenza = potenza1.getText();

                        int cilindrataInt = 0;
                        int potenzaInt = 0;

                        try {
                                cilindrataInt = cilindrata.equals("") ? 0 : Integer.parseInt(cilindrata);
                                potenzaInt = potenza.equals("") ? 0 : Integer.parseInt(potenza);
                        } catch (Exception NumberFormatException){
                                JOptionPane.showMessageDialog(f,"Devi inserire un numero!");
                        }

                        Automobile auto;
                        Vector<Integer> count = new Vector<>();
                        for(int i=0;i<v.size();i++){
                                auto = v.get(i);

                                if( ((marca.equals(auto.getMarca()) ) || (marca.equals("")) )
                                        &&
                                        ((modello.equals(auto.getModello()) ) || (modello.equals("")) )
                                        &&
                                        ((cilindrataInt == auto.getCilindrata()) || (cilindrata.equals("")) )
                                        &&
                                        ((potenzaInt == auto.getPotenza() ) || (potenza.equals("")) )){

                                        count.add(i+1);
                                }
                        }
                        JOptionPane.showMessageDialog(f,"Ho trovato " + count.size() + " auto nei numeri di indice " + count);
                }

        }
}
