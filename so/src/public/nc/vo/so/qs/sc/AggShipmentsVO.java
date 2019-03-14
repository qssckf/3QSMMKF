package nc.vo.so.qs.sc;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.qs.sc.ShipmentsVO")
public class AggShipmentsVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggShipmentsVOMeta.class);
    return billMeta;
  }

  @Override
  public ShipmentsVO getParentVO() {
    return (ShipmentsVO) this.getParent();
  }
}