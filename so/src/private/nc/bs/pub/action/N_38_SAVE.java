package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.bs.so.shipmentsinfo.plugin.bpplugin.ShipmentsInfoPluginPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.so.m38.IPreOrderMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderVO;

public class N_38_SAVE extends AbstractPfAction<PreOrderVO>{

	@Override
	protected CompareAroundProcesser<PreOrderVO> getCompareAroundProcesserWithRules(Object userObj) {
		// TODO 自动生成的方法存根
		
		CompareAroundProcesser<PreOrderVO> processor = new CompareAroundProcesser<PreOrderVO>(ActionPlugInPoint.SendAction);
		
		IRule<PreOrderVO> rule=null;
		rule = new nc.bs.pubapp.pub.rule.CommitStatusCheckRule();
		
		return processor;
	}

	@Override
	protected PreOrderVO[] processBP(Object arg0, PreOrderVO[] clientFullVOs,PreOrderVO[] originBills) {
		// TODO 自动生成的方法存根
		
		IPreOrderMaintain operator = NCLocator.getInstance().lookup(IPreOrderMaintain.class);
		
		PreOrderVO[] bills=null;
		
		try{
			
			bills=operator.save(clientFullVOs, originBills);
			
		}catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		
		return bills;
	}

}
