package nc.ui.so.wa.piece.ace.view.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pub.beans.UIList;
import nc.vo.pub.formulaedit.FormulaItem;

public class BasicTabBuilder extends AbstractPieceTabBuilder {

	@Override
	public UIList getUIList() {
		// TODO 自动生成的方法存根
		Map<String, FormulaItem> allTableItems = getAllVariableItems();
		List<String> itemsList = new ArrayList();
		itemsList.addAll(allTableItems.keySet());
		String[] item = (String[])itemsList.toArray(new String[itemsList.size()]);
		
		  if (this.getVariableUIList() == null) {
			  this.setVariableUIList(new UIList(item));
			  
			  
			  if (this.getVariableUIList().getVisibleRowCount() >= 1) {
				  this.getVariableUIList().setSelectedIndex(0);
	      }
	    }
		return this.getVariableUIList();
	}

	@Override
	public Map<String, FormulaItem> getAllVariableItems() {
		// TODO 自动生成的方法存根
		 Map<String, FormulaItem> tableItems = new HashMap();
			
		 String[][] svalue={{"计件产品加工费","PieceProductProcessCost","岗位计件标准"},
				 	  {"计件产品装车费","PieceProductCarCost","计件产品装车费"},
				 	  {"计件产品其他费用","PieceProductOtherCost","计件产品其他费用"},
			          {"利润提成比例","ProfitPushMoneyRatio","利润提成比例"},
			          {"计件产品发货标准","PieceProductSendStand","计件产品发货标准"}
	   			};
		
		for (int i = 0; i < svalue.length; i++) {
			   tableItems.put(svalue[i][0], new FormulaItem(svalue[i][2], svalue[i][1], svalue[i][2]));
	     }
		   return tableItems;
	}

	@Override
	public String getTabName() {
		// TODO 自动生成的方法存根
		return "标准类";
	}

}
