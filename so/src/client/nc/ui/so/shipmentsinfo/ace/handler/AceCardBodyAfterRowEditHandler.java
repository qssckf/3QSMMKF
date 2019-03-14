package nc.ui.so.shipmentsinfo.ace.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.AppUiContext;
/*    */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*    */ import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.vo.pub.lang.UFDate;
/*    */ import nc.vo.pub.lang.UFDouble;
/*    */ import nc.vo.pubapp.AppContext;
/*    */ import nc.vo.so.m38.rule.HeadTotalCalculateRule;
/*    */ import nc.vo.so.pub.SOConstant;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ 
/*    */ public class AceCardBodyAfterRowEditHandler implements IAppEventHandler<CardBodyAfterRowEditEvent>{
/*    */   public AceCardBodyAfterRowEditHandler() {}
/*    */   
/*    */   public void handleAppEvent(CardBodyAfterRowEditEvent e)
/*    */   {
/* 23 */     int[] rows = e.getRows();
/* 24 */     BodyRowEditType edittype = e.getRowEditType();
/* 25 */     if ((BodyRowEditType.ADDLINE == edittype) || (BodyRowEditType.INSERTLINE == edittype))
/*    */     {
/*    */ 
/* 28 */       BillCardPanel cardPanel = e.getBillCardPanel();
/* 29 */       IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */       
/*    */ 
/* 32 */       String pk_org = keyValue.getHeadStringValue("pk_org");
/*    */       
/* 34 */       String pk_group = keyValue.getHeadStringValue("pk_group");
/*    */       
/*    */ 
/* 37 */       String origcurrency = keyValue.getHeadStringValue("corigcurrencyid");
/*    */       
/*    */ 
///* 40 */       UFDate billdate = keyValue.getHeadUFDateValue("dbilldate");
/*    */       
/* 42 */       UFDouble discount = keyValue.getHeadUFDoubleValue("ndiscountrate");
/*    */       
/*    */ 
/*    */ 
/* 46 */       UFDate busidate = AppUiContext.getInstance().getBusiDate();
/* 47 */       busidate = busidate.asLocalEnd();
/*    */       
/* 49 */       for (int row : rows) {
/* 50 */         keyValue.setBodyValue(row, "pk_org", pk_org);
/* 51 */         keyValue.setBodyValue(row, "pk_group", pk_group);
/* 52 */         keyValue.setBodyValue(row, "corigcurrencyid", origcurrency);
///* 53 */         keyValue.setBodyValue(row, "dbilldate", billdate);
/* 54 */         keyValue.setBodyValue(row, "ndiscountrate", discount);
/* 55 */         keyValue.setBodyValue(row, "nitemdiscountrate", SOConstant.ONEHUNDRED);
/*    */         
/*    */ 
/* 58 */         keyValue.setBodyValue(row, "dsenddate", busidate);
/* 59 */         keyValue.setBodyValue(row, "dreceivedate", busidate);
/*    */       }
/*    */       
/*    */     }
/* 63 */     else if ((BodyRowEditType.DELLINE == edittype) || (BodyRowEditType.PASTELINE == edittype) || (BodyRowEditType.PASTELINETOTAIL == edittype))
/*    */     {
/*    */ 
/*    */ 
/* 67 */       BillCardPanel cardPanel = e.getBillCardPanel();
/* 68 */       IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 69 */       HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
/* 70 */       totalrule.calculateHeadTotal();
/*    */     }
/*    */   }
/*    */ }
