package nc.bs.so.shipmentsinfo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pub.VOStatus;

/**
 * 标准单据送审的BP
 */
public class AceShipmentsInfoSendApproveBP {
  /**
   * 送审动作
   * 
   * @param vos 单据VO数组
   * @param script 单据动作脚本对象
   * @return 送审后的单据VO数组
   */
  
  public AggShipmentsVO[] sendApprove(AggShipmentsVO[] clientBills,
      AggShipmentsVO[] originBills) {
      for(AggShipmentsVO clientFullVO:clientBills){
		          clientFullVO.getParentVO().setAttributeValue("approvestatus", BillStatusEnum.COMMIT.value());
		          clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
	      }
    // 数据持久化
    AggShipmentsVO[] returnVos =
    		new BillUpdate<AggShipmentsVO>().update(clientBills, originBills);
    return returnVos;
  }
}
