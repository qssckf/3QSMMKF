package nc.ui.so.qs.mmplanbill.readyplan.actions;
 
 import java.awt.Dimension;
 import java.awt.event.ActionEvent;
 import javax.swing.BorderFactory;
 import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
 import nc.ui.pub.beans.UIPanel;
 import nc.ui.pub.beans.UITable;
 import nc.ui.pub.bill.BillCardPanel;
 import nc.ui.pub.style.Style;
 import nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare;
 import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
 import nc.ui.scmpub.action.SCMActionInitializer;
 import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.qs.mmplanbill.readyplan.view.RdMmOrderBillFrom;
 import nc.ui.uif2.NCAction;
 import nc.ui.uif2.TangramContainer;
import nc.vo.uif2.LoginContext;
 
 
 
 public class SPShowHidAction
   extends NCAction
 {
   private static final long serialVersionUID = -2099832720860500161L;
   private RdMmOrderBillFrom card;
   private TangramContainer contain;
   private OnhandPanelAdaptor adaptor;
   private CompositeBillDataPrepare userdefitemPreparator;
   private LoginContext logincontext;
   
   public CompositeBillDataPrepare getUserdefitemPreparator()
   {
     return this.userdefitemPreparator;
   }
   
   public void setUserdefitemPreparator(CompositeBillDataPrepare userdefitemPreparator)
   {
     this.userdefitemPreparator = userdefitemPreparator;
   }
   
   public LoginContext getLogincontext() {
     return this.logincontext;
   }
   
   public void setLogincontext(LoginContext logincontext) {
     this.logincontext = logincontext;
   }
   
 
   public SPShowHidAction()
   {
     SCMActionInitializer.initializeAction(this, "OnhandInfo");
   }
   
 
 
 
   public void doAction(ActionEvent e)
     throws Exception
   {
     if (this.card.getExtendedPanel() == null) {
       if (!SysInitGroupQuery.isICEnabled()) {
         return;
       }
       OnhandPanelAdaptor adaptor = new OnhandPanelAdaptor("nc.ui.ic.onhand.pane.QueryOnHandInfoPanel");
       
       if (this.adaptor == null)
       {
         UIPanel refpanel = adaptor.getRefPane();
         this.adaptor = adaptor;
         adaptor.setOnhandPanelSrc(this.card);
         adaptor.setUserdefitemPreparator(this.userdefitemPreparator);
         adaptor.setLogincontext(this.logincontext);
         adaptor.initialize();
         
         refpanel.setPreferredSize(new Dimension(300, 180));
         refpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Style.getColor("系统按钮条.背景")));
         
         this.card.add(refpanel, "South");
         this.card.setExtendedPanel(adaptor);
       }
     }
     else {
       this.adaptor = this.card.getExtendedPanel();
     }
     
     UIPanel refpanel = this.adaptor.getRefPane();
     if (this.adaptor.isComponentDisplayable()) {
       refpanel.setVisible(false);
       this.adaptor.setIsComponentDisplayable(false);
     }
     else {
       refpanel.setVisible(true);
       this.adaptor.setIsComponentDisplayable(true);
     }
     
     this.contain.resetLayout();
     this.card.showMeUp();
     
     this.adaptor.freshData(0);
     

   }
   
   public RdMmOrderBillFrom getCard()
   {
     return this.card;
   }
   
   public TangramContainer getContain() {
     return this.contain;
   }
   
   public void setCard(RdMmOrderBillFrom card) {
     this.card = card;
   }
   
   public void setContain(TangramContainer contain) {
     this.contain = contain;
   }
 }

