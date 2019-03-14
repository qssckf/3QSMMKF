package nc.ui.so.qs.mmplanmmdetail.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdMakeRPMOAction extends NCAction{

	private final String iconurl="themeres/ui/toolbaricons/sort_press.png";
	private BillManageModel model;
	private IRdMmService rdservice;
	
	public RdMakeRPMOAction(){
		
		this.setBtnName("����������б�");
		this.setCode("RdMakeRPMO");
		this.putValue("ShortDescription", "����������б������ں�����Ա����Ͷ��������");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		this.setEnabled(false);
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}

	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		
		return rdservice;
	}


	public BillManageModel getModel() {
		return model;
	}


	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		
		try{
			 
			 Object[] objs = getModel().getSelectedOperaDatas();
				
			 if ((null == objs) || (MMValueCheck.isEmpty(objs))) {
				throw new BusinessException("û��ѡ���κ����ݣ�����ʧ�ܣ�") ;
			 }
			 
			 List<RdPorductDetailVO> rds=new ArrayList();
			 
			 for(Object obj:objs){
				 
				 if(obj instanceof RdPorductDetailVO){
					 
					 rds.add((RdPorductDetailVO) obj);
					 
					 
				 }else{
					 throw new BusinessException("�������Ͳ���ȷ������ʧ��");
				 }
				 
				 
			 }
			 

			 RdPorductDetailVO[] rdvo=rds.toArray(new RdPorductDetailVO[rds.size()]);

			 RdPorductDetailVO[] retobj=this.getRdservice().DoRdPMO(rdvo);
			 
			 this.getModel().directlyUpdate(retobj);
			 
			 ShowStatusBarMsgUtil.showStatusBarMsg("���ȵ���ϸ����������б���", this.getModel().getContext());
				 
	

		 }catch(Exception ex){
			 
			 ShowStatusBarMsgUtil.showErrorMsg("����", ex.getMessage(), this.getModel().getContext());
			 
			 throw new BusinessException(ex);
		 }
		 
		
		
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		
		RdPorductDetailVO selectvo=(RdPorductDetailVO)this.getModel().getSelectedData();
		
		if(selectvo!=null && selectvo.getBillstatus()==1){
			return true;
		}else{
			return false;
		}
		
	}
	
}