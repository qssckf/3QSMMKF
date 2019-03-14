package nc.vo.so.qs.appinterface.query;

public class PreOrderQuery extends AbstractPreOrderQuery{

	@Override
	public String getPksSql() {
		// TODO 自动生成的方法存根
		
		String maker = getCuserid();
		String pk_group = getPk_group();
		String date=getDate();
		
		String sql = getBaseSql().replace("#billmaker#", maker).replace("#pk_group#", pk_group).replace("#date#", date);
		
		return sql;
		
	}

}
