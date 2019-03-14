package nc.ui.so.wa.piece.fetch.uidataset;

import java.sql.SQLException;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.so.wa.piece.fetch.ace.view.BusFetchDataMainPanel;
import nc.util.mmf.framework.base.MMStringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;
import nc.vo.so.wa.piece.fetch.UIAllDataVO;

public class PieceBusSetUIData {
	
	private BusFetchDataMainPanel DataMainPnl;
	private String pk_group;
	
	public BusFetchDataMainPanel getDataMainPnl() {
		return DataMainPnl;
	}

	public void setDataMainPnl(BusFetchDataMainPanel dataMainPnl) {
		DataMainPnl = dataMainPnl;
	}

	public String getPk_group() {
		return pk_group;
	}

	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}
	
	public void PieceBusSetUIData(){
		
	}
	
	public void setUIItemforFetchRule(BusFetchDataMainPanel parent,String refPK,String pk_group) throws SQLException{
		
		this.DataMainPnl=parent;
		this.pk_group=pk_group;
		
		HideAllColInitUI();
		
		if(MMStringUtil.isEmpty(refPK) || MMStringUtil.isEmpty(parent.getWaPeriodRefPane().getRefPK())){
			return;
		}
		
		Integer fetchtype=getSelectedFetchObj(parent);
		
		PieceFetchRecordVO paramvo=getParamVO(fetchtype);
		
		UIAllDataVO vos=null;
		
		try{
			
			PieceBusFetchService pieceService=new PieceBusFetchService();
			
			vos=pieceService.getVosUiDataSet(paramvo);
			
		}catch(BusinessException e){
			this.getDataMainPnl().getWaPeriodRefPane().setPK(null);
			 MessageDialog.showWarningDlg(this.DataMainPnl, "错误", e.getMessage());
			 HideAllColInitUI();
			 return;
		}
		
		
		setUIItem(vos);
	}
	
	private void setUIItem(UIAllDataVO vos) {
		// TODO 自动生成的方法存根
		setShowCol4Schema();
		
		setUIData(vos.getVos());		
		
	}
	
	private void setUIData(PieceFetchRecordVO[] vos){
		
		try{
			
			this.getDataMainPnl().getBillListView().getBillListPanel().getBodyBillModel().setBodyDataVO(vos);
			
		}catch(IllegalArgumentException e){
			ExceptionUtils.wrappException(e);
		}
		
	}
	
	private void setShowCol4Schema(){
		
		this.getDataMainPnl().getBillListView().getBillListPanel().showBodyTableCol("fetchsf");
		this.getDataMainPnl().getBillListView().getBillListPanel().showBodyTableCol("code");
		this.getDataMainPnl().getBillListView().getBillListPanel().showBodyTableCol("name");
		this.getDataMainPnl().getBillListView().getBillListPanel().showBodyTableCol("accountperiod");
		this.getDataMainPnl().getBillListView().getBillListPanel().showBodyTableCol("startdate");
		this.getDataMainPnl().getBillListView().getBillListPanel().showBodyTableCol("enddate");
		
	}
	
	private PieceFetchRecordVO getParamVO(Integer fetchtype){
		
		String period=null;
		period=this.getDataMainPnl().getWaPeriodRefPane().getRefPK();
		period=period.substring(0, 4)+"-"+period.substring(4);
		
		PieceFetchRecordVO paramvo=new PieceFetchRecordVO();
		paramvo.setPk_org(this.getDataMainPnl().getRefFactory().getRefPK());
		paramvo.setPk_group(this.getPk_group());
		paramvo.setFetchtype(fetchtype);
		paramvo.setAccountperiod(period);
		
		return paramvo;

		
	}
	
	private Integer getSelectedFetchObj(BusFetchDataMainPanel parent) {
		// TODO 自动生成的方法存根
		
		return this.getDataMainPnl().getFetchDataObj().getSelectedIndex()+1;
	}

	private void HideAllColInitUI() {
		// TODO 自动生成的方法存根
		
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("pk_group");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("pk_org");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("pk_org_v");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("pk_ftid");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("pk_ruleid");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("code");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("name");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("fetchsf");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("accountperiod");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("voclass1");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("voclass2");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("smartdef1");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("smartdef2");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("startdate");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("enddate");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def1");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def2");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def3");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def4");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def5");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def6");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def7");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def8");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def10");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def11");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def12");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def13");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def14");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def15");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def16");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def17");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def18");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def19");
		this.DataMainPnl.getBillListView().getBillListPanel().hideBodyTableCol("def20");
		

	}

}
