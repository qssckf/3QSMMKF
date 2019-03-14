package nc.ui.so.qs.mmplanbill.view;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.sfbase.client.ClientToolKit;
import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.ml.NCLangRes;
import nc.ui.pf.change.PfUtilUITools;
import nc.ui.pf.pub.PfUIDataCache;
import nc.ui.pub.bill.BillItemHyperlinkEvent;
import nc.ui.pub.bill.BillItemHyperlinkListener;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.msg.PfLinkData;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.sm.power.FuncRegisterCacheAccessor;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.sm.funcreg.FuncRegisterVO;

public class IntoProdDetailListView extends ShowUpableBillListView{

	public static final String[] LinkQueryBusiActiveCodes = { "LinkQuery" };
	private int openMode = 1;
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		this.getBillListPanel().getHeadItem("pomvbillcode").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				// TODO 自动生成的方法存根

				// TODO 自动生成的方法存根
				
				try{
					
					
					
					String vbillcode=(String) event.getValue();
					
					if(vbillcode!=null){
						
						BilltypeVO resultVO = PfUIDataCache.getBillType("55A2");
						
						Collection<PMOAggVO> col=IntoProdDetailListView.this.getMDQueryService().queryBillOfVOByCond(PMOHeadVO.class,"vbillcode='"+vbillcode+"' and dr=0",false);
						
						if(col!=null && col.size()>0){
							
							PMOAggVO agg=col.toArray(new PMOAggVO[col.size()])[0];
			
							final PMOHeadVO head=agg.getParentVO();
							
							PfLinkData pflink = new PfLinkData();
							pflink.setBillID(head.getCpmohid());
							pflink.setBillType("55A2");
							pflink.setUserObject("APPROVE");
							
							String customNode = PfUtilUITools.findCustomNodeOfBilltype(resultVO, pflink);
							
							String nodecode = customNode;
							
							if (StringUtils.isEmpty(customNode)) {
								nodecode = resultVO.getNodecode();
							}
							
							if (StringUtils.isEmpty(nodecode)) {
								MessageDialog.showHintDlg(null, NCLangRes.getInstance().getStrByID("101203", "UPP101203-000036"), NCLangRes.getInstance().getStrByID("pfworkflow", "noNodeView"));
								
								return;
							}
							
							FuncRegisterVO funcregvo = FuncRegisterCacheAccessor.getInstance().getFuncRegisterVOByFunCode(nodecode);
							
							if (funcregvo == null) {
									
								MessageDialog.showErrorDlg(ClientToolKit.getApplet(), NCLangRes.getInstance().getStrByID("sysframev5", "UPPsysframev5-000062"), NCLangRes.getInstance().getStrByID("sysframev5", "UPPsysframev5-000095") + nodecode);
								
							}
							
		
							ILinkQueryData qryData = new ILinkQueryData() {
								public String getBillID() {
									return head.getCpmohid();
								}
								
								public String getBillType() {
									return "55A2";
								}
								
								public String getPkOrg() {
									return head.getPk_org();
								}
								
								public Object getUserObject() {
									return null;
								}
							};
							
							FuncletInitData initData = new FuncletInitData(3, qryData);
							
							FuncletWindowLauncher.openFuncNodeForceModalDialog(IntoProdDetailListView.this.getModel().getContext().getEntranceUI(), funcregvo, initData, null, null, false, null, openMode == 1 ? null : LinkQueryBusiActiveCodes);
							
							
							
						}else{
							
							MessageDialog.showHintDlg(IntoProdDetailListView.this, "提示", "根据单据号没有找到符合条件的生产订单！");
							
							return;
							
						}
						
						
						
					}else{
						
						throw new BusinessException("没有得到生产订单号，无法查询!");
						
					}
					
					
					
				}catch(Exception e){
					
					ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), IntoProdDetailListView.this.getModel().getContext());
					
					return;
					
				}
				

			}
			
		});
		
		
	}
	
	
	
	

}
