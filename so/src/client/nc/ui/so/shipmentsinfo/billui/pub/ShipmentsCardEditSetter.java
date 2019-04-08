package nc.ui.so.shipmentsinfo.billui.pub;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import nc.bs.framework.common.NCLocator;
///*     */ import nc.itf.so.m38trantype.IM38TranTypeService;
		  import nc.itf.so.qs.sc.FQ01trantype.IFQ01TranTypeService;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.so.pub.editable.SOCardEditableSetter;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
///*     */ import nc.vo.so.m38trantype.entity.M38TranTypeVO;
		  import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;
/*     */ import nc.vo.so.pub.enumeration.AskPriceRule;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ 
/*     */ 
/*     */ public class ShipmentsCardEditSetter
/*     */ {
/*     */   private static final String HEAD_SHIP = "h_";
/*     */
/*  26 */   private static final String[] REVISE_BODY_EDITKEY = { "cmaterialvid", "nnum", "nastnum", "nqtunitnum", "norigtaxmny", "norigmny", "ntax", "norigtaxprice", "norigprice", "norigtaxnetprice", "norignetprice", "nqtorigtaxprice", "nqtorigprice", "nqtorigtaxnetprc", "nqtorignetprice", "dsenddate", "dreceivedate", "creceivecustid", "creceiveaddrid", "creceiveareaid", "creceivesiteid", "csendstockorgvid", "csettleorgvid", "ctrafficorgvid", "carorgvid", "cprofitcentervid", "memo" };
/*     */
/*  28 */   private static final String[] REVISE_HEAD_EDITKEY = {  "ndiscountrate", "vmemo" };
/*     */   
/*     */   private Map<String, Boolean> hmEditEnable;
/*     */   
/*     */   public ShipmentsCardEditSetter() {}
/*     */   
/*     */   public void cacheEditEnable(BillCardPanel card)
/*     */   {
/*  81 */     this.hmEditEnable = new HashMap();
/*  82 */     for (BillItem headitem : card.getHeadShowItems()) {
/*  83 */       String headkey = getHeadKey(headitem);
/*  84 */       this.hmEditEnable.put(headkey, Boolean.valueOf(headitem.isEdit()));
/*     */     }
/*     */     
/*  87 */     for (BillItem bodyitem : card.getBodyItems()) {
/*  88 */       this.hmEditEnable.put(bodyitem.getKey(), Boolean.valueOf(bodyitem.isEdit()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void setEditEnable(BillCardPanel card)
/*     */   {
/*  95 */     for (BillItem headitem : card.getHeadShowItems()) {
/*  96 */       String headkey = getHeadKey(headitem);
/*  97 */       if (this.hmEditEnable.containsKey(headkey)) {
/*  98 */         headitem.setEdit(((Boolean)this.hmEditEnable.get(headkey)).booleanValue());
/*     */       }
/*     */     }
/*     */     
/* 102 */     for (BillItem bodyitem : card.getBodyItems()) {
/* 103 */       String bodykey = bodyitem.getKey();
/* 104 */       if (this.hmEditEnable.containsKey(bodykey)) {
/* 105 */         bodyitem.setEdit(((Boolean)this.hmEditEnable.get(bodykey)).booleanValue());
/*     */       }
/*     */     }
/* 108 */     setEditByTranType(card);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setReviseCardEdit(BillCardPanel card)
/*     */   {
/* 114 */     for (BillItem headitem : card.getHeadShowItems()) {
/* 115 */       headitem.setEdit(false);
/*     */     }
/* 117 */     for (BillItem bodyitem : card.getBodyShowItems()) {
/* 118 */       bodyitem.setEdit(false);
/*     */     }
/* 120 */     for (String key : REVISE_HEAD_EDITKEY) {
/* 121 */       BillItem headitem = card.getHeadItem(key);
/* 122 */       String headkey = getHeadKey(key);
/* 123 */       if (this.hmEditEnable.containsKey(headkey)) {
/* 124 */         headitem.setEdit(((Boolean)this.hmEditEnable.get(headkey)).booleanValue());
/*     */       }
/*     */     }
/* 127 */     for (String key : REVISE_BODY_EDITKEY)
/*     */     {
/* 129 */       BillItem bodyitem = card.getBodyItem(key);
/*     */       
/* 131 */       if (this.hmEditEnable.containsKey(key)) {
/* 132 */         bodyitem.setEdit(((Boolean)this.hmEditEnable.get(key)).booleanValue());
/*     */       }
/*     */     }
/*     */     
/* 136 */     setEditByTranType(card);
/*     */   }
/*     */   
/*     */   private String getHeadKey(BillItem headitem) {
/* 140 */     return "h_" + headitem.getKey();
/*     */   }
/*     */   
/*     */   private String getHeadKey(String headkey) {
/* 144 */     return "h_" + headkey;
/*     */   }
/*     */   
///*     */   private M38TranTypeVO getTranTypeVO(BillCardPanel card) {
/*     */   private FQ01TranTypeVO getTranTypeVO(BillCardPanel card) {
/* 148 */     IKeyValue keyValue = new CardKeyValue(card);
/* 149 */     String ctrantypeid = keyValue.getHeadStringValue("ctrantypeid");
/* 150 */     if (PubAppTool.isNull(ctrantypeid)) {
/* 151 */       return null;
/*     */     }
///* 153 */     IM38TranTypeService srv = (IM38TranTypeService)NCLocator.getInstance().lookup(IM38TranTypeService.class);
///* 155 */     M38TranTypeVO trantypevo = null;
/*     */     IFQ01TranTypeService srv = (IFQ01TranTypeService)NCLocator.getInstance().lookup(IFQ01TranTypeService.class);
/*     */ 	  FQ01TranTypeVO trantypevo = null;
/*     */     try {
/* 157 */       trantypevo = srv.queryTranTypeVO(ctrantypeid);
/*     */     }
/*     */     catch (BusinessException e) {
/* 160 */       ExceptionUtils.wrappException(e);
/*     */     }
/* 162 */     return trantypevo;
/*     */   }
/*     */   
/*     */   private boolean isAskPrice(IKeyValue keyValue, int i) {
/* 166 */     UFDouble nasktaxnetprice = keyValue.getBodyUFDoubleValue(i, "naskqtorigtxntprc");
/*     */     
/* 168 */     UFDouble nasknetprice = keyValue.getBodyUFDoubleValue(i, "naskqtorignetprice");
/*     */     
/* 170 */     return (null != nasktaxnetprice) || (null != nasknetprice);
/*     */   }
/*     */   
/*     */   private void setEditByTranType(BillCardPanel card)
/*     */   {
///* 175 */     M38TranTypeVO tranvo = getTranTypeVO(card);
/*     */     FQ01TranTypeVO tranvo = getTranTypeVO(card);
/* 176 */     if (null == tranvo) {
/* 177 */       return;
/*     */     }
/* 179 */     UFBoolean modifydis = tranvo.getBmodifydiscount() == null ? UFBoolean.FALSE : tranvo.getBmodifydiscount();
/*     */     
/*     */ 
/*     */ 
/* 183 */     BillItem disrateitem = card.getBodyItem("nitemdiscountrate");
/* 184 */     if (disrateitem.isShow()) {
/* 185 */       boolean isedit = (disrateitem.isEdit()) && (modifydis.booleanValue());
/* 186 */       disrateitem.setEdit(isedit);
/*     */     }
/* 188 */     Integer askrule = tranvo.getFaskqtrule();
/*     */     
/* 190 */     if ((AskPriceRule.ASKPRICE_NORMAL.equalsValue(askrule)) || (AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(askrule)))
/*     */     {
/* 192 */       SOCardEditableSetter soeditsetter = new SOCardEditableSetter();
/* 193 */       IKeyValue keyValue = new CardKeyValue(card);
/* 194 */       UFBoolean modifyask = tranvo.getBmodifyaskedqt() == null ? UFBoolean.FALSE : tranvo.getBmodifyaskedqt();
/*     */       
/*     */ 
/* 197 */       UFBoolean modifyunask = tranvo.getBmodifyunaskedqt() == null ? UFBoolean.FALSE : tranvo.getBmodifyunaskedqt();
/*     */       
/*     */ 
/*     */ 
/* 201 */       int bodycount = keyValue.getBodyCount();
/* 202 */       for (int i = 0; i < bodycount; i++)
/*     */       {
/* 204 */         if (isAskPrice(keyValue, i)) {
/* 205 */           soeditsetter.setBodyPriceMnyEdit(card, i, modifyask.booleanValue());
/*     */         }
/*     */         else {
/* 208 */           soeditsetter.setBodyPriceMnyEdit(card, i, modifyunask.booleanValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }