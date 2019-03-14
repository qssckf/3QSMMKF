package nc.bs.so.shipmentsinfo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据审核的BP
 */
public class AceShipmentsInfoApproveBP {

  /**
   * 审核动作
   * 
   * @param vos
   * @param script
   * @return
   */
  

  public AggShipmentsVO[] approve(AggShipmentsVO[] clientBills,
      AggShipmentsVO[] originBills) {
    BillUpdate<AggShipmentsVO> update = new BillUpdate<AggShipmentsVO>();
    AggShipmentsVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }
  
}
