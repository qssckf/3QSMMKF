package nc.ui.so.qs.mmplanbill.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.VersionConflictException;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.uapbd.IMaterialPubService;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillItemHyperlinkEvent;
import nc.ui.pub.bill.BillItemHyperlinkListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.table.BillTableBooleanCellRenderer;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.ui.sm.power.FuncRegisterCacheAccessor;
import nc.ui.so.qs.mmplanbill.model.IntoProdManageAppModel;
import nc.ui.so.qs.mmplanbill.model.PlanDetailMManageAppModel;
import nc.ui.so.qs.mmplanbill.process.model.PlanBillProcessPara;
import nc.ui.so.qs.mmplanbill.process.view.BomVersionTableCellRenderer;
import nc.ui.so.qs.mmplanbill.readyplan.model.PlanDetailPara;
import nc.ui.so.qs.mmplanbill.readyplan.model.RdMmModel;
import nc.ui.so.qs.pub.tools.ScreenTools;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;
import nc.vo.so.qs.sc.en.Billstatus;
import nc.vo.util.BDVersionValidationUtil;

public class PlanDetailListView extends ShowUpableBillListView implements ListSelectionListener,OnhandPanelSrc{
	 
	private PlanDetailMManageAppModel rdmodel;
	private IntoProdManageAppModel IpModel;
	private IPlanBillSerive planservice;
	private BomVersionTableCellRenderer cellRenderer;
	
	public IntoProdManageAppModel getIpModel() {
		return IpModel;
	}

	public void setIpModel(IntoProdManageAppModel ipModel) {
		IpModel = ipModel;
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
	
	public PlanDetailMManageAppModel getRdmodel() {
		return rdmodel;
	}

	public void setRdmodel(PlanDetailMManageAppModel rdmodel) {
		this.rdmodel = rdmodel;
	}



	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		this.getBillListPanel().getHeadItem("opera").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				// TODO 自动生成的方法存根
				
				FuncRegisterVO frVO = FuncRegisterCacheAccessor.getInstance().getFuncRegisterVOByFunCode("4006200203");
				
				if(frVO!=null){
					
					FuncletInitData funInitData = new FuncletInitData();
					
					PlanDetailPara initdata=new PlanDetailPara();
					
					
					try{
						
						PlanProduceDetailVO selectVo=(PlanProduceDetailVO) PlanDetailListView.this.getModel().getSelectedData();
						
						if(selectVo==null){
							
							ShowStatusBarMsgUtil.showErrorMsg("错误", "没有得到待处理对象!", PlanDetailListView.this.getModel().getContext());
							
							return;
						}
						

						PlanProduceDetailVO pd=selectVo;
						
						//验证ts
						PlanDetailListView.this.getPlanservice().validateVOTs(new SuperVO[]{pd});
							
						if(pd.getTotalnum().toDouble()<pd.getPlannum().toDouble()){
							
							initdata.setPlanVo(Convert2RdVO(selectVo));
							
							initdata.setModel(PlanDetailListView.this.getModel());
							initdata.setCmodel(PlanDetailListView.this.getRdmodel());
							initdata.setList(PlanDetailListView.this);
							initdata.setOperatype("ADD");
							initdata.setEdit(true);
							
							funInitData.setInitData(initdata);	
							

							int w = 680;
							int h = 680;
							if ((ScreenTools.getScreen().width == 1024) && (ScreenTools.getScreen().height == 768))
							{
								w = 780;
							}
							
							FuncletWindowLauncher.openFuncNodeDialog(PlanDetailListView.this.getModel().getContext().getEntranceUI(), frVO, funInitData, null, true, false, ScreenTools.newDimension(w, h));
								
						}else{
								
							throw new BusinessException("排产累计数量已经超过计划生产数量，不能继续排产");
							
						}
							
							
							

		
						
						
					}catch(nc.bs.uif2.VersionConflictException e0){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误","生产调度单展开明细信息被他人修改，请刷新后再试！", PlanDetailListView.this.getModel().getContext());
						
						return;
						
					}catch(BusinessException e){
						
						ShowStatusBarMsgUtil.showErrorMsg("错误",e.getMessage(), PlanDetailListView.this.getModel().getContext());
						
						return;
					}
					

				}else{
					
					ShowStatusBarMsgUtil.showErrorMsg("错误", "没有找到业务处理的功能节点", PlanDetailListView.this.getModel().getContext());
					
					return;
					
				}
				
			
				
				
				
			}


			private RdPorductDetailVO Convert2RdVO(PlanProduceDetailVO vo) {
				// TODO 自动生成的方法存根
				
				RdPorductDetailVO retvo=new RdPorductDetailVO();
				
				retvo.setPk_group(vo.getPk_group());
				retvo.setPk_org(vo.getPk_org());
				retvo.setPk_org_v(vo.getPk_org_v());
				retvo.setPk_customer(vo.getPk_customer());
				retvo.setEndcustomer(vo.getEndcustomer());
				retvo.setVsrcplanbid(vo.getPk_planbill_b());
				retvo.setVsrcplanid(vo.getPk_planbill());
				retvo.setPk_material(vo.getPk_material());
				retvo.setPk_bomid(vo.getBomid());
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
				retvo.setDef1(vo.getVbdef1());
				retvo.setDef2(vo.getVbdef2());
				retvo.setDef3(vo.getVbdef3());
				retvo.setDef4(vo.getVbdef4());
				retvo.setDef5(vo.getVbdef5());
				retvo.setDef6(vo.getVbdef6());
				retvo.setDef7(vo.getVbdef7());
				retvo.setDef8(vo.getVbdef8());
				retvo.setDef9(vo.getVbdef9());
				retvo.setDef10(vo.getVbdef10());
				retvo.setDef11(vo.getVbdef11());
				retvo.setDef12(vo.getVbdef12());
				retvo.setDef13(vo.getVbdef13());
				retvo.setDef14(vo.getVbdef14());
				retvo.setDef15(vo.getVbdef15());
				retvo.setDef16(vo.getVbdef16());
				retvo.setDef17(vo.getVbdef17());
				retvo.setDef18(vo.getVbdef18());
				retvo.setDef19(vo.getVbdef19());
				retvo.setDef20(vo.getVbdef20());
				retvo.setBillstatus(Billstatus.INIT.toIntValue());
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
				retvo.setQunit(vo.getMunit());
				retvo.setRate(vo.getVchangerate());
				retvo.setProdnum(vo.getPlannum().sub(vo.getTotalnum()));
				retvo.setProdnastnum(vo.getPlannastnum().sub(vo.getTotalnastuum()));
				retvo.setTintoprodnum(UFDouble.ZERO_DBL);
				retvo.setTintopronastnum(UFDouble.ZERO_DBL);
				retvo.setProddate(new UFDateTime(InvocationInfoProxy.getInstance().getBizDateTime()));
				retvo.setEnddate(new UFDateTime(InvocationInfoProxy.getInstance().getBizDateTime()));

				return retvo;
			}
			
		});
		
		initCellRenderer(this.billListPanel);
		
		getBillListPanel().getParentListPanel().getTable().getSelectionModel().addListSelectionListener(this);
		
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
			
			String pk=(String) getBillListPanel().getParentListPanel().getTableModel().getValueAt(headRow, "pk_planbill_b");
			
			Collection<RdPorductDetailVO> col= this.getMDQueryService().queryBillOfVOByCond(RdPorductDetailVO.class, "vsrcplanbid='"+pk+"'", false);
			
			if(col!=null && col.size()>0){
				
				this.getRdmodel().initModel(col.toArray(new RdPorductDetailVO[col.size()]));
				
			}else{
				
				this.getRdmodel().initModel(null);
				this.getIpModel().initModel(null);
			}
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		
	
		
		
	}

	@Override
	public OnhandDimParamVO getQryOnhandDim(int row) {
		// TODO 自动生成的方法存根
		

		try {
			
			IMaterialPubService service = (IMaterialPubService)NCLocator.getInstance().lookup(IMaterialPubService.class);
						
			DefaultConstEnum material1=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "pk_material");
			DefaultConstEnum group=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "pk_group");
			DefaultConstEnum org=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "pk_org");
			DefaultConstEnum unitenum=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "munit");
			DefaultConstEnum prodenum=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "cproductorid");
			DefaultConstEnum projenum=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "cprojectid");
			DefaultConstEnum cvenenum=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "cvendorid");
			DefaultConstEnum org_v=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "pk_org_v");
			DefaultConstEnum vfree1=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree1");
			DefaultConstEnum vfree2=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree2");
			DefaultConstEnum vfree3=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree3");
			DefaultConstEnum vfree4=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree4");
			DefaultConstEnum vfree5=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree5");
			DefaultConstEnum vfree6=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree6");
			DefaultConstEnum vfree7=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree7");
			DefaultConstEnum vfree8=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree8");
			DefaultConstEnum vfree9=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree9");
			DefaultConstEnum vfree10=(DefaultConstEnum)this.getBillListPanel().getParentListPanel().getTableModel().getValueObjectAt(row, "vfree10");
			
			Map<String, MaterialVO> matermap;
			
			matermap = service.queryMaterialBaseInfoByPks(new String[]{(String) material1.getValue()},new String[]{"pk_source"});
			String pk_material_v= matermap.get((String) material1.getValue()).getPk_source();
			
			OnhandDimParamVO paraVO = new OnhandDimParamVO();
			
			paraVO.setPk_group(group==null?null:group.getValue().toString());
			paraVO.setCastunitid(unitenum==null?null:unitenum.getValue().toString());
			paraVO.setCmaterialoid(material1==null?null:material1.getValue().toString());
			paraVO.setCmaterialvid(pk_material_v);
			paraVO.setCproductorid(prodenum==null?null:prodenum.getValue().toString());
			paraVO.setCprojectid(projenum==null?null:projenum.getValue().toString());
			paraVO.setCvendorid(cvenenum==null?null:cvenenum.getValue().toString());
			paraVO.setPk_org(org==null?null:org.getValue().toString());
			paraVO.setPk_org_v(org_v==null?null:org_v.getValue().toString());
			paraVO.setVchangerate((String) getBillListPanel().getParentListPanel().getTableModel().getValueAt(row, "vchangerate"));
			paraVO.setVfree1(vfree1==null?null:vfree1.getValue().toString());
			paraVO.setVfree2(vfree2==null?null:vfree2.getValue().toString());
			paraVO.setVfree3(vfree3==null?null:vfree3.getValue().toString());
			paraVO.setVfree4(vfree4==null?null:vfree4.getValue().toString());
			paraVO.setVfree5(vfree5==null?null:vfree5.getValue().toString());
			paraVO.setVfree6(vfree6==null?null:vfree6.getValue().toString());
			paraVO.setVfree7(vfree7==null?null:vfree7.getValue().toString());
			paraVO.setVfree8(vfree8==null?null:vfree8.getValue().toString());
			paraVO.setVfree9(vfree9==null?null:vfree9.getValue().toString());
			paraVO.setVfree10(vfree10==null?null:vfree10.getValue().toString());
			
			return paraVO;
			
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappException(e);
		}
		
		

		
		
		
		return null;
	}

	@Override
	public void addCardBodyRowChangedEvent(IAppEventHandler<CardBodyRowChangedEvent> l) {
		// TODO 自动生成的方法存根
		((IAppModelEx)getModel()).addAppEventListener(CardBodyRowChangedEvent.class, l);
	}
	
	

}
