package nc.ui.so.qs.sc.shipments.billui.pub;
/*    */ 
/*    */ import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.FQ01trantype.IFQ01TranTypeService;
///*    */ import nc.itf.so.m38trantype.IM38TranTypeService;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.so.pub.findprice.IFindPriceConfig;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.largessprice.ILargessPriceConfig;
/*    */ import nc.vo.pub.BusinessException;
/*    */ import nc.vo.pub.lang.UFBoolean;
/*    */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*    */ import nc.vo.pubapp.pattern.pub.PubAppTool;
///*    */ import nc.vo.so.m38trantype.entity.M38TranTypeVO;
/*    */ import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;
/*    */ 
/*    */ 
/*    */ public class ShipmentsLarPriceConfig implements ILargessPriceConfig {
/*    */   private BillCardPanel cardPanel;
/*    */   private FQ01TranTypeVO fq01trantypevo;
/*    */   
/*    */   public ShipmentsLarPriceConfig(BillCardPanel cardPanel)
/*    */   {
/* 26 */     this.cardPanel = cardPanel;
/*    */   }
/*    */   
/*    */ 
/*    */   public Integer getLargessPriceMode()
/*    */   {
/* 32 */     FQ01TranTypeVO trantype = getTranTypeVO();
/* 33 */     if (null == trantype) {
/* 34 */       return LargessGetqtRule.ZERO_QT.getIntegerValue();
/*    */     }
/* 36 */     return trantype.getFlargessgetqtrule();
/*    */   }
/*    */   
/*    */ 
/*    */   public IFindPriceConfig getFindPriceConfig()
/*    */   {
/* 42 */     ShipmentsFindPriceConfig findconfig = new ShipmentsFindPriceConfig(this.cardPanel);
/*    */     
/* 44 */     return findconfig;
/*    */   }
/*    */   
/*    */   public void processAfterGetPrice(int[] rows, String chgkey)
/*    */   {
/* 49 */     FQ01TranTypeVO trantype = getTranTypeVO();
/* 50 */     ShipmentsCalculator calcultor = new ShipmentsCalculator(this.cardPanel);
/* 51 */     calcultor.setTranTypeVO(trantype);
/* 52 */     calcultor.setChangePrice(UFBoolean.TRUE);
/* 53 */     calcultor.calculate(rows, chgkey);
/*    */   }
/*    */   
/*    */   private FQ01TranTypeVO getTranTypeVO() {
/* 57 */     if (null == this.fq01trantypevo) {
/* 58 */       IKeyValue keyValue = new CardKeyValue(this.cardPanel);
/* 59 */       String trantypeid = keyValue.getHeadStringValue("transtypepk");
/* 60 */       if (PubAppTool.isNull(trantypeid)) {
/* 61 */         return null;
/*    */       }
/* 63 */       IFQ01TranTypeService srv = (IFQ01TranTypeService)NCLocator.getInstance().lookup(IFQ01TranTypeService.class);
/*    */       try
/*    */       {
/* 66 */         this.fq01trantypevo = srv.queryTranTypeVO(trantypeid);
/*    */       }
/*    */       catch (BusinessException e) {
/* 69 */         ExceptionUtils.wrappException(e);
/*    */       }
/*    */     }
/*    */     
/* 73 */     return this.fq01trantypevo;
/*    */   }
/*    */ }