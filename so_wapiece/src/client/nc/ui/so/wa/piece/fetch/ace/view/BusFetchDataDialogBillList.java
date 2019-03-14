package nc.ui.so.wa.piece.fetch.ace.view;

import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.editor.BillListView;

public class BusFetchDataDialogBillList extends BillListView {

	public BusFetchDataDialogBillList(BillManageModel model){
		super.setModel(model);
		super.initUI();
	}
	
	@Override
	public void handleEvent(AppEvent event) {
		// TODO 自动生成的方法存根
		super.handleEvent(event);
	}
	
	public BillScrollPane getBodyPanel() {
		return getBillListPanel().getParentListPanel();
	}
	
	public BillScrollPane getBillScrollPane(){
		return getBillListPanel().getParentListPanel();
	 }

}
