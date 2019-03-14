package nc.ui.so.qs.mmplanbill.process.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;

import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillTableCellRenderer;

public class BomVersionTableCellRenderer extends BillTableCellRenderer{
	
	 private int currentRow;
	 private BillModel billModel;
	 private boolean bMultiSelect;
	 private static final Color COLOR_BACKGROUND = Color.LIGHT_GRAY;
	 
	 private static final Color COLOR_FONT = Color.RED;
	 
	 public BomVersionTableCellRenderer(BillModel billModel, boolean bMultiSelect){
		 
		 this.billModel = billModel;
	     this.bMultiSelect = bMultiSelect; 
	 }
	 
	 public void setBMultiSelect(boolean bMultiSelect) {
		 this.bMultiSelect = bMultiSelect;
	 }
	 
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	   {
	     Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	     
	     if (this.billModel == null) {
	       return cell;
	     }
	     if ((this.bMultiSelect) && (this.billModel.getRowState(row) == 4)) {
	       cell.setBackground(COLOR_BACKGROUND);
	       cell.setForeground(COLOR_FONT);
	     } else if ((!this.bMultiSelect) && (this.currentRow == row)) {
	       cell.setBackground(COLOR_BACKGROUND);
	       cell.setForeground(COLOR_FONT);
	     }
	     return cell;
	   }
	   
	   public void setCurrentRow(int currentRow) {
	     this.currentRow = currentRow;
	   }
	 
	

}
