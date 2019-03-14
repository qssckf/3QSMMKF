package nc.vo.so.qs.sc;
/*    */ 
/*    */ import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
/*    */ import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
/*    */ import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
/*    */ 
/*    */ public class ShipmentsViewVO extends AbstractDataView
/*    */ {
/*    */   private static final long serialVersionUID = 959689656644555165L;
/*    */   
/*    */   public ShipmentsViewVO() {}
/*    */   
/*    */   public AggShipmentsVO changeToBill()
/*    */   {
/* 15 */     AggShipmentsVO bill = new AggShipmentsVO();
/* 16 */     bill.setParent(getHead());
/* 17 */     ShipmentsBVO[] items = { getItem() };
/*    */     
/*    */ 
/* 20 */     bill.setChildrenVO(items);
/* 21 */     return bill;
/*    */   }
/*    */   
/*    */   public ShipmentsVO getHead() {
/* 25 */     return (ShipmentsVO)getVO(ShipmentsVO.class);
/*    */   }
/*    */   
/*    */   public ShipmentsBVO getItem() {
/* 29 */     return (ShipmentsBVO)getVO(ShipmentsBVO.class);
/*    */   }
/*    */   
/*    */   public IDataViewMeta getMetaData()
/*    */   {
/* 34 */     return DataViewMetaFactory.getInstance().getBillViewMeta(AggShipmentsVO.class);
/*    */   }
/*    */   
/*    */   public void setHead(ShipmentsVO head) {
/* 38 */     setVO(head);
/*    */   }
/*    */   
/*    */   public void setItem(ShipmentsBVO item) {
/* 42 */     setVO(item);
/*    */   }
/*    */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\lib\pubso_sellingrequisition.jar
 * Qualified Name:     nc.vo.so.m38.entity.PreOrderViewVO
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */