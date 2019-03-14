package nc.ui.so.fq01.billref.m30;
 
 import nc.ui.pub.bill.BillListPanel;
 import nc.ui.pubapp.ClientContext;
 import nc.ui.so.m38.billui.pub.PreOrderPrecision;
 
 public class M30RefFQUIInit implements nc.ui.pubapp.billref.src.IRefPanelInit
 {
   public M30RefFQUIInit() {}
   
   public void refMasterPanelInit(BillListPanel masterPanel)
   {
     PreOrderPrecision.getInstance().setListPrecision(ClientContext.getInstance().getPk_group(), masterPanel);
   }
   
 
   public void refSinglePanelInit(BillListPanel singlePanel)
   {
     String pk_group = ClientContext.getInstance().getPk_group();
     
     PreOrderPrecision.getInstance().setSingleTableScale(pk_group, singlePanel);
   }
 }

