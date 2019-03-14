package nc.bs.so.shipmentsinfo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceShipmentsInfoUnApproveBP {

  public AggShipmentsVO[] unApprove(AggShipmentsVO[] clientBills,
      AggShipmentsVO[] originBills) {
    BillUpdate<AggShipmentsVO> update = new BillUpdate<AggShipmentsVO>();
    AggShipmentsVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }
  
  private void setHeadVOStatus(AggShipmentsVO[] clientBills) {
        for (AggShipmentsVO clientBill : clientBills) {
            clientBill.getParentVO().setStatus(VOStatus.UPDATED);
        }
    }
}
