 package nc.ui.so.m38.billui.action;
 
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
 import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
 import nc.vo.so.m38.entity.PreOrderHVO;
 import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.qs.sc.MmPlanBillVO;
 
 public class PreOrderUnApproveAction extends UNApproveScriptAction
 {
   private static final long serialVersionUID = -2422714587726948187L;
   
   public PreOrderUnApproveAction() {}
   
   private IPlanBillSerive PlanService;
   
   public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
   }
   
	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}
	
   
   protected void beforeCheck(Object vo){
	   
	   try{
		   
		   List<String> pks=new ArrayList();
			 
		   if(vo instanceof PreOrderVO[]){
			    
			   PreOrderVO[] prevos=(PreOrderVO[]) vo;
			   
			   for(PreOrderVO prevo:prevos){
				   
				   PreOrderBVO[] bvos=prevo.getChildrenVO();
				   
				   for(PreOrderBVO bvo:bvos){
					   
					   pks.add(bvo.getCpreorderbid());
					   
				   }
				   
				   Collection<MmPlanBillVO> col=this.getMDQueryService().queryBillOfVOByPKs(MmPlanBillVO.class, pks.toArray(new String[pks.size()]), false);
				   
				   if(col!=null && col.size()>0){
					   
					   throw new BusinessException("已经生成生产调度单，不能取消审核！");
					   
				   }
				   
			   }
			   
		   }
		 
		   
	   }catch(Exception e){
		   ExceptionUtils.wrappException(e);
	   }
	   
	  
	   super.beforeCheck(vo);
   }
   
   protected boolean isActionEnable()
   {
     boolean isEnable = (getModel().getAppUiState() == nc.ui.pubapp.uif2app.AppUiState.NOT_EDIT) && (null != getModel().getSelectedData());
     
 
 
     if (isEnable) {
       Object[] selectedRows = getModel().getSelectedOperaDatas();
       PreOrderVO selectedData = (PreOrderVO)getModel().getSelectedData();
       Integer billstatus = selectedData.getParentVO().getFstatusflag();
       
       isEnable = ((null != selectedRows) && (selectedRows.length > 1)) || (BillStatus.AUDIT.equalsValue(billstatus));
     }
     
 
     return isEnable;
   }
 }

