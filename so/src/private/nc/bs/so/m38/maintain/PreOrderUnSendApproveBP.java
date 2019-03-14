package nc.bs.so.m38.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.qs.sc.AggShipmentsVO;

public class PreOrderUnSendApproveBP {
	
	public PreOrderVO[] unSend(PreOrderVO[] clientBills,PreOrderVO[] originBills) {
		
		this.setHeadVOStatus(clientBills);
		BillUpdate<PreOrderVO> update = new BillUpdate<PreOrderVO>();
		PreOrderVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;

	}
	
	private void setHeadVOStatus(PreOrderVO[] clientBills) {
	        for (PreOrderVO clientBill : clientBills) {
	        clientBill.getParentVO().setAttributeValue("fstatusflag",1);
	            clientBill.getParentVO().setStatus(VOStatus.UPDATED);
	        }
	}

}
