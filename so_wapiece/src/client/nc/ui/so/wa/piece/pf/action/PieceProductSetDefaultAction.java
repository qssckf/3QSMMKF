package nc.ui.so.wa.piece.pf.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.IPieceWaMaintain;
import nc.itf.so.wa.piece.pf.IPieceProductVOMaintain;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.wa.piece.PieceProductVO;

public class PieceProductSetDefaultAction extends NCAction{
	
	private IPieceProductVOMaintain service;
	
	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}
	
	public IPieceProductVOMaintain getService() {
		
		if(this.service==null){
			this.service=NCLocator.getInstance().lookup(IPieceProductVOMaintain.class);
		}
		
		return this.service;
	}

	public PieceProductSetDefaultAction(){
		this.setBtnName("��ΪĬ��");
		this.setCode("SetDefault");
	}

	private BatchBillTableModel model = null;
	
	public BatchBillTableModel getModel() {
		return model;
	}

	public void setModel(BatchBillTableModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������

		Object[] vos=this.getModel().getSelectedOperaDatas();
		
		String pk_oldid=null;
		
		List<String> ids=new ArrayList();
		
		if(vos.length>0 && vos.length>1 ){
			throw new BusinessException("����ѡ�����һ���ļƼ���Ʒ��¼��������Ĭ�ϲ�����");
		}
		
		PieceProductVO vo=(PieceProductVO) this.getModel().getSelectedData();
		
		if(UFBoolean.TRUE.equals(vo.getIsdefault())){
			throw new BusinessException("��ǰѡ���¼�Ѿ�ΪĬ��״̬�������ظ����ã�");
		}
		
		pk_oldid=this.getService().isHaveDefault(vo.getPk_org());

		if(pk_oldid!=null){
			
			ids.add(pk_oldid);
			
			int res = MessageDialog.showYesNoDlg(getModel().getContext().getEntranceUI(),"����","��ǰ��֯�Ѿ����ڼƼ���Ʒ������Ĭ�ϲ����������������в�ƷĬ��״̬���Ƿ������");

			if(res==8){
				
				return;
				
			}

		}
		
		PieceProductVO ret=this.getService().setDefault(vo);


		ids.add(ret.getPk_ppid());
		
		Collection<PieceProductVO> cols=this.getMDQueryService().queryBillOfVOByPKs(PieceProductVO.class, ids.toArray(new String[ids.size()]), false);
		
		
		int rows=this.getModel().getRowCount();
		
		for(PieceProductVO retvo:cols){
			
			for(int i=0;i<rows;i++){
				
				PieceProductVO rowobj=(PieceProductVO) this.getModel().getRow(i);
				
				if(rowobj.getPk_ppid().equals(retvo.getPk_ppid())){
					
					this.getModel().updateLine(i, retvo);
					break;
				}
				
			}
			
		}
		
		ShowStatusBarMsgUtil.showStatusBarMsg("����״̬����ִ����ϣ�", this.getModel().getContext());
		
		
		
	}

	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		
		Integer[] rows=this.getModel().getSelectedOperaRows();
		
		Boolean sf=false;
		
		if(rows!=null && rows.length==1){
			
			sf=true;
			
		}
		
		return getModel().getUiState()==UIState.NOT_EDIT && sf;
		
	}

	
	
}
