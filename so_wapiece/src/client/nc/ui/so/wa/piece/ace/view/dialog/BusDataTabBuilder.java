package nc.ui.so.wa.piece.ace.view.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pub.beans.UIList;
import nc.vo.pub.formulaedit.FormulaItem;

public class BusDataTabBuilder extends AbstractPieceTabBuilder {

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
			
		 String[][] svalue={{"当月销售总金额","MonData_TotalMoney","当月销售总金额"},
				 	  {"当月总混合料价","MonData_TotalHfMoney","当月总混合料价"},
				 	  {"当月总加工费","MonData_TotalProcessCost","当月总加工费"},
			          {"当月总费用","MonData_TotalCost","当月总费用"},
			          {"当月发货总量","MonData_TotalNum","当月发货总量"},
			          {"当月发货总量","MonData_TotalNum","当月发货总量"},
			          {"当月小组发货总金额","MonData_TeamTotalMoney","当月小组发货总金额"},
			          {"当月个人销售白油数量","MonData_BYTotalNum","当月个人销售白油数量"}
	   			};
		
		for (int i = 0; i < svalue.length; i++) {
			   tableItems.put(svalue[i][0], new FormulaItem(svalue[i][2], svalue[i][1], svalue[i][2]));
	     }
		   return tableItems;
	}

	@Override
	public String getTabName() {
		// TODO 自动生成的方法存根
		return "业务数据类";
	}

}
