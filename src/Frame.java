/*
* @author Tutino Giuseppe
* @version 2.2
*
* */

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Frame
{
        JFrame f = new JFrame();

        private DefaultListModel<String> l;
        private JScrollPane s;
        private JList lista;

        Frame()
        {
                f.setName("Concessionaria");
                f.setSize(800,400);
                f.setResizable(false);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                f.setLayout(null);


                //Init dal file di auto
                Vector<Automobile> v = new Vector<>();
                Vector<Automobile> aVendute = new Vector<>();

                //Vende auto
                JButton b1 = new JButton("<html><p style='color: blue;'>Vendi auto</p></html>");//"Vendi auto"
                b1.addActionListener(e -> {
                        int index = Integer.parseInt(JOptionPane.showInputDialog(f,"Inserire il numero dell'indice"));
                        Automobile a = v.get(index);

                        v.remove(index);

                        String targa = "";
                        Random r = new Random();
                        for(int i=0;i<7;i++) {
                                targa += String.valueOf(r.nextInt(9));
                        }

                        a.setTarga(targa);
                        aVendute.add(a);
                        RefreshAuto(v);
                        SaveFile(v,aVendute);
                });


                //Aggiunge auto
                JButton b2 = new JButton("<html><p style='color: blue;'>Aggiungi auto</p></html>");
                b2.addActionListener(e -> {
                        Automobile a1 = new Automobile();
                        String marca = JOptionPane.showInputDialog(f,"Inserire marca");
                        String modello = JOptionPane.showInputDialog(f,"Inserire modello");
                        int cilindrata = Integer.parseInt(JOptionPane.showInputDialog(f,"Inserisci cilindrata"));
                        int potenza = Integer.parseInt(JOptionPane.showInputDialog(f,"Inserisci potenza"));
                        int euro = Integer.parseInt(JOptionPane.showInputDialog(f,"Inserisici classe euro"));
                        int posti = Integer.parseInt(JOptionPane.showInputDialog(f,"Inserici numero posti"));
                        int porte = Integer.parseInt(JOptionPane.showInputDialog(f,"Inserisci numero porte"));

                        a1.setMarca(marca);
                        a1.setModello(modello);
                        a1.setCilindrata(cilindrata);
                        a1.setPotenza(potenza);
                        a1.setEuro(euro);
                        a1.setPosti(posti);
                        a1.setPorte(porte);
                        a1.setTarga("##@@@##");

                        v.add(a1);
                        RefreshAuto(v);
                        SaveFile(v,aVendute);
                });

                //Cerca auto
                JButton b3 = new JButton("<html><p style='color: blue;'>Cerca auto</p></html>");
                b3.addActionListener(e -> {
                        String marca = JOptionPane.showInputDialog(f,"Inserire marca (lascia vuoto se non interessato)");
                        String modello = JOptionPane.showInputDialog(f,"Inserire modello (lascia vuoto se non interessato)");
                        String cilindrata = JOptionPane.showInputDialog(f,"Inserisci cilindrata (lascia vuoto se non interessato)");
                        String potenza = JOptionPane.showInputDialog(f,"Inserisci potenza (lascia vuoto se non interessato)");

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

                                        count.add(i);
                                }
                        }
                        JOptionPane.showMessageDialog(f,"Ho trovato " + count.size() + " auto nei numeri di indice " + count);

                });


                b1.setBounds(50,50,200,30);
                b2.setBounds(50,100,200,30);
                b3.setBounds(50,150,200,30);

                JLabel label = new JLabel("<html><span style='color:red;'>Indice</span> Marca Modello " +
                        "(<span style='color:green;'>cilindrata</span>,<span style='color:blue;'>potenza</span>,euro,posti,porte)</html>");
                label.setBounds(300,30,500,20);

                f.add(b1);
                f.add(b2);
                f.add(b3);
                f.add(label);
                InitAuto(v);
                f.setVisible(true);

                //Click sulle auto in lista
                lista.addMouseListener(new MouseAdapter()
                {
                        @Override
                        public void mouseClicked(MouseEvent e)
                        {
                                if(e.getClickCount()==2){

                                        JList t = (JList) e.getSource();
                                        //System.out.print(t.locationToIndex(e.getPoint()));
                                        Automobile a = v.elementAt(t.locationToIndex(e.getPoint()));
                                        ImageIcon i = new ImageIcon("assets/auto.jpg");
                                        JOptionPane.showMessageDialog(f,a,"Info",JOptionPane.PLAIN_MESSAGE,i);
                                }

                        }
                });
        }

        private void InitAuto(Vector<Automobile> v)
        {

                JFileChooser filec = new JFileChooser();
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


                this.l = new DefaultListModel<>();
                for(i=0;i<v.size();i++){
                        this.l.addElement("<html><span style='color: red;'>"+i+"</span> " + v.elementAt(i).getMarca() + " " + v.elementAt(i).getModello()+
                                " (<span style='color:green;'>" +v.elementAt(i).getCilindrata()+ "</span>, <span style='color:blue;'>" +v.elementAt(i).getPotenza()+
                                "</span>, " +v.elementAt(i).getEuro()+ ", " +v.elementAt(i).getPosti()+ ", " +v.elementAt(i).getPorte()+")</html>");
                }

                this.lista = new JList(l);
                this.s = new JScrollPane();
                this.s.setViewportView(lista);

                s.setBounds(300,50,500,300);
                f.add(s);
        }

        private void RefreshAuto(Vector<Automobile> v)
        {
                f.remove(this.s);
                this.l.removeAllElements();
                for(int i=0;i<v.size();i++){
                        this.l.addElement("<html><span style='color: red;'>"+i+"</span> " + v.elementAt(i).getMarca() + " " + v.elementAt(i).getModello()+
                                " (<span style='color:green;'>" +v.elementAt(i).getCilindrata()+ "</span>, <span style='color:blue;'>" +v.elementAt(i).getPotenza()+
                                "</span>, " +v.elementAt(i).getEuro()+ ", " +v.elementAt(i).getPosti()+ ", " +v.elementAt(i).getPorte()+")</html>");
                }

                f.add(this.s);
                f.revalidate();
        }

        private void SaveFile(Vector<Automobile> v, Vector<Automobile> aVendute){
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

        public static void main(String[] args)
        {
                new Frame();
        }
}
