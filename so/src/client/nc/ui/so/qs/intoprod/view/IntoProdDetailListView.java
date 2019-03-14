package nc.ui.so.qs.intoprod.view;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
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
import nc.ui.so.qs.intoprod.input.model.IntoProdDetailPara;
import nc.ui.so.qs.intoprod.model.ManageAppModel;
import nc.ui.so.qs.mmplanbill.readyplan.model.PlanDetailPara;
import nc.ui.so.qs.mmplanbill.view.PlanDetailMmListView;
import nc.ui.so.qs.pub.tools.ScreenTools;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class IntoProdDetailListView extends ShowUpableBillListView{
	
	private IRdMmService rdservice;
	private ManageAppModel rdmodel;
	public static final String[] LinkQueryBusiActiveCodes = { "LinkQuery" };
	private int openMode = 1;
	
	public ManageAppModel getRdmodel() {
		return rdmodel;
	}

	public void setRdmodel(ManageAppModel rdmodel) {
		this.rdmodel = rdmodel;
	}
	
	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		return rdservice;
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		this.getBillListPanel().getHeadItem("opera").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				
				FuncRegisterVO frVO = FuncRegisterCacheAccessor.getInstance().getFuncRegisterVOByFunCode("4006200205");
				
				if(frVO!=null){
					
					FuncletInitData funInitData = new FuncletInitData();
					
					IntoProdDetailPara initdata=new IntoProdDetailPara();
					
					
					try{
						
						IntoProdDetailVO selectVo=(IntoProdDetailVO) IntoProdDetailListView.this.getModel().getSelectedData();
						
						if(selectVo==null){
							
							ShowStatusBarMsgUtil.showErrorMsg("错误", "没有得到待处理对象!", IntoProdDetailListView.this.getModel().getContext());
							
							return;
						}
						
						String srcid=selectVo.getVsrcrdid();
						
						RdPorductDetailVO pd=IntoProdDetailListView.this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class, srcid, false);
						
						//验证ts
						IntoProdDetailListView.this.getRdservice().validateVOTs(new SuperVO[]{pd,selectVo});

						initdata.setItVo(selectVo);
						
						initdata.setModel(IntoProdDetailListView.this.getRdmodel());
						initdata.setCmodel(IntoProdDetailListView.this.getModel());
						initdata.setList(IntoProdDetailListView.this);
						initdata.setOperatype("EDIT");
						
						if(selectVo.getSfmakebill().equals("Y")){
							initdata.setEdit(false);
						}else{
							initdata.setEdit(true);
						}
						
						
						funInitData.setInitData(initdata);	
						

						int w = 680;
						int h = 680;
						if ((ScreenTools.getScreen().width == 1024) && (ScreenTools.getScreen().height == 768))
						{
							w = 780;
						}
						
						FuncletWindowLauncher.openFuncNodeDialog(IntoProdDetailListView.this.getModel().getContext().getEntranceUI(), frVO, funInitData, null, true, false, ScreenTools.newDimension(w, h));

					}catch(nc.bs.uif2.VersionConflictException e0){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误","生产排产单被他人修改，请刷新后再试！", IntoProdDetailListView.this.getModel().getContext());
						
						return;
						
					}catch(BusinessException e){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), IntoProdDetailListView.this.getModel().getContext());
						
						return;
					}
					

				}else{
					
					ShowStatusBarMsgUtil.showErrorMsg("错误", "没有找到业务处理的功能节点", IntoProdDetailListView.this.getModel().getContext());
					
					return;
					
				}
				
			}
			
		});
		
		this.getBillListPanel().getHeadItem("pomvbillcode").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
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
