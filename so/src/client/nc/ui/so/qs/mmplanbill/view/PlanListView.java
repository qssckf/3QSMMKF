package nc.ui.so.qs.mmplanbill.view;

import java.util.Collection;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.sfbase.client.ClientToolKit;
import nc.ui.ebpur.purproject.view.ReleaseSuppItemFormView;
import nc.ui.ml.NCLangRes;
import nc.ui.pf.change.PfUtilUITools;
import nc.ui.pf.pub.PfUIDataCache;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItemHyperlinkEvent;
import nc.ui.pub.bill.BillItemHyperlinkListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.table.BillTableBooleanCellRenderer;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.msg.PfLinkData;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.sm.power.FuncRegisterCacheAccessor;
import nc.ui.so.qs.mmplanbill.model.IntoProdManageAppModel;
import nc.ui.so.qs.mmplanbill.model.PlanDetailMManageAppModel;
import nc.ui.so.qs.mmplanbill.model.PlanDetailManageAppModel;
import nc.ui.so.qs.mmplanbill.process.model.BomChildAppModel;
import nc.ui.so.qs.mmplanbill.process.model.PlanBillProcessPara;
import nc.ui.so.qs.mmplanbill.process.view.BomVersionTableCellRenderer;
import nc.ui.so.qs.pub.tools.ScreenTools;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanListView extends ShowUpableBillListView implements ListSelectionListener{

	private IPlanBillSerive PlanService;
	private PlanDetailManageAppModel PlanDetailModel;
	private PlanDetailMManageAppModel PlanDetailMModel;
	private IntoProdManageAppModel IpModel;
	private BomVersionTableCellRenderer cellRenderer;
	public int selectIndex = 0;
	private Boolean IsChange;
	private Boolean IsFrist;
	public static final String[] LinkQueryBusiActiveCodes = { "LinkQuery" };
	private int openMode = 1;
	
	public IntoProdManageAppModel getIpModel() {
		return IpModel;
	}

	public void setIpModel(IntoProdManageAppModel ipModel) {
		IpModel = ipModel;
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	public PlanDetailManageAppModel getPlanDetailModel() {
		return PlanDetailModel;
	}

	public void setPlanDetailModel(PlanDetailManageAppModel planDetailModel) {
		PlanDetailModel = planDetailModel;
	}
	
	public PlanDetailMManageAppModel getPlanDetailMModel() {
		return PlanDetailMModel;
	}

	public void setPlanDetailMModel(PlanDetailMManageAppModel planDetailMModel) {
		PlanDetailMModel = planDetailMModel;
	}


	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}
	
	


	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		this.IsFrist=true;
		
		BillListPanel billlist = this.getBillListPanel();
		
		//建立单据项目超链接操作事件
		billlist.getHeadItem("handleReply").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				// TODO 自动生成的方法存根
				
				FuncRegisterVO frVO = FuncRegisterCacheAccessor.getInstance().getFuncRegisterVOByFunCode("4006200202");
				
				if(frVO!=null){
					
					FuncletInitData funInitData = new FuncletInitData();
					
					PlanBillProcessPara initdata=new PlanBillProcessPara();
					
					MmPlanBillVO selectVo=(MmPlanBillVO) PlanListView.this.getModel().getSelectedData();
					
					if(selectVo==null){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误", "没有得到待处理对象!", PlanListView.this.getModel().getContext());
						
						return;
					}
					
					
					try{
						
						initdata.setPlanVo(selectVo);
						
						Boolean sf=selectVo.getSfexand().booleanValue();
						
						if(sf || PlanListView.this.getPlanService().QueryPlanBillIsExists(selectVo.getPk_planbill())){
							initdata.setEdited(false);
						}else{
							initdata.setEdited(true);
						}
						
						initdata.setModel(PlanListView.this.getModel());
						
						initdata.setList(PlanListView.this);
						
						funInitData.setInitData(initdata);	
						
						int w = 680;
						int h = 680;
						if ((ScreenTools.getScreen().width == 1024) && (ScreenTools.getScreen().height == 768))
						{
							w = 780;
						}
						
						FuncletWindowLauncher.openFuncNodeDialog(PlanListView.this.getModel().getContext().getEntranceUI(), frVO, funInitData, null, true, false, ScreenTools.newDimension(w, h));

					}catch(BusinessException e){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), PlanListView.this.getModel().getContext());
						
						return;
					}
					

				}else{
					
					ShowStatusBarMsgUtil.showErrorMsg("错误", "没有找到业务处理的功能节点", PlanListView.this.getModel().getContext());
					
					return;
					
				}
				
			}
			
		});
		
		initCellRenderer(this.billListPanel);
		
		getBillListPanel().getParentListPanel().getTable().getSelectionModel().addListSelectionListener(this);
		
		
		billlist.getHeadItem("vscrcode").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				// TODO 自动生成的方法存根
				
				try{
					

					String vbillcode=(String) event.getValue();
					
					if(vbillcode!=null){
						
						BilltypeVO resultVO = PfUIDataCache.getBillType("38");
						
						Collection<PreOrderVO> col=PlanListView.this.getMDQueryService().queryBillOfVOByCond(PreOrderVO.class,"vbillcode='"+vbillcode+"' and dr=0",false);
						
						if(col!=null && col.size()>0){
							
							PreOrderVO agg=col.toArray(new PreOrderVO[col.size()])[0];
			
							final PreOrderHVO head=agg.getParentVO();
							
							PfLinkData pflink = new PfLinkData();
							pflink.setBillID(head.getCpreorderid());
							pflink.setBillType("38");
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
									return head.getCpreorderid();
								}
								
								public String getBillType() {
									return "38";
								}
								
								public String getPkOrg() {
									return head.getPk_org();
								}
								
								public Object getUserObject() {
									return null;
								}
							};
							
							FuncletInitData initData = new FuncletInitData(3, qryData);
							
							FuncletWindowLauncher.openFuncNodeForceModalDialog(PlanListView.this.getModel().getContext().getEntranceUI(), funcregvo, initData, null, null, false, null, openMode == 1 ? null : LinkQueryBusiActiveCodes);
							
							
							
						}else{
							
							MessageDialog.showHintDlg(PlanListView.this, "提示", "根据单据号没有找到符合条件的预订单！");
							
							return;
							
						}
						
						
						
					}else{
						
						throw new BusinessException("没有得到预订单号，无法查询!");
						
					}
					
					
					
				}catch(Exception e){
					
					ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), PlanListView.this.getModel().getContext());
					
					return;
					
				}
			}
		});
		

	}
	
	
	private void initCellRenderer(BillListPanel panel)
	{
	   TableColumnModel columnModel = panel.getHeadTable().getColumnModel();
	   for (int i = 0; i < columnModel.getColumnCount(); i++) {
	     TableColumn column = columnModel.getColumn(i);
	     
	     if (!(column.getCellRenderer() instanceof BillTableBooleanCellRenderer))
	     {
	
	       column.setCellRenderer(getCellRenderer());
	     }
	   }
	}
	
	private BomVersionTableCellRenderer getCellRenderer()
	{
	     if (this.cellRenderer == null) {
	       this.cellRenderer = new BomVersionTableCellRenderer(this.billListPanel.getHeadBillModel(), false);
	     }
	     
	     return this.cellRenderer;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO 自动生成的方法存根
		
		if (!e.getValueIsAdjusting()) {
			 
			int headRow = ((ListSelectionModel)e.getSource()).getAnchorSelectionIndex();
			 
			 if(headRow==this.selectIndex && !this.IsFrist){
				 this.IsChange=false;
			 }else{
				 
				 if(this.IsFrist){
					 
					 this.IsFrist=false;
					 
				 }

				 this.IsChange=true;
				 
			 }
			 
			 this.selectIndex = headRow;
			 
			 getCellRenderer().setCurrentRow(headRow);
			 
			 if (headRow >= 0) {
				 
				 headRowChange(headRow);
				 
				 if(IsChange){
					 try{
						 SetPlanDetailInfo(headRow);
					 }catch(Exception ex){
						 ExceptionUtils.wrappException(ex);
					 }
					 
				 }
				 
			 }
		}
		
		
	}
	
	private void headRowChange(int headRow) {
		// TODO 自动生成的方法存根
		
		int state = getBillListPanel().getParentListPanel().getTableModel().getRowState(headRow);
		   
		getBillListPanel().repaint();
		if (state == -1) {
		     getBillListPanel().getChildListPanel().cancelSelectAllTableRow();
		     getBillListPanel().updateUI();
		}
		
		
		
	}

	private void SetPlanDetailInfo(int headRow) throws BusinessException {
		// TODO 自动生成的方法存根
	
		String pk_planbill = (String) getBillListPanel().getParentListPanel().getTableModel().getValueAt(headRow, "pk_planbill");
		
		SuperVO[] plandetails=this.getPlanService().quyerPlanDetailVOs("pk_planbill='"+pk_planbill+"'");
		
		if(plandetails!=null && plandetails.length>0){
			this.getPlanDetailModel().initModel(plandetails);
		}else{
			this.getPlanDetailModel().initModel(null);
			this.getPlanDetailMModel().initModel(null);
			this.getIpModel().initModel(null);
		}
		
	}
	
	

}
