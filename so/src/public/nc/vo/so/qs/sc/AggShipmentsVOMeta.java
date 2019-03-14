package nc.vo.so.qs.sc;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggShipmentsVOMeta extends AbstractBillMeta {
  public AggShipmentsVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.so.qs.sc.ShipmentsVO.class);
    this.addChildren(nc.vo.so.qs.sc.ShipmentsBVO.class);
  }
}