package nc.ui.so.qs.intoprod.input.action;

import java.awt.event.ActionEvent;

import org.apache.axis.utils.StringUtils;

import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.actions.SaveAction;
import nc.ui.so.qs.intoprod.input.model.ItAppModel;
import nc.ui.so.qs.mmplanbill.readyplan.model.RdMmModel;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class IntoProdSaveAction extends SaveAction{

	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		
		Object value = this.getEditor().getValue();
		
		validate(value);
		
		IntoProdDetailVO vo=(IntoProdDetailVO) value;
		
		if(StringUtils.isEmpty(vo.getPk_itpd())){
			
			vo.setStatus(2);
			this.doAddSave(vo);
			
		}else{
			
			vo.setStatus(1);
			this.doEditSave(vo);
		}
		
		
		
	}

	@Override
	protected void doEditSave(Object value) throws Exception {
		// TODO 自动生成的方法存根
		Object returnObj = this.getModel().update(value);
		this.getModel().setUiState(UIState.NOT_EDIT);
		
		
		if(returnObj instanceof IntoProdDetailVO){
			
			IntoProdDetailVO vo=(IntoProdDetailVO)returnObj;
			
			RdPorductDetailVO ppd=this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class, vo.getVsrcrdid(), false);
			
			if(ppd!=null){
				
				((ItAppModel)this.getModel()).getParentModel().directlyUpdate(ppd);
				
				((ItAppModel)this.getModel()).getChModel().directlyUpdate(returnObj);
				
				SFClientUtil.closeFuncWindow("4006200205");
				
				
			}else{
				throw new BusinessException("没有找到来源单据数据，保存失败，请检查！");
			}

		}else{
			throw new BusinessException("返回数据类型格式不正确，保存失败，请检查！");
		}

	}

	@Override
	protected void doAddSave(Object value) throws Exception {
		// TODO 自动生成的方法存根
		
		Object returnObj = this.getModel().add(value);		
		this.getModel().setUiState(UIState.NOT_EDIT);
		
		if ((this.getModel() instanceof HierachicalDataAppModel)) {
			((HierachicalDataAppModel)this.getModel()).setSelectedData(returnObj);
		}
		
		if(returnObj instanceof IntoProdDetailVO){
		
			IntoProdDetailVO vo=(IntoProdDetailVO)returnObj;
			
			RdPorductDetailVO ppd=this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class, vo.getVsrcrdid(), false);
			
			if(ppd!=null){
				
				((ItAppModel)this.getModel()).getParentModel().directlyUpdate(ppd);
				
				((ItAppModel)this.getModel()).getChModel().directlyAdd(returnObj);
				
				SFClientUtil.closeFuncWindow("4006200205");
				
				
			}else{
				throw new BusinessException("没有找到来源单据数据，保存失败，请检查！");
			}

		}else{
			throw new BusinessException("返回数据类型格式不正确，保存失败，请检查！");
		}
	}
	
	
	
	
	
	
}
