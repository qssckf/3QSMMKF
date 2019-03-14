package nc.bs.so.wa.piece.pf.ace.rule;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.wa.piece.PieceProductVO;

public class BillSetDefaultValueRule implements IRule<PieceProductVO>{

	private BaseDAO bao;
	
	public BaseDAO getBao() {
		
		if(bao==null){
			this.bao=new BaseDAO();
		}
		return bao;
	}
	
	@Override
	public void process(PieceProductVO[] vos) {
		// TODO 自动生成的方法存根
		
		if(vos!=null && vos.length>0){
			
			String pk_org=vos[0].getPk_org();
			
			String sql=bulidSQL(vos[0].getTableName(),"isdefault","pk_org",pk_org);
			
			try {
				getBao().executeUpdate(sql);
			} catch (DAOException e) {
				// TODO 自动生成的 catch 块
				ExceptionUtils.wrappException(e);
			}
			
		}
		
		
		
		
	}
	
	public String bulidSQL(String table,String defaultfield,String pkorgfield,String pk_org){
		
		String sql = "update %1$s set %2$s='N' where %3$s='%4$s' and dr=0 ";
		sql = String.format(sql, new Object[] { table,defaultfield,pkorgfield,pk_org});
		
		return sql;
	}

}
