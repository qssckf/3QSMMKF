package nc.vo.so.qs.appinterface.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.cmp.tools.StringUtil;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.bd.pub.sqlutil.BDSqlInUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.qs.appinterface.util.BillQuery;
import nc.vo.so.qs.appinterface.util.TaskMetaData;

public class PreOrderExecQuery extends BillQuery{
	
	private DataAccessUtils dao;
	
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
		String maker = getCuserid();
		String pk_group = getPk_group();
		
		String sql = getBaseSql().replace("#billmaker#", maker).replace("#pk_group#", pk_group);
		
		return sql;
	}

	@Override
	public List<Map<String, Object>> queryByPks(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String inSql=BDSqlInUtil.getInSql(pks, false);
		
		String sql="select m.billdate,m.ksname,m.matname,m.materialspec,m.kzname,m.orgname,m.ynastnum,m.hsprice,m.jshj,m.fnastnum,n.sonastnum,m.wfnastnum from";
		sql=sql+" (select b.cpreorderbid,a.vbillcode,substr(a.dbilldate,0,10) billdate,ks.name ksname,mat.name matname,mat.materialspec,kz.name kzname,org.name orgname,b.nastnum ynastnum,b.nqtorigtaxprice hsprice,b.norigtaxmny jshj,sum(nvl(c.nastnum,0)) fnastnum,b.nastnum-sum(nvl(c.nastnum,0)) wfnastnum from so_preorder a";
		sql=sql+" left join so_preorder_b b on a.cpreorderid=b.cpreorderid and b.dr=0";
		sql=sql+" left join so_shipments_b c on c.csrcbid=b.cpreorderbid and c.dr=0";
		sql=sql+" inner join bd_cust_supplier ks on a.vdef1=ks.pk_cust_sup";
		sql=sql+" inner join bd_material mat on b.cmaterialid=mat.pk_material";
		sql=sql+" left join bd_defdoc kz on b.vfree1=kz.pk_defdoc";
		sql=sql+" left join org_orgs org on a.pk_org=org.pk_org";
		sql=sql+" where b.cpreorderbid in "+inSql;
		sql=sql+" group by  b.cpreorderbid,a.vbillcode,substr(a.dbilldate,0,10),ks.name,mat.name,mat.materialspec,kz.name,org.name,b.nastnum,b.nqtorigtaxprice,b.norigtaxmny)m";
		sql=sql+" left join (select nvl(c.csrcbid,d.csrcbid) csrcbid,d.cfirstbid,sum(d.nastnum) sonastnum from ";
		sql=sql+" so_shipments_b c full join so_saleorder_b d on c.pk_shipments_b = d.csrcbid ";
		sql=sql+" where nvl(c.dr,0)=0 and nvl(d.dr,0)=0";
		sql=sql+" group by nvl(c.csrcbid,d.csrcbid),d.cfirstbid) n on m.cpreorderbid=n.csrcbid";
		sql=sql+" order by substr(m.billdate,0,10) desc;";
		
		IRowSet row=this.getDao().query(sql);
		
		List<Map<String, Object>> retlist=new ArrayList();
		
		while(row.next()){
			
			Map<String, Object> item=new HashMap();
			
//			item.put("billdate", row.getString(0));
//			item.put("ks", row.getString(1));
//			item.put("mat", row.getString(2));
//			item.put("spec", row.getString(3));
//			item.put("kz", row.getString(4));
//			item.put("pk_org", row.getString(5)==null?'空':row.getString(5));
//			item.put("prenum", row.getString(6));
//			item.put("fhnum", row.getString(7));
//			item.put("prenastnum", row.getString(8));
//			item.put("fhnastnum", row.getString(9));
//			item.put("wfnum", row.getString(10));
//			item.put("wfnastnum", row.getString(11));
			
			item.put("billdate", row.getString(0));
			item.put("ks", row.getString(1));
			item.put("mat", row.getString(2));
			item.put("spec", row.getString(3));
			item.put("kz", row.getString(4));
			item.put("pk_org", row.getString(5)==null?'空':row.getString(5));
			item.put("prenastnum", row.getString(6));
			item.put("hsprice", row.getString(7));
			item.put("jshj", row.getString(8));
			item.put("fnastnum", row.getString(9));
			item.put("sonastnum", row.getString(10));
			item.put("wfnastnum", row.getString(11));

			retlist.add(item);

		}
		
		return retlist;
	}
	
	private String getBaseSql() {
		// TODO 自动生成的方法存根
		
		if (!StringUtil.isEmptyWithTrim(getCondition()))
	    {
			
	      String sql = "select so_preorder_b.cpreorderbid from so_preorder left join so_preorder_b on so_preorder.cpreorderid=so_preorder_b.cpreorderid where so_preorder.dr=0 and so_preorder_b.dr=0 and so_preorder.pk_group='#pk_group#' and so_preorder.billmaker='#billmaker#' and so_preorder.dr=0 and so_preorder_b.dr=0 and ("+this.getCondition()+") order by so_preorder.dbilldate desc";
	
	      return sql;
	    }
	    return "select so_preorder_b.cpreorderbid from so_preorder left join so_preorder_b on so_preorder.cpreorderid=so_preorder_b.cpreorderid where so_preorder.dr=0 and so_preorder_b.dr=0 and so_preorder.pk_group='#pk_group#' and so_preorder.billmaker='#billmaker#' and so_preorder.dr=0 and so_preorder_b.dr=0 order by so_preorder.dbilldate desc";
		

	}
	
	

}
