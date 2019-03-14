package nc.bs.so.m38.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.qs.sc.AggShipmentsVO;

public class PreOrderSendApproveBP {

	
	public PreOrderVO[] sendApprove(PreOrderVO[] clientBills,PreOrderVO[] originBills){
		
		for(PreOrderVO clientFullVO:clientBills){
	          clientFullVO.getParentVO().setAttributeValue("fstatusflag", 7);
	          clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		
		PreOrderVO[] returnVos =new BillUpdate<PreOrderVO>().update(clientBills, originBills);
		
		
	    return returnVos;
	
	}
	
	
	
}
