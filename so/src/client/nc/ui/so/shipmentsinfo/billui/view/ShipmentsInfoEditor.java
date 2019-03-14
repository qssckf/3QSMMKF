package nc.ui.so.shipmentsinfo.billui.view;
/*     */ 
/*     */ import java.util.List;
/*     */ import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
/*     */ import nc.ui.pub.beans.UILabel;
/*     */ import nc.ui.pub.beans.UIRefPane;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillData;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pub.bill.BillModel;
/*     */ import nc.ui.pub.bill.BillScrollPane;
/*     */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
/*     */ import nc.ui.pubapp.uif2app.model.IAppModelEx;
/*     */ import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
/*     */ import nc.ui.scmf.ic.batchcode.ref.ScmBatchAdaptor;
/*     */ import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
/*     */ import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
/*     */ import nc.ui.scmpub.ref.FilterCustomerRefUtils;
/*     */ import nc.ui.scmpub.util.BillCardPanelUtils;
///*     */ import nc.ui.so.m38.billui.pub.PreOrderCardEditSetter;
			import nc.ui.so.shipmentsinfo.billui.pub.ShipmentsCardEditSetter;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.ui.so.pub.listener.SOBillTotalListener;
/*     */ import nc.ui.uif2.model.AbstractAppModel;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDate;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.AppContext;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*     */ import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
/*     */ import nc.vo.so.pub.SOConstant;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ import nc.vo.uif2.LoginContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShipmentsInfoEditor extends ShowUpableBillForm implements OnhandPanelSrc
/*     */ {
/*  41 */   private static final String[] BODY_NOEDIT = { "ndiscountrate", "cunitid", "ccurrencyid", "nqttaxprice", "nqtprice", "nqttaxnetprice", "nqtnetprice", "ntaxprice", "nprice", "ntaxnetprice", "nnetprice", "ndiscount", "norigdiscount", "ngroupmny", "ngrouptaxmny", "nglobaltaxmny", "nglobalmny", "cpriceformid", "cpriceitemtableid", "cpricepolicyid", "naskqtorigtaxprc", "naskqtorigprice", "naskqtorigtxntprc", "naskqtorignetprice", "narrnum", "carrangeid", "darrdate", "csettleorgid", "carorgid", "cprofitcenterid", "csendstockorgid", "ctrafficorgid", "fbuysellflag", "btriatradeflag", "ntaxmny", "nmny" };
/*     */   
/*     */   private static final int DEFDABATEDATE = 3;
/*     */    
/*  98 */   private static final String[] HEAD_NOEDIT = { "vbillcode", "ntotalnum", "nheadsummny", "fstatusflag", "cdeptid" };
///*     */   
///*     */ 
/*     */   private static final long serialVersionUID = -4607945892107326567L;
///*     */   
///*     */ 
/*     */   private OnhandPanelAdaptor adaptor;
///*     */   
///*     */ 
/*     */   private List<String> clearHyperlink;
///*     */   
///*     */   private PreOrderCardEditSetter editsetter;
/*     */   private ShipmentsCardEditSetter editsetter;
///*     */ 
/*     */   public ShipmentsInfoEditor() {}
///*     */   
///*     */ 
/*     */   public void addCardBodyRowChangedEvent(IAppEventHandler<CardBodyRowChangedEvent> l)
/*     */   {
/* 117 */     ((IAppModelEx)getModel()).addAppEventListener(CardBodyRowChangedEvent.class, l);
/*     */   }
///*     */   
/*     */   public List<String> getClearHyperlink()
/*     */   {
/* 122 */     return this.clearHyperlink;
/*     */   }
///*     */   
/*     */   public OnhandPanelAdaptor getExtendedPanel() {
/* 126 */     return this.adaptor;
/*     */   }
/*     */   
/*     */   public OnhandDimParamVO getQryOnhandDim(int row)
/*     */   {
/* 131 */     OnhandDimParamVO paravo = null;
/* 132 */     IKeyValue keyValue = new CardKeyValue(getBillCardPanel());
/* 133 */     String marterialvid = keyValue.getBodyStringValue(row, "cmaterialvid");
/*     */     
/* 135 */     if (PubAppTool.isNull(marterialvid)) {
/* 136 */       return paravo;
/*     */     }
/*     */     
/* 139 */     paravo = getOnhandDimParamVO(keyValue, row);
/* 140 */     return paravo;
/*     */   }
///*     */   
/*     */   public void initUI()
/*     */   {
/* 145 */     super.initUI();
/*     */     
/* 147 */     initEditEnable();
/* 148 */     if (SysInitGroupQuery.isICEnabled()) {
/* 149 */       ScmBatchAdaptor scmbach = new ScmBatchAdaptor("nc.ui.ic.batchcode.ref.BatchRefPane");
/*     */       
/* 151 */       UIRefPane uiref = scmbach.getRefPane();
/*     */       
/* 153 */       getBillCardPanel().getBodyItem("vbatchcode").setComponent(uiref);
/*     */     }
/*     */     
/*     */ 
/* 157 */     clearHyperlink();
/*     */     
/* 159 */     initRefCondition();
/*     */     
/*     */ 
///* 162 */     this.editsetter = new PreOrderCardEditSetter();
///* 163 */     this.editsetter.cacheEditEnable(getBillCardPanel());
/* 162 */     this.editsetter = new ShipmentsCardEditSetter();
/* 163 */     this.editsetter.cacheEditEnable(getBillCardPanel());
/*     */     
/* 165 */     BillCardPanel cardPanel = getBillCardPanel();
/* 166 */     cardPanel.getBodyPanel().setTotalRowShow(true);
/* 167 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 168 */     SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
/* 169 */     cardPanel.getBillModel().addTotalListener(totallis);
/*     */     
/* 171 */     initFillEnabled(cardPanel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initFillEnabled(BillCardPanel cardPanel)
/*     */   {
/* 180 */     BillCardPanelUtils util = new BillCardPanelUtils(cardPanel);
/* 181 */     util.disableItemsFill();
/* 182 */     util.enableItemsFill(SOConstant.FILLENABLEDKEY);
/*     */     
/* 184 */     for (int i = 1; i < 21; i++) {
/* 185 */       BillItem bodyitem = getBillCardPanel().getBodyItem("def" + i);
/*     */       
/* 187 */       bodyitem.setFillEnabled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setCardEdit() {
/* 192 */     this.editsetter.setEditEnable(getBillCardPanel());
/*     */   }
/*     */   
/*     */   public void setClearHyperlink(List<String> clearHyperlink) {
/* 196 */     this.clearHyperlink = clearHyperlink;
/*     */   }
/*     */   
/*     */   public void setExtendedPanel(OnhandPanelAdaptor adaptor) {
/* 200 */     this.adaptor = adaptor;
/*     */   }
/*     */   
/*     */   public void setReviseEdit() {
/* 204 */     this.editsetter.setReviseCardEdit(getBillCardPanel());
/*     */   }
/*     */   
/*     */   protected void onAdd()
/*     */   {
/* 209 */     super.onAdd();
/* 210 */     if (isEditable()) {
/* 211 */       setCardEdit();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void setDefaultValue()
/*     */   {
/* 218 */     IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
/* 219 */     int irowcount = this.billCardPanel.getRowCount();
/*     */     
/*     */ 
/* 222 */     String pk_org = getModel().getContext().getPk_org();
/* 223 */     if (!PubAppTool.isNull(pk_org)) {
/* 224 */       keyValue.setHeadValue("pk_org", pk_org);
/* 225 */       this.billCardPanel.getBillData().loadEditHeadRelation("pk_org");
/*     */       
/* 227 */       String pk_group = AppContext.getInstance().getPkGroup();
/* 228 */       keyValue.setHeadValue("pk_group", pk_group);
/*     */       
/* 230 */       UFDate busidate = AppContext.getInstance().getBusiDate();
/* 231 */       keyValue.setHeadValue("dbilldate", busidate);
/*     */       
///* 233 */       keyValue.setHeadValue("dabatedate", busidate.getDateAfter(3).asLocalEnd());
/*     */       
/*     */ 
/*     */ 
/* 237 */       UFDate localend = busidate.asLocalEnd();
/*     */       
/* 239 */       for (int i = 0; i < irowcount; i++)
/*     */       {
/* 241 */         keyValue.setBodyValue(i, "pk_group", pk_group);
/*     */         
/* 243 */         keyValue.setBodyValue(i, "pk_org", pk_org);
/*     */         //表体日期
///* 245 */         keyValue.setBodyValue(i, "dbilldate", busidate);
/*     */         
/* 247 */         keyValue.setBodyValue(i, "nitemdiscountrate", new UFDouble(100));
/*     */         
/*     */      //表体失效日期
///* 250 */         keyValue.setBodyValue(i, "dsenddate", localend);
/* 251 */         keyValue.setBodyValue(i, "dreceivedate", localend);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void clearHyperlink() {
/* 257 */     for (String key : getClearHyperlink()) {
/* 258 */       BillItem item = getBillCardPanel().getBillData().getHeadItem(key);
/* 259 */       item.getCaptionLabel().setHyperlinkLabel(false);
/*     */     }
/*     */   }
/*     */   
/*     */   private OnhandDimParamVO getOnhandDimParamVO(IKeyValue keyValue, int row) {
/* 264 */     OnhandDimParamVO paravo = new OnhandDimParamVO();
/*     */     
/* 266 */     String pk_group = AppContext.getInstance().getPkGroup();
/* 267 */     paravo.setPk_group(pk_group);
/*     */     
/*     */ 
/* 270 */     String cmarterialvid = keyValue.getBodyStringValue(row, "cmaterialvid");
/*     */     
/* 272 */     paravo.setCmaterialvid(cmarterialvid);
/* 273 */     String cmarterialid = keyValue.getBodyStringValue(row, "cmaterialid");
/*     */     
/* 275 */     paravo.setCmaterialoid(cmarterialid);
/*     */     
/* 277 */     String castunitid = keyValue.getBodyStringValue(row, "castunitid");
/*     */     
/* 279 */     paravo.setCastunitid(castunitid);
/*     */     
/*     */ 
/* 282 */     String vchangerate = keyValue.getBodyStringValue(row, "vchangerate");
/*     */     
/* 284 */     paravo.setVchangerate(vchangerate);
/*     */     //生产厂商
///* 286 */     String cproductorid = keyValue.getBodyStringValue(row, "cproductorid");
///*     */     
///* 288 */     paravo.setCproductorid(cproductorid);
/*     */     //项目
///* 290 */     String cprojectid = keyValue.getBodyStringValue(row, "cprojectid");
///*     */     
///* 292 */     paravo.setCprojectid(cprojectid);
/*     */     //供应商
///* 294 */     String cvendorid = keyValue.getBodyStringValue(row, "cvendorid");
///* 295 */     paravo.setCvendorid(cvendorid);
/*     */     
/*     */ 
/* 298 */     String vbatchcode = keyValue.getBodyStringValue(row, "vbatchcode");
/*     */     
/* 300 */     paravo.setVbatchcode(vbatchcode);
/*     */     
/*     */ 
/* 303 */     String csendstockorgid = keyValue.getBodyStringValue(row, "csendstockorgid");
/*     */     
/* 305 */     paravo.setPk_org(csendstockorgid);
/*     */     
/*     */ 
/* 308 */     String csendstockorgvid = keyValue.getBodyStringValue(row, "csendstockorgvid");
/*     */     
/* 310 */     paravo.setPk_org_v(csendstockorgvid);
/*     */     
/*     */ 
/* 313 */     String cwarehouseid = keyValue.getBodyStringValue(row, "csendstordocid");
/*     */     
/* 315 */     paravo.setCwarehouseid(cwarehouseid);
/*     */     
/*     */ 
/* 318 */     String vfree1 = keyValue.getBodyStringValue(row, "vfree1");
/* 319 */     paravo.setVfree1(vfree1);
/*     */     
/* 321 */     String vfree2 = keyValue.getBodyStringValue(row, "vfree2");
/* 322 */     paravo.setVfree1(vfree2);
/*     */     
/* 324 */     String vfree3 = keyValue.getBodyStringValue(row, "vfree3");
/* 325 */     paravo.setVfree1(vfree3);
/*     */     
/* 327 */     String vfree4 = keyValue.getBodyStringValue(row, "vfree4");
/* 328 */     paravo.setVfree1(vfree4);
/*     */     
/* 330 */     String vfree5 = keyValue.getBodyStringValue(row, "vfree5");
/* 331 */     paravo.setVfree1(vfree5);
/*     */     
/* 333 */     String vfree6 = keyValue.getBodyStringValue(row, "vfree6");
/* 334 */     paravo.setVfree1(vfree6);
/*     */     
/* 336 */     String vfree7 = keyValue.getBodyStringValue(row, "vfree7");
/* 337 */     paravo.setVfree1(vfree7);
/*     */     
/* 339 */     String vfree8 = keyValue.getBodyStringValue(row, "vfree8");
/* 340 */     paravo.setVfree1(vfree8);
/*     */     
/* 342 */     String vfree9 = keyValue.getBodyStringValue(row, "vfree9");
/* 343 */     paravo.setVfree1(vfree9);
/*     */     
/* 345 */     String vfree10 = keyValue.getBodyStringValue(row, "vfree10");
/* 346 */     paravo.setVfree1(vfree10);
/*     */     
/* 348 */     return paravo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initEditEnable()
/*     */   {
/* 356 */     for (String key : HEAD_NOEDIT) {
/* 357 */       BillItem headitem = getBillCardPanel().getHeadTailItem(key);
/* 358 */       if (null != headitem) {
/* 359 */         headitem.setEdit(false);
/*     */       }
/*     */     }
/*     */     
/* 363 */     for (String key : BODY_NOEDIT) {
/* 364 */       BillItem bodyitem = getBillCardPanel().getBodyItem(key);
/* 365 */       if (null != bodyitem) {
/* 366 */         bodyitem.setEdit(false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void initRefCondition()
/*     */   {
/* 374 */     BillItem customeritem = getBillCardPanel().getHeadTailItem("ccustomerid");
/*     */     
/* 376 */     FilterCustomerRefUtils filterutils = new FilterCustomerRefUtils(customeritem);
/*     */     
/* 378 */     filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
/*     */     
/* 380 */     BillItem invoicecustomeritem = getBillCardPanel().getHeadTailItem("cinvoicecustid");
/*     */     
/* 382 */     FilterCustomerRefUtils invoicefilterutils = new FilterCustomerRefUtils(invoicecustomeritem);
/*     */     
/* 384 */     invoicefilterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
/*     */   }
/*     */
	}