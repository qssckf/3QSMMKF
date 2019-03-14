package nc.ui.so.wa.piece.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.IPieceWaMaintain;
import nc.ui.uif2.actions.batch.BatchDelLineAction;
import nc.vo.pub.BusinessException;
import nc.vo.so.wa.piece.FormulaDefine;

public class PieceFormulaDefineDelLineAction extends BatchDelLineAction{
	
	private IPieceWaMaintain queryserivce;

	public IPieceWaMaintain getQueryserivce() {
		
		if(this.queryserivce==null){
			this.queryserivce=NCLocator.getInstance().lookup(IPieceWaMaintain.class);
		}
		return queryserivce;
	}

	@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent) throws Exception {
		// TODO �Զ����ɵķ������
		
		FormulaDefine fd;
		
		fd=(FormulaDefine) this.getModel().getSelectedData();
		
		if(!fd.getPk_wpid().isEmpty()){
			
			if(this.getQueryserivce().CheckPieceFormulaExist(fd.getPk_wpid())){
				throw new BusinessException("�Ƽ���ʽ�Ѿ����ã�����ɾ����");
			}else{
				return super.beforeStartDoAction(actionEvent); 
			}
			
		}else{
			return false;
		}
		
	}
	
	
	

}
