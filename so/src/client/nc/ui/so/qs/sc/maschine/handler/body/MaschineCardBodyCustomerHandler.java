package nc.ui.so.qs.sc.maschine.handler.body;

import nc.ui.mmf.framework.handler.MMBaseHandler;
import nc.ui.mmf.framework.util.BillCardPanelUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;






public class MaschineCardBodyCustomerHandler extends MMBaseHandler{
	
	public MaschineCardBodyCustomerHandler() {}
  
	public void beforeEdit(CardPanelEvent e) {}
  
	public void afterEdit(CardPanelEvent event){
		CardBodyAfterEditEvent e = (CardBodyAfterEditEvent)event;
		RefMoreSelectedUtils util = new RefMoreSelectedUtils(e.getBillCardPanel());

		int[] rows = util.refMoreSelected(e.getRow(), e.getKey(), true);
		String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
		BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
		for (int row : rows) {
//    		utils.setBodyValue(Integer.valueOf(row + 1), row, "isequence", tabCode);
//			utils.setBodyValue(Integer.valueOf(row + 1), row, "memo", tabCode);
			utils.setBodyValue(null, row, "pk_cusclass", tabCode);
		}
	}
}