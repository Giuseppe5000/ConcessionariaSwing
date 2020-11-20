/*
* @author Tutino Giuseppe
* @version 2.2
*
* */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class Frame
{
        JFrame f = new JFrame();
        private DefaultListModel<String> l;
        private JScrollPane s;
        private JList lista;

        Frame()
        {
                f.getContentPane().setBackground(Color.DARK_GRAY);
                f.setName("Concessionaria");
                f.setSize(800,395);
                f.setResizable(false);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                f.setLayout(null);

                UIManager.put("OptionPane.background",Color.DARK_GRAY);
                UIManager.put("OptionPane.messageForeground",Color.WHITE);

                Vector<Automobile> v = new Vector<>();
                Vector<Automobile> aVendute = new Vector<>();

                //Vende auto
                JButton b1 = new JButton("Vendi auto selezionata");
                b1.setBackground(Color.BLACK);
                b1.setForeground(Color.WHITE);
                b1.addActionListener(e -> {
                        SellCar.Sell(lista,v,aVendute);
                        RefreshAuto(v);
                        SaveFile.Save(v,aVendute);
                });


                //Aggiunge auto
                JButton b2 = new JButton("Aggiungi auto");
                b2.setBackground(Color.BLACK);
                b2.setForeground(Color.WHITE);
                b2.addActionListener(e -> {
                        AddCar.Add(f,v);
                        RefreshAuto(v);
                        SaveFile.Save(v,aVendute);
                });

                //Cerca auto
                JButton b3 = new JButton("Cerca auto");
                b3.setBackground(Color.BLACK);
                b3.setForeground(Color.WHITE);
                b3.addActionListener(e -> SearchCar.Search(f,v));


                b1.setBounds(50,50,200,30);
                b2.setBounds(50,100,200,30);
                b3.setBounds(50,150,200,30);

                JLabel label = new JLabel("<html><span style='color:red;'>Indice</span> Marca Modello " +
                        "(cilindrata,potenza,euro,posti,porte)</html>");
                label.setForeground(Color.WHITE);
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
                                        EditCar.Edit(f,v,lista);
                                        RefreshAuto(v);
                                        SaveFile.Save(v,aVendute);
                                }

                        }
                });
        }

        private void InitAuto(Vector<Automobile> v)
        {
                Init.InitCar(v);

                this.l = new DefaultListModel<>();
                addToList(v);

                this.lista = new JList<>(this.l);
                this.lista.setForeground(Color.WHITE);
                this.lista.setBackground(Color.GRAY);

                this.s = new JScrollPane();
                this.s.setViewportView(lista);

                this.s.setBounds(300,50,517,310);
                f.add(s);
        }

        private void RefreshAuto(Vector<Automobile> v)
        {
                this.l.removeAllElements();
                addToList(v);
                f.revalidate();
        }

        private void addToList(Vector<Automobile> v)
        {
                for(int i=0;i<v.size();i++){
                        this.l.addElement("<html><span style='color: black;'>"+(i+1)+"</span> " + v.elementAt(i).getMarca() + " " + v.elementAt(i).getModello()+
                                " (" +v.elementAt(i).getCilindrata()+ ", " +v.elementAt(i).getPotenza()+
                                ", " +v.elementAt(i).getEuro()+ ", " +v.elementAt(i).getPosti()+ ", " +v.elementAt(i).getPorte()+")</html>");
                }
        }

        public static void main(String[] args)
        {
                new Frame();
        }
}
