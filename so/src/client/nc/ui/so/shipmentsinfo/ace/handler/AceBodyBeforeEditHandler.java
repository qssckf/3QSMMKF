package nc.ui.so.shipmentsinfo.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.shipmentsinfo.handler.AstUnitEditHandler;
import nc.ui.so.shipmentsinfo.handler.ChangeRateEditHandler;
import nc.ui.so.shipmentsinfo.handler.LargessFlagEditHandler;
import nc.ui.so.shipmentsinfo.handler.MaterialEditHandler;
import nc.ui.so.shipmentsinfo.handler.SendStockOrgEditHandler;
import nc.ui.so.shipmentsinfo.handler.SendStordocEditHandler;
import nc.ui.so.shipmentsinfo.handler.TrafficOrgEditHandler;
import nc.ui.so.shipmentsinfo.handler.BatchCodeEditHandler;
import nc.ui.so.shipmentsinfo.handler.ReceiveAddressEditHandler;
//import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.so.shipmentsinfo.billui.view.ShipmentsInfoEditor;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.qs.sc.ShipmentsBVO;
/**
 * 表体字段编辑前事件处理类
 * 
 * @since 6.0
 * @version 2011-7-7 下午02:52:57
 * @author duy
 */
public class AceBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {
	private ShipmentsInfoEditor editor;
	
	public AceBodyBeforeEditHandler() {}
	
	public ShipmentsInfoEditor getEditor()
	{
	  return this.editor;
	}
	
	
	
    @Override
    public void handleAppEvent(CardBodyBeforeEditEvent e) {
        e.setReturnValue(Boolean.TRUE);
//         // 物料
//        String key = e.getKey();
//        if (key.equals("cateralvid")) {
//            nc.ui.so.shipmentsinfo.handler.MaterialHandler handler = new nc.ui.so.shipmentsinfo.handler.MaterialHandler();
//            handler.beforeEdit(e);
//        }
    	
    	String editKey = e.getKey();
    	BillCardPanel cardPanel = e.getBillCardPanel();
    	IKeyValue keyValue = new CardKeyValue(cardPanel);
    	String trantypeid = keyValue.getHeadStringValue("transtypepk");
    	if (PubAppTool.isNull(trantypeid)) {
    	  ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0012"));
    	}
    	else if ("cmaterialvid".equals(editKey)) {
    		MaterialEditHandler handler = new MaterialEditHandler();
    		handler.beforeEdit(e);
    	}
    	else if ("castunitid".equals(editKey)) {
    	    AstUnitEditHandler handler = new AstUnitEditHandler();
    	    handler.beforeEdit(e);
    	}
    	else if ("vchangerate".equals(editKey)) {
    	    ChangeRateEditHandler handler = new ChangeRateEditHandler();
    	    handler.beforeEdit(e);
    	}
    	else if ("blargessflag".equals(editKey)) {
    		LargessFlagEditHandler handler = new LargessFlagEditHandler();
    		handler.beforeEdit(e);
    	}
    	else if ("csendstockorgvid".equals(editKey)) {
    	    SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
    	    handler.beforeEdit(e);
    	}
    	else if ("csendstordocid".equals(editKey)) {
    	    SendStordocEditHandler handler = new SendStordocEditHandler();
    	    handler.beforeEdit(e);
    	}
    	else if ("ctrafficorgvid".equals(editKey)) {
    	    TrafficOrgEditHandler handler = new TrafficOrgEditHandler();
    	    handler.beforeEdit(e);
    	}
    	else if ("vbatchcode".equals(editKey)) {
    	    BatchCodeEditHandler handler = new BatchCodeEditHandler();
    	    handler.setEditor(this.editor);
    	    handler.beforeEdit(e);
    	}
    	else if ("creceiveaddrid".equals(editKey)) {
    	    ReceiveAddressEditHandler handler = new ReceiveAddressEditHandler();
    	    handler.beforeEdit(e);
    	}
    	
    }

    public void setEditor(ShipmentsInfoEditor editor) {
    	this.editor = editor;
    }
}
