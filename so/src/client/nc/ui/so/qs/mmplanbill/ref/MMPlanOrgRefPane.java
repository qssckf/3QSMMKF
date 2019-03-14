package nc.ui.so.qs.mmplanbill.ref;
 
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.vo.uap.rbac.FuncSubInfo;
import nc.vo.uif2.LoginContext;



public class MMPlanOrgRefPane
  extends UIRefPane
{
  private LoginContext context;
  private static final long serialVersionUID = -9047030815460287824L;
  
  public MMPlanOrgRefPane()
  {
    setRefNodeName("调度组织");
    setRefType(0);
  }
  
  public LoginContext getContext() {
    return this.context;
  }
  
  public void setContext(LoginContext context) {
    this.context = context;
    
    processLimitPlanOrg();
  }
  
  public void processLimitPlanOrg() {
    String[] orgPKs = this.context.getFuncInfo().getFuncPermissionPkorgs();
    
    orgPKs = orgPKs == null ? new String[0] : orgPKs;
    AbstractRefModel refModel = getRefModel();
    if (refModel != null) {
      refModel.setFilterPks(orgPKs);
    }
  }
}
