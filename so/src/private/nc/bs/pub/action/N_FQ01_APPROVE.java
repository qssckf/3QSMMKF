package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoApproveBP;
import nc.bs.so.shipmentsinfo.plugin.bpplugin.ShipmentsInfoPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.framework.common.NCLocator;

public class N_FQ01_APPROVE extends AbstractPfAction<AggShipmentsVO> {

  public N_FQ01_APPROVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<AggShipmentsVO> getCompareAroundProcesserWithRules(Object userObj) {
	  CompareAroundProcesser<AggShipmentsVO> processor =new CompareAroundProcesser<AggShipmentsVO>(ShipmentsInfoPluginPoint.APPROVE);
	  IRule<AggShipmentsVO> rule=null;
	  rule = new nc.bs.pubapp.pub.rule.ApproveStatusCheckRule();
				   				   				    				   				   
	  processor.addBeforeRule(rule);
				   				   
	  return processor;
  }
  
  @Override
  protected AggShipmentsVO[] processBP(Object userObj,
      AggShipmentsVO[] clientFullVOs, AggShipmentsVO[] originBills) {AggShipmentsVO[] bills = null;
      nc.itf.so.IShipmentsInfoMaintain operator = NCLocator.getInstance().lookup(nc.itf.so.IShipmentsInfoMaintain.class);
      try {
          bills=operator.approve(clientFullVOs,originBills);
      } catch (BusinessException e) {
    	  ExceptionUtils.wrappBusinessException(e.getMessage());
			      }
      return bills;
  }

}
