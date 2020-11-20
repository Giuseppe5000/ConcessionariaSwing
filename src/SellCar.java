import javax.swing.*;
import java.util.Random;
import java.util.Vector;

public class SellCar
{
        public static void Sell(JList lista, Vector<Automobile> v,Vector<Automobile> aVendute)
        {
                int index = lista.getSelectedIndex();
                Automobile a = v.get(index);

                v.remove(index);

                String targa;
                StringBuilder C = new StringBuilder();
                Random r = new Random();

                char c = (char) ('a' + r.nextInt(26));
                char c2 = (char) ('a' + r.nextInt(26));

                String A = (c +""+ c2).toUpperCase();
                String B = (c2 +""+c).toUpperCase();

                for(int i=0;i<3;i++) {
                        C.append(r.nextInt(9));
                }
                targa = A + C + B;

                a.setTarga(targa);
                aVendute.add(a);
        }
}
