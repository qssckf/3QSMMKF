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
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		 Map<String, FormulaItem> tableItems = new HashMap();
			
		 String[][] svalue={{"���������ܽ��","MonData_TotalMoney","���������ܽ��"},
				 	  {"�����ܻ���ϼ�","MonData_TotalHfMoney","�����ܻ���ϼ�"},
				 	  {"�����ܼӹ���","MonData_TotalProcessCost","�����ܼӹ���"},
			          {"�����ܷ���","MonData_TotalCost","�����ܷ���"},
			          {"���·�������","MonData_TotalNum","���·�������"},
			          {"���·�������","MonData_TotalNum","���·�������"},
			          {"����С�鷢���ܽ��","MonData_TeamTotalMoney","����С�鷢���ܽ��"},
			          {"���¸������۰�������","MonData_BYTotalNum","���¸������۰�������"}
	   			};
		
		for (int i = 0; i < svalue.length; i++) {
			   tableItems.put(svalue[i][0], new FormulaItem(svalue[i][2], svalue[i][1], svalue[i][2]));
	     }
		   return tableItems;
	}

	@Override
	public String getTabName() {
		// TODO �Զ����ɵķ������
		return "ҵ��������";
	}

}
