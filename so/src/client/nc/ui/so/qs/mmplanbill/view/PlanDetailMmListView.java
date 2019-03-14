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
import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.ml.NCLangRes;
import nc.ui.pf.change.PfUtilUITools;
import nc.ui.pf.pub.PfUIDataCache;
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
import nc.ui.so.qs.mmplanbill.process.view.BomVersionTableCellRenderer;
import nc.ui.so.qs.mmplanbill.readyplan.model.PlanDetailPara;
import nc.ui.so.qs.pub.tools.ScreenTools;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class PlanDetailMmListView extends ShowUpableBillListView implements ListSelectionListener{
	
	private IPlanBillSerive planservice;
	private IntoProdManageAppModel rdmodel;
	private BomVersionTableCellRenderer cellRenderer;
	public static final String[] LinkQueryBusiActiveCodes = { "LinkQuery" };
	private int openMode = 1;
	

	public IntoProdManageAppModel getRdmodel() {
		return rdmodel;
	}

	public void setRdmodel(IntoProdManageAppModel rdmodel) {
		this.rdmodel = rdmodel;
	}

	public IPlanBillSerive getPlanservice() {
		
		if(this.planservice==null){
			
			this.planservice=NCLocator.getInstance().lookup(IPlanBillSerive.class);
			
		}
		
		return planservice;
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		this.getBillListPanel().getHeadItem("detail").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				// TODO 自动生成的方法存根
				

				// TODO 自动生成的方法存根
				
				FuncRegisterVO frVO = FuncRegisterCacheAccessor.getInstance().getFuncRegisterVOByFunCode("4006200203");
				
				if(frVO!=null){
					
					FuncletInitData funInitData = new FuncletInitData();
					
					PlanDetailPara initdata=new PlanDetailPara();
					
					
					try{
						
						RdPorductDetailVO selectVo=(RdPorductDetailVO) PlanDetailMmListView.this.getModel().getSelectedData();
						
						if(selectVo==null){
							
							ShowStatusBarMsgUtil.showErrorMsg("错误", "没有得到待处理对象!", PlanDetailMmListView.this.getModel().getContext());
							
							return;
						}
						
						String srcbid=selectVo.getVsrcplanbid();
						
						PlanProduceDetailVO pd=PlanDetailMmListView.this.getMDQueryService().queryBillOfVOByPK(PlanProduceDetailVO.class, srcbid, false);
						
						//验证ts
						PlanDetailMmListView.this.getPlanservice().validateVOTs(new SuperVO[]{pd,selectVo});
						
							

						initdata.setPlanVo(selectVo);
						
						initdata.setModel(PlanDetailMmListView.this.getRdmodel());
						initdata.setCmodel(PlanDetailMmListView.this.getModel());
						initdata.setList(PlanDetailMmListView.this);
						initdata.setOperatype("EDIT");
						
						if(selectVo.getBillstatus()!=1 || pd.getTotalnum().toDouble()>=pd.getPlannum().toDouble()){
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
						
						FuncletWindowLauncher.openFuncNodeDialog(PlanDetailMmListView.this.getModel().getContext().getEntranceUI(), frVO, funInitData, null, true, false, ScreenTools.newDimension(w, h));

					}catch(nc.bs.uif2.VersionConflictException e0){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误","生产调度 单展开明细被他人修改，请刷新后再试！", PlanDetailMmListView.this.getModel().getContext());
						
						return;
						
					}catch(BusinessException e){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), PlanDetailMmListView.this.getModel().getContext());
						
						return;
					}
					

				}else{
					
					ShowStatusBarMsgUtil.showErrorMsg("错误", "没有找到业务处理的功能节点", PlanDetailMmListView.this.getModel().getContext());
					
					return;
					
				}
				
			}
			
		});
		
		this.getBillListPanel().getHeadItem("def2").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				// TODO 自动生成的方法存根
				
				try{
					
					
					
					String vbillcode=(String) event.getValue();
					
					if(vbillcode!=null){
						
						BilltypeVO resultVO = PfUIDataCache.getBillType("55A2");
						
						Collection<PMOAggVO> col=PlanDetailMmListView.this.getMDQueryService().queryBillOfVOByCond(PMOHeadVO.class,"vbillcode='"+vbillcode+"' and dr=0",false);
						
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
							
							FuncletWindowLauncher.openFuncNodeForceModalDialog(PlanDetailMmListView.this.getModel().getContext().getEntranceUI(), funcregvo, initData, null, null, false, null, openMode == 1 ? null : LinkQueryBusiActiveCodes);
							
							
							
						}else{
							
							MessageDialog.showHintDlg(PlanDetailMmListView.this, "提示", "根据单据号没有找到符合条件的生产订单！");
							
							return;
							
						}
						
						
						
					}else{
						
						throw new BusinessException("没有得到生产订单号，无法查询!");
						
					}
					
					
					
				}catch(Exception e){
					
					ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), PlanDetailMmListView.this.getModel().getContext());
					
					return;
					
				}
				
				
			}
			
		});
		
		initCellRenderer(this.billListPanel);
		
		getBillListPanel().getParentListPanel().getTable().getSelectionModel().addListSelectionListener(this);
	}

	private void initCellRenderer(BillListPanel panel) {
		// TODO 自动生成的方法存根
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
			 
			
			 
			 if (headRow >= 0) {
				 
				 headStatusRowChange(headRow);
				 
				 getCellRenderer().setCurrentRow(headRow);
				 
				 headRowChange(headRow);

				 
			 }
		 }
	}
	
	private void headStatusRowChange(int headRow) {
		// TODO 自动生成的方法存根
		
		int state = getBillListPanel().getParentListPanel().getTableModel().getRowState(headRow);
		   
		getBillListPanel().repaint();
		if (state == -1) {
		     getBillListPanel().getChildListPanel().cancelSelectAllTableRow();
		     getBillListPanel().updateUI();
		}
		
	}
	
	private void headRowChange(int headRow) {
		// TODO 自动生成的方法存根
		
		try{
			
			String pk=(String) getBillListPanel().getParentListPanel().getTableModel().getValueAt(headRow, "pk_rdpd");
			
			Collection<IntoProdDetailVO> col= this.getMDQueryService().queryBillOfVOByCond(IntoProdDetailVO.class, "vsrcrdid='"+pk+"'", false);
			
			if(col!=null && col.size()>0){
				
				this.getRdmodel().initModel(col.toArray(new IntoProdDetailVO[col.size()]));
				
			}else{
				
				this.getRdmodel().initModel(null);
			}
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		
	}
	

}
