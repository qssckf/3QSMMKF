package nc.ui.so.wa.piece.handler;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.fetch.IPieceFetchInfoVOMaintain;
import nc.pub.smart.provider.Provider;
import nc.pub.smart.provider.impl.SqlProvider;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.pub.BusinessException;
import nc.vo.smart.SmartDefVO;

public class BodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{

	private IPieceFetchInfoVOMaintain service;
	private Map<String,SmartDefVO> SmartDef=new HashMap();
	
	
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
    // TODO Auto-generated method stub
    
		String pk_def;
		SmartDefVO smart = null;
		int row=e.getRow();
		CardPanelValueUtils panel= new CardPanelValueUtils(e.getBillCardPanel());
	  
		if ((e.getKey().equals("smartdef1") ||e.getKey().equals("smartdef2"))&&e.getValue()!=null){
		  
			pk_def=(String)e.getValue();
		  
			if (this.SmartDef.get(pk_def)==null){
				
				try {
					smart=this.getService().querySmartDefByPK(pk_def);
					this.SmartDef.put(pk_def, smart);
				} catch (BusinessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			  
			}else{
				smart=this.SmartDef.get(pk_def);
			}
		  
			Provider[] provider=(smart.getSmartmodel().getProviders());
  			
  			if (provider.length>1){
  				MessageDialog.showErrorDlg(e.getBillCardPanel(), "����", "��ѡ�������ģ���ж���Ĺ�����Ŀ����1�������ܲ�֧�֣�������ѡ��");
  				if (e.getKey().equals("smartdef1")){
  					panel.setBodyValue(null, row, "smartdef1");
  				}
  				
  				if (e.getKey().equals("smartdef2")){
  					panel.setBodyValue(null, row, "smartdef2");
  				}

  			}
  			
  			if (!(provider[0] instanceof SqlProvider)){
  				MessageDialog.showErrorDlg(e.getBillCardPanel(), "����", "��ѡ�������ģ���ж���Ĺ������Ͳ�������ű���������ѡ��");
  			
  				if (e.getKey().equals("smartdef1")){
  					panel.setBodyValue(null, row, "smartdef1");
  				}
  				
  				if (e.getKey().equals("smartdef2")){
  					panel.setBodyValue(null, row, "smartdef2");
  				}
  				
  			}
  			
  			if (e.getKey().equals("smartdef1")){
  				if (e.getValue().equals(e.getBillCardPanel().getBodyValueAt(row, "smartdef2"))){
	  				MessageDialog.showErrorDlg(e.getBillCardPanel(), "����", "�������ӱ�����ģ��ѡ��ֵһ�£�������ѡ��");
	  				panel.setBodyValue(null, row, "smartdef1");
	  			}
  			}
  			
  			if (e.getKey().equals("smartdef2")){
  				if (e.getValue().equals(e.getBillCardPanel().getBodyValueAt(row, "smartdef1"))){
	  				MessageDialog.showErrorDlg(e.getBillCardPanel(), "����", "��������������ģ��ѡ��ֵһ�£�������ѡ��");
	  				panel.setBodyValue(null, row, "smartdef2");
	  			}
  			}
		  
		}
	  
	}
	
	public IPieceFetchInfoVOMaintain getService() {
		
		if(this.service==null){
			
			this.service=(IPieceFetchInfoVOMaintain)NCLocator.getInstance().lookup(IPieceFetchInfoVOMaintain.class);
		}
		
		return service;
	}


}
