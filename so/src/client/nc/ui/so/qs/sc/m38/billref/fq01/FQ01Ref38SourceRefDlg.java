package nc.ui.so.qs.sc.m38.billref.fq01;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import nc.ui.pub.pf.BillSourceVar;
/*    */ import nc.ui.pubapp.billref.src.view.SourceRefDlg;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FQ01Ref38SourceRefDlg extends SourceRefDlg {
/*    */	
/*    */   private static final long serialVersionUID = 3581432494198863325L;
/*    */   
/*    */   public FQ01Ref38SourceRefDlg(Container parent, BillSourceVar bsVar)
/*    */   {
/* 15 */     super(parent, bsVar);
/*    */   }
/*    */   
/*    */ 
/*    */   public String getRefBillInfoBeanPath()
/*    */   {
/* 21 */     return "nc/ui/so/qs/sc/m38/billref/fq01/FQ01RefM38Info.xml";
/*    */   }
/*    */ }