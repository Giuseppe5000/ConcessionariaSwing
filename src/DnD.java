import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

class GestoreDrag extends TransferHandler
{

        public int getSourceActions(JComponent c)
        {
                return TransferHandler.MOVE;
        }


        protected Transferable createTransferable(JComponent c)
        {
                return new StringSelection( (String) (((JList) c).getSelectedValue()) );
        }



        protected void exportDone(JComponent c, Transferable data, int action)
        {
                JList lista = (JList) c;
                String Drag = (String) lista.getSelectedValue();

                if (action == TransferHandler.MOVE)
                {
                        DefaultListModel datiLista = (DefaultListModel) lista.getModel();
                        datiLista.removeElement(Drag);
                }
        }


}
class GestoreDrop extends TransferHandler
{
        public boolean canImport(TransferHandler.TransferSupport support) {
                return true;
        }

        public boolean importData(TransferHandler.TransferSupport support)
        {
                Transferable data = support.getTransferable();
                int i;
                try {
                        String Drop = (String) data.getTransferData(DataFlavor.stringFlavor);
                        char s = Drop.charAt(34);
                        char s1 = Drop.charAt(35);

                        try {
                                i = Integer.parseInt(s+""+s1);
                        } catch (Exception e){
                                i = Integer.parseInt(s+"");
                        }

                } catch (Exception e)
                {

                        return false;
                }

                SellCar.Sell(Frame.lista,Frame.v,Frame.aVendute,i-1);
                Frame.RefreshAuto(Frame.v,Frame.aVendute);
                SaveFile.Save(Frame.v,Frame.aVendute);
                return true;
        }
}
