package nc.ui.so.qs.mmplanbill.view;

import java.util.Map;
import java.util.Vector;

import javax.swing.SwingUtilities;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.AbstractFunclet;
import nc.itf.org.IStockOrgQryService;
import nc.itf.pubapp.pub.bill.IOrgInfo;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.md.model.IBusinessEntity;
import nc.md.model.MetaDataException;
import nc.md.util.MDUtil;
import nc.ui.dbcache.DBCacheFacade;
import nc.ui.mmpps.psm0010.view.ScheduleOrgPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent;
import nc.ui.pubapp.uif2app.view.BaseOrgPanel;
import nc.ui.pubapp.uif2app.view.util.OrgUtils;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.org.StockOrgVO;
import nc.vo.org.orgmodel.OrgTypeVO;
import nc.vo.org.util.OrgTypeManager;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class PlanOrgPanel extends BaseOrgPanel {

	@Override
	public void setRefPane(UIRefPane newRefPane) {
		// TODO 自动生成的方法存根
		super.setRefPane(newRefPane);
		this.setEnabled(true);
		
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		
		super.initUI();
		this.setLabelName("调度组织");
		setEnabled(true);
		
	}

	@Override
	public void setModel(AbstractUIAppModel model) {
		// TODO 自动生成的方法存根
		super.setModel(model);
		
		model.addAppEventListener(new AppEventListener(){

			@Override
			public void handleEvent(AppEvent event) {
				// TODO 自动生成的方法存根
				
				if ((event instanceof AppUiStateChangeEvent)) {
					
					if (((AppUiStateChangeEvent)event).getNewState() != UIState.EDIT) {
						
						PlanOrgPanel.this.setEnabled(true);
						
						final PlanOrgPanel panel=PlanOrgPanel.this;
						
						SwingUtilities.invokeLater(new Runnable(){
							@Override
								public void run() {
								// TODO 自动生成的方法存根
									panel.requestFocus();
								}
							}
						);

					}else{
						PlanOrgPanel.this.setEnabled(false);
					}
					
				}
			}
		});
	}
	
	protected void handleUiStateChanged(AppUiStateChangeEvent event){
		
		if (event.getNewState() == UIState.EDIT) {
			return;
		}
		
		if ((event.getNewState() == UIState.NOT_EDIT) && (event.getOldState() == UIState.EDIT)) {
			return;
		}
		
		String orgtypecode = ((AbstractFunclet)getModel().getContext().getEntranceUI()).getFuncletContext().getFuncRegisterVO().getOrgtypecode();
		
		if ("GROUPORGTYPE00000000".equals(orgtypecode)) {
			setPkOrg(getModel().getContext().getPk_group());
		}else if ("GLOBLE00000000000000".equals(orgtypecode)) {
			setPkOrg("GLOBLE00000000000000");
		}else if (getDefaultOrgType() == 2) {
			processPermissionDefaultOrg(orgtypecode);
		}else if (getDefaultOrgType() == 0) {
			setPkOrg(null);
		}
		
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run() {
				if (StringUtils.isEmpty(PlanOrgPanel.this.getRefPane().getText())) {
					PlanOrgPanel.this.getRefPane().requestFocus();
				}
			}
		});
	}
	
    private boolean isNeedVidOrgPanel(){
    	
      AbstractFunclet abstractFunclet = (AbstractFunclet)getModel().getContext().getEntranceUI();
      String beanID = abstractFunclet.getFuncletContext().getFuncRegisterVO().getMdid();
      if (beanID != null) {
        try {
          IBean bean = MDBaseQueryFacade.getInstance().getBeanByID(beanID);
          if ((null != bean) && (MDUtil.isEntityType(bean))) {
            Map<String, String> infMap = ((IBusinessEntity)bean).getBizInterfaceMapInfo(IOrgInfo.class.getName());

            if ((infMap == null) || (infMap.size() == 0)) {
              return false;
            }
          }
        }catch (MetaDataException e) {
          ExceptionUtils.wrappException(e);
        }
      }
      
      String orgTypeCode = abstractFunclet.getFuncletContext().getFuncRegisterVO().getOrgtypecode();
      OrgTypeVO orgTypeVO = OrgTypeManager.getInstance().getOrgTypeByID(orgTypeCode);
      return orgTypeVO.getIsbusinessunittype().booleanValue();
    }
    
    private void processPermissionDefaultOrg(String orgtypecode) {
      
    	try {
    	 
    		String defaultOrg = OrgUtils.getDefaultOrgPK(orgtypecode);
        
    		if (hasPermission(defaultOrg)) {
    			if (isNeedVidOrgPanel()){
    				setPkOrg(getVIDByOID(defaultOrg));
    			}else {
    				setPkOrg(defaultOrg);
    			}
    		}else {
    			setPkOrg(null);
    		}
    	}catch (Exception e) {
    		ExceptionUtils.wrappException(e);
    	}
    }
    
    private String getVIDByOID(String OID) {
      
    	String VID = null;
    	String sql = "select pk_vid from org_orgs_v where pk_org='" + OID + "' order by vno desc";
      
    	Vector<?> vid = DBCacheFacade.getFromDBCache(sql);
    	if ((!CollectionUtils.isEmpty(vid)) && (!CollectionUtils.isEmpty((Vector)vid.get(0)))) {
    		VID = (String)((Vector)vid.get(0)).get(0);
    	}
    	
    	return VID;
    }
    
    protected boolean hasPermission(String defaultOrg) {
      
    	String[] orgPKs = ((AbstractFunclet)getModel().getContext().getEntranceUI()).getFuncletContext().getFuncSubInfo().getFuncPermissionPkorgs();
      
    	boolean isOk = false;
    	
    	for (int i = 0; (orgPKs != null) && (i < orgPKs.length); i++) {
    		if ((StringUtils.equals(orgPKs[i], defaultOrg)) && (defaultOrg != null)) {
    			isOk = true;
    			break;
    		}
    	}
      
    
    	if (isOk) {
    		try {
    			StockOrgVO vo = ((IStockOrgQryService)NCLocator.getInstance().lookup(IStockOrgQryService.class)).queryStockOrgByorgid(defaultOrg);
          
    			Boolean isFactory = Boolean.valueOf(OrgTypeManager.getInstance().isTypeOfByPk(vo.getPk_org(), "FACTORYTYPE000000000"));
    			if ((isFactory != null) && (isFactory.booleanValue())) {
    				return true;
    			}
    		}catch (BusinessException e){
    			ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("5001003_0", "05001003-0076"));
    		}
    	}
      
    	return false;
    }
    
}
