package nc.qs.sc.bill.data.access;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;

import nc.bill.data.access.BatchGetMeteDataRelationItemVaules;
import nc.bill.data.access.BillAccessRefBatchMatch;
import nc.bill.data.access.ComboBoxBillItemUtil;
import nc.bill.data.access.GetMeteDataRelationItemVaule;
/*     */ import nc.bill.data.access.formula.FormulaProc;
/*     */ import nc.bill.data.access.formula.FormulaUtil;
/*     */ import nc.bs.framework.common.NCLocator;
/*     */ import nc.bs.logging.Logger;
/*     */ import nc.itf.uap.billtemplate.IBillTemplateQry;
/*     */ import nc.md.data.access.DASFacade;
/*     */ import nc.md.data.access.NCObject;
/*     */ import nc.md.model.IBusinessEntity;
/*     */ import nc.md.model.access.javamap.BeanStyleEnum;
/*     */ import nc.md.model.access.javamap.IBeanStyle;
/*     */ import nc.ui.bd.ref.AbstractRefModel;
/*     */ import nc.ui.bd.ref.RefPubUtil;
/*     */ import nc.ui.pub.beans.constenum.DefaultConstEnum;
/*     */ import nc.ui.pub.beans.constenum.IConstEnum;
/*     */ import nc.vo.bill.pub.MiscUtil;
/*     */ import nc.vo.pub.AggregatedValueObject;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.ISuperVO;
/*     */ import nc.vo.pub.bill.BillTabVO;
/*     */ import nc.vo.pub.bill.BillTempletHeadVO;
/*     */ import nc.vo.pub.bill.BillTempletVO;
/*     */ import nc.vo.pub.bill.IMetaDataProperty;
/*     */ import nc.vo.pub.bill.server.BillDataVO;
/*     */ import nc.vo.pub.bill.server.BillItemMeta;
/*     */ import nc.vo.pub.bill.server.BillVO;
/*     */ import nc.vo.pub.bill.server.IBillItemMeta;
/*     */ import nc.vo.pub.lang.MultiLangText;
/*     */ import nc.vo.pub.templet.translator.BillTranslator;
/*     */ 
/*     */ public class BillAccess
/*     */ {
/*  38 */   private BillTempletVO billTempletVO = null;
/*  39 */   private Object oData = null;
/*  40 */   private String pk_billTemplet = null;
/*  41 */   private BillVO billVO = null;
/*     */   
/*  43 */   BillAccessRefBatchMatch refBatchMatch = new BillAccessRefBatchMatch();
/*     */   
/*     */   public BillAccess(String pk_billTemplet, Object oData) {
/*  46 */     this.pk_billTemplet = pk_billTemplet;
/*  47 */     this.oData = oData;
/*  48 */     init();
/*     */   }
/*     */   
/*     */   public BillAccess(BillTempletVO billTempletVO, Object oData)
/*     */   {
/*  53 */     this.billTempletVO = billTempletVO;
/*  54 */     BillTranslator.translate(billTempletVO);
/*  55 */     this.oData = oData;
/*     */   }
/*     */   
/*     */   private void init()
/*     */   {
/*  60 */     this.billTempletVO = getBillTempletVO(this.pk_billTemplet);
/*     */   }
/*     */   
/*     */   private int getBodyRowAcount()
/*     */   {
/*  65 */     int rowAccount = 0;
/*  66 */     if ((this.oData instanceof AggregatedValueObject)) {
/*  67 */       AggregatedValueObject aggrVO = (AggregatedValueObject)this.oData;
/*  68 */       if (aggrVO.getChildrenVO() != null) {
/*  69 */         rowAccount = aggrVO.getChildrenVO().length;
/*     */       }
/*     */     }
/*  72 */     return rowAccount;
/*     */   }
/*     */   
/*     */   private BillTempletVO getBillTempletVO(String pk_billTemplet)
/*     */   {
/*  77 */     BillTempletVO vo = null;
/*  78 */     IBillTemplateQry qry = (IBillTemplateQry)NCLocator.getInstance().lookup(IBillTemplateQry.class.getName());
/*     */     try
/*     */     {
/*  81 */       vo = qry.findTempletData(pk_billTemplet);
/*  82 */       BillTranslator.translate(vo);
/*     */     }
/*     */     catch (BusinessException e) {
/*  85 */       Logger.error(e.getMessage(), e);
/*     */     }
/*  87 */     return vo;
/*     */   }
/*     */   
/*     */   private BillItemMeta[] getHeadTailItemMetas() {
/*  91 */     return (BillItemMeta[])MiscUtil.ArraysCat(getBillVO().getBillItemMeta(0), getBillVO().getBillItemMeta(2));
/*     */   }
/*     */   
/*     */ 
/*     */   private BillTabVO[] getBaseBodyTabVos()
/*     */   {
/*  97 */     BillTabVO[] tabVOs = getBillTempletVO().getHeadVO().getStructvo().getBillTabVOs();
/*     */     
/*     */ 
/* 100 */     if (tabVOs == null) {
/* 101 */       return null;
/*     */     }
/* 103 */     ArrayList<BillTabVO> list = new ArrayList();
/* 104 */     for (int i = 0; i < tabVOs.length; i++) {
/* 105 */       BillTabVO vo = tabVOs[i];
/* 106 */       if ((vo.getPos().intValue() == 1) && (vo.getBasetab() == null)) {
/* 107 */         list.add(vo);
/*     */       }
/*     */     }
/* 110 */     if (list.size() == 0)
/* 111 */       return null;
/* 112 */     BillTabVO[] vos = (BillTabVO[])list.toArray(new BillTabVO[list.size()]);
/*     */     
/*     */ 
/* 115 */     return vos;
/*     */   }
/*     */   
/*     */   private void setItemValue(IBillItemMeta item, ISuperVO superVO, Object value)
/*     */   {
/* 120 */     if (value == null) {
/* 121 */       return;
/*     */     }
/*     */     
/* 124 */     Object billObject = value;
/* 125 */     if (item != null)
/*     */     {
/* 127 */       switch (item.getDataType())
/*     */       {
/*     */       case 6: 
/* 130 */         if (value != null) {
/* 131 */           long begin = System.currentTimeMillis();
/* 132 */           Logger.debug("单据项:" + item.getKey() + " COMBO匹配开始");
/* 133 */           billObject = new DefaultConstEnum(value, value.toString());
/* 134 */           DefaultConstEnum[] enumData = ComboBoxBillItemUtil.getInstance().getComboxItems(item);
/*     */           
/* 136 */           if (enumData != null) {
/* 137 */             for (int i = 0; i < enumData.length; i++) {
/* 138 */               if (value.toString().equals(enumData[i].getValue().toString()))
/*     */               {
/* 140 */                 billObject = enumData[i]; }
/*     */             }
/*     */           }
/* 143 */           long end = System.currentTimeMillis();
/* 144 */           Logger.debug("单据项:" + item.getKey() + " COMBO匹配结束，共花费：" + (end - begin) + "Ms");
/*     */         }
/* 146 */         break;
/*     */       
/*     */ 
/*     */ 
/*     */       case 5: 
/* 151 */         if ((item.getMetaDataProperty() == null) && (item.getRefType() != null))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 156 */           String refNodeName = item.getRefType().split(",")[0];
/*     */           
/* 158 */           AbstractRefModel model = BillAccessUtil.getInstance().getRefModel(refNodeName);
/*     */           
/* 160 */           if (model == null)
/*     */           {
/* 162 */             model = RefPubUtil.getRefModel(refNodeName);
/* 163 */             BillAccessUtil.getInstance().putRefMOdel(refNodeName, model);
/*     */           }
/*     */           
/*     */ 
/* 167 */           this.refBatchMatch.addMatchData(item, superVO, value, model);
/*     */         }
/*     */         else {
/* 170 */           billObject = new DefaultConstEnum(value, value.toString());
/* 171 */           superVO.setAttributeValue(item.getKey() + "_ID", value.toString());
/*     */         }
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
/* 186 */         break;
/*     */       
/*     */ 
/*     */       case 7: 
/* 190 */         if ((item.getMetaDataProperty() == null) && (item.getRefType() != null))
/*     */         {
/*     */ 
/*     */ 
/* 194 */           String refNodeName = item.getRefType().split(",")[0];
/*     */           
/* 196 */           AbstractRefModel model = BillAccessUtil.getInstance().getRefModel(refNodeName);
/*     */           
/* 198 */           if (model == null)
/*     */           {
/* 200 */             model = RefPubUtil.getRefModel(refNodeName);
/* 201 */             BillAccessUtil.getInstance().putRefMOdel(refNodeName, model);
/*     */           }
/*     */           
/*     */ 
/* 205 */           this.refBatchMatch.addMatchData(item, superVO, value, model); }
/* 206 */         break;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       case 17: 
/* 212 */         if ((value instanceof MultiLangText)) {
/* 213 */           billObject = value.toString();
/* 214 */           if (billObject == null) {
/* 215 */             billObject = ((MultiLangText)value).getText();
/*     */           }
/*     */         }
/*     */         
/*     */         break;
/*     */       case 4: 
/* 221 */         if ((value instanceof nc.vo.pub.lang.UFBoolean)) {
/* 222 */           billObject = value.toString();
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 228 */       superVO.setAttributeValue(item.getKey(), billObject);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void loadLoadRelation(IBillItemMeta[] items, ISuperVO superVO, NCObject ncObject)
/*     */   {
/* 237 */     for (int i = 0; i < items.length; i++)
/*     */     {
/*     */ 
/* 240 */       loadLoadRelation(items[i], superVO, ncObject);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void loadLoadRelation(IBillItemMeta item, ISuperVO superVO, NCObject ncObject)
/*     */   {
/* 247 */     if ((item.getDataType() == 5) && (item.getMetaDataProperty() != null))
/*     */     {
/*     */ 
/* 250 */       ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(item);
/*     */       
/* 252 */       if (relationitem != null)
/*     */       {
/*     */ 
/* 255 */         IConstEnum idConstEnum = (IConstEnum)superVO.getAttributeValue(item.getKey());
/*     */         
/* 257 */         String id = null;
/* 258 */         if (idConstEnum != null) {
/* 259 */           id = (String)idConstEnum.getValue();
/*     */         }
/*     */         
/*     */ 
/* 263 */         if (id != null)
/*     */         {
/* 265 */           GetMeteDataRelationItemVaule getBillRelationItemValue = new GetMeteDataRelationItemVaule(item.getMetaDataProperty().getRefBusinessEntity());
/*     */           
/* 267 */           IConstEnum[] o = getBillRelationItemValue.getRelationItemValue(relationitem, new String[] { id });
/*     */           
/*     */ 
/* 270 */           if (o != null) {
/* 271 */             for (int i = 0; i < o.length; i++) {
/* 272 */               if (o[i].getValue() != null) {
/* 273 */                 Object[] v = (Object[])o[i].getValue();
/*     */                 
/*     */ 
/*     */ 
/*     */ 
/* 278 */                 Object oldValue = superVO.getAttributeValue(((IConstEnum)relationitem.get(i)).getName());
/*     */                 
/*     */ 
/* 281 */                 if ((oldValue != null) && ((oldValue instanceof DefaultConstEnum)))
/*     */                 {
/* 283 */                   Object pk = ((DefaultConstEnum)oldValue).getValue();
/*     */                   
/* 285 */                   String name = v[0] == null ? null : v[0].toString();
/*     */                   
/* 287 */                   DefaultConstEnum newValue = new DefaultConstEnum(pk, name);
/*     */                   
/* 289 */                   superVO.setAttributeValue(((IConstEnum)relationitem.get(i)).getName(), newValue);
/*     */                 }
/*     */                 else {
/* 292 */                   superVO.setAttributeValue(((IConstEnum)relationitem.get(i)).getName(), v[0]);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
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
/*     */   private void loadLoadRelation(String itemkey, ISuperVO superVO, NCObject ncObject)
/*     */   {
/* 315 */     IBillItemMeta item = getBillVO().getbillItemMeta(itemkey);
/*     */     
/* 317 */     if ((item.getDataType() == 5) && (item.getMetaDataProperty() != null))
/*     */     {
/*     */ 
/* 320 */       ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(item);
/*     */       
/* 322 */       if (relationitem != null)
/*     */       {
/*     */ 
/* 325 */         IConstEnum idConstEnum = (IConstEnum)superVO.getAttributeValue(itemkey);
/*     */         
/* 327 */         String id = null;
/* 328 */         if (idConstEnum != null) {
/* 329 */           id = (String)idConstEnum.getValue();
/*     */         }
/*     */         
/*     */ 
/* 333 */         if (id != null)
/*     */         {
/* 335 */           GetMeteDataRelationItemVaule getBillRelationItemValue = new GetMeteDataRelationItemVaule(item.getMetaDataProperty().getRefBusinessEntity());
/*     */           
/* 337 */           IConstEnum[] o = getBillRelationItemValue.getRelationItemValue(relationitem, new String[] { id });
/*     */           
/*     */ 
/* 340 */           if (o != null) {
/* 341 */             for (int i = 0; i < o.length; i++) {
/* 342 */               if (o[i].getValue() != null) {
/* 343 */                 Object[] v = (Object[])o[i].getValue();
/*     */                 
/*     */ 
/*     */ 
/*     */ 
/* 348 */                 Object oldValue = superVO.getAttributeValue(((IConstEnum)relationitem.get(i)).getName());
/*     */                 
/*     */ 
/* 351 */                 if ((oldValue != null) && ((oldValue instanceof DefaultConstEnum)))
/*     */                 {
/* 353 */                   Object pk = ((DefaultConstEnum)oldValue).getValue();
/*     */                   
/* 355 */                   String name = v[0] == null ? null : v[0].toString();
/*     */                   
/* 357 */                   DefaultConstEnum newValue = new DefaultConstEnum(pk, name);
/*     */                   
/* 359 */                   superVO.setAttributeValue(((IConstEnum)relationitem.get(i)).getName(), newValue);
/*     */                 }
/*     */                 else {
/* 362 */                   superVO.setAttributeValue(((IConstEnum)relationitem.get(i)).getName(), v[0]);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BillVO getBillVOByMetaData()
/*     */   {
/* 379 */     if ((this.billTempletVO == null) || (this.oData == null)) {
/* 380 */       return null;
/*     */     }
/* 382 */     long begin = System.currentTimeMillis();
/* 383 */     Logger.debug("业务数据开始转成BIllVO");
/*     */     
/* 385 */     IBusinessEntity be = this.billTempletVO.getHeadVO().getBillMetaDataBusinessEntity();
/*     */     
/*     */ 
/* 388 */     BillItemMeta[] itemMetas = getHeadTailItemMetas();
/* 389 */     BillTabVO[] tabvos = getBaseBodyTabVos();
/*     */     
/* 391 */     if (itemMetas != null) {
/* 392 */       NCObject ncobject = null;
/*     */       
/* 394 */       long ojbectBegin = System.currentTimeMillis();
/* 395 */       Logger.debug("构造表头表尾NCObject开始");
/*     */       
/* 397 */       if (be.getBeanStyle().getStyle() == BeanStyleEnum.AGGVO_HEAD) {
/* 398 */         ncobject = DASFacade.newInstanceWithContainedObject(be, this.oData);
/* 399 */       } else if ((be.getBeanStyle().getStyle() == BeanStyleEnum.NCVO) || (be.getBeanStyle().getStyle() == BeanStyleEnum.POJO))
/*     */       {
/* 401 */         if ((this.oData instanceof AggregatedValueObject)) {
/* 402 */           this.oData = ((AggregatedValueObject)this.oData).getParentVO();
/* 403 */           ncobject = DASFacade.newInstanceWithContainedObject(be, this.oData);
/*     */         }
/*     */         else {
/* 406 */           ncobject = DASFacade.newInstanceWithContainedObject(be, this.oData);
/*     */         }
/*     */       }
/*     */       
/* 410 */       long objectEnd = System.currentTimeMillis();
/* 411 */       Logger.debug("构造表头表尾NCObject结束，共花费" + (objectEnd - ojbectBegin) + "ms");
/*     */       
/*     */ 
/* 414 */       ISuperVO parentVO = new BillDataVO();
/*     */       
/* 416 */       getBillVO().setParentVO(parentVO);
/*     */       
/*     */ 
/* 419 */       dealWithHeadTailVO(itemMetas, ncobject, parentVO);
/*     */       
/*     */ 
/* 422 */       if (tabvos != null) {
/* 423 */         long begin1 = System.currentTimeMillis();
/* 424 */         Logger.debug("设置表体数据开始");
/* 425 */         Logger.debug("表体共" + tabvos.length + "个页签的数据要处理");
/* 426 */         for (int i = 0; i < tabvos.length; i++) {
/* 427 */           BillTabVO tabVO = tabvos[i];
/*     */           
/* 429 */           NCObject[] ncos = (NCObject[])ncobject.getAttributeValue(tabVO.getMetadatapath());
/*     */           
/*     */ 
/* 432 */           if (ncos != null)
/*     */           {
/* 434 */             dealWithChildVOsByTab(tabVO, ncos);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 439 */         long end1 = System.currentTimeMillis();
/* 440 */         Logger.debug("设置表体数据结束,共花费" + (end1 - begin1) + "ms");
/*     */       }
/*     */     }
/*     */     else {
/* 444 */       long begin1 = System.currentTimeMillis();
/* 445 */       Logger.debug("设置表体数据开始");
/* 446 */       Logger.debug("表体共" + tabvos.length + "个页签的数据要处理");
/* 447 */       Object[] vos = null;
/* 448 */       if ((this.oData instanceof AggregatedValueObject)) {
/* 449 */         vos = ((AggregatedValueObject)this.oData).getChildrenVO();
/* 450 */       } else if (this.oData.getClass().isArray()) {
/* 451 */         vos = (Object[])this.oData;
/*     */       }
/* 453 */       if ((vos != null) && (tabvos != null) && (tabvos[0].getBillMetaDataBusinessEntity() != null))
/*     */       {
/*     */ 
/* 456 */         NCObject[] ncos = new NCObject[vos.length];
/*     */         
/* 458 */         for (int i = 0; i < ncos.length; i++) {
/* 459 */           ncos[i] = DASFacade.newInstanceWithContainedObject(tabvos[0].getBillMetaDataBusinessEntity(), vos[i]);
/*     */         }
/*     */         
/*     */ 
/* 463 */         dealWithChildVOsByTab(tabvos[0], ncos);
/*     */       }
/* 465 */       long end1 = System.currentTimeMillis();
/* 466 */       Logger.debug("设置表体数据结束,共花费" + (end1 - begin1) + "ms");
/*     */     }
/* 468 */     long end = System.currentTimeMillis();
/* 469 */     Logger.debug("业务数据转成BIllVO结束，共花费:" + (end - begin) + "ms");
/* 470 */     return getBillVO();
/*     */   }
/*     */   
/*     */   private void executeFormula(IBillItemMeta[] itemMetas, ISuperVO[] vos)
/*     */   {
/* 475 */     if (vos == null) {
/* 476 */       return;
/*     */     }
/*     */     
/* 479 */     FormulaProc formulaProc = new FormulaProc();
/* 480 */     formulaProc.execFormulasWithVO(vos, FormulaUtil.getAllLoadFormulars(itemMetas), itemMetas);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void dealWithHeadTailVO(BillItemMeta[] itemMetas, NCObject ncobject, ISuperVO parentVO)
/*     */   {
/* 487 */     long begin = System.currentTimeMillis();
/* 488 */     Logger.debug("设置表头表尾数据开始");
/*     */     
/* 490 */     Logger.debug("给表头表尾数据赋值参照匹配开始");
/* 491 */     this.refBatchMatch.clear();
/* 492 */     loadLoadValue(itemMetas, parentVO, ncobject);
/* 493 */     this.refBatchMatch.executeBatch();
/* 494 */     long end1 = System.currentTimeMillis();
/* 495 */     Logger.debug("给表头表尾数据赋值参照匹配结束,共花费" + (end1 - begin) + "ms");
/*     */     
/* 497 */     Logger.debug("表头表尾数据加载关联项开始");
/*     */     
/* 499 */     loadLoadRelation(itemMetas, getBillVO().getParentVO(), ncobject);
/* 500 */     long end2 = System.currentTimeMillis();
/* 501 */     Logger.debug("表头表尾数据加载关联项结束,共花费" + (end2 - end1) + "ms");
/*     */     
/* 503 */     Logger.debug("表头表尾数据加载公式开始");
/* 504 */     executeFormula(itemMetas, new ISuperVO[] { parentVO });
/* 505 */     long end3 = System.currentTimeMillis();
/* 506 */     Logger.debug("表头表尾数据加载公式结束,共花费" + (end3 - end2) + "ms");
/*     */     
/* 508 */     Logger.debug("设置表头表尾数始结束,共花费" + (end3 - begin) + "ms");
/*     */   }
/*     */   
/*     */   private void dealWithChildVOsByTab(BillTabVO tabVO, NCObject[] ncos)
/*     */   {
/* 513 */     IBillItemMeta[] tabItemMetas = getBillVO().getBodyBillItemMeta(tabVO.getTabcode());
/*     */     
/* 515 */     ISuperVO[] childVOs = new BillDataVO[ncos.length];
/* 516 */     getBillVO().setChildrenVO(tabVO.getTabcode(), childVOs);
/*     */     
/* 518 */     long begin = System.currentTimeMillis();
/* 519 */     Logger.debug("设置表体页签" + tabVO.getTabname() + "数据开始");
/* 520 */     Logger.debug("给表体数据赋值参照匹配开始");
/* 521 */     this.refBatchMatch.clear();
/* 522 */     for (int j = 0; j < ncos.length; j++) {
/* 523 */       childVOs[j] = new BillDataVO();
/* 524 */       loadLoadValue(tabItemMetas, childVOs[j], ncos[j]);
/*     */     }
/*     */     
/* 527 */     this.refBatchMatch.executeBatch();
/*     */     
/* 529 */     long end1 = System.currentTimeMillis();
/* 530 */     Logger.debug("给表体数据赋值参照匹配结束,共花费" + (end1 - begin) + "ms");
/*     */     
/* 532 */     Logger.debug("表体数据加载关联项开始");
/* 533 */     loadLoadRelationItemValues(tabItemMetas, childVOs, ncos);
/* 534 */     long end2 = System.currentTimeMillis();
/* 535 */     Logger.debug("表体数据加载关联项结束,共花费" + (end2 - end1) + "ms");
/*     */     
/* 537 */     Logger.debug("表体数据加载公式开始");
/* 538 */     executeFormula(tabItemMetas, childVOs);
/*     */     
/* 540 */     long end3 = System.currentTimeMillis();
/* 541 */     Logger.debug("表体数据加载公式结束,共花费" + (end3 - end2) + "ms");
/*     */     
/* 543 */     Logger.debug("设置表体页签" + tabVO.getTabname() + "数据结束,共花费" + (end3 - begin) + "ms");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void loadLoadRelationItemValues(IBillItemMeta[] tabItemMetas, ISuperVO[] superVOs, NCObject[] ncos)
/*     */   {
/* 550 */     BatchGetMeteDataRelationItemVaules gvs = new BatchGetMeteDataRelationItemVaules();
/*     */     
/* 552 */     for (int col = 0; col < tabItemMetas.length; col++) {
/* 553 */       IBillItemMeta item = tabItemMetas[col];
/*     */       
/* 555 */       if ((item.getDataType() == 5) && (item.getMetaDataProperty() != null))
/*     */       {
/*     */ 
/* 558 */         ArrayList<IConstEnum> relationitem = getMetaDataRelationItems(item);
/*     */         
/* 560 */         if ((relationitem != null) && (superVOs.length > 0))
/*     */         {
/* 562 */           String[] ids = new String[superVOs.length];
/*     */           
/* 564 */           for (int row = 0; row < superVOs.length; row++) {
/* 565 */             Object o = superVOs[row].getAttributeValue(item.getKey());
/*     */             
/*     */ 
/* 568 */             if ((o != null) && ((o instanceof IConstEnum))) {
/* 569 */               ids[row] = ((String)((IConstEnum)o).getValue());
/*     */             }
/*     */           }
/* 572 */           gvs.addRelationItem(item, relationitem, ids);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 578 */     IConstEnum[] o = gvs.getRelationItemValues();
/*     */     
/* 580 */     if (o == null) {
/* 581 */       return;
/*     */     }
/*     */     
/* 584 */     for (int row = 0; row < superVOs.length; row++) {
/* 585 */       if (o != null) {
/* 586 */         for (int i = 0; i < o.length; i++) {
/* 587 */           if (o[i].getValue() != null) {
/* 588 */             Object[] v = (Object[])o[i].getValue();
/* 589 */             Object oldValue = superVOs[row].getAttributeValue(o[i].getName());
/*     */             
/* 591 */             if ((oldValue != null) && ((oldValue instanceof DefaultConstEnum)))
/*     */             {
/* 593 */               Object pk = ((DefaultConstEnum)oldValue).getValue();
/*     */               
/* 595 */               String name = v[row] == null ? null : v[row].toString();
/*     */               
/* 597 */               DefaultConstEnum newValue = new DefaultConstEnum(pk, name);
/*     */               
/* 599 */               superVOs[row].setAttributeValue(o[i].getName(), newValue);
/*     */             }
/*     */             else {
/* 602 */               superVOs[row].setAttributeValue(o[i].getName(), v[row]);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void loadLoadValue(IBillItemMeta[] itemMetas, ISuperVO superVO, NCObject ncobject)
/*     */   {
/* 615 */     for (int i = 0; i < itemMetas.length; i++)
/*     */     {
/*     */ 
/* 618 */       if ("ibodyrowcount".equals(itemMetas[i].getKey())) {
/* 619 */         superVO.setAttributeValue(itemMetas[i].getKey(), Integer.valueOf(getBodyRowAcount()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 624 */       else if (itemMetas[i].getMetaDataProperty() != null)
/*     */       {
/* 626 */         if (itemMetas[i].getIDColName() == null) {
/* 627 */           setItemValue(itemMetas[i], superVO, ncobject.getAttributeValue(itemMetas[i].getMetaDataProperty().getAttribute()));
/*     */         }else{
					setItemValue(itemMetas[i], superVO, ncobject.getAttributeValue(itemMetas[i].getKey()));
				  }
/*     */       }
/*     */       else {
/* 631 */         superVO.setAttributeValue(itemMetas[i].getKey(), ncobject.getAttributeValue(itemMetas[i].getKey()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private BillTempletVO getBillTempletVO()
/*     */   {
/* 638 */     return this.billTempletVO;
/*     */   }
/*     */   
/*     */   private ArrayList<IConstEnum> getMetaDataRelationItems(IBillItemMeta item)
/*     */   {
/* 643 */     if (item.getDataType() != 5) {
/* 644 */       return null;
/*     */     }
/* 646 */     if (item.getMetaDataProperty() == null) {
/* 647 */       return null;
/*     */     }
/* 649 */     ArrayList<IConstEnum> ics = new ArrayList();
/*     */     
/*     */ 
/* 652 */     if (item.getRelationItemMeta() != null) {
/* 653 */       for (int i = 0; i < item.getRelationItemMeta().size(); i++) {
/* 654 */         IBillItemMeta ritem = (IBillItemMeta)item.getRelationItemMeta().get(i);
/*     */         
/* 656 */         IConstEnum ic = new DefaultConstEnum(ritem.getMetadatapath(), ritem.getKey());
/*     */         
/* 658 */         ics.add(ic);
/*     */       }
/*     */     }
/* 661 */     IConstEnum ic = getItemSelfRelationItem(item);
/* 662 */     if (ic != null) {
/* 663 */       ics.add(ic);
/*     */     }
/* 665 */     if (ics.size() == 0) {
/* 666 */       ics = null;
/*     */     }
/* 668 */     return ics;
/*     */   }
/*     */   
/*     */ 
/*     */   private IConstEnum getItemSelfRelationItem(IBillItemMeta item)
/*     */   {
/* 674 */     String showattname = getRefItemShowAttributeName(item);
/* 675 */     IConstEnum ic = null;
/* 676 */     if (showattname != null) {
/* 677 */       ic = new DefaultConstEnum(showattname, item.getKey());
/*     */     }
/*     */     
/* 680 */     return ic;
/*     */   }
/*     */   
/*     */   private String getRefItemShowAttributeName(IBillItemMeta item) {
/* 684 */     String showattname = item.getMetaDataProperty().getBDNameAttributeName();
/*     */     
/*     */ 
/*     */ 
/* 688 */     return showattname;
/*     */   }
/*     */   
/*     */   private BillVO getBillVO() {
/* 692 */     if (this.billVO == null) {
/* 693 */       this.billVO = new BillVO(getBillTempletVO());
/*     */     }
/* 695 */     return this.billVO;
/*     */   }
/*     */   
/*     */   public Map<String, List<Map<String, Object>>> billVO2Map(String billtype) {
/* 699 */     BillVO billVO = getBillVOByMetaData();
/* 700 */     if (billVO == null) {
/* 701 */       return null;
/*     */     }
/*     */     
/* 704 */     Map<String, List<Map<String, Object>>> map = new LinkedHashMap();
/*     */     
/*     */ 
/*     */ 
/* 708 */     putHeadTailData(billVO, map, 0,billtype);
/*     */     
/* 710 */     putHeadTailData(billVO, map, 2,billtype);
/*     */     
/*     */ 
/* 713 */     putBodyData(billVO, map,billtype);
/*     */     
/* 715 */     return map;
/*     */   }
/*     */   
/*     */   private void putHeadTailData(BillVO billVO, Map<String, List<Map<String, Object>>> map, int pos,String billtype)
/*     */   {
/* 720 */     ISuperVO headTailVO = billVO.getParentVO();
/* 721 */     BillTabVO[] tabVOs = billVO.getTabVOPos(pos);
/* 722 */     BillAccessUtil accessUtil = BillAccessUtil.getInstance();
/* 723 */     List<Map<String, Object>> list = new ArrayList();
/*     */     
/* 725 */     for (int i = 0; i < tabVOs.length; i++) {
/* 726 */       if (tabVOs[i].getBasetab() == null) {
/* 727 */         Map<String, Object> tabMap = new LinkedHashMap();
/* 728 */         tabMap.put("tabCode", tabVOs[i].getTabcode());
/* 729 */         tabMap.put("tabName", tabVOs[i].getTabname());
/* 730 */         tabMap.put("tabContent", accessUtil.getTabDataMap(billVO, tabVOs[i], headTailVO,billtype));
/*     */         
/* 732 */         list.add(tabMap);
/*     */       }
/*     */     }
/* 735 */     String key = pos == 0 ? "head" : "tail";
/*     */     
/* 737 */     map.put(key, list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void putBodyData(BillVO billVO, Map<String, List<Map<String, Object>>> map,String billtype)
/*     */   {
/* 744 */     BillTabVO[] tabVOs = billVO.getTabVOPos(1);
/*     */     
/* 746 */     BillAccessUtil accessUtil = BillAccessUtil.getInstance();
/* 747 */     List<Map<String, Object>> list = new ArrayList();
/*     */     
/* 749 */     for (int i = 0; i < tabVOs.length; i++)
/*     */     {
/* 751 */       Map<String, Object> tabMap = new LinkedHashMap();
/* 752 */       tabMap.put("tabCode", tabVOs[i].getTabcode());
/* 753 */       tabMap.put("tabName", tabVOs[i].getTabname());
/* 754 */       tabMap.put("tabContent", accessUtil.getTabDataMap(billVO, tabVOs[i], billVO.getChildrenVO(tabVOs[i].getTabcode()),billtype));
/*     */       
/*     */ 
/*     */ 
/* 758 */       list.add(tabMap);
/*     */     }
/*     */     
/* 761 */     map.put("body", list);
/*     */   }
/*     */ }

