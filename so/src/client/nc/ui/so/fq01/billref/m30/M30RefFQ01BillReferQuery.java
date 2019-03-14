package nc.ui.so.fq01.billref.m30;

import java.awt.Container;

import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QStockOrgFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;

public class M30RefFQ01BillReferQuery extends DefaultBillReferQuery{

	public M30RefFQ01BillReferQuery(Container c, TemplateInfo info) {
		super(c, info);
		// TODO 自动生成的构造函数存根
	}
	
	public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator){
		
		setDefaultPk_org(dlgDelegator);
		initFilterRef(dlgDelegator);
		
	    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] { "pk_org" });
	    
	    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
	    dlgDelegator.setPowerEnable(true);
		
		
	}

	private void initFilterRef(QueryConditionDLGDelegator dlgDelegator) {
		// TODO 自动生成的方法存根
		 QTransTypeFilter trantype = new QTransTypeFilter(dlgDelegator,"transtypepk", "FQ01");
		 
		 trantype.filter();
		 
		 QDeptFilter dept = new QDeptFilter(dlgDelegator, "cdeptid");
		 dept.setPk_orgCode("pk_org");
		 dept.addEditorListener();
		 
		 QMarbasclassFilter marclass = new QMarbasclassFilter(dlgDelegator, "so_shipmentsb.cmaterialid.pk_marbasclass");
		 marclass.setPk_orgCode("pk_org");
		 marclass.addEditorListener();
		 
		 QPsndocFilter employee = new QPsndocFilter(dlgDelegator, "cemployeeid");
		 employee.setPk_orgCode("pk_org");
		 employee.addEditorListener();
		 
		 QStockOrgFilter stockOrg = new QStockOrgFilter(dlgDelegator, "so_shipmentsb.csendstockorgid");
		 stockOrg.filter();
		 
		 
	}

	private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) {
		
		String defaultOrg = null;
		try{
			
			 defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
			
		}catch (Exception ex) {
			ExceptionUtils.wrappException(ex);
		}
		
		if ((defaultOrg != null) && (defaultOrg.trim().length() > 0)) {
			
			dlgDelegator.setDefaultValue("pk_org", defaultOrg);
			
		}

	}
	
}
