package nc.bs.so.qs.sc.intoprod.bp;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.RdPorductDetailVO;
import nc.vo.so.qs.sc.IntoProdDetailVO;

public class UpdatePushRdStautsRule implements IRule<IntoProdDetailVO>{

	private BaseDAO dao;
	
	public BaseDAO getDao() {
		
		if(dao==null){
			this.dao=new BaseDAO();
		}
		
		return dao;
	}
	
	@Override
	public void process(IntoProdDetailVO[] objs) {
		// TODO 自动生成的方法存根
		
		try{
			
			for(IntoProdDetailVO obj:objs){
				
				obj.setStatus(1);
				
				this.getDao().updateVO(obj, new String[]{"pomvbillcode","sfmakebill"});
				
				
			}
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		
	
		
		
	}
	
	

}
