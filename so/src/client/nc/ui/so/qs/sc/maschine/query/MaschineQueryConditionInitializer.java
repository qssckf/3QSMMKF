package nc.ui.so.qs.sc.maschine.query;

import nc.ui.bd.wk.wk0104.query.OrgFilter;
import nc.ui.mmbd.pub.query.reffilter.QWKFilter;
import nc.ui.mmbd.pub.query.reffilter.QWkgFilter;
import nc.ui.mmf.busi.query.reffilter.QDeptFilter;
import nc.ui.mmf.busi.query.reffilter.QMarAndDefFilter;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;

 
 
 
 
 
 
 
public class MaschineQueryConditionInitializer implements IQueryConditionDLGInitializer {
  public MaschineQueryConditionInitializer() {}
  
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator)
  {
//    condDLGDelegator.addRedundancyInfo("pk_org", "pk_org");
//    
//    condDLGDelegator.addRedundancyInfo("pk_group", "pk_group");

//    QMarAndDefFilter marAndDefQueryFilter = new QMarAndDefFilter(condDLGDelegator);
//    
//    marAndDefQueryFilter.addFilterMapsListeners(); 

    condDLGDelegator.setPowerEnable(true);
    
    condDLGDelegator.registerNeedPermissionOrgFieldCode("pk_org");
  }
}