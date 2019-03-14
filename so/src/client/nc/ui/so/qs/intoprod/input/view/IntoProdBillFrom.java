package nc.ui.so.qs.intoprod.input.view;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class IntoProdBillFrom extends ShowUpableBillForm{
	
	private String[] headFields={"actuaproddate","expandline","expandunit","prodnum","prodnastnum"};
	private String[] headTailFields = {"creator","creationtime","modifier","modifiedtime"};
	

	@Override
	protected void onEdit() {
		// TODO 自动生成的方法存根
		super.onEdit();
		
		BillItem[] heads=this.getBillCardPanel().getHeadItems();
				
		for(BillItem item:heads){
			item.setEnabled(false);
		}
		
		for(String headtail:headTailFields){
			this.getBillCardPanel().getHeadTailItem(headtail).setEnabled(false);
		}
		
		for(String field:headFields){
			if(this.billCardPanel.getHeadTailItem(field)!=null){
				this.billCardPanel.getHeadTailItem(field).setEnabled(true);
			}
		}
	
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		
		super.initUI();
		
		this.setComponentVisible(true);
		
		
	}
	
	
	
	
	

}
