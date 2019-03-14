package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoDeleteBP;
import nc.bs.so.shipmentsinfo.plugin.bpplugin.ShipmentsInfoPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.framework.common.NCLocator;

public class N_FQ01_DELETE extends AbstractPfAction<AggShipmentsVO> {

  @Override
  protected CompareAroundProcesser<AggShipmentsVO> getCompareAroundProcesserWithRules(Object userObj) {
	  CompareAroundProcesser<AggShipmentsVO> processor =new CompareAroundProcesser<AggShipmentsVO>(ShipmentsInfoPluginPoint.SCRIPT_DELETE);
    // TODO 在此处添加前后规则
	  IRule<AggShipmentsVO> rule=null;
				  
	  return processor;
  }

  @Override
  protected AggShipmentsVO[] processBP(Object userObj,AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) {
    nc.itf.so.IShipmentsInfoMaintain operator = NCLocator.getInstance().lookup(nc.itf.so.IShipmentsInfoMaintain.class);
    try {
          operator.delete(clientFullVOs,originBills);
      } catch (BusinessException e) {
    	  ExceptionUtils.wrappBusinessException(e.getMessage());
			      }
    return clientFullVOs;
  }

}
