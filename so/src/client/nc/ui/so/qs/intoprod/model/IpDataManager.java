package nc.ui.so.qs.intoprod.model;

import java.util.Collection;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.IQueryAndRefreshManager;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;
import nc.vo.util.SqlWhereUtil;

public class IpDataManager implements IModelDataManager,IQueryAndRefreshManager{

	private AbstractUIAppModel model;
	private IQueryScheme scheme;
	private String sqlWhere;
	
	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}

	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	@Override
	public void initModel() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void initModelBySqlWhere(String paramString) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void initModelByQueryScheme(IQueryScheme qryScheme) {
		// TODO 自动生成的方法存根

		try{
			
			this.scheme = qryScheme;
			
			IntoProdDetailVO[] data=null;
			
			data = queryIpVOByCon(qryScheme.getWhereSQLOnly());
			
			this.getModel().initModel(data);
			
			if ((data == null) || (data.length == 0)) {
				ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getQueryNullInfo(), getModel().getContext());
			}else {
				ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getQuerySuccessInfo(data.length), getModel().getContext());
			}
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
	
	}

	private IntoProdDetailVO[] queryIpVOByCon(String whereSQLOnly) throws MetaDataException {
		// TODO 自动生成的方法存根
		
		IntoProdDetailVO[] IpVos = null;
		
		String wheresql=createCondition(whereSQLOnly);
		
		this.sqlWhere=wheresql;
		
		Collection<IntoProdDetailVO> col=this.getMDQueryService().queryBillOfVOByCond(IntoProdDetailVO.class,wheresql, false);
		
		return col.toArray(new IntoProdDetailVO[col.size()]);
	}

	private String createCondition(String sqlWhere2) {
		// TODO 自动生成的方法存根
		
		SqlWhereUtil swu = new SqlWhereUtil();
		swu.s("sfmakebill='N' and processer='"+InvocationInfoProxy.getInstance().getUserId()+"' ");
		swu.and(sqlWhere2);
		return swu.getSQLWhere();

	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		this.initModelByQueryScheme(this.scheme);
	}
	
	

}
