package view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer{

   public Component getTableCellRendererComponent(JTable table, Object value,
		   boolean isSelected, boolean isFocus, int row, int col) {
      //On écrit dans le bouton ce que contient la cellule
		ImageIcon settings = new ImageIcon("./src/images/vu.png");
		this.setIcon(settings);
      //On retourne notre bouton
      return this;
   }
}