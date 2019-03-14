package nc.ui.so.qs.sc.maschine.service;
/*    */ 
/*    */ import nc.bs.bd.service.ValueObjWithErrLog;
///*    */ import nc.itf.pd.pd0404.IPDWkMaintain;
/*    */ import nc.ui.pubapp.uif2app.actions.IDataOperationService;
///*    */ import nc.vo.pd.pd0404.entity.AggWkVO;
/*    */ import nc.vo.pub.BusinessException;
/*    */ import nc.vo.pubapp.pattern.model.entity.bill.IBill;
		import nc.itf.so.qs.sc.maschine.service.IMaschineMaintain;
		import nc.vo.so.qs.sc.AggMaschineVO;
		import nc.bs.framework.common.NCLocator;
import nc.vo.so.qs.sc.MaschineVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineMaintainService
/*    */   implements IDataOperationService
/*    */ {
/*    */   public MaschineMaintainService() {}
/*    */   
/*    */   public IBill[] insert(IBill[] value) throws BusinessException {
/* 22 */     IMaschineMaintain operator = (IMaschineMaintain)NCLocator.getInstance().lookup(IMaschineMaintain.class);
/* 23 */     AggMaschineVO[] vos = operator.insert((AggMaschineVO[])value);
/* 24 */     return vos;
/*    */   }
/*    */   
/*    */   public IBill[] update(IBill[] value) throws BusinessException {
/* 29 */     IMaschineMaintain operator = (IMaschineMaintain)NCLocator.getInstance().lookup(IMaschineMaintain.class);
/* 30 */     AggMaschineVO[] vos = operator.update((AggMaschineVO[])value);
/* 31 */     return vos;
/*    */   }
/*    */   
/*    */   public IBill[] delete(IBill[] value) throws BusinessException {
/* 36 */     IMaschineMaintain operator = (IMaschineMaintain)NCLocator.getInstance().lookup(IMaschineMaintain.class);
/* 37 */     operator.delete((AggMaschineVO[])value);
/* 38 */     return value;
/*    */   }

			public IBill MaschineEnable(Object obj) throws BusinessException {
				IMaschineMaintain operator = (IMaschineMaintain)NCLocator.getInstance().lookup(IMaschineMaintain.class);
				AggMaschineVO vos = operator.enable((AggMaschineVO)obj);
				return vos;
			}
			
			public IBill Maschineunenable(Object obj) throws BusinessException {
				IMaschineMaintain operator = (IMaschineMaintain)NCLocator.getInstance().lookup(IMaschineMaintain.class);
				AggMaschineVO vos = operator.unable((AggMaschineVO)obj);
				return vos;
			}			
			public IBill MaschineDisable(Object obj) throws BusinessException {
				IMaschineMaintain operator = (IMaschineMaintain)NCLocator.getInstance().lookup(IMaschineMaintain.class);
				AggMaschineVO vos = operator.disable((AggMaschineVO)obj);
				return vos;
			}
/*    */ }