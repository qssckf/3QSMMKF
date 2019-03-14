package nc.ui.so.qs.mmplanbill.readyplan.handevent;
 
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.so.pub.keyvalue.IKeyValue;

	public class DeptEditHandler{
		
		public DeptEditHandler() {}
  
		public void beforeEdit(CardHeadTailBeforeEditEvent e){
			
			BillCardPanel cardPanel = e.getBillCardPanel();
			IKeyValue keyValue = new nc.ui.so.pub.keyvalue.CardKeyValue(cardPanel);
    
			String pk_org = keyValue.getHeadStringValue("pk_org");
    
			BillItem item = cardPanel.getHeadItem("pk_dept");
			FilterDeptRefUtils utils = new FilterDeptRefUtils((nc.ui.pub.beans.UIRefPane)item.getComponent());
    
			utils.filterItemRefByOrg(pk_org);
    
			e.setReturnValue(Boolean.TRUE);
  }
}

