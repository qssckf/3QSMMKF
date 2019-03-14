package nc.ui.so.shipmentsinfo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38.IQueryRelationOrg;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
//import nc.ui.so.m38.billui.pub.BodyDefaultRule;
//import nc.ui.so.m38.billui.pub.ClearBodyValueRule;
//import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.qs.sc.shipments.billui.pub.BodyDefaultRule;
import nc.ui.so.qs.sc.shipments.billui.pub.ClearBodyValueRule;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
//import nc.vo.so.m38.keyrela.PreOrderKeyrela;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SOUnitDefaultRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.qs.sc.FQ01.keyrela.ShipmentsKeyrela;

public class MaterialEditHandler
{
  public MaterialEditHandler() {}
  
  public void afterEdit(CardBodyAfterEditEvent e)
  {
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
    int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);
    
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    
    //清除表体行
    ClearBodyValueRule cbvr = new ClearBodyValueRule(keyValue);
    cbvr.clearBodyValue(rows);
    

    SOUnitDefaultRule unitdef = new SOUnitDefaultRule(keyValue);
    unitdef.setDefaultSaleUnit(rows);
    
    SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
    for (int row : rows) {
      unitrate.calcAstChangeRate(row);
      unitrate.calcQtChangeRate(row);
    }
    //设置默认值
    BodyDefaultRule defrule = new BodyDefaultRule(keyValue);
    defrule.setBodyDefaultValue(rows);
    

    SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
    custrefrule.setRelaReceiveCust(rows);
    
    //收货地点
    IKeyRela keyRela = new ShipmentsKeyrela();
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue, keyRela);
    defaddrule.setCustDefaultAddress(rows);
    
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceStockTrafficOrg(rows, GetRelationOrg(keyValue, rows));
    


    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    
    SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
    changeraterule.calcBodyExchangeRates(rows);
    
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);
    
    SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
    globalerate.calcGlobalExchangeRate(rows);
    

    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);
    

    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    

    ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, "cmaterialvid");
    

    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
    
    cardPanel.getBodyItem("vfree1").setEnabled(true);
    cardPanel.getBodyItem("vfree2").setEnabled(true);
    cardPanel.getBodyItem("vfree3").setEnabled(true);
  }
  




  public void beforeEdit(CardBodyBeforeEditEvent e)
  {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    
    Integer billstatus = keyValue.getHeadIntegerValue("fstatusflag");
    int row = e.getRow();
    UFDouble narrnum = keyValue.getBodyUFDoubleValue(row, "narrnum");
    
    if ((BillStatus.AUDIT.equalsValue(billstatus)) && (!MathTool.isZero(narrnum))) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    
    UFBoolean bclose = keyValue.getBodyUFBooleanValue(row, "blineclose");
    
    if ((null != bclose) && (bclose.booleanValue())) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    
    UIRefPane refPane = (UIRefPane)cardPanel.getBodyItem("cmaterialvid").getComponent();
    

    refPane.setMultiSelectedEnabled(true);
    
    BillItem cmaterialvid = cardPanel.getBodyItem("cmaterialvid");
    FilterMaterialRefUtils utils = new FilterMaterialRefUtils((UIRefPane)cmaterialvid.getComponent());
    
    String pk_org = keyValue.getHeadStringValue("pk_org");
    utils.filterItemRefByOrg(pk_org);
    e.setReturnValue(Boolean.TRUE);
  }

  private Map<String, String[]> GetRelationOrg(IKeyValue keyValue, int[] rows)
  {
    Map<String, String[]> hmRelationOrgid = null;
    
    String pk_org = keyValue.getHeadStringValue("pk_org");
    String ccustomerid = keyValue.getHeadStringValue("ccustomerid");
    
    List<String> alMaterialid = new ArrayList();
    
    for (int row : rows) {
      String cmaterialid = keyValue.getBodyStringValue(row, "cmaterialid");
      
      if (!PubAppTool.isNull(cmaterialid))
      {

        alMaterialid.add(cmaterialid); }
    }
    if (alMaterialid.size() == 0) {
      return null;
    }
    
    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);
    

    try
    {
      IQueryRelationOrg service = (IQueryRelationOrg)NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      
      hmRelationOrgid = service.querySaleRelationOrg(ccustomerid, pk_org, cmaterialids);

    }
    catch (BusinessException e1)
    {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }
}
