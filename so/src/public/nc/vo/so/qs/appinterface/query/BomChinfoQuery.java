package nc.vo.so.qs.appinterface.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.bd.pub.sqlutil.BDSqlInUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.qs.appinterface.util.BillQuery;
import nc.vo.so.qs.appinterface.util.TaskMetaData;

public class BomChinfoQuery extends BillQuery{
	
	private String Bomid;
	private String pk_org;
	private DataAccessUtils dao;
	
	public DataAccessUtils getDao() {
    	
    	if(dao==null){
    		dao=new DataAccessUtils();
    	}
		return dao;
	}

	public String getBomid() {
		return Bomid;
	}

	public void setBomid(String bomid) {
		Bomid = bomid;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	
	

	@Override
	public String getIdentifier() {
		// TODO 自动生成的方法存根
		StringBuffer sb = new StringBuffer();
		 

		sb.append(getPk_group());
		sb.append(getCuserid());
		sb.append(this.getPk_org());
		sb.append(this.getBomid());
	 
		return sb.toString();
	}

	@Override
	public TaskMetaData queryTaskMetaData(String paramString)
			throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getPksSql() {
		// TODO 自动生成的方法存根
		String bomid=this.getBomid();
		String pk_org=this.getPk_org();
		
		String sql = getBaseSql().replace("#cbomid#", bomid).replace("#pk_org#", pk_org);
		
		return sql;
		

	}

	private String getBaseSql() {
		// TODO 自动生成的方法存根
		
		String sql="select cbomid from bd_bom where cbomid='#cbomid#' and pk_org='#pk_org#' and dr=0;";
		return sql;
	}

	@Override
	public List<Map<String, Object>> queryByPks(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String inSql=BDSqlInUtil.getInSql(pks, false);
		
		String sql="select hcmaterialid pk_materail,parentmatcode,parentmatname,matercode,matername,cbomversion,materspec,matertype,level bomlevel,munit,nitemnum itemnum,vnote itemmemo,munitid,qunitid,c_materail,rate from ";
		sql=sql+"(select a.pk_org,b.cbom_bid,a.cbomid,a.hcmaterialid ,mar.code parentmatcode,mar.name parentmatname,a.hversion,b.vitemversion,bbom.hversion cbomversion,b.cmaterialid,cmar.code matercode,cmar.name matername,cmar.materialspec materspec,cmar.materialtype matertype,unit.name munit,b.nitemnum,b.vnote,b.cmeasureid munitid,b.cassmeasureid qunitid,b.cmaterialid c_materail,b.vfree1 kz,b.vfree2 ys,b.vchangerate rate ";
		sql=sql+"from bd_bom a ";
		sql=sql+"inner join bd_bom_b b on a.cbomid=b.cbomid ";
		sql=sql+"left join bd_material mar on a.hcmaterialid=mar.pk_material ";
		sql=sql+"left join bd_material cmar on b.cmaterialid=cmar.pk_material ";
		sql=sql+"left join bd_bom bbom on b.vitemversion=bbom.cbomid ";
		sql=sql+"left join bd_measdoc unit on b.cmeasureid=unit.pk_measdoc ";
		sql=sql+"where a.dr=0 and b.dr=0) start with cbomid in "+inSql+" connect by prior vitemversion=cbomid order by level";
		
		
		IRowSet row=this.getDao().query(sql);
		
		List<Map<String, Object>> retlist=new ArrayList();
		
		while(row.next()){
			
			Map<String, Object> item=new HashMap();
			item.put("fmat", row.getString(0));
			item.put("fmatcode", row.getString(1));
			item.put("fmatname", row.getString(2));
			item.put("cmatcode", row.getString(3));
			item.put("cmatname", row.getString(4));
			item.put("cbomver", row.getString(5));
			item.put("materspec", row.getString(6));
			item.put("matertype", row.getString(7));
			item.put("bomlevel", row.getString(8));
			item.put("munit", row.getString(9));
			item.put("itemnum", row.getUFDouble(10));
			item.put("itemmemo", row.getString(11));
			
			retlist.add(item);
			
		}
		
		return retlist;
	}
	
	

}
