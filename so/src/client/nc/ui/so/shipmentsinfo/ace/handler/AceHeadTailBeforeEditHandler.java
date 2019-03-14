package nc.ui.so.shipmentsinfo.ace.handler;

import uap.lfw.dbl.cpdocsysattr.sysservice.SysPrintService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pf.pub.TranstypeRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.so.qs.sc.ShipmentsVO;
/**
 * 单据表头表尾字段编辑前事件处理类
 * 
 * @since 6.0
 * @version 2011-7-7 下午02:51:21
 * @author duy
 */
public class AceHeadTailBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent> {

    @Override
    public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    	
    	
    	if("transtypepk".equals(e.getKey())){
            BillCardPanel panel = e.getBillCardPanel();
            
            Object pk_group = e.getBillCardPanel().getHeadItem("pk_group").getValueObject();
    		
//            String transtypepk = panel.getHeadItem("transtypepk").getValue();
            
        	StringBuilder sb = new StringBuilder();
        		
        	sb.append(" isnull(dr,0)=0 and parentbilltype = 'FQ01'");
        		
        	sb.append(" and pk_group ='").append(pk_group == null ? "" : pk_group).append("' ");
        		
            UIRefPane refPane=(UIRefPane)panel.getHeadItem("transtypepk").getComponent();
            TranstypeRefModel refModel = (TranstypeRefModel)refPane.getRefModel();
        		
            refModel.setWhere(sb.toString());
    	}
    	


        
    }

}
