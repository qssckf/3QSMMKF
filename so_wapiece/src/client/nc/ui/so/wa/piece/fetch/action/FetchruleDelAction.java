package nc.ui.so.wa.piece.fetch.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.fetch.IPieceFetchInfoVOMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.batch.BatchDelLineAction;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.wa.piece.fetch.PieceFetchInfoVO;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;

public class FetchruleDelAction extends BatchDelLineAction {

	private IPieceFetchInfoVOMaintain IPfetchCreateDataService;
	


	@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent)
			throws Exception {
		// TODO �Զ����ɵķ������
		
		PieceFetchInfoVO bd=(PieceFetchInfoVO) this.getModel().getSelectedData();
		
		IRowSet rows=this.getFetchDataService().QueryFetchRecord(bd.getPk_fetchid());
		
		if(rows.size()>0){
			int msg=MessageDialog.showOkCancelDlg(this.getBatchBillTable(), "����", "����ѡ��ļ�¼�Ѿ�����ȡ����¼�����ѡ��ɾ����ǿ�����ҵ��ȡ����¼���Ƿ������");
			if(msg==1){
				this.getFetchDataService().DeleteExistsRecords(PieceFetchRecordVO.class, "pk_ruleid='"+bd.getPk_fetchid()+"'");
				Class clazz1=Class.forName(bd.getVoclass1());
				this.getFetchDataService().DeleteExistsRecords(clazz1, "pk_fetchid='"+bd.getPk_fetchid()+"'");
				Class clazz2=Class.forName(bd.getVoclass2());
				this.getFetchDataService().DeleteExistsRecords(clazz2, "pk_fetchid='"+bd.getPk_fetchid()+"'");
			}else{
				return false;
			}
		}
		
		return super.beforeStartDoAction(actionEvent);
	}







	public IPieceFetchInfoVOMaintain getFetchDataService(){
		if (this.IPfetchCreateDataService==null){
			this.IPfetchCreateDataService=(IPieceFetchInfoVOMaintain)NCLocator.getInstance().lookup(IPieceFetchInfoVOMaintain.class);
			
		}
		return this.IPfetchCreateDataService;
	}






}
