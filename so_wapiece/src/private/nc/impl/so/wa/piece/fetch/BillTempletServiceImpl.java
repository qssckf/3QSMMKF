package nc.impl.so.wa.piece.fetch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.bs.pub.bill.BillTempletDAO;
import nc.itf.so.wa.piece.fetch.IBillTempletService;
import nc.itf.uap.template.assign.TemplateAssignPara;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.BeanMappingListProcessor;
import nc.jdbc.framework.processor.BeanMappingProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.bill.BillTemplateRuntimeException;
import nc.vo.pub.bill.BillTempletBodyVO;
import nc.vo.pub.bill.BillTempletBodyVOMeta;
import nc.vo.pub.bill.BillTempletHeadVO;
import nc.vo.pub.bill.BillTempletHeadVOMeta;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.pub.pftemplate.SystemplateVO;

public class BillTempletServiceImpl implements IBillTempletService {

	 public static String LIST = "list";
	 public static String CARD = "card";
	 public static String ALL = "all";
	 public static String NODEF = "nodef";
	private BaseDAO basedao;
	private JdbcSession jdbcsession;
	private PersistenceManager sessionManager;


	private String getTemplateAvailable(String id) throws BusinessException
	{
		String where = "funnode='"+id+"'";
		Collection<?> vos = getBasedao().retrieveByClause(SystemplateVO.class, where);
		if (isEmpty(vos)) return null;
		SystemplateVO[] systemvo=(SystemplateVO[])vos.toArray(new SystemplateVO[0]);
		return systemvo[0].getTemplateid();
	}

	private BaseDAO getBasedao() {
		// TODO 自动生成的方法存根
		 if (this.basedao == null) {
			 this.basedao = new BaseDAO();
		 }
		 return this.basedao;
	}

	  private <T> boolean isEmpty(Collection<T> c) {
		  return (c == null) || (c.size() == 0);
	  }

	@Override
	public BillTempletVO queryByID(String id, String corp)
			throws BusinessException, SQLException {
		// TODO 自动生成的方法存根
		
		BillTempletVO btVO =findTempletData(this.getTemplateAvailable(id), null, corp);
		
		return btVO;
	}
	
	public BillTempletVO findTempletData(String id, String option, String pkCorp)
	{
		if (id == null) {
			return null;
		}
		BillTempletVO billtemplet = null;
		
		BillTempletHeadVO headVO = findBillTempletHead(id);
		
		if (headVO == null) {
			return null;
		}
		
		BillTempletBodyVO[] bodyVOs = findBillTempletBodys(id, option, pkCorp);

		billtemplet = new BillTempletVO(headVO, bodyVOs);

		
		return billtemplet;
	}

	private BillTempletBodyVO[] findBillTempletBodys(String id, String option,
			String pkCorp) {
		// TODO 自动生成的方法存根
		try {
			if (id == null) {
				return null;
			}
			
			if (id.length() < 20) {
				id = id + getSpaceString(20 - id.length());
			}

			StringBuffer sb = new StringBuffer("select ").append(BillTempletBodyVO.getPkFieldName());

			sb.append(",");

			sb.append(BillTempletBodyVO.getFkFieldName());

			String[] names = BillTempletBodyVO.getTableFieldNames();

			for (int i = 0; i < names.length; i++) {
				sb.append(",").append(names[i]);
			}

			sb.append(" from pub_billtemplet_b where pk_billtemplet = ? ");
			
			if (NODEF.equals(option)) {
				sb.append(" and (isnull(pk_corp,'~')='~' or pk_corp='@@@@')");
			}
			
			if (!ALL.equals(option))
			{
				if (CARD.equals(option)) {
					sb.append(" and cardflag = 1 ");
			} else if (LIST.equals(option))
				sb.append(" and listflag = 1 ");
				sb.append(" and (isnull(cast(itemtype as char),'~')='~' or itemtype=0 or itemtype=1 or itemtype=2)");

				sb.append(" and (isnull(pk_corp,'~')='~' or pk_corp='@@@@'");
				if (pkCorp != null) {
					sb.append(" or pk_corp='").append(pkCorp).append("'");
					if (!"0001".equals(pkCorp))
						sb.append(" or pk_corp='0001'");
				}
				sb.append(")");
			}
			
			sb.append(" order by pos,showorder,pk_billtemplet_b");
			String strFollowSql = sb.toString();

			SQLParameter parameter = new SQLParameter();

			parameter.addParam(id);

			ArrayList abodyVO = (ArrayList)getJdbcsession().executeQuery(strFollowSql, parameter, new BeanMappingListProcessor(BillTempletBodyVO.class, new BillTempletBodyVOMeta()));

			BillTempletBodyVO[] bodyvos = new BillTempletBodyVO[abodyVO.size()];
			bodyvos = (BillTempletBodyVO[])abodyVO.toArray(bodyvos);
			return bodyvos;
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new BillTemplateRuntimeException(e.getMessage());
		} finally {
			release();
		}
	}

	private BillTempletHeadVO findBillTempletHead(String id) {
		// TODO 自动生成的方法存根
		try {
			if (id == null) {
				return null;
			}
			
			if (id.length() < 20) {
				id = id + getSpaceString(20 - id.length());
			}

			String strMainSql = "select pk_billtemplet,pk_billtypecode,pk_corp,bill_templetname,bill_templetcaption,model_type,options,shareflag,nodecode,resid,ts,metadataclass,modulecode,layer,pk_org from pub_billtemplet where pk_billtemplet = ?";

			SQLParameter parameter = new SQLParameter();

			parameter.addParam(id);


			BillTempletHeadVO headVO = (BillTempletHeadVO)getJdbcsession().executeQuery(strMainSql, parameter, new BeanMappingProcessor(BillTempletHeadVO.class, new BillTempletHeadVOMeta()));


			return headVO;

		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new BillTemplateRuntimeException(e.getMessage());
		} finally {
			release();
		}
	}
	
	private String getSpaceString(int len) {
		StringBuffer s = new StringBuffer();
		
		for (int i = 0; i < len; i++) {
			s.append(" ");
		}
		
		return s.toString();
	}
	
	private JdbcSession getJdbcsession() throws DbException
	{
		if (this.jdbcsession == null) {
			this.sessionManager = PersistenceManager.getInstance();
			this.jdbcsession = this.sessionManager.getJdbcSession();
		}
		return this.jdbcsession;
	}
	
	public void release() {
		try {
			this.jdbcsession = null;
			if (this.sessionManager != null) {
				this.sessionManager.release();
			}
		}catch (Exception e) {}
	}
}
