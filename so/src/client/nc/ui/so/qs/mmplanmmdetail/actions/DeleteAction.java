package nc.ui.so.qs.mmplanmmdetail.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import org.apache.commons.collections.CollectionUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.so.qs.sc.bs.bd.service.ErrLogElement;
import nc.ui.bd.pub.action.DeleteWithProgressBarAction;
import nc.ui.so.qs.mmplanbill.readyplan.model.RdMmModel;
import nc.ui.so.qs.mmplanbill.view.PlanDetailMmListView;
import nc.ui.so.qs.mmplanmmdetail.actions.rule.RdDeleteFilterRule;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.model.BillManageModel;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class DeleteAction extends BDDeleteAction{
	
	private final String iconurl="themeres/ui/toolbaricons/delete_row_highlight.png";
	private BillManageModel fmodel = null;
	private IPlanBillSerive planservice;
	
	
	public IPlanBillSerive getPlanservice() {
		
		if(this.planservice==null){
			
			this.planservice=NCLocator.getInstance().lookup(IPlanBillSerive.class);
			
		}
		
		return planservice;
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

	public DeleteAction(){
		
		super.addVaildateRule(new RdDeleteFilterRule());
		
		this.setBtnName("删除");
		this.setCode("RdDelete");
		this.putValue("ShortDescription", "删除初始化状态下的待生产记录");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		this.setEnabled(false);
		
	}
	
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		
		validate();
		
		DelteWithProgressBar();
		
	}

	private void DelteWithProgressBar() {
		// TODO 自动生成的方法存根
		 showProgressWaitMsg();
		 SwingWorker<Object, Object> worker = new SwingWorker()
		 {
			 
			 protected Object doInBackground() throws Exception {
				 DeleteAction.this.Delete();
				 return null;
			 }
			 
			 protected void done() {
				 DeleteAction.this.showProgressBar(false);
				 try {
					 get();
					 DeleteAction.this.showSuccessInfo();
				 } catch (Exception e) {
					 DeleteAction.this.handlerExeption(e);
				 }
			 }
		 };
		 worker.execute();
		 if (!worker.isDone()) {
			 showProgressBar(true);
		 }
		
		
	}
	
	

	protected void Delete() throws Exception {
		// TODO 自动生成的方法存根
		Object[] datas=this.getSelectedOperaDatas();
		
		if(datas!=null && datas.length>0){
			
			Object returnObj=datas[0];
			
			RdPorductDetailVO vo=(RdPorductDetailVO)returnObj;
			
			PlanProduceDetailVO pd=this.getMDQueryService().queryBillOfVOByPK(PlanProduceDetailVO.class, vo.getVsrcplanbid(), false);

			this.getPlanservice().validateVOTs(new SuperVO[]{pd});
			
			this.getModel().delete();
			
			if(returnObj instanceof RdPorductDetailVO){
								
				PlanProduceDetailVO ppd=this.getMDQueryService().queryBillOfVOByPK(PlanProduceDetailVO.class, vo.getVsrcplanbid(), false);
				
				if(ppd!=null){
					
					this.getFmodel().directlyUpdate(ppd);
					
				}else{
					throw new BusinessException("没有找到来源单据数据，保存失败，请检查！");
				}

			}else{
				throw new BusinessException("返回数据类型格式不正确，保存失败，请检查！");
			}
			
		}else{
			throw new BusinessException("没有选择任何数据，不能完成删除操作！");
		}
	
	}

	@Override
	protected void showErrorLog(ArrayList<ErrLogElement> errLogList) throws BusinessException {
		// TODO 自动生成的方法存根
		
		if(!CollectionUtils.isEmpty(errLogList)){
			
			StringBuilder errMsg = new StringBuilder("数据删除效验失败!"+"\n");
			
			for (int i = 0; i < errLogList.size(); i++) {
				ErrLogElement element = (ErrLogElement)errLogList.get(i);
				errMsg.append("生产日期：【").append(element.getVo().getAttributeValue("proddate")).append("】,排产数量：【").append(element.getVo().getAttributeValue("prodnum")).append("】，错误信息：").append(element.getErrReason()).append("\n");
			}
			
			throw new BusinessException(errMsg.toString());
		}
	}



	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		
		RdPorductDetailVO selectvo=(RdPorductDetailVO)this.getModel().getSelectedData();
		
		if(selectvo!=null && selectvo.getBillstatus()==1){
			return true;
		}else{
			return false;
		}
	
	}
	
	

}
