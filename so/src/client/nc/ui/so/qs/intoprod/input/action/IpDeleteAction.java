package nc.ui.so.qs.intoprod.input.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import org.apache.commons.collections.CollectionUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.so.qs.sc.bs.bd.service.ErrLogElement;
import nc.ui.so.qs.intoprod.input.action.rule.IpDeleteFilterRule;
import nc.ui.so.qs.mmplanmmdetail.actions.BDDeleteAction;
import nc.ui.so.qs.mmplanmmdetail.actions.DeleteAction;
import nc.ui.so.qs.mmplanmmdetail.actions.rule.RdDeleteFilterRule;
import nc.ui.uif2.model.BillManageModel;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class IpDeleteAction extends BDDeleteAction{
	
	private final String iconurl="themeres/ui/toolbaricons/delete_row_highlight.png";
	private BillManageModel fmodel = null;
	private IRdMmService rdservice;
	
	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		return rdservice;
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	public BillManageModel getFmodel() {
		return fmodel;
	}

	public void setFmodel(BillManageModel fmodel) {
		this.fmodel = fmodel;
	}
	
	public IpDeleteAction(){
		
		super.addVaildateRule(new IpDeleteFilterRule());
		
		this.setBtnName("ɾ��");
		this.setCode("RdDelete");
		this.putValue("ShortDescription", "ɾ��Ͷ����¼");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		this.setEnabled(false);
		
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		validate();
		
		DelteWithProgressBar();
	}

	private void DelteWithProgressBar() {
		// TODO �Զ����ɵķ������
		 showProgressWaitMsg();
		 SwingWorker<Object, Object> worker = new SwingWorker()
		 {
			 
			 protected Object doInBackground() throws Exception {
				 IpDeleteAction.this.Delete();
				 return null;
			 }
			 
			 protected void done() {
				 IpDeleteAction.this.showProgressBar(false);
				 try {
					 get();
					 IpDeleteAction.this.showSuccessInfo();
				 } catch (Exception e) {
					 IpDeleteAction.this.handlerExeption(e);
				 }
			 }
		 };
		 worker.execute();
		 if (!worker.isDone()) {
			 showProgressBar(true);
		 }
	}
	
	protected void Delete() throws Exception {
		// TODO �Զ����ɵķ������
		Object[] datas=this.getSelectedOperaDatas();
		
		if(datas!=null && datas.length>0){
			
			Object returnObj=datas[0];
			
			IntoProdDetailVO vo=(IntoProdDetailVO)returnObj;
			
			RdPorductDetailVO pd=this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class, vo.getVsrcrdid(), false);

			this.getRdservice().validateVOTs(new SuperVO[]{pd});
			
			this.getModel().delete();
			
			if(returnObj instanceof IntoProdDetailVO){
								
				RdPorductDetailVO ppd=this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class, vo.getVsrcrdid(), false);
				
				if(ppd!=null){
					
					this.getFmodel().directlyUpdate(ppd);
					
				}else{
					throw new BusinessException("û���ҵ���Դ�������ݣ�����ʧ�ܣ����飡");
				}

			}else{
				throw new BusinessException("�����������͸�ʽ����ȷ������ʧ�ܣ����飡");
			}
			
		}else{
			throw new BusinessException("û��ѡ���κ����ݣ��������ɾ��������");
		}
	
	}
	
	@Override
	protected void showErrorLog(ArrayList<ErrLogElement> errLogList) throws BusinessException {
		// TODO �Զ����ɵķ������
		
		if(!CollectionUtils.isEmpty(errLogList)){
			
			StringBuilder errMsg = new StringBuilder("����ɾ��Ч��ʧ��!"+"\n");
			
			for (int i = 0; i < errLogList.size(); i++) {
				ErrLogElement element = (ErrLogElement)errLogList.get(i);
				errMsg.append("Ͷ�����ڣ���").append(element.getVo().getAttributeValue("actuaproddate")).append("��,Ͷ����������").append(element.getVo().getAttributeValue("prodnum")).append("����������Ϣ��").append(element.getErrReason()).append("\n");
			}
			
			throw new BusinessException(errMsg.toString());
		}
	}

	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		IntoProdDetailVO selectvo=(IntoProdDetailVO)this.getModel().getSelectedData();
		
		if(selectvo != null && selectvo.getSfmakebill().toString().equals("N")){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	

}
