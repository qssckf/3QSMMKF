package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoInsertBP;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoUpdateBP;
import nc.bs.so.shipmentsinfo.plugin.bpplugin.ShipmentsInfoPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.jcom.lang.StringUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.bs.framework.common.NCLocator;

public class N_FQ01_SAVEBASE extends AbstractPfAction<AggShipmentsVO> {

  @Override
  protected CompareAroundProcesser<AggShipmentsVO> getCompareAroundProcesserWithRules(
      Object userObj) {
      CompareAroundProcesser<AggShipmentsVO> processor = null;
		AggShipmentsVO[] clientFullVOs = (AggShipmentsVO[]) this.getVos();
		/*BillTransferTool<AggShipmentsVO> tool = new BillTransferTool<AggShipmentsVO>(
				clientFullVOs);
		clientFullVOs = tool.getClientFullInfoBill();*/
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggShipmentsVO>(
					ShipmentsInfoPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggShipmentsVO>(
					ShipmentsInfoPluginPoint.SCRIPT_INSERT);
		}
		   // TODO 在此处添加前后规则
		   IRule<AggShipmentsVO> rule=null;
					  
				  		
    return processor;
  }

  @Override
  protected AggShipmentsVO[] processBP(Object userObj,AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) {
      
      AggShipmentsVO[] bills = null;
      try {
            nc.itf.so.IShipmentsInfoMaintain operator = NCLocator.getInstance().lookup(nc.itf.so.IShipmentsInfoMaintain.class);
            if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO().getPrimaryKey())) {
              bills = operator.update(clientFullVOs,originBills);
		            } else {
              bills = operator.insert(clientFullVOs,originBills);
		            }
      } catch (BusinessException e) {
    	  ExceptionUtils.wrappBusinessException(e.getMessage());
			      }
      return bills;
  }
}
