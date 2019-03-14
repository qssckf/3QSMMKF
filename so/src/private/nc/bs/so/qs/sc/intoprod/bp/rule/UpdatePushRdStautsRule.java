package nc.bs.so.qs.sc.intoprod.bp.rule;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class UpdatePushRdStautsRule implements IRule<RdPorductDetailVO>{

	private BaseDAO dao;
	
	public BaseDAO getDao() {
		
		if(dao==null){
			this.dao=new BaseDAO();
		}
		
		return dao;
	}
	
	@Override
	public void process(RdPorductDetailVO[] objs) {
		// TODO 自动生成的方法存根
		
		try{
			
			for(RdPorductDetailVO obj:objs){
				
				obj.setStatus(1);
				
				this.getDao().updateVO(obj, new String[]{"billstatus","sfmakebill","def2"});
				
				
			}
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		
	
		
		
	}
	
	

}
