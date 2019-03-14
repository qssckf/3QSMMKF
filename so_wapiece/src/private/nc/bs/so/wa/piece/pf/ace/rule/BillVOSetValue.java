package nc.bs.so.wa.piece.pf.ace.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.wa.piece.PieceProductVO;


public class BillVOSetValue implements IRule<PieceProductVO>{
	
	private String[] headField={};
	private String[] headValue={};

	private BaseDAO getBaseDao() {
		// TODO 自动生成的方法存根
		return new BaseDAO();
	}

	public String[] getHeadField() {
		return headField;
	}


	public void setHeadField(String[] headField) {
		this.headField = headField;
	}


	public String[] getHeadValue() {
		return headValue;
	}


	public void setHeadValue(String[] headValue) {
		this.headValue = headValue;
	}


	public BillVOSetValue(){
		
	}

	@Override
	public void process(PieceProductVO[] objs) {
		// TODO 自动生成的方法存根
		
		if(this.headField.length>0){
			
			for(PieceProductVO obj:objs){
				
				for(int i=0;i<headField.length;i++){
					obj.setAttributeValue(headField[i], headValue[i]);
				}
				obj.setStatus(1);
				
				try {
					getBaseDao().updateVO(obj,this.getHeadField());
				} catch (DAOException e) {
					// TODO 自动生成的 catch 块
					ExceptionUtils.wrappException(e);
				}
			}
			
		}
		
		
	}

}
