package nc.ui.so.qs.mmplanbill.process.view;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class PlanBillProcessBillFrom extends ShowUpableBillForm{
	
	private String[] headFields={"plandate","vbdef1"};
	
	

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		
		super.initUI();
		
		this.setComponentVisible(true);
	}



	@Override
	protected void onEdit() {
		// TODO 自动生成的方法存根
		super.onEdit();
		
		BillItem[] heads=this.getBillCardPanel().getHeadItems();
		
		for(BillItem item:heads){
			item.setEnabled(false);
		}
		
		for(String field:headFields){
			if(this.billCardPanel.getHeadTailItem(field)!=null){
				this.billCardPanel.getHeadTailItem(field).setEnabled(true);
			}
		}
		
		
		
	}
	
	
	
	
	

}
