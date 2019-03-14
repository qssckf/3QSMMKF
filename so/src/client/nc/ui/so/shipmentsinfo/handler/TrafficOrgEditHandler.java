package nc.ui.so.shipmentsinfo.handler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
/*     */ import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
/*     */ import nc.itf.scmpub.reference.uap.org.TrafficOrgPubService;
/*     */ import nc.ui.bd.ref.AbstractRefModel;
/*     */ import nc.ui.pub.beans.UIRefPane;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.vo.bd.stordoc.StordocVO;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ 
/*     */ public class TrafficOrgEditHandler
/*     */ {
/*     */   public TrafficOrgEditHandler() {}
/*     */   
/*     */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*     */   {
/*  28 */     int row = e.getRow();
/*  29 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  30 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*  31 */     String csendstockorgid = getSendStockOrgID(keyValue, row);
/*     */     
/*     */ 
/*  34 */     if (PubAppTool.isNull(csendstockorgid)) {
/*  35 */       e.setReturnValue(Boolean.FALSE);
/*  36 */       return;
/*     */     }
/*     */     
/*  39 */     Map<String, Collection<String>> trafficOrgIDMap = null;
/*  40 */     String[] trafficOrgVIDs = null;
/*     */     
/*  42 */     String[] csendstockorgids = { csendstockorgid };
/*     */     
/*     */ 
/*  45 */     trafficOrgIDMap = TrafficOrgPubService.getTrafficOrgIDSByStockOrgIDSWithIsSend(csendstockorgids);
/*     */     
/*     */ 
/*  48 */     trafficOrgVIDs = getTraficOrgVIDs(trafficOrgIDMap);
/*  49 */     BillItem trafficOrgvItem = cardPanel.getBodyItem("ctrafficorgvid");
/*     */     
/*  51 */     UIRefPane trafficOrgvRefPane = (UIRefPane)trafficOrgvItem.getComponent();
/*  52 */     AbstractRefModel model = trafficOrgvRefPane.getRefModel();
/*  53 */     if ((null != trafficOrgVIDs) && (trafficOrgVIDs.length > 0)) {
/*  54 */       model.setFilterPks(trafficOrgVIDs);
/*     */     }
/*     */     else {
/*  57 */       model.setFilterPks(new String[0]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private String getSendStockOrgID(IKeyValue keyValue, int row)
/*     */   {
/*  64 */     String sendstordocid = keyValue.getBodyStringValue(row, "csendstordocid");
/*     */     
/*  66 */     String sendstockorgid = keyValue.getBodyStringValue(row, "csendstockorgid");
/*     */     
/*     */ 
/*  69 */     if (PubAppTool.isNull(sendstordocid)) {
/*  70 */       return sendstockorgid;
/*     */     }
/*     */     
/*  73 */     String[] sendstordocids = { sendstordocid };
/*     */     
/*     */ 
/*  76 */     String[] fields = { "pk_stordoc", "pk_org" };
/*     */     
/*     */ 
/*  79 */     StordocVO[] stordocVOs = StordocPubService.queryStordocByPks(sendstordocids, fields);
/*     */     
/*  81 */     if ((null != stordocVOs) && (stordocVOs.length > 0)) {
/*  82 */       return stordocVOs[0].getPk_org();
/*     */     }
/*  84 */     return sendstockorgid;
/*     */   }
/*     */   
/*     */ 
/*     */   private String[] getTraficOrgVIDs(Map<String, Collection<String>> trafficOrgIDMap)
/*     */   {
/*  90 */     String[] ret = null;
/*  91 */     if ((null == trafficOrgIDMap) || (trafficOrgIDMap.size() == 0)) {
/*  92 */       return ret;
/*     */     }
/*     */     
/*  95 */     List<String> trafficOrgIDList = new ArrayList();
/*  96 */     Set<Map.Entry<String, Collection<String>>> entrySet = trafficOrgIDMap.entrySet();
/*     */     
/*  98 */     for (Map.Entry<String, Collection<String>> entry : entrySet) {
/*  99 */       Collection<String> idList = (Collection)entry.getValue();
/* 100 */       trafficOrgIDList.addAll(idList);
/*     */     }
/* 102 */     String[] trafficOrgIDs = new String[trafficOrgIDList.size()];
/* 103 */     trafficOrgIDList.toArray(trafficOrgIDs);
/* 104 */     Map<String, String> vidMap = null;
/*     */     
/* 106 */     vidMap = OrgUnitPubService.getNewVIDSByOrgIDS(trafficOrgIDs);
/*     */     
/* 108 */     if (vidMap != null) {
/* 109 */       List<String> vidlist = new ArrayList();
/* 110 */       for (Map.Entry<String, String> entry : vidMap.entrySet()) {
/* 111 */         String value = (String)entry.getValue();
/* 112 */         if ((value != null) && (value.length() > 0)) {
/* 113 */           vidlist.add(entry.getValue());
/*     */         }
/*     */       }
/* 116 */       if (vidlist.size() > 0) {
/* 117 */         ret = (String[])vidlist.toArray(new String[vidlist.size()]);
/*     */       }
/*     */     }
/* 120 */     return ret;
/*     */   }
/*     */ }