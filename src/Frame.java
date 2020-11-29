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
        public static JFrame f = new JFrame();
        public static DefaultListModel<String> l;
        public static DefaultListModel<String> l1;
        public static JList lista;
        private JList lista1;
        public static Vector<Automobile> v = new Vector<>();
        public static Vector<Automobile> aVendute = new Vector<>();

        Frame()
        {
                f.getContentPane().setBackground(Color.DARK_GRAY);
                f.setName("Concessionaria");
                f.setSize(800,750);
                f.setResizable(false);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                f.setLayout(null);

                UIManager.put("OptionPane.background",Color.DARK_GRAY);
                UIManager.put("OptionPane.messageForeground",Color.WHITE);

                //Aggiunge auto
                JButton b1 = new JButton("Aggiungi auto");
                b1.setBackground(Color.BLACK);
                b1.setForeground(Color.WHITE);
                b1.addActionListener(e -> {
                        AddCar.Add(f,v);
                        RefreshAuto(v,aVendute);
                        SaveFile.Save(v,aVendute);
                });

                //Cerca auto
                JButton b2 = new JButton("Cerca auto");
                b2.setBackground(Color.BLACK);
                b2.setForeground(Color.WHITE);
                b2.addActionListener(e -> SearchCar.Search(f,v,aVendute));


                b1.setBounds(50,100,200,30);
                b2.setBounds(50,150,200,30);

                JLabel label = new JLabel("<html><span style='color:red;'>Indice</span> Marca Modello " +
                        "(cilindrata,potenza,euro,posti,porte)</html>");
                label.setForeground(Color.WHITE);
                label.setBounds(300,30,500,20);

                JLabel label1 = new JLabel("Auto vendute");
                label1.setForeground(Color.WHITE);
                label1.setBounds(300,380,500,20);

                f.add(b1);
                f.add(b2);
                f.add(label);
                f.add(label1);
                InitAuto(v,aVendute);
                f.setVisible(true);

                //Click sulle auto in lista
                lista.addMouseListener(new MouseAdapter()
                {
                        @Override
                        public void mouseClicked(MouseEvent e)
                        {
                                if(e.getClickCount()==2){
                                        EditCar.Edit(f,v,lista);
                                        RefreshAuto(v,aVendute);
                                        SaveFile.Save(v,aVendute);
                                }

                        }
                });

                lista1.addMouseListener(new MouseAdapter()
                {
                        @Override
                        public void mouseClicked(MouseEvent e)
                        {
                                if(e.getClickCount()==2){
                                        EditCar.Edit(f,aVendute,lista1);
                                        RefreshAuto(v,aVendute);
                                        SaveFile.Save(v,aVendute);
                                }

                        }
                });
        }

        private void InitAuto(Vector<Automobile> v, Vector<Automobile> aVendute)
        {
                Init.InitCar(v,true);
                Init.InitCar(aVendute,false);

                l = new DefaultListModel<>();
                addToList(v,l);

                l1 = new DefaultListModel<>();
                addToList(aVendute,l1);

                lista = new JList<>(l);
                lista.setForeground(Color.WHITE);
                lista.setBackground(Color.GRAY);

                lista1 = new JList<>(l1);
                lista1.setForeground(Color.WHITE);
                lista1.setBackground(Color.GRAY);

                JScrollPane s = new JScrollPane();
                s.setViewportView(lista);

                JScrollPane s1 = new JScrollPane();
                s1.setViewportView(lista1);


                lista.setDragEnabled(true);
                lista1.setDropMode(DropMode.INSERT);
                lista.setTransferHandler(new GestoreDrag());
                lista1.setTransferHandler(new GestoreDrop());


                s.setBounds(300,50,517,310);
                s1.setBounds(300,400,517,310);
                f.add(s);
                f.add(s1);
        }

        public static void RefreshAuto(Vector<Automobile> v, Vector<Automobile> aVendute)
        {
                l.removeAllElements();
                l1.removeAllElements();
                addToList(v,l);
                addToList(aVendute,l1);
                f.revalidate();
        }

        public static void addToList(Vector<Automobile> v,DefaultListModel<String> l)
        {
                for(int i=0;i<v.size();i++){
                        l.addElement("<html><span style='color: black;'>"+(i+1)+"</span> " + v.elementAt(i).getMarca() + " " + v.elementAt(i).getModello()+
                                " (" +v.elementAt(i).getCilindrata()+ ", " +v.elementAt(i).getPotenza()+
                                ", " +v.elementAt(i).getEuro()+ ", " +v.elementAt(i).getPosti()+ ", " +v.elementAt(i).getPorte()+")</html>");
                }
        }

        public static void main(String[] args)
        {
                new Frame();
        }
}
