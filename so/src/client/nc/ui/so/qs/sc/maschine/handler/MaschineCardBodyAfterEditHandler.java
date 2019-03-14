package nc.ui.so.qs.sc.maschine.handler;
 
import nc.ui.mmf.framework.handler.MMBaseHandler;
import nc.ui.mmf.framework.handler.MMEventHandler;
//import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCastunitidHandler;
//import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCmaterialvidHandler;
//import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCmatericlassidHandler;
//import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyNtimeHandler;
//import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkProBodyVChangeRateHandler;
//import nc.ui.pd.pd0404.handler.tab_repltime.PDWkCardBodyCaftermatrelatshowHandler;
//import nc.ui.pd.pd0404.handler.tab_repltime.PDWkCardBodyCprematrelatshowHandler;
//import nc.ui.pd.pd0404.handler.tab_subst.PDWkCardBodyCsubstitutewkidHandler;
//import nc.ui.pd.pd0404.handler.tab_wip.PDWkCardBodyCstordocidHandler;
//import nc.ui.pd.pd0404.view.PDWkBillForm;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
 
import nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyCusclassHandler;
import nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyCustomerHandler;
import nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyDeptHandler;
import nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyMaterclassHandler;
import nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyMaterialHandler;
import nc.ui.so.qs.sc.maschine.view.MaschineBillForm;


public class MaschineCardBodyAfterEditHandler extends MMEventHandler implements IAppEventHandler<CardBodyAfterEditEvent> {
	
	private MaschineBillForm editor;
  
	public MaschineCardBodyAfterEditHandler() {}

	public void initMap() {
		putHandler("custpk_customer", MaschineCardBodyCustomerHandler.class);
		putHandler("custpk_cusclass", MaschineCardBodyCusclassHandler.class);
		putHandler("materpk_materclass", MaschineCardBodyMaterclassHandler.class);
		putHandler("materpk_material", MaschineCardBodyMaterialHandler.class);
		putHandler("deptpk_dept", MaschineCardBodyDeptHandler.class);
				
				
///* 26 */     putHandler("subst_itemscsubstitutewkid", PDWkCardBodyCsubstitutewkidHandler.class);
///*    */     
///* 28 */     putHandler("wip_itemscstordocid", PDWkCardBodyCstordocidHandler.class);
///*    */     
///*    */ 
///* 31 */     putHandler("prodinv_itemscastunitid", PDWkCardBodyCastunitidHandler.class);
///*    */     
///* 33 */     putHandler("prodinv_itemscmaterialclassid", PDWkCardBodyCmatericlassidHandler.class);
///*    */     
///* 35 */     putHandler("prodinv_itemsntime", PDWkCardBodyNtimeHandler.class);
///*    */     
///* 37 */     putHandler("replace_itemscprematrelatshow", PDWkCardBodyCprematrelatshowHandler.class);
///*    */     
///* 39 */     putHandler("replace_itemscaftermatrelatshow", PDWkCardBodyCaftermatrelatshowHandler.class);
///*    */     
///* 41 */     putHandler("prodinv_itemscmaterialvid", PDWkCardBodyCmaterialvidHandler.class);
///*    */     
///*    */ 
///*    */ 
///* 45 */     putHandler("prodinv_itemsvchangerate", PDWkProBodyVChangeRateHandler.class);
///*    */     
///* 47 */     putHandler("prodinv_itemsnnum", nc.ui.pd.pd0404.handler.tab_prodinv.PDWkProBodyNumHandler.class);
///*    */     
///* 49 */     putHandler("prodinv_itemsnastnum", nc.ui.pd.pd0404.handler.tab_prodinv.PDWkProBodyAstNumHandler.class);
		}
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		String tabCode = e.getTableCode();
		String key = e.getKey();
		MMBaseHandler handler = getHandler(tabCode + key);
//		if ((handler instanceof PDWkCardBodyCmatericlassidHandler)) {
//			((PDWkCardBodyCmatericlassidHandler)handler).setEditor(getEditor());
//			}
//		if ((handler instanceof PDWkCardBodyCmaterialvidHandler)) {
//			((PDWkCardBodyCmaterialvidHandler)handler).setEditor(getEditor());
//			}
		if (handler != null) {
			handler.afterEdit(e);
			}
		}
  
	public MaschineBillForm getEditor() {
		return this.editor;
		}
  
	public void setEditor(MaschineBillForm editor) {
		this.editor = editor;
  }
}
