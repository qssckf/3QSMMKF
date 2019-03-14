package nc.ui.so.qs.intoprod.input.action;

import java.awt.event.ActionEvent;
import java.util.Collection;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.BillManageModel;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RefreshAction extends NCAction{

	private final String iconurl="themeres/ui/toolbaricons/refresh_highlight.png";
	private BillManageModel model = null;
	private BillManageModel fmodel = null;
	private IRdMmService rdservice;
	
	public RefreshAction(){
		this.setBtnName("刷新");
		this.setCode("DetailRefreshB");
		this.putValue("ShortDescription", "根据选择的排产记录刷新投产记录");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		this.setEnabled(false);
	}
	
	
	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}
	
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public BillManageModel getFmodel() {
		return fmodel;
	}

	public void setFmodel(BillManageModel fmodel) {
		this.fmodel = fmodel;
		this.fmodel.addAppEventListener(this);
	}

	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		return rdservice;
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		RdPorductDetailVO vo=(RdPorductDetailVO) this.getFmodel().getSelectedData();
		
		if(vo!=null){
			String pk_rd=vo.getPk_rdpd();
			Collection<IntoProdDetailVO> col=this.getMDQueryService().queryBillOfVOByCond(IntoProdDetailVO.class, "vsrcrdid='"+pk_rd+"'", false);
			
			IntoProdDetailVO[] ipdetail=col.toArray(new IntoProdDetailVO[col.size()]);
			
			if(ipdetail!=null && ipdetail.length>0){
				this.getModel().initModel(ipdetail);
			}else{
				this.getModel().initModel(null);
			}
			
		}
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		return this.getFmodel().getSelectedData()==null?false:true;
	}

}
