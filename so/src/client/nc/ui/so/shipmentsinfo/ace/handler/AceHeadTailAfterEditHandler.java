package nc.ui.so.shipmentsinfo.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.shipmentsinfo.handler.head.ChannelTypeEditHandler;
import nc.ui.so.shipmentsinfo.handler.head.CustomerEditHandler;
import nc.ui.so.shipmentsinfo.handler.head.DiscountRateEditHandler;
import nc.ui.so.shipmentsinfo.handler.head.OrigCurrencyEditHandler;
import nc.ui.so.shipmentsinfo.handler.head.TransportTypeEditHandler;
//import nc.ui.so.m38.billui.editor.headevent.ChannelTypeEditHandler;
//import nc.ui.so.m38.billui.editor.headevent.CustomerEditHandler;
//import nc.ui.so.m38.billui.editor.headevent.DiscountRateEditHandler;
//import nc.ui.so.m38.billui.editor.headevent.OrigCurrencyEditHandler;
//import nc.ui.so.m38.billui.editor.headevent.TransportTypeEditHandler;
import nc.vo.so.qs.sc.ShipmentsVO;
/**
 * 单据表头表尾字段编辑后事件处理类
 * 
 * @since 6.0
 * @version 2011-7-7 下午02:52:22
 * @author duy
 */
public class AceHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {
	
	public AceHeadTailAfterEditHandler(){}
	
    @Override
    public void handleAppEvent(CardHeadTailAfterEditEvent e) {
//    	if("ccustomerid".equals(e.getKey())){
//    		BillCardPanel panel = e.getBillCardPanel();
//    		Object customer = panel.getHeadItem("ccustomerid").getValueObject();
//    		panel.setHeadItem("cinvoicecustid", customer);
//    		panel.setHeadItem("corigcurrencyid", "1002Z0100000000001K1");
//    		panel.setHeadItem("ndiscountrate", "100");
//    		
//    	}
    	String editkey = e.getKey();
    	
    	if ("corigcurrencyid".equals(editkey)) {
    		OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
    		handler.afteEdit(e);
    	}
    	else if ("ndiscountrate".equals(editkey)) {
    		DiscountRateEditHandler handler = new DiscountRateEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("ccustomerid".equals(editkey)) {
    		CustomerEditHandler handler = new CustomerEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("cchanneltypeid".equals(editkey)) {
    		ChannelTypeEditHandler handler = new ChannelTypeEditHandler();
    		handler.afterEdit(e);
    	}
    	else if ("ctransporttypeid".equals(editkey)) {
    		TransportTypeEditHandler handler = new TransportTypeEditHandler();
    		handler.afterEdit(e);	
    	}
    }	  
}
