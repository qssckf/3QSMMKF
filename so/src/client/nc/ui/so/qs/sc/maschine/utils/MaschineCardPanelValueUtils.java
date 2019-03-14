package nc.ui.so.qs.sc.maschine.utils;
/*     */ 
/*     */ import nc.md.model.IAttribute;
/*     */ import nc.md.model.type.IType;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillData;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pub.bill.BillModel;
/*     */ import nc.ui.pub.bill.BillScrollPane;
/*     */ import nc.ui.pubapp.uif2app.view.util.BillRowNoUtils;
/*     */ import nc.vo.pub.bill.IMetaDataProperty;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDate;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.data.ValueUtils;
/*     */ import nc.vo.pubapp.pattern.model.entity.bill.IBill;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaschineCardPanelValueUtils
/*     */ {
/*  23 */   private BillCardPanel card = null;
/*     */   
/*     */   public MaschineCardPanelValueUtils(BillCardPanel cardPanel) {
/*  26 */     this.card = cardPanel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BillCardPanel getCardPanel()
/*     */   {
/*  35 */     return this.card;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadEditHeadRelation(String sItemKey)
/*     */   {
/*  44 */     this.card.getBillData().loadEditHeadRelation(sItemKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getHeadTailValue(String sItemKey)
/*     */   {
/*  60 */     BillItem btTemp = this.card.getHeadTailItem(sItemKey);
/*  61 */     if (btTemp == null) {
/*  62 */       return null;
/*     */     }
/*  64 */     return btTemp.getValueObject();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getHeadTailStringValue(String sItemKey)
/*     */   {
/*  80 */     BillItem btTemp = this.card.getHeadTailItem(sItemKey);
/*  81 */     if (btTemp == null) {
/*  82 */       return null;
/*     */     }
/*  84 */     ValueUtils.getInstance();return ValueUtils.getString(btTemp.getValueObject());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UFDate getHeadTailUFDateValue(String sItemKey)
/*     */   {
/* 100 */     BillItem btTemp = this.card.getHeadTailItem(sItemKey);
/* 101 */     if (btTemp == null) {
/* 102 */       return null;
/*     */     }
/* 104 */     ValueUtils.getInstance();return ValueUtils.getUFDate(btTemp.getValueObject());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UFBoolean getHeadTailUFBooleanValue(String sItemKey)
/*     */   {
/* 118 */     BillItem headitem = this.card.getHeadTailItem(sItemKey);
/* 119 */     if (null == headitem) {
/* 120 */       return null;
/*     */     }
/* 122 */     ValueUtils.getInstance();return ValueUtils.getUFBoolean(headitem.getValueObject());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UFDouble getHeadTailUFDoubleValue(String sItemKey)
/*     */   {
/* 137 */     BillItem headitem = this.card.getHeadTailItem(sItemKey);
/* 138 */     if (null == headitem) {
/* 139 */       return null;
/*     */     }
/* 141 */     ValueUtils.getInstance();return ValueUtils.getUFDouble(headitem.getValueObject());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BillItem getHeadTailItem(String sItemKey)
/*     */   {
/* 157 */     BillItem btTemp = this.card.getHeadTailItem(sItemKey);
/*     */     
/* 159 */     return btTemp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BillItem getBodyItem(String sItemKey)
/*     */   {
/* 175 */     BillItem btTemp = this.card.getBodyItem(sItemKey);
/* 176 */     return btTemp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHeadTailValue(String sItemKey, Object value)
/*     */   {
/* 193 */     BillItem item = this.card.getHeadItem(sItemKey);
/* 194 */     if (item == null) { item = this.card.getTailItem(sItemKey);
/*     */     }
/* 196 */     if (item != null) { item.setValue(value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getBodyValue(int iRow, String sItemKey, String tableCode)
/*     */   {
/* 216 */     Object oTemp = null;
/*     */     
/* 218 */     BillItem bt = null;
/* 219 */     if (tableCode == null) {
/* 220 */       bt = this.card.getBodyItem(sItemKey);
/*     */     } else {
/* 222 */       bt = this.card.getBodyItem(tableCode, sItemKey);
/*     */     }
/*     */     
/* 225 */     if (null == bt) { return null;
/*     */     }
/* 227 */     IMetaDataProperty meta = bt.getMetaDataProperty();
/* 228 */     int type = bt.getDataType();
/* 229 */     if (meta != null) {
/* 230 */       type = meta.getAttribute().getDataType().getTypeType();
/*     */     }
/* 232 */     if ((type == 204) || (type == 201) || (type == 207)) {
/* 233 */       if (tableCode == null) { oTemp = this.card.getBillModel().getValueAt(iRow, sItemKey + "_ID");
/*     */       } else {
/* 235 */         oTemp = this.card.getBillModel(tableCode).getValueAt(iRow, sItemKey + "_ID");
/*     */       }
/* 237 */     } else if (tableCode == null) { oTemp = this.card.getBillModel().getValueAt(iRow, sItemKey);
/*     */     } else {
/* 239 */       oTemp = this.card.getBillModel(tableCode).getValueAt(iRow, sItemKey);
/*     */     }
/* 241 */     return oTemp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getBodyStringValue(int iRow, String sItemKey)
/*     */   {
/* 259 */     Object value = getBodyValue(iRow, sItemKey, null);
/* 260 */     ValueUtils.getInstance();return ValueUtils.getString(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UFDouble getBodyUFDoubleValue(int iRow, String sItemKey)
/*     */   {
/* 275 */     Object objValue = getBodyValue(iRow, sItemKey);
/* 276 */     ValueUtils.getInstance();return ValueUtils.getUFDouble(objValue);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UFBoolean getBodyUFBooleanValue(int iRow, String sItemKey)
/*     */   {
/* 291 */     Object objValue = getBodyValue(iRow, sItemKey);
/* 292 */     ValueUtils.getInstance();return ValueUtils.getUFBoolean(objValue);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getBodyValue(int iRow, String sItemKey)
/*     */   {
/* 310 */     return getBodyValue(iRow, sItemKey, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBodyValue(Object value, int iRow, String sItemKey, String tableCode)
/*     */   {
/* 331 */     BillModel bm = null;
/* 332 */     if (tableCode == null) {
/* 333 */       bm = this.card.getBillModel();
/*     */     } else {
/* 335 */       bm = this.card.getBillModel(tableCode);
/*     */     }
/* 337 */     if (bm == null) { return;
/*     */     }
/* 339 */     BillItem bt = bm.getItemByKey(sItemKey);
/* 340 */     if (bt == null) { return;
/*     */     }
/* 342 */     IMetaDataProperty meta = bt.getMetaDataProperty();
/* 343 */     int type = bt.getDataType();
/* 344 */     if (meta != null) {
/* 345 */       type = meta.getAttribute().getDataType().getTypeType();
/*     */     }
/* 347 */     if ((type == 204) || (type == 201) || (type == 207)) {
/* 348 */       bm.setValueAt(value, iRow, sItemKey + "_ID");
/* 349 */       bm.loadLoadRelationItemValue(iRow, sItemKey);
/*     */     } else {
/* 351 */       bm.setValueAt(value, iRow, sItemKey);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBodyValue(Object value, int iRow, String sItemKey)
/*     */   {
/* 371 */     setBodyValue(value, iRow, sItemKey, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBodyValue(Object value, String sItemKey, String tableCode)
/*     */   {
/* 390 */     BillModel bm = null;
/* 391 */     if (tableCode == null) {
/* 392 */       bm = this.card.getBillModel();
/*     */     } else {
/* 394 */       bm = this.card.getBillModel(tableCode);
/*     */     }
/* 396 */     if (bm == null) { return;
/*     */     }
/* 398 */     BillItem bt = bm.getItemByKey(sItemKey);
/* 399 */     if (bt == null) { return;
/*     */     }
/* 401 */     IMetaDataProperty meta = bt.getMetaDataProperty();
/*     */     
/* 403 */     int type = bt.getDataType();
/* 404 */     if (meta != null) {
/* 405 */       type = meta.getAttribute().getDataType().getTypeType();
/*     */     }
/* 407 */     for (int iRow = 0; iRow < this.card.getRowCount(); iRow++) {
/* 408 */       if ((type == 204) || (type == 201) || (type == 207)) {
/* 409 */         bm.setValueAt(value, iRow, sItemKey + "_ID");
/* 410 */         bm.loadLoadRelationItemValue(iRow, sItemKey);
/*     */       } else {
/* 412 */         bm.setValueAt(value, iRow, sItemKey);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBodyValue(Object value, String sItemKey)
/*     */   {
/* 431 */     setBodyValue(value, sItemKey, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void resetCardPanel(String rownoKey, String tableCode)
/*     */   {
/* 448 */     BillItem[] headItems = this.card.getHeadItems();
/* 449 */     for (BillItem item : headItems) {
/* 450 */       item.setValue(item.getDefaultValue());
/*     */     }
/*     */     
/*     */ 
/* 454 */     BillItem[] bodyItems = this.card.getBodyItems();
/* 455 */     for (BillItem item : bodyItems) {
/* 456 */       if (!item.getKey().equals(rownoKey))
/*     */       {
/*     */ 
/* 459 */         setBodyValue(item.getDefaultValue(), item.getKey(), tableCode);
/*     */       }
/*     */     }
/*     */     
/* 463 */     BillItem[] tailItems = this.card.getTailItems();
/* 464 */     for (BillItem item : tailItems) {
/* 465 */       item.setValue(item.getDefaultValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clearHeadValueByItemKeys(String[] itemKeys)
/*     */   {
/* 481 */     for (String itemKey : itemKeys) {
/* 482 */       setHeadTailValue(itemKey, null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clearAllRowValueByItemKeys(String[] itemKeys, String tableCode)
/*     */   {
/* 500 */     int rowCount = this.card.getRowCount();
/* 501 */     for (int i = 0; i < rowCount; i++) {
/* 502 */       clearRowValueByItemKeys(i, itemKeys, tableCode);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clearRowValueByItemKeys(int iRow, String[] itemKeys, String tableCode)
/*     */   {
/* 522 */     for (String itemKey : itemKeys) {
/* 523 */       setBodyValue(null, iRow, itemKey, tableCode);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <B> B getBillValueVO(Class<? extends IBill> B)
/*     */   {
/* 538 */     return (B) this.card.getBillData().getBillObjectByMetaData();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <B> B getChangeBillValueVO(Class<? extends IBill> B)
/*     */   {
/* 552 */     return (B) this.card.getBillData().getChangeBillObjectByMetaData();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isHeadItemNull(String itemKey)
/*     */   {
/* 567 */     Object value = getHeadTailValue(itemKey);
/* 568 */     if (value == null) return true;
/* 569 */     PubAppTool.getInstance();return PubAppTool.isNull(value.toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBodyItemNull(int iRow, String sItemKey, String tableCode)
/*     */   {
/* 588 */     Object value = getBodyValue(iRow, sItemKey, tableCode);
/* 589 */     if (value == null) return true;
/* 590 */     PubAppTool.getInstance();return PubAppTool.isNull(value.toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCardDisabled()
/*     */   {
/* 603 */     this.card.setEnabled(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCardEnabled()
/*     */   {
/* 616 */     this.card.setEnabled(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addBodyLine(String sRowNOKey)
/*     */   {
/* 631 */     this.card.addLine();
/* 632 */     BillRowNoUtils.addLineRowNo(this.card, sRowNOKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delBodyLine()
/*     */   {
/* 645 */     int count = this.card.getRowCount();
/* 646 */     int[] rows = new int[count];
/* 647 */     for (int i = 0; i < count; i++) {
/* 648 */       rows[i] = i;
/*     */     }
/* 650 */     this.card.getBodyPanel().delLine(rows);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void delBodyLine(Integer[] rowindexs)
/*     */   {
/* 665 */     if ((rowindexs == null) || (rowindexs.length == 0)) {
/* 666 */       return;
/*     */     }
/* 668 */     int[] rows = new int[rowindexs.length];
/* 669 */     for (int i = 0; i < rowindexs.length; i++) {
/* 670 */       rows[i] = rowindexs[i].intValue();
/*     */     }
/* 672 */     this.card.getBodyPanel().delLine(rows);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void hideTabTableCol(String tablecode, String colom)
/*     */   {
/* 684 */     getCardPanel().getBodyPanel(tablecode).hideTableCol(colom);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void showTabTableCol(String tablecode, String colom)
/*     */   {
/* 696 */     getCardPanel().getBodyPanel(tablecode).showTableCol(colom);
/*     */   }
/*     */   
/*     */   public BillCardPanel getBillCardPanel()
/*     */   {
/* 701 */     return this.card;
/*     */   }
/*     */ }