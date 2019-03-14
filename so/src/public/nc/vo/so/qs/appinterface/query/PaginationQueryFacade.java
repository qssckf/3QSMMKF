package nc.vo.so.qs.appinterface.query;
 
 import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.page.LimitSQLBuilder;
import nc.jdbc.framework.page.SQLBuilderFactory;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.vo.pf.mobileapp.query.IPaginationQuery;
import nc.vo.pf.mobileapp.query.PksVO;
import nc.vo.pf.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
 
 
 
 
 
 
 
 
 
 
 
 
 public class PaginationQueryFacade
 {
   private static int DEFAULT_LOAD_FACTOR = 1;
   
   private static PaginationQueryFacade instance = new PaginationQueryFacade();
   
   private Map<String, WeakReference<PksVO>> requestPksMap = new HashMap();
   private IUAPQueryBS qry = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
   
   private PaginationQueryFacade() {}
   
   public static PaginationQueryFacade getInstance()
   {
     return instance;
   }
   
 
 
 
 
 
 
 
 
 
   public <T> List<T> query(IPaginationQuery<T> query, int startline, int count,int pagenum) throws BusinessException{
	   
     String requestId = query.getIdentifier();
     PksVO pksvo = null;
     
     WeakReference<PksVO> ref = (WeakReference)this.requestPksMap.get(requestId);
     if (ref != null) {
       pksvo = (PksVO)ref.get();
     }
     
 
     if ((pksvo == null) || (!pksvo.includes(startline, count))) {
       pksvo = queryPks(query,startline,count,pagenum);
     }
     
     String[] realPks = pksvo.getPksOf(startline, count);
     
     return query.queryByPks(realPks);
   }
   
   private PksVO queryPks(IPaginationQuery query, int startLine, int count,int pagenum) throws BusinessException
   {
     int loadCount = calculateLoadCount(startLine, count);
     
     if (RuntimeEnv.getInstance().isRunningInServer())
     {
 
       int dbtype = new BaseDAO(InvocationInfoProxy.getInstance().getUserDataSource()).getDBType();
       LimitSQLBuilder builder = SQLBuilderFactory.getInstance().createLimitSQLBuilder(dbtype);
       
       String sql = builder.build(query.getPksSql(), pagenum, loadCount);
       String[] pks = queryPks(sql);
       
       PksVO pksvo = new PksVO();
       pksvo.setPks(pks);
       pksvo.setStartIdx(0);
       
       if (pks.length < loadCount) {
         pksvo.setTotalCount(pks.length);
       }
       
       return pksvo;
     }
     
     String sql = query.getPksSql();
     
     String[] pks = queryPks(sql);
     
     PksVO pksvo = new PksVO();
     pksvo.setPks(pks);
     pksvo.setStartIdx(0);
     pksvo.setTotalCount(pks.length);
     
     return pksvo;
   }
   
   private String[] queryPks(String sql) throws BusinessException
   {
     List<String> pksList = (List)this.qry.executeQuery(sql, new ColumnListProcessor());
     
     String[] pks = null;
     if (ArrayUtil.isNull(pksList)) {
       pks = new String[0];
     } else {
       pks = (String[])pksList.toArray(new String[pksList.size()]);
     }
     
     return pks;
   }
   
   private int calculateLoadCount(int startLine, int count) {
     return startLine + count * DEFAULT_LOAD_FACTOR;
   }
 }
