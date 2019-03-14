package nc.ui.so.wa.piece.action;

import nc.ui.uif2.actions.batch.BatchAddLineAction;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.so.wa.piece.FormulaDefine;

public class PieceFormulaDefineAddLineAction extends BatchAddLineAction{

	@Override
	protected void setDefaultData(Object obj) {
		// TODO 自动生成的方法存根
		super.setDefaultData(obj);
		UFDateTime df=new UFDateTime();
		
		FormulaDefine baseDocVO=(FormulaDefine)obj;
		baseDocVO.setPk_group(this.getModel().getContext().getPk_group());
		baseDocVO.setPk_org(this.getModel().getContext().getPk_org());
		baseDocVO.setCreationtime(df.getDateTimeAfter(0));
		baseDocVO.setCreator(this.getModel().getContext().getPk_loginUser());
		
	}
	
	

}
