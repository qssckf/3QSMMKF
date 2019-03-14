package nc.impl.so.wa.piece.fetch;

import java.util.ArrayList;
import java.util.Collection;

import nc.hr.frame.persistence.AppendBeanArrayProcessor;
import nc.impl.pub.ace.AcePieceFetchInfoVOPubServiceImpl;
import nc.bs.bd.baseservice.md.BatchBaseService;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.so.wa.piece.fetch.ace.rule.DataUniqueCheckRule;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.md.MDBaseQueryFacade;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.hr.append.IAppendableVO;
import nc.tool.so.wa.fetchdata.FetchDataMappingMeta;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.db.MMSqlBuilder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.smart.SmartDefVO;
import nc.vo.so.wa.piece.fetch.PieceFetchInfoVO;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;

public class PieceFetchInfoVOMaintainImpl extends AcePieceFetchInfoVOPubServiceImpl implements nc.itf.so.wa.piece.fetch.IPieceFetchInfoVOMaintain {

  private BaseDAO baseDao;
  private DataAccessUtils dataacc;
  private BatchOperateVO batchVO;
  private String beanid;

@Override
  public PieceFetchInfoVO[] query(IQueryScheme queryScheme)
      throws BusinessException {
      return super.pubquerybasedoc(queryScheme);
  }


  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
    BatchSaveAction<PieceFetchInfoVO> saveAction = new BatchSaveAction<PieceFetchInfoVO>();
    BatchOperateVO retData = saveAction.batchSave(batchVO);
    //调用编码、名称唯一性校验规则
    new DataUniqueCheckRule().process(new BatchOperateVO[] {
      batchVO    });
    return retData;
  }


  @Override
  public SmartDefVO querySmartDefByPK(String pk_def) throws BusinessException,Exception {
	// TODO 自动生成的方法存根
	
	  MMSqlBuilder wheresql = new MMSqlBuilder();
	  wheresql.append("pk_def", pk_def);
	  Collection<SmartDefVO> Vos = null;
	  
	  try {
		  
		  Vos = MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(SmartDefVO.class, wheresql.toString(), false);
	  
	  }catch (MetaDataException e){
		  
			 ExceptionUtils.wrappException(e);
	  }
		 
	  SmartDefVO[] smarts=(SmartDefVO[])Vos.toArray(new SmartDefVO[0]);
	  return smarts[0];
  }


  @Override
  public SmartDefVO[] querySmartDef() throws BusinessException, Exception {
	// TODO 自动生成的方法存根
	  
	  MMSqlBuilder wheresql = new MMSqlBuilder();
	  wheresql.append("pk_dir", "1001AF1000000008Z7GL");
	  
	  Collection<SmartDefVO> Vos = null;
	  try{
		  Vos = MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(SmartDefVO.class, wheresql.toString(), false);
	  }catch (MetaDataException e){
			 ExceptionUtils.wrappException(e);
	  }
	  
	  return (SmartDefVO[])Vos.toArray(new SmartDefVO[0]);

  }


  @Override
  public IRowSet QueryFetchRecord(String id) throws BusinessException {
	// TODO 自动生成的方法存根
	  
	  DataAccessUtils dao = new DataAccessUtils();
	  String sql="select pk_ftid from so_piecefetchrecordvo where pk_ruleid='"+id+"'";
	  IRowSet rowset = dao.query(sql);	
	  return rowset;
	  
  }

  private BaseDAO getBaseDao() {
		// TODO 自动生成的方法存根
		if (this.baseDao == null)
		{
			this.baseDao = new BaseDAO();
		}
		return this.baseDao;
  }

  @Override
  public void DeleteExistsRecords(Class c, String condition) throws BusinessException {
	// TODO 自动生成的方法存根
	  this.getBaseDao().deleteByClause(c, condition);
  }


  @Override
  public boolean CheckPeriodCalcSF(String pk_org, String accountpeirod) throws BusinessException {
	// TODO 自动生成的方法存根
	
	  MMSqlBuilder sql = new MMSqlBuilder();
	  sql.append("select pk_wadataid from so_pwdata");
	  sql.append("where dr=0 and ");
	  sql.append("pk_org",pk_org);
	  sql.append(" and ");	
	  sql.append("calcsf",UFBoolean.TRUE);
	  sql.append(" and ");
	  sql.append("cmonth",accountpeirod);
	
	  IRowSet set = this.getDataBaseHandle().query(sql.toString());
		
	  if (set.next()){
			return false;
	  }
		
	  return true;
  }
  
  public DataAccessUtils getDataBaseHandle(){
		if (this.dataacc==null){
			this.dataacc=new DataAccessUtils();
		}
		return this.dataacc;
  }
  
  
  private VOQuery getVOQueryService(Class c) {
		// TODO 自动生成的方法存根
		return new VOQuery(c);
  }

  @Override
  public ArrayList<String> QueryTableCodeRecord(Class c, String condition) throws BusinessException {
	// TODO 自动生成的方法存根
	
	String cname;
	SuperVO[] svo=(SuperVO[]) getVOQueryService(c).query(condition, null);

	ArrayList<String> code=new ArrayList<String>();
	
	if (svo.length>0){
		cname=(String)svo[0].getAttributeValue("code");
		for(int i=0;i<svo.length;i++){
			if(i==0){
				code.add((String)svo[i].getAttributeValue("code"));
			}else{
				if (!((String)svo[i].getAttributeValue("code")).equals(cname)){
					cname=(String)svo[i].getAttributeValue("code");
					code.add((String)svo[i].getAttributeValue("code"));
				}
			}
		}
	}

	return code;
  }


  @Override
  public Boolean InsertFecthDataTable(String sql, String classname)throws BusinessException {
	// TODO 自动生成的方法存根
	  
	  	Class c = null; 	
		try {
			c= Class.forName(classname);
		 } catch (ClassNotFoundException e) {
					ExceptionUtils.wrappBusinessException("找不到类型名为：" + classname + " 的类型！");
		}
		
		IAppendableVO[] vos=this.executeQueryVOs(sql, c);
		
		if(vos==null){
			return false;
		}else{
			if(vos.length>0){
				this.getBaseDao().insertObject(vos, new FetchDataMappingMeta(c));
				return true;
			}
		}
		
		return true;
  }
  
  private IAppendableVO[] executeQueryVOs(String sql, Class c) {
		// TODO 自动生成的方法存根

		try {
			IAppendableVO[] vos = (IAppendableVO[])getBaseDao().executeQuery(sql,  new AppendBeanArrayProcessor(c));
			
			return vos;
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
  }

  private BatchOperateVO getBatchOperateService() {
		// TODO 自动生成的方法存根
		if(this.batchVO==null){
			this.batchVO=new BatchOperateVO();
		}
		return this.batchVO;
  }

  @Override
  public void BatchVOSave(PieceFetchRecordVO[] vos) throws BusinessException {
	// TODO 自动生成的方法存根
	  
	  this.getBatchOperateService().setAddObjs(vos);
	  BatchBaseService<PieceFetchRecordVO> bs=new BatchBaseService<PieceFetchRecordVO>(this.getBeanID());
	  BatchOperateVO bills=bs.batchSave(this.getBatchOperateService());
	
  }
  
  private String getBeanID() throws MetaDataException {
		// TODO 自动生成的方法存根
		if (this.beanid==null){
			this.beanid=MDBaseQueryFacade.getInstance().getBeanByFullClassName(PieceFetchRecordVO.class.getName()).getID();
		}
		return this.beanid;
  }


  @Override
  public SuperVO[] getDetailInfoVO(Class c, String condition) throws BusinessException {
	// TODO 自动生成的方法存根
	
	  SuperVO[] svo=(SuperVO[]) getVOQueryService(c).query(condition, null);
	  return svo;

  }
  
}
