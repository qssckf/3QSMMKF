package nc.ui.so.qs.intoprod.view;

import java.util.Collection;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pub.bill.BillItemHyperlinkEvent;
import nc.ui.pub.bill.BillItemHyperlinkListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.table.BillTableBooleanCellRenderer;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.sm.power.FuncRegisterCacheAccessor;
import nc.ui.so.qs.intoprod.input.model.IntoProdDetailPara;
import nc.ui.so.qs.intoprod.model.IntoProdAppModel;
import nc.ui.so.qs.mmplanbill.process.view.BomVersionTableCellRenderer;
import nc.ui.so.qs.mmplanbill.view.PlanDetailListView;
import nc.ui.so.qs.pub.tools.ScreenTools;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;
import nc.vo.so.qs.sc.en.Billstatus;

public class IntoProdRdListView extends ShowUpableBillListView implements ListSelectionListener{
	
	private IntoProdAppModel rdmodel;
	private BomVersionTableCellRenderer cellRenderer;
	private IRdMmService rdservice;
	
	public IntoProdAppModel getRdmodel() {
		return rdmodel;
	}

	public void setRdmodel(IntoProdAppModel rdmodel) {
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
				// TODO 自动生成的方法存根
				FuncRegisterVO frVO = FuncRegisterCacheAccessor.getInstance().getFuncRegisterVOByFunCode("4006200205");
				
				if(frVO!=null){
					
					FuncletInitData funInitData = new FuncletInitData();
					
					IntoProdDetailPara initdata = new IntoProdDetailPara();
					
					try{
						
						RdPorductDetailVO selectVo=(RdPorductDetailVO) IntoProdRdListView.this.getModel().getSelectedData();
						
						if(selectVo==null){
							
							ShowStatusBarMsgUtil.showErrorMsg("错误", "没有得到待处理对象!", IntoProdRdListView.this.getModel().getContext());
							
							return;
						}
						
						RdPorductDetailVO it=selectVo;
						
						IntoProdRdListView.this.getRdservice().validateVOTs(new SuperVO[]{it});
						
						if(it.getTintoprodnum().toDouble()<it.getProdnum().toDouble()){
							
							initdata.setItVo(Convert2RdVO(selectVo));
							
							initdata.setModel(IntoProdRdListView.this.getModel());
							initdata.setCmodel(IntoProdRdListView.this.getRdmodel());
							initdata.setList(IntoProdRdListView.this);
							initdata.setOperatype("ADD");
							initdata.setEdit(true);
							
							funInitData.setInitData(initdata);	
							

							int w = 680;
							int h = 680;
							if ((ScreenTools.getScreen().width == 1024) && (ScreenTools.getScreen().height == 768))
							{
								w = 780;
							}
							
							FuncletWindowLauncher.openFuncNodeDialog(IntoProdRdListView.this.getModel().getContext().getEntranceUI(), frVO, funInitData, null, true, false, ScreenTools.newDimension(w, h));
							
							
						}else{
							throw new BusinessException("投产累计数量已经超过排产生产数量，不能继续投产");
						}
						
						
					}catch(nc.bs.uif2.VersionConflictException e0){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误","生产投产单信息被他人修改，请刷新后再试！", IntoProdRdListView.this.getModel().getContext());
						
						return;
					}catch(BusinessException e){

						ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), IntoProdRdListView.this.getModel().getContext());
						
						return;
					}
					
				}else{
					
					ShowStatusBarMsgUtil.showErrorMsg("错误", "没有找到业务处理的功能节点", IntoProdRdListView.this.getModel().getContext());
					
					return;
					
				}
			}

			private IntoProdDetailVO Convert2RdVO(RdPorductDetailVO vo) {
				// TODO 自动生成的方法存根
				
				IntoProdDetailVO retvo=new IntoProdDetailVO();
				retvo.setVsrcplanid(vo.getVsrcplanid());
				retvo.setVsrcplanbid(vo.getVsrcplanbid());
				retvo.setVsrcrdid(vo.getPk_rdpd());
				retvo.setPk_group(vo.getPk_group());
				retvo.setPk_org(vo.getPk_org());
				retvo.setPk_org_v(vo.getPk_org_v());
				retvo.setPk_dept(vo.getPk_dept());
				retvo.setPk_machine(vo.getPk_machine());
				retvo.setPk_customer(vo.getPk_customer());
				retvo.setEndcustomer(vo.getEndcustomer());
				retvo.setPrevbillcode(vo.getPrevbillcode());
				retvo.setPlanbillcode(vo.getPlanbillcode());
				retvo.setVsrcplanbid(vo.getVsrcplanbid());
				retvo.setVsrcplanid(vo.getVsrcplanid());
				retvo.setPk_material(vo.getPk_material());
				retvo.setPk_bomid(vo.getPk_bomid());
				retvo.setVfree1(vo.getVfree1());
				retvo.setVfree2(vo.getVfree2());
				retvo.setVfree3(vo.getVfree3());
				retvo.setVfree4(vo.getVfree4());
				retvo.setVfree5(vo.getVfree5());
				retvo.setVfree6(vo.getVfree6());
				retvo.setVfree7(vo.getVfree7());
				retvo.setVfree8(vo.getVfree8());
				retvo.setVfree9(vo.getVfree9());
				retvo.setVfree10(vo.getVfree10());
				retvo.setDef1(vo.getDef1());
				retvo.setDef2(vo.getDef2());
				retvo.setDef3(vo.getDef3());
				retvo.setDef4(vo.getDef4());
				retvo.setDef5(vo.getDef5());
				retvo.setDef6(vo.getDef6());
				retvo.setDef7(vo.getDef7());
				retvo.setDef8(vo.getDef8());
				retvo.setDef9(vo.getDef9());
				retvo.setDef10(vo.getDef10());
				retvo.setDef11(vo.getDef11());
				retvo.setDef12(vo.getDef12());
				retvo.setDef13(vo.getDef13());
				retvo.setDef14(vo.getDef14());
				retvo.setDef15(vo.getDef15());
				retvo.setDef16(vo.getDef16());
				retvo.setDef17(vo.getDef17());
				retvo.setDef18(vo.getDef18());
				retvo.setDef19(vo.getDef19());
				retvo.setDef20(vo.getDef20());
				retvo.setSplitstofftype(vo.getSplitstofftype());
				retvo.setSilkwide(vo.getSilkwide());
				retvo.setElongation(vo.getElongation());
				retvo.setTension(vo.getTension());
				retvo.setSpacer(vo.getSpacer());
				retvo.setOtherproduction(vo.getOtherproduction());
				retvo.setTagline(vo.getTagline());
				retvo.setLatitudedensity(vo.getLatitudedensity());
				retvo.setWidth(vo.getWidth());
				retvo.setLength(vo.getLength());
				retvo.setUnit(vo.getUnit());
				retvo.setQunit(vo.getQunit());
				retvo.setRate(vo.getRate());
				retvo.setProdnum(vo.getProdnum().sub(vo.getTintoprodnum()));
				retvo.setProdnastnum(vo.getProdnastnum().sub(vo.getTintopronastnum()));
				retvo.setProddate(new UFDateTime(InvocationInfoProxy.getInstance().getBizDateTime()));
				retvo.setEnddate(new UFDateTime(InvocationInfoProxy.getInstance().getBizDateTime()));
				retvo.setExpandline(1);
				retvo.setProcesser(vo.getProcesser());
				
				return retvo;
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
	
	private void headStatusRowChange(int headRow) {
		// TODO 自动生成的方法存根
		
		int state = getBillListPanel().getParentListPanel().getTableModel().getRowState(headRow);
		   
		getBillListPanel().repaint();
		if (state == -1) {
		     getBillListPanel().getChildListPanel().cancelSelectAllTableRow();
		     getBillListPanel().updateUI();
		}
		
		
		
	}

}
