package nc.ui.so.qs.sc.maschine.service;
/*    */ 
/*    */ import nc.bs.framework.common.NCLocator;
///*    */ import nc.itf.pd.pd0404.IPDWkMaintain;
/*    */ import nc.ui.pubapp.pub.task.ISingleBillService;
///*    */ import nc.vo.pd.pd0404.entity.AggWkVO;
		import nc.itf.so.qs.sc.maschine.service.IMaschineMaintain;
		import nc.vo.so.qs.sc.AggMaschineVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineDeleteService
/*    */   implements ISingleBillService<AggMaschineVO>
/*    */ {
/*    */   public MaschineDeleteService() {}
/*    */   
/*    */   public AggMaschineVO operateBill(AggMaschineVO bill)
/*    */     throws Exception
/*    */   {
/* 20 */     IMaschineMaintain operator = (IMaschineMaintain)NCLocator.getInstance().lookup(IMaschineMaintain.class);
/* 21 */     operator.delete(new AggMaschineVO[] { bill });
/*    */     
/*    */ 
/* 24 */     return bill;
/*    */   }
/*    */ }