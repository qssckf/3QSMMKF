package nc.ui.so.qs.sc.maschine.handler;
/*    */ 
/*    */ import nc.ui.mmf.framework.handler.MMBaseHandler;
/*    */ import nc.ui.mmf.framework.handler.MMEventHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailBfrequencyHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailBprodlineHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailFcapacitycalcHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailFtimeunitHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailNastnumHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailNequipfactorHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailNparallelnumHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailNtimeHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailNworkefficiencyHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailNworkerfactorHandler;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.qs.sc.maschine.handler.head.MaschineHeadTailMstatusHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineCardHeadTailAfterEditHandler
/*    */   extends MMEventHandler
/*    */   implements IAppEventHandler<CardHeadTailAfterEditEvent>
/*    */ {
/*    */   public MaschineCardHeadTailAfterEditHandler() {}
/*    */   
/*    */   public void initMap()
/*    */   {
//			   putHandler("mstatus", MaschineHeadTailMstatusHandler.class);
///* 34 */     putHandler("bprodline", PDWkHeadTailBprodlineHandler.class);
///* 35 */     putHandler("bfrequency", PDWkHeadTailBfrequencyHandler.class);
///* 36 */     putHandler("fcapacitycalc", PDWkHeadTailFcapacitycalcHandler.class);
///* 37 */     putHandler("nastnum", PDWkHeadTailNastnumHandler.class);
///* 38 */     putHandler("ntime", PDWkHeadTailNtimeHandler.class);
///* 39 */     putHandler("nparallelnum", PDWkHeadTailNparallelnumHandler.class);
///* 40 */     putHandler("nequipfactor", PDWkHeadTailNequipfactorHandler.class);
///* 41 */     putHandler("nworkefficiency", PDWkHeadTailNworkefficiencyHandler.class);
///* 42 */     putHandler("nworkerfactor", PDWkHeadTailNworkerfactorHandler.class);
///* 43 */     putHandler("ftimeunit", PDWkHeadTailFtimeunitHandler.class);
/*    */   }
/*    */   
/*    */   public void handleAppEvent(CardHeadTailAfterEditEvent e)
/*    */   {
/* 48 */     MMBaseHandler handler = getHandler(e.getKey());
/* 49 */     if (handler != null) {
/* 50 */       handler.afterEdit(e);
/*    */     }
/* 52 */     e.getBillCardPanel().execHeadTailLoadFormulas();
/*    */   }
/*    */ }
