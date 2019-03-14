package nc.ui.so.qs.mmplanbill.readyplan.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.SwingWorker;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.progress.IProgressMonitor;
import nc.ui.pub.beans.progress.NCProgresses;
import nc.ui.pubapp.uif2app.query2.action.QueryExecutor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.ToftPanelAdaptor;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.ui.uif2.components.progress.TPAProgressUtil;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;

public class LoadMachineAction extends NCAction {
	
	
	public LoadMachineAction(){
		this.setCode("loadMaiinfo");
		this.setBtnName("读取机器信息");
		this.putValue(Action.SHORT_DESCRIPTION, "按照当前产品情况读取可以使用及匹配的生产机器型号！");
	}
	
	private ShowUpableBillForm billform;
	private IPlanBillSerive PlanService;
	private AbstractAppModel model;
	private boolean showProgress = true;
	private IProgressMonitor progressMonitor = null;
	private boolean isTPAMonitor = true;
	private TPAProgressUtil tpaProgressUtil;
	private final String ShowName="数据载入中，请稍后！";
	private IAutoShowUpComponent showUpComponent;
	
	public IAutoShowUpComponent getShowUpComponent() {
		return this.showUpComponent;
	}
	
	public boolean isShowProgress() { return this.showProgress; }
	
	public TPAProgressUtil getTpaProgressUtil() {
	  
		if ((this.tpaProgressUtil == null) && (this.isTPAMonitor)) {
			this.tpaProgressUtil = new TPAProgressUtil();
			this.tpaProgressUtil.setContext(getModel().getContext());
		}
	  
		return this.tpaProgressUtil;
	}
	
	public void setShowProgress(boolean showProgress){
	    
		this.showProgress = showProgress;
	}
	
	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}
	
	
	public AbstractAppModel getModel() {
		return model;
	}


	public void setModel(AbstractAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}


	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	public boolean isTPAMonitor() {
		return (this.isTPAMonitor) && (getModel() != null) && ((getTpaProgressUtil().getContext().getEntranceUI() instanceof ToftPanelAdaptor));
	}

	private String LinkWhereSql(String sql,String where){
		
		if(sql==null){
			return where;
		}else{
			return sql+where;
		}
		
	}

	@Override
	public void doAction(ActionEvent event) throws Exception {
		// TODO 自动生成的方法存根
		
	
		
		if(this.isShowProgress()){
			
			if ((this.progressMonitor != null) && (!this.progressMonitor.isDone())) {
				return;
			}
			
			this.progressMonitor=(this.isTPAMonitor()?this.getTpaProgressUtil().getTPAProgressMonitor():NCProgresses.createDialogProgressMonitor(null));
			
			this.progressMonitor.beginTask(ShowName, -1);
			this.progressMonitor.setProcessInfo(ShowName);
			
			SwingWorker<Object[], Object> sw = new SwingWorker() {

				private Exception failed = null;
				
				
				@Override
				protected Object doInBackground() throws Exception {
					// TODO 自动生成的方法存根
					
					try{
						
						String dept=LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("pk_dept").getValue();
					
						String cust=LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("endcustomer").getValue();
					
						String mat=LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("pk_material").getValue();
					
						if(dept==null || cust==null || mat==null){
						
							throw new BusinessException("客户、生产产品、生产部门不能为空！请选择信息后再进行载入操作！");
						
						}
					
						String wheresql=null;
					
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("splitstofftype").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql, " and a.splitstofftype='"+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("splitstofftype").getValue()+"'");
						
						}
					
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("silkwide").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql," and (a.silkwidestart<="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("silkwide").getValue()+" and a.silkwideend>="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("silkwide").getValue()+")");
												
						}
						
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("elongation").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql," and (a.elongationstart<="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("elongation").getValue()+" and a.elongationend>="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("elongation").getValue()+")");

						}
						
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("tension").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql," and (a.tensionstart<="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("tension").getValue()+" and a.tensionend>="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("tension").getValue()+")");

						}
						
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("latitudedensity").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql," and (a.latitudedensitystart<="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("latitudedensity").getValue()+" and a.latitudedensityend>="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("latitudedensity").getValue()+")");

						}
						
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("width").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql," and (a.widthstart<="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("width").getValue()+" and a.widthend>="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("width").getValue()+")");

						}
						
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("length").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql," and (a.lengthstart<="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("length").getValue()+" and a.lengend>="+LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("length").getValue()+")");

						}
						
						if(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("spacer").getValue()!=null){
							
							wheresql=LoadMachineAction.this.LinkWhereSql(wheresql, " and a.spacer='"+new UFBoolean(LoadMachineAction.this.getBillform().getBillCardPanel().getHeadItem("spacer").getValue()).toString()+"'");
						
						}

						
						SuperVO[] retvo=LoadMachineAction.this.getPlanService().queryMasInfoBySql(mat, cust, dept, wheresql);
						
						if (retvo!=null && retvo.length > 0){
							 
							 LoadMachineAction.this.getModel().initModel(retvo);
							 
							 ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0265", null, new String[] { "" + retvo.length }), getModel().getContext());

						 }else{
							 
							 LoadMachineAction.this.getModel().initModel(null);
							 
							 ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getQueryNullInfo(), getModel().getContext());
						 }
						
						
						
					}catch(Exception e){
						
						this.failed = e;
						
					}
					
					return null;
					

				}
				
				protected void done(){
					
					if (LoadMachineAction.this.progressMonitor != null) {
						LoadMachineAction.this.progressMonitor.done();
						LoadMachineAction.this.progressMonitor = null;
					}
				  
					if (this.failed != null) {
						
						ShowStatusBarMsgUtil.showErrorMsgWithClear(NCLangRes.getInstance().getStrByID("uif2", "QueryAction-0000"), this.failed.getMessage(), LoadMachineAction.this.getModel().getContext());
						nc.bs.logging.Logger.error(this.failed.getMessage(), this.failed);
						
						return;
					}
				  
					if (LoadMachineAction.this.getShowUpComponent() != null) {
							
						LoadMachineAction.this.getShowUpComponent().showMeUp();
					}
				  
				}
				
				
				
			};
			
			sw.execute();
			
			
			
			
		}
		
		
	}
	
}
