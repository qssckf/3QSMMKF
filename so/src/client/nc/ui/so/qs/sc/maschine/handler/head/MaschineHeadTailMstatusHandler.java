package nc.ui.so.qs.sc.maschine.handler.head;
/*    */ 
/*    */ import nc.ui.mmf.framework.handler.MMBaseHandler;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
/*    */ import nc.util.mmf.framework.base.MMNumberUtil;
/*    */ import nc.vo.pub.lang.UFDouble;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineHeadTailMstatusHandler
/*    */   extends MMBaseHandler
/*    */ {
/*    */   public MaschineHeadTailMstatusHandler() {}
/*    */   
/*    */   public void beforeEdit(CardPanelEvent event) {}
/*    */   
/*    */   public void afterEdit(CardPanelEvent event)
/*    */   {
///* 26 */     CardHeadTailAfterEditEvent e = (CardHeadTailAfterEditEvent)event;
///* 27 */     UFDouble ntime = e.getValue() == null ? UFDouble.ZERO_DBL : new UFDouble(Double.valueOf(e.getValue().toString()));
///*    */     
///* 29 */     String sastnum = e.getBillCardPanel().getHeadItem("nastnum").getValue();
///* 30 */     UFDouble nastnum = sastnum == null ? UFDouble.ZERO_DBL : new UFDouble(sastnum);
///* 31 */     if ((!MMNumberUtil.isEqual(UFDouble.ZERO_DBL, nastnum)) && (!MMNumberUtil.isEqual(UFDouble.ZERO_DBL, ntime))) {
///* 32 */       UFDouble nprodfactor = ntime == UFDouble.ZERO_DBL ? null : nastnum.div(ntime);
///* 33 */       UFDouble nprodbeat = nastnum == UFDouble.ZERO_DBL ? null : ntime.div(nastnum);
///* 34 */       e.getBillCardPanel().setHeadItem("nprodfactor", nprodfactor);
///* 35 */       e.getBillCardPanel().setHeadItem("nprodbeat", nprodbeat);
///*    */     } else {
///* 37 */       e.getBillCardPanel().setHeadItem("nprodfactor", Integer.valueOf(0));
///* 38 */       e.getBillCardPanel().setHeadItem("nprodbeat", Integer.valueOf(0));
///*    */     }
/*    */     
/*    */ 
/*    */ 
///* 43 */     new MaschineHeadTailEventUtil().setNrationCapacity(event);
/*    */   }
/*    */ }