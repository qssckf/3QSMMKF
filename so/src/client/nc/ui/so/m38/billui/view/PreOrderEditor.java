 package nc.ui.so.m38.billui.view;
 
 import java.util.List;
 import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
 import nc.ui.pub.beans.UILabel;
 import nc.ui.pub.beans.UIRefPane;
 import nc.ui.pub.bill.BillCardPanel;
 import nc.ui.pub.bill.BillData;
 import nc.ui.pub.bill.BillItem;
 import nc.ui.pub.bill.BillModel;
 import nc.ui.pub.bill.BillScrollPane;
 import nc.ui.pubapp.uif2app.event.IAppEventHandler;
 import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
 import nc.ui.pubapp.uif2app.model.IAppModelEx;
 import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
 import nc.ui.scmf.ic.batchcode.ref.ScmBatchAdaptor;
 import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
 import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
 import nc.ui.scmpub.ref.FilterCustomerRefUtils;
 import nc.ui.scmpub.util.BillCardPanelUtils;
 import nc.ui.so.m38.billui.pub.PreOrderCardEditSetter;
 import nc.ui.so.pub.keyvalue.CardKeyValue;
 import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.ui.so.qs.m38.view.BomInfoPanel;
 import nc.ui.uif2.model.AbstractAppModel;
 import nc.vo.pub.lang.UFBoolean;
 import nc.vo.pub.lang.UFDate;
 import nc.vo.pub.lang.UFDouble;
 import nc.vo.pubapp.AppContext;
 import nc.vo.pubapp.pattern.pub.PubAppTool;
 import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
 import nc.vo.so.pub.SOConstant;
 import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;
 
 
 
 public class PreOrderEditor
   extends ShowUpableBillForm
   implements OnhandPanelSrc
 {
   private static final String[] BODY_NOEDIT = { "ndiscountrate", "cunitid", "ccurrencyid", "nqttaxprice", "nqtprice", "nqttaxnetprice", "nqtnetprice", "ntaxprice", "nprice", "ntaxnetprice", "nnetprice", "ndiscount", "norigdiscount", "ngroupmny", "ngrouptaxmny", "nglobaltaxmny", "nglobalmny", "cpriceformid", "cpriceitemtableid", "cpricepolicyid", "naskqtorigtaxprc", "naskqtorigprice", "naskqtorigtxntprc", "naskqtorignetprice", "narrnum", "carrangeid", "darrdate", "frowstatus", "csettleorgid", "carorgid", "cprofitcenterid", "csendstockorgid", "ctrafficorgid", "fbuysellflag", "btriatradeflag", "ntaxmny", "nmny" };
   
   private static final int DEFDABATEDATE = 3;

   private static final String[] HEAD_NOEDIT = { "vbillcode", "ntotalnum", "nheadsummny", "fstatusflag", "cdeptid" };
   
 
   private static final long serialVersionUID = -4607945892107326567L;
   
 
   private OnhandPanelAdaptor adaptor;
   
 
   private List<String> clearHyperlink;
   
   private PreOrderCardEditSetter editsetter;
   
 
   public PreOrderEditor() {}
   
 
   public void addCardBodyRowChangedEvent(IAppEventHandler<CardBodyRowChangedEvent> l)
   {
     ((IAppModelEx)getModel()).addAppEventListener(CardBodyRowChangedEvent.class, l);
   }
   
   public List<String> getClearHyperlink()
   {
     return this.clearHyperlink;
   }
   
   public OnhandPanelAdaptor getExtendedPanel() {
     return this.adaptor;
   }
   
   public OnhandDimParamVO getQryOnhandDim(int row)
   {
     OnhandDimParamVO paravo = null;
     IKeyValue keyValue = new CardKeyValue(getBillCardPanel());
     String marterialvid = keyValue.getBodyStringValue(row, "cmaterialvid");
     
     if (PubAppTool.isNull(marterialvid)) {
       return paravo;
     }
     
     paravo = getOnhandDimParamVO(keyValue, row);
     return paravo;
   }
   
   public void initUI()
   {
     super.initUI();
     
     initEditEnable();
     if (SysInitGroupQuery.isICEnabled()) {
       ScmBatchAdaptor scmbach = new ScmBatchAdaptor("nc.ui.ic.batchcode.ref.BatchRefPane");
       
       UIRefPane uiref = scmbach.getRefPane();
       
       getBillCardPanel().getBodyItem("vbatchcode").setComponent(uiref);
     }
     
 
     clearHyperlink();
     
     initRefCondition();
     
 
     this.editsetter = new PreOrderCardEditSetter();
     this.editsetter.cacheEditEnable(getBillCardPanel());
     
     BillCardPanel cardPanel = getBillCardPanel();
     cardPanel.getBodyPanel().setTotalRowShow(true);
     IKeyValue keyValue = new CardKeyValue(cardPanel);
     SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
     cardPanel.getBillModel().addTotalListener(totallis);
     
     initFillEnabled(cardPanel);
   }
   
 
 
 
 
   private void initFillEnabled(BillCardPanel cardPanel)
   {
     BillCardPanelUtils util = new BillCardPanelUtils(cardPanel);
     util.disableItemsFill();
     util.enableItemsFill(SOConstant.FILLENABLEDKEY);
     
     for (int i = 1; i < 21; i++) {
       BillItem bodyitem = getBillCardPanel().getBodyItem("vbdef" + i);
       
       bodyitem.setFillEnabled(true);
     }
   }
   
   public void setCardEdit() {
     this.editsetter.setEditEnable(getBillCardPanel());
   }
   
   public void setClearHyperlink(List<String> clearHyperlink) {
     this.clearHyperlink = clearHyperlink;
   }
   
   public void setExtendedPanel(OnhandPanelAdaptor adaptor) {
     this.adaptor = adaptor;
   }
   
   public void setReviseEdit() {
     this.editsetter.setReviseCardEdit(getBillCardPanel());
   }
   
   protected void onAdd()
   {
     super.onAdd();
     if (isEditable()) {
       setCardEdit();
     }
   }
   
 
   protected void setDefaultValue()
   {
     IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
     int irowcount = this.billCardPanel.getRowCount();
     
 
     String pk_org = getModel().getContext().getPk_org();
     if (!PubAppTool.isNull(pk_org)) {
       keyValue.setHeadValue("pk_org", pk_org);
       this.billCardPanel.getBillData().loadEditHeadRelation("pk_org");
       
       String pk_group = AppContext.getInstance().getPkGroup();
       keyValue.setHeadValue("pk_group", pk_group);
       
       UFDate busidate = AppContext.getInstance().getBusiDate();
       keyValue.setHeadValue("dbilldate", busidate);
       
       keyValue.setHeadValue("dabatedate", busidate.getDateAfter(3).asLocalEnd());
       
 
 
       UFDate localend = busidate.asLocalEnd();
       
       for (int i = 0; i < irowcount; i++)
       {
         keyValue.setBodyValue(i, "pk_group", pk_group);
         
         keyValue.setBodyValue(i, "pk_org", pk_org);
         
         keyValue.setBodyValue(i, "dbilldate", busidate);
         
         keyValue.setBodyValue(i, "nitemdiscountrate", new UFDouble(100));
         
 
         keyValue.setBodyValue(i, "dsenddate", localend);
         keyValue.setBodyValue(i, "dreceivedate", localend);
       }
     }
   }
   
   private void clearHyperlink() {
     for (String key : getClearHyperlink()) {
       BillItem item = getBillCardPanel().getBillData().getHeadItem(key);
       item.getCaptionLabel().setHyperlinkLabel(false);
     }
   }
   
   private OnhandDimParamVO getOnhandDimParamVO(IKeyValue keyValue, int row) {
     OnhandDimParamVO paravo = new OnhandDimParamVO();
     
     String pk_group = AppContext.getInstance().getPkGroup();
     paravo.setPk_group(pk_group);
     
 
     String cmarterialvid = keyValue.getBodyStringValue(row, "cmaterialvid");
     
     paravo.setCmaterialvid(cmarterialvid);
     String cmarterialid = keyValue.getBodyStringValue(row, "cmaterialid");
     
     paravo.setCmaterialoid(cmarterialid);
     
     String castunitid = keyValue.getBodyStringValue(row, "castunitid");
     
     paravo.setCastunitid(castunitid);
     
 
     String vchangerate = keyValue.getBodyStringValue(row, "vchangerate");
     
     paravo.setVchangerate(vchangerate);
     
     String cproductorid = keyValue.getBodyStringValue(row, "cproductorid");
     
     paravo.setCproductorid(cproductorid);
     
     String cprojectid = keyValue.getBodyStringValue(row, "cprojectid");
     
     paravo.setCprojectid(cprojectid);
     
     String cvendorid = keyValue.getBodyStringValue(row, "cvendorid");
     paravo.setCvendorid(cvendorid);
     
 
     String vbatchcode = keyValue.getBodyStringValue(row, "vbatchcode");
     
     paravo.setVbatchcode(vbatchcode);
     
 
     String csendstockorgid = keyValue.getBodyStringValue(row, "csendstockorgid");
     
     paravo.setPk_org(csendstockorgid);
     
 
     String csendstockorgvid = keyValue.getBodyStringValue(row, "csendstockorgvid");
     
     paravo.setPk_org_v(csendstockorgvid);
     
 
     String cwarehouseid = keyValue.getBodyStringValue(row, "csendstordocid");
     
     paravo.setCwarehouseid(cwarehouseid);
     
 
     String vfree1 = keyValue.getBodyStringValue(row, "vfree1");
     paravo.setVfree1(vfree1);
     
     String vfree2 = keyValue.getBodyStringValue(row, "vfree2");
     paravo.setVfree1(vfree2);
     
     String vfree3 = keyValue.getBodyStringValue(row, "vfree3");
     paravo.setVfree1(vfree3);
     
     String vfree4 = keyValue.getBodyStringValue(row, "vfree4");
     paravo.setVfree1(vfree4);
     
     String vfree5 = keyValue.getBodyStringValue(row, "vfree5");
     paravo.setVfree1(vfree5);
     
     String vfree6 = keyValue.getBodyStringValue(row, "vfree6");
     paravo.setVfree1(vfree6);
     
     String vfree7 = keyValue.getBodyStringValue(row, "vfree7");
     paravo.setVfree1(vfree7);
     
     String vfree8 = keyValue.getBodyStringValue(row, "vfree8");
     paravo.setVfree1(vfree8);
     
     String vfree9 = keyValue.getBodyStringValue(row, "vfree9");
     paravo.setVfree1(vfree9);
     
     String vfree10 = keyValue.getBodyStringValue(row, "vfree10");
     paravo.setVfree1(vfree10);
     
     return paravo;
   }
   
 
 
 
   private void initEditEnable()
   {
     for (String key : HEAD_NOEDIT) {
       BillItem headitem = getBillCardPanel().getHeadTailItem(key);
       if (null != headitem) {
         headitem.setEdit(false);
       }
     }
     
     for (String key : BODY_NOEDIT) {
       BillItem bodyitem = getBillCardPanel().getBodyItem(key);
       if (null != bodyitem) {
         bodyitem.setEdit(false);
       }
     }
   }
   
 
   private void initRefCondition()
   {
     BillItem customeritem = getBillCardPanel().getHeadTailItem("ccustomerid");
     
     FilterCustomerRefUtils filterutils = new FilterCustomerRefUtils(customeritem);
     
     filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
     
     BillItem invoicecustomeritem = getBillCardPanel().getHeadTailItem("cinvoicecustid");
     
     FilterCustomerRefUtils invoicefilterutils = new FilterCustomerRefUtils(invoicecustomeritem);
     
     invoicefilterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
     
     BomInfoPanel bompanel=new BomInfoPanel(this);
     this.getBillCardPanel().getBodyItem("vbdef11").setComponent(bompanel);
     
   }
 }

