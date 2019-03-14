package nc.ui.so.qs.intoprod.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class IpMakePMOAction extends NCAction{
	
	private final String iconurl="themeres/ui/toolbaricons/sift_display_hide_press.png";
	private BillManageModel model;
	private IRdMmService rdservice;
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public IpMakePMOAction(){
		
		this.setBtnName("生成生产订单");
		this.setCode("RdRelease");
		this.putValue("ShortDescription", "生成生产订单");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		this.setEnabled(false);
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		
		return rdservice;
	}
	

	@Override
	public void doAction(ActionEvent paramActionEvent) throws Exception {
		// TODO 自动生成的方法存根
		
		try{
			
			Object[] objs = getModel().getSelectedOperaDatas();
			
			 if ((null == objs) || (MMValueCheck.isEmpty(objs))) {
				throw new BusinessException("没有选择任何数据，操作失败！") ;
			 }
			 
			 List<IntoProdDetailVO> ips = new ArrayList();
			 
			 for(Object obj:objs){
				 
				 if(obj instanceof IntoProdDetailVO){
					 ips.add((IntoProdDetailVO) obj);
				 }else{
					 throw new BusinessException("数据类型不正确，操作失败");
				 }
				 
			 }
			 
			 HashSet<String> orgs=new HashSet();
			 
			 for(IntoProdDetailVO ip:ips){
				 orgs.add(ip.getPk_org());
			 }
				
			 if(orgs.size()>1){
				 
				 throw new BusinessException("不能选择多个组织的数据，操作失败");
			 }
			 
			 IntoProdDetailVO[] ipvo = ips.toArray(new IntoProdDetailVO[ips.size()]);
			 
			 this.getRdservice().DoRelease(ipvo);
			 
			 List<String> rdpks=new ArrayList();
			 
			 for(IntoProdDetailVO ip:ipvo){
				 
				 rdpks.add(ip.getPk_itpd());
				 
			 }
			 
			 Collection<IntoProdDetailVO> col=this.getMDQueryService().queryBillOfVOByPKs(IntoProdDetailVO.class, rdpks.toArray(new String[rdpks.size()]), false);
			 
			 this.getModel().directlyDelete(col.toArray(new IntoProdDetailVO[col.size()]));
			 
			 ShowStatusBarMsgUtil.showStatusBarMsg("投产单明细生成生产订单成功！", this.getModel().getContext());
			
			
		}catch(Exception ex){
			
			ShowStatusBarMsgUtil.showErrorMsg("错误", ex.getMessage(), this.getModel().getContext());
			 
			 throw new BusinessException(ex);
			
		}
		
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		
		IntoProdDetailVO selectvo=(IntoProdDetailVO)this.getModel().getSelectedData();
		
		if(selectvo!=null && selectvo.getSfmakebill().toString().equals("N")){
			return true;
		}else{
			return false;
		}
		
	}

}
