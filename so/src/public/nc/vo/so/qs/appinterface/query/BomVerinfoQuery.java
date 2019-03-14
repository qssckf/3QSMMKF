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

public class BomVerinfoQuery extends BillQuery {
	
	private DataAccessUtils dao;
	private String Material;
	private String pk_org;
	
	public String getMaterial() {
		return Material;
	}


	public void setMaterial(String material) {
		Material = material;
	}

	public String getPk_org() {
		return pk_org;
	}


	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}


	public DataAccessUtils getDao() {
    	
    	if(dao==null){
    		dao=new DataAccessUtils();
    	}
		return dao;
	}
	
	


	@Override
	public String getIdentifier() {
		// TODO 自动生成的方法存根
		StringBuffer sb = new StringBuffer();
		 

		sb.append(getPk_group());
		sb.append(getCuserid());
		sb.append(this.getPk_org());
		sb.append(this.getMaterial());
	 
		return sb.toString();
	}


	@Override
	public TaskMetaData queryTaskMetaData(String paramString) throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getPksSql() {
		// TODO 自动生成的方法存根
		
		String mat=this.getMaterial();
		String pk_org=this.getPk_org();
		
		String sql = getBaseSql().replace("#pk_material#", mat).replace("#pk_org#", pk_org);
		
		return sql;
	}

	private String getBaseSql() {
		// TODO 自动生成的方法存根
		
		String sql="select cbomid from bd_bom where hcmaterialid='#pk_material#' and pk_org='#pk_org#' and dr=0;";
		return sql;
	}


	@Override
	public List<Map<String, Object>> queryByPks(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String inSql=BDSqlInUtil.getInSql(pks, false);
		
		String sql="select bd_bom.pk_org,cbomid,hcmaterialid pk_material,bd_material.name matername,bd_material.materialspec materspec,bd_material.materialtype matertype,";
		sql=sql+"decode(bd_bom.fbomtype,1,'生产BOM',2,'包装BOM',3,'配置BOM') bomtype,bd_bom.hversion bomversion,bd_bom.hvnote memo from bd_bom";
		sql=sql+" left join bd_material on bd_bom.hcmaterialid=bd_material.pk_material";
		sql=sql+" where bd_bom.dr=0 and bd_bom.cbomid in "+inSql;
		
		IRowSet row=this.getDao().query(sql);
		
		List<Map<String, Object>> retlist=new ArrayList();
		
		while(row.next()){
			
			Map<String, Object> item=new HashMap();
			
			item.put("pk_org", row.getString(0));
			item.put("cbomid", row.getString(1));
			item.put("material", row.getString(2));
			item.put("matername", row.getString(3));
			item.put("materspec", row.getString(4));
			item.put("matertype", row.getString(5));
			item.put("bomtype", row.getString(6));
			item.put("bomversion", row.getString(7));
			item.put("memo", row.getString(8));
			
			retlist.add(item);
		}
		
		return retlist;
	}

}
