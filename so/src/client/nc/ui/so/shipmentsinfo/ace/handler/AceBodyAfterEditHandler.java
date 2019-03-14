package nc.ui.so.shipmentsinfo.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.shipmentsinfo.billui.view.ShipmentsInfoEditor;
//import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
//import nc.ui.so.m38.billui.editor.bodyevent.AstNumEditHandler;
import nc.ui.so.shipmentsinfo.handler.AstNumEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.AstUnitEditHandler;
import nc.ui.so.shipmentsinfo.handler.AstUnitEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.BatchCodeEditHandler;
import nc.ui.so.shipmentsinfo.handler.BatchCodeEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.FreeEditHandler;
import nc.ui.so.shipmentsinfo.handler.FreeEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.LargessFlagEditHandler;
import nc.ui.so.shipmentsinfo.handler.LargessFlagEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.NumEditHandler;
import nc.ui.so.shipmentsinfo.handler.NumEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.PriceItemEditHandler;
import nc.ui.so.shipmentsinfo.handler.PriceItemEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.QtUnitEditHandler;
import nc.ui.so.shipmentsinfo.ace.handler.QtUnitEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.QtUnitNumEditHandler;
import nc.ui.so.shipmentsinfo.handler.QtUnitNumEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.ReceiveAddressEditHandler;
import nc.ui.so.shipmentsinfo.handler.ReceiveAddressEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.ReceiveAreaEditHandler;
import nc.ui.so.shipmentsinfo.handler.ReceiveAreaEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.ReceiveCountryEditHandler;
import nc.ui.so.shipmentsinfo.handler.ReceiveCountryEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.ReceiveCustEditHandler;
import nc.ui.so.shipmentsinfo.handler.ReceiveCustEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.SendCountryEditHandler;
import nc.ui.so.shipmentsinfo.handler.SendCountryEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.SendStockOrgEditHandler;
import nc.ui.so.shipmentsinfo.handler.SendStockOrgEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.SendStordocEditHandler;
import nc.ui.so.shipmentsinfo.handler.SendStordocEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.SettleOrgEditHandler;
import nc.ui.so.shipmentsinfo.handler.SettleOrgEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.TaxCodeEditHandler;
import nc.ui.so.shipmentsinfo.handler.TaxCodeEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.TaxCountryEditHandler;
import nc.ui.so.shipmentsinfo.handler.TaxCountryEditHandler;
//import nc.ui.so.m38.billui.editor.bodyevent.TaxTypeFlagEditHandler;
import nc.ui.so.shipmentsinfo.handler.TaxTypeFlagEditHandler;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
import nc.ui.so.shipmentsinfo.handler.MaterialEditHandler;
import nc.vo.so.pub.util.SOFreeUtil;
import nc.vo.so.qs.sc.ShipmentsBVO;
/**
 *单据表体字段编辑后事件
 * 
 * @since 6.0
 * @version 2011-7-12 下午08:17:33
 * @author duy
 */
public class AceBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {
	private ShipmentsInfoEditor editor;
	
	public AceBodyAfterEditHandler() {}
	
	public ShipmentsInfoEditor getEditor()
	{
	  return this.editor;
	}
	
	
    @Override
    public void handleAppEvent(CardBodyAfterEditEvent e) {
        
//    	String key = e.getKey();
//        if (key.equals("cateralvid")) {
//            nc.ui.so.shipmentsinfo.handler.MaterialHandler handler = new nc.ui.so.shipmentsinfo.handler.MaterialHandler();
//            handler.afterEdit(e);
//        }
    	
    	int[] editrows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    	if (null == editrows) {
    		return;
    	}
       
    	BillCardPanel cardPanel = e.getBillCardPanel();
    	boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
    	cardPanel.getBodyPanel().setTotalRowShow(false);
       
    	String editKey = e.getKey();
       
    	if ("cmaterialvid".equals(editKey)) {
    		MaterialEditHandler handler = new MaterialEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("nastnum".equals(editKey)) {
    	    AstNumEditHandler handler = new AstNumEditHandler();
    	    handler.afterEdit(e);
    	}
    	else if ("nnum".equals(editKey)) {
    		NumEditHandler handler = new NumEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("nqtunitnum".equals(editKey)) {
    		QtUnitNumEditHandler handler = new QtUnitNumEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("castunitid".equals(editKey)) {
    		AstUnitEditHandler handler = new AstUnitEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("cqtunitid".equals(editKey)) {
    		QtUnitEditHandler handler = new QtUnitEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("blargessflag".equals(editKey)) {
    		LargessFlagEditHandler handler = new LargessFlagEditHandler();
    		handler.afterEdit(e);
    	}
	   else if ("csendstockorgvid".equals(editKey)) {
	     SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
	     handler.afterEdit(e);
	   }
	   	else if ("csendstordocid".equals(editKey)) {
	   		SendStordocEditHandler handler = new SendStordocEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("csettleorgvid".equals(editKey)) {
	   		SettleOrgEditHandler handler = new SettleOrgEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("creceiveareaid".equals(editKey)) {
	   		ReceiveAreaEditHandler handler = new ReceiveAreaEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("cpriceitemid".equals(editKey)) {
	   		PriceItemEditHandler handler = new PriceItemEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("creceiveaddrid".equals(editKey)) {
	   		ReceiveAddressEditHandler handler = new ReceiveAddressEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("creceivecustid".equals(editKey)) {
	   		ReceiveCustEditHandler handler = new ReceiveCustEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("csendcountryid".equals(editKey)) {
	   		SendCountryEditHandler handler = new SendCountryEditHandler();
	   		handler.afterEdit(e); 
	   	}
	   	else if ("ctaxcountryid".equals(editKey)) {
	   		TaxCountryEditHandler handler = new TaxCountryEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("crececountryid".equals(editKey)) {
	   		ReceiveCountryEditHandler handler = new ReceiveCountryEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("ctaxcodeid".equals(editKey)) {
	   		TaxCodeEditHandler handler = new TaxCodeEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("ftaxtypeflag".equals(editKey)) {
	   		TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if (SOFreeUtil.isFreeKey(editKey)) {
	   		FreeEditHandler handler = new FreeEditHandler();
	   		handler.afterEdit(e);
	   	}
	   	else if ("vbatchcode".equals(editKey)) {
	   		BatchCodeEditHandler handler = new BatchCodeEditHandler();
	   		handler.setEditor(this.editor);
	   		handler.afterEdit(e);
	   	}else
	   	{
	   	    ShipmentsCalculator calculator = new ShipmentsCalculator(e.getBillCardPanel());
	   	      
	   	    calculator.calculate(editrows, editKey);
	   	}
	   	    cardPanel.getBodyPanel().setTotalRowShow(istotalshow);
       
    }
    
    public void setEditor(ShipmentsInfoEditor editor)
    {
      this.editor = editor;
    }
}
