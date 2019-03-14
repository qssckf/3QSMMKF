/*      */ package nc.jdbc.framework;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import nc.bs.logging.Logger;
/*      */ import nc.jdbc.framework.exception.DbException;
/*      */ import nc.jdbc.framework.exception.ExceptionFactory;
/*      */ import nc.jdbc.framework.exception.UnKnownException;
/*      */ import nc.jdbc.framework.generator.SequenceGenerator;
/*      */ import nc.jdbc.framework.mapping.AttributeMapping;
/*      */ import nc.jdbc.framework.mapping.BeanMapping;
/*      */ import nc.jdbc.framework.mapping.IMappingMeta;
/*      */ import nc.jdbc.framework.mapping.MappingMetaManager;
/*      */ import nc.jdbc.framework.processor.BaseProcessor;
/*      */ import nc.jdbc.framework.processor.BeanListProcessor;
/*      */ import nc.jdbc.framework.processor.BeanMappingListProcessor;
/*      */ import nc.jdbc.framework.processor.ResultSetProcessor;
/*      */ import nc.jdbc.framework.util.DBUtil;
/*      */ import nc.jdbc.framework.util.SQLHelper;
/*      */ import nc.vo.pub.BeanHelper;
import nc.vo.pub.SuperVO;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JdbcPersistenceManager
/*      */   extends PersistenceManager
/*      */ {
/*      */   JdbcSession session;
/*   47 */   String dataSource = null;
/*      */   
/*   49 */   private DatabaseMetaData dbmd = null;
/*      */   
/*   51 */   private static Map<String, ColCache> colCacheMap = new ConcurrentHashMap();
/*      */   
/*      */   class ColCache {
/*   54 */     private Map<String, Map<String, Integer>> typeCache = new ConcurrentHashMap();
/*   55 */     private Map<String, Map<String, Integer>> sizeCache = new ConcurrentHashMap();
/*      */     
/*      */ 
/*      */     ColCache() {}
/*      */   }
/*      */   
/*      */   protected JdbcPersistenceManager()
/*      */     throws DbException
/*      */   {
/*   64 */     init();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected JdbcPersistenceManager(String dataSource)
/*      */     throws DbException
/*      */   {
/*   76 */     this.dataSource = dataSource;
/*   77 */     init();
/*      */   }
/*      */   
/*      */   protected JdbcPersistenceManager(JdbcSession session) {
/*   81 */     session.setMaxRows(this.maxRows);
/*   82 */     this.session = session;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JdbcSession getJdbcSession()
/*      */   {
/*   91 */     return this.session;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void release()
/*      */   {
/*   98 */     if (this.dbmd != null)
/*   99 */       this.dbmd = null;
/*  100 */     if (this.session != null) {
/*  101 */       this.session.closeAll();
/*  102 */       this.session = null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String insertWithPK(SuperVO vo)
/*      */     throws DbException
/*      */   {
/*  113 */     String[] pk = insertWithPK(new SuperVO[] { vo });
/*  114 */     return pk[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String insert(SuperVO vo)
/*      */     throws DbException
/*      */   {
/*  126 */     String[] pk = insert(new SuperVO[] { vo });
/*  127 */     return pk[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] insertWithPK(List vos)
/*      */     throws DbException
/*      */   {
/*  139 */     return insertWithPK((SuperVO[])vos.toArray(new SuperVO[0]));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] insert(List vos)
/*      */     throws DbException
/*      */   {
/*  152 */     return insert((SuperVO[])vos.toArray(new SuperVO[0]));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] insertWithPK(SuperVO[] vos)
/*      */     throws DbException
/*      */   {
/*  161 */     return insert(vos, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] insert(SuperVO[] vos)
/*      */     throws DbException
/*      */   {
/*  174 */     return insert(vos, false);
/*      */   }
/*      */   
/*      */   private String[] preparePK(SuperVO[] vos, boolean withPK) {
/*  178 */     String corpPk = SQLHelper.getCorpPk();
/*  179 */     if (withPK) {
/*  180 */       String[] pks = new String[vos.length];
/*  181 */       int[] idx = new int[vos.length];
/*  182 */       int length = 0;
/*  183 */       for (int i = 0; i < vos.length; i++) {
/*  184 */         if (vos[i] != null)
/*      */         {
/*      */ 
/*  187 */           String thePK = vos[i].getPrimaryKey();
/*  188 */           if ((thePK == null) || (thePK.trim().length() == 0)) {
/*  189 */             idx[(length++)] = i;
/*      */           } else {
/*  191 */             pks[i] = thePK;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  196 */       if (length > 0) {
/*  197 */         String[] npks = new SequenceGenerator(this.dataSource).generate(corpPk, length);
/*      */         
/*  199 */         for (int i = 0; i < length; i++) {
/*  200 */           vos[idx[i]].setPrimaryKey(npks[i]);
/*  201 */           pks[idx[i]] = npks[i];
/*      */         }
/*      */       }
/*  204 */       return pks;
/*      */     }
/*      */     
/*  207 */     String[] pks = new SequenceGenerator(this.dataSource).generate(corpPk, vos.length);
/*      */     
/*  209 */     for (int i = 0; i < vos.length; i++) {
/*  210 */       if (vos[i] != null) {
/*  211 */         vos[i].setPrimaryKey(pks[i]);
/*      */       } else {
/*  213 */         pks[i] = null;
/*      */       }
/*      */     }
/*  216 */     return pks;
/*      */   }
/*      */   
/*      */ 
/*      */   protected String[] insert(SuperVO[] vos, boolean withPK)
/*      */     throws DbException
/*      */   {
/*  223 */     isNull(vos);
/*  224 */     if (vos.length == 0) {
/*  225 */       return new String[0];
/*      */     }
/*  227 */     String[] pks = null;
/*      */     try {
/*  229 */       String tableName = vos[0].getTableName();
/*      */       
/*  231 */       Map<String, Integer> types = getColmnTypes(tableName);
/*  232 */       Map<String, Integer> sizes = getColmnSize(tableName);
/*      */       
/*  234 */       String[] names = getValidNames(vos[0], types);
/*      */       
/*  236 */       String sql = SQLHelper.getInsertSQL(tableName, names);
/*      */       
/*  238 */       pks = preparePK(vos, withPK);
/*      */       
/*  240 */       if (vos.length == 1) {
/*  241 */         SQLParameter parameter = getSQLParam(vos[0], names, types, sizes);
/*      */         
/*  243 */         this.session.executeUpdate(sql, parameter);
/*      */       } else {
/*  245 */         SQLParameter[] parameters = new SQLParameter[vos.length];
/*  246 */         for (int i = 0; i < vos.length; i++) {
/*  247 */           if (vos[i] != null)
/*      */           {
/*  249 */             parameters[i] = getSQLParam(vos[i], names, types, sizes);
/*      */           }
/*      */         }
/*  252 */         this.session.addBatch(sql, parameters);
/*  253 */         this.session.executeBatch();
/*      */       }
/*      */     }
/*      */     finally {}
/*      */     
/*      */ 
/*  259 */     return pks;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String insertObjectWithPK(Object vo, IMappingMeta meta)
/*      */     throws DbException
/*      */   {
/*  271 */     return insertObjectWithPK(new Object[] { vo }, meta)[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] insertObjectWithPK(Object[] vo, IMappingMeta meta)
/*      */     throws DbException
/*      */   {
/*  282 */     return insertObject(vo, meta, true);
/*      */   }
/*      */   
/*      */   public String insertObject(Object vo, IMappingMeta meta) throws DbException
/*      */   {
/*  287 */     return insertObject(new Object[] { vo }, meta)[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] insertObject(Object[] vo, IMappingMeta meta)
/*      */     throws DbException
/*      */   {
/*  300 */     return insertObject(vo, meta, false);
/*      */   }
/*      */   
/*      */   protected String[] insertObject(Object[] vo, IMappingMeta meta, boolean withPK) throws DbException
/*      */   {
/*  305 */     isNull(vo);
/*  306 */     if (vo.length == 0) {
/*  307 */       return new String[0];
/*      */     }
/*  309 */     if ((vo[0] instanceof SuperVO)) {
/*  310 */       SuperVO[] svos = new SuperVO[vo.length];
/*  311 */       System.arraycopy(vo, 0, svos, 0, vo.length);
/*  312 */       return insert(svos, meta, withPK);
/*      */     }
/*      */     
/*  315 */     AttributeMapping map = MappingMetaManager.getMapingMeta(meta);
/*      */     
/*  317 */     String tableName = meta.getTableName();
/*      */     
/*  319 */     String corpPk = SQLHelper.getCorpPk();
/*  320 */     String[] pk = new SequenceGenerator(this.dataSource).generate(corpPk, vo.length);
/*  321 */     Map<String, Integer> types = getColmnTypes(tableName);
/*  322 */     Map<String, Integer> sizes = getColmnSize(tableName);
/*      */     
/*      */ 
/*      */ 
/*  326 */     for (int i = 0; i < vo.length; i++)
/*  327 */       if (vo[i] != null)
/*      */       {
/*  329 */         String beanPkName = map.getAttributeName(meta.getPrimaryKey()).toLowerCase();
/*      */         
/*  331 */         if (withPK) {
/*  332 */           String thePK = (String)BeanHelper.getProperty(vo[i], beanPkName);
/*      */           
/*      */ 
/*  335 */           if ((thePK == null) || (thePK.trim().length() == 0)) {
/*  336 */             BeanHelper.setProperty(vo[i], beanPkName, pk[i]);
/*      */           } else {
/*  338 */             pk[i] = thePK;
/*      */           }
/*      */         } else {
/*  341 */           BeanHelper.setProperty(vo[i], beanPkName, pk[i]);
/*      */         }
/*  343 */         BeanMapping mapping = new BeanMapping(vo[i], meta);
/*  344 */         if (types != null)
/*  345 */           mapping.setType(types);
/*  346 */         if (sizes != null)
/*  347 */           mapping.setSizes(sizes);
/*  348 */         SQLParameter parameter = mapping.getInsertParamter();
/*  349 */         this.session.addBatch(mapping.getInsertSQL(), parameter);
/*      */       }
/*  351 */     this.session.executeBatch();
/*  352 */     return pk;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int update(SuperVO vo)
/*      */     throws DbException
/*      */   {
/*  362 */     if (vo == null) {
/*  363 */       throw new IllegalArgumentException("vo parameter is null");
/*      */     }
/*  365 */     return update(new SuperVO[] { vo }, null);
/*      */   }
/*      */   
/*      */   public int update(List vos) throws DbException {
/*  369 */     return update((SuperVO[])vos.toArray(new SuperVO[0]), null);
/*      */   }
/*      */   
/*      */   public int update(SuperVO[] vo) throws DbException
/*      */   {
/*  374 */     return update(vo, null);
/*      */   }
/*      */   
/*      */   public int update(SuperVO[] vo, String[] fieldNames)
/*      */     throws DbException
/*      */   {
/*  380 */     return update(vo, fieldNames, null, null);
/*      */   }
/*      */   
/*      */   public int update(SuperVO[] vo, String[] fieldNames, String whereClause, SQLParameter param)
/*      */     throws DbException
/*      */   {
/*  386 */     isNull(vo);
/*  387 */     if (vo.length == 0)
/*  388 */       return 0;
/*  389 */     int row = 0;
/*      */     
/*      */ 
/*      */ 
/*  393 */     String tableName = vo[0].getTableName();
/*  394 */     String pkName = vo[0].getPKFieldName();
/*      */     
/*      */ 
/*      */ 
/*  398 */     Map<String, Integer> types = getColmnTypes(tableName);
/*  399 */     Map<String, Integer> sizes = getColmnSize(tableName);
/*  400 */     String[] names; 
			   if (fieldNames != null) {
/*  401 */       names = fieldNames;
/*      */     }
/*      */     else {
/*  404 */       names = getUpdateValidNames(vo[0], types, pkName);
/*      */     }
/*      */     
/*  407 */     String sql = SQLHelper.getUpdateSQL(tableName, names, pkName);
/*  408 */     if (vo.length == 1) {
/*  409 */       SQLParameter parameter = getSQLParam(vo[0], names, types, sizes);
/*  410 */       parameter.addParam(vo[0].getPrimaryKey());
/*  411 */       if (whereClause == null) {
/*  412 */         row = this.session.executeUpdate(sql, parameter);
/*      */       } else {
/*  414 */         addParameter(parameter, param);
/*  415 */         row = this.session.executeUpdate(sql + " and " + whereClause, parameter);
/*      */       }
/*      */     }
/*      */     else {
/*  419 */       for (int i = 0; i < vo.length; i++)
/*  420 */         if (vo[i] != null)
/*      */         {
/*      */ 
/*  423 */           SQLParameter parameter = getSQLParam(vo[i], names, types, sizes);
/*  424 */           parameter.addParam(vo[i].getPrimaryKey());
/*  425 */           if (whereClause == null) {
/*  426 */             this.session.addBatch(sql, parameter);
/*      */           } else {
/*  428 */             addParameter(parameter, param);
/*  429 */             this.session.addBatch(sql + " and " + whereClause, parameter);
/*      */           }
/*      */         }
/*  432 */       row = this.session.executeBatch();
/*      */     }
/*  434 */     return row;
/*      */   }
/*      */   
/*      */   public int updateObject(Object vo, IMappingMeta meta) throws DbException
/*      */   {
/*  439 */     return updateObject(new Object[] { vo }, meta);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int updateObject(Object vo, IMappingMeta meta, String whereClause)
/*      */     throws DbException
/*      */   {
/*  450 */     return updateObject(new Object[] { vo }, meta, whereClause);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int updateObject(Object[] vo, IMappingMeta meta, String whereClause)
/*      */     throws DbException
/*      */   {
/*  461 */     return updateObject(vo, meta, whereClause, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int updateObject(Object[] vo, IMappingMeta meta, String whereClause, SQLParameter param)
/*      */     throws DbException
/*      */   {
/*  473 */     isNull(vo);
/*  474 */     if (vo.length == 0) {
/*  475 */       return 0;
/*      */     }
/*      */     
/*  478 */     if ((vo[0] instanceof SuperVO)) {
/*  479 */       SuperVO[] svos = new SuperVO[vo.length];
/*  480 */       System.arraycopy(vo, 0, svos, 0, vo.length);
/*  481 */       return update(svos, meta, whereClause, param);
/*      */     }
/*      */     
/*  484 */     String tableName = meta.getTableName();
/*  485 */     Map<String, Integer> types = getColmnTypes(tableName);
/*  486 */     Map<String, Integer> sizes = getColmnSize(tableName);
/*  487 */     if (vo.length == 1) {
/*  488 */       if (vo[0] == null)
/*  489 */         return -1;
/*  490 */       BeanMapping mapping = new BeanMapping(vo[0], meta);
/*  491 */       if (types != null) {
/*  492 */         mapping.setType(types);
/*      */       }
/*  494 */       if (sizes != null) {
/*  495 */         mapping.setSizes(sizes);
/*      */       }
/*  497 */       SQLParameter parameter = mapping.getUpdateParamter();
/*  498 */       if (whereClause == null) {
/*  499 */         if (mapping.isNullPK())
/*  500 */           return -1;
/*  501 */         return this.session.executeUpdate(mapping.getUpdateSQL(), parameter);
/*      */       }
/*      */       
/*  504 */       addParameter(parameter, param);
/*  505 */       if (mapping.isNullPK()) {
/*  506 */         return this.session.executeUpdate(mapping.getUpdateSQL() + " WHERE " + whereClause, parameter);
/*      */       }
/*      */       
/*  509 */       return this.session.executeUpdate(mapping.getUpdateSQL() + " AND " + whereClause, parameter);
/*      */     }
/*      */     
/*      */ 
/*  513 */     for (int i = 0; i < vo.length; i++) {
/*  514 */       if (vo[i] != null)
/*      */       {
/*  516 */         BeanMapping mapping = new BeanMapping(vo[i], meta);
/*  517 */         if (types != null)
/*  518 */           mapping.setType(types);
/*  519 */         SQLParameter parameter = mapping.getUpdateParamter();
/*  520 */         if (whereClause == null) {
/*  521 */           if (mapping.isNullPK())
/*  522 */             return -1;
/*  523 */           this.session.addBatch(mapping.getUpdateSQL(), parameter);
/*      */         }
/*      */         else {
/*  526 */           addParameter(parameter, param);
/*  527 */           if (mapping.isNullPK()) {
/*  528 */             this.session.addBatch(mapping.getUpdateSQL() + " WHERE " + whereClause, parameter);
/*      */           }
/*      */           else
/*  531 */             this.session.addBatch(mapping.getUpdateSQL() + " AND " + whereClause, parameter);
/*      */         }
/*      */       }
/*      */     }
/*  535 */     return this.session.executeBatch();
/*      */   }
/*      */   
/*      */   private void addParameter(SQLParameter parameter, SQLParameter addParams) {
/*  539 */     if (addParams != null) {
/*  540 */       for (int i = 0; i < addParams.getCountParams(); i++) {
/*  541 */         parameter.addParam(addParams.get(i));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public int updateObject(Object[] vo, IMappingMeta meta)
/*      */     throws DbException
/*      */   {
/*  550 */     return updateObject(vo, meta, null);
/*      */   }
/*      */   
/*      */   public int delete(List vos) throws DbException {
/*  554 */     isNull(vos);
/*  555 */     return delete((SuperVO[])vos.toArray(new SuperVO[0]));
/*      */   }
/*      */   
/*      */   public int delete(SuperVO vo) throws DbException {
/*  559 */     isNull(vo);
/*  560 */     return delete(new SuperVO[] { vo });
/*      */   }
/*      */   
/*      */   public int delete(SuperVO[] vo) throws DbException {
/*  564 */     isNull(vo);
/*  565 */     if (vo.length == 0) {
/*  566 */       return 0;
/*      */     }
/*  568 */     String sql = SQLHelper.getDeleteByPKSQL(vo[0].getTableName(), vo[0].getPKFieldName());
/*      */     
/*      */ 
/*  571 */     for (int i = 0; i < vo.length; i++)
/*  572 */       if (vo[i] != null)
/*      */       {
/*  574 */         SQLParameter parameter = new SQLParameter();
/*  575 */         parameter.addParam(vo[i].getPrimaryKey());
/*  576 */         this.session.addBatch(sql, parameter);
/*      */       }
/*  578 */     return this.session.executeBatch();
/*      */   }
/*      */   
/*      */   public void deleteObject(Object vo, IMappingMeta meta) throws DbException
/*      */   {
/*  583 */     deleteObject(new Object[] { vo }, meta);
/*      */   }
/*      */   
/*      */   public void deleteObject(Object[] vos, IMappingMeta meta) throws DbException
/*      */   {
/*  588 */     isNull(vos);
/*  589 */     if (vos.length == 0)
/*  590 */       return;
/*  591 */     for (int i = 0; i < vos.length; i++)
/*  592 */       if (vos[i] != null)
/*      */       {
/*  594 */         BeanMapping mapping = new BeanMapping(vos[i], meta);
/*  595 */         SQLParameter parameter = mapping.getDeleteParamter();
/*  596 */         this.session.addBatch(mapping.getDeleteSQL(), parameter);
/*      */       }
/*  598 */     this.session.executeBatch();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int deleteByPK(IMappingMeta meta, String pk)
/*      */     throws DbException
/*      */   {
/*  610 */     return deleteByPKs(meta, new String[] { pk });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int deleteByPKs(IMappingMeta meta, String[] pks)
/*      */     throws DbException
/*      */   {
/*  623 */     String sql = "DELETE FROM " + meta.getTableName() + " WHERE " + meta.getPrimaryKey() + "=?";
/*      */     
/*  625 */     for (int i = 0; i < pks.length; i++) {
/*  626 */       SQLParameter parameter = new SQLParameter();
/*  627 */       parameter.addParam(pks[i]);
/*  628 */       this.session.addBatch(sql, parameter);
/*      */     }
/*  630 */     return this.session.executeBatch();
/*      */   }
/*      */   
/*      */ 
/*      */   public int deleteByPK(Class className, String pk)
/*      */     throws DbException
/*      */   {
/*  637 */     return deleteByPKs(className, new String[] { pk });
/*      */   }
/*      */   
/*      */ 
/*      */   public int deleteByPKs(Class className, String[] pks)
/*      */     throws DbException
/*      */   {
/*  644 */     SuperVO supervo = initSuperVOClass(className);
/*  645 */     String sql = "DELETE FROM " + supervo.getTableName() + " WHERE " + supervo.getPKFieldName() + "=?";
/*      */     
/*  647 */     for (int i = 0; i < pks.length; i++) {
/*  648 */       SQLParameter parameter = new SQLParameter();
/*  649 */       parameter.addParam(pks[i]);
/*  650 */       this.session.addBatch(sql, parameter);
/*      */     }
/*  652 */     return this.session.executeBatch();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int deleteByClause(IMappingMeta meta, String wherestr)
/*      */     throws DbException
/*      */   {
/*  664 */     return deleteByClause(meta, wherestr, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int deleteByClause(Class className, String wherestr)
/*      */     throws DbException
/*      */   {
/*  675 */     return deleteByClause(className, wherestr, null);
/*      */   }
/*      */   
/*      */   public int deleteByClause(Class className, String wherestr, SQLParameter params)
/*      */     throws DbException
/*      */   {
/*  681 */     SuperVO supervo = initSuperVOClass(className);
/*  682 */     String sql = "DELETE FROM " + supervo.getTableName();
/*      */     
/*  684 */     if (wherestr != null) {
/*  685 */       wherestr = wherestr.trim();
/*  686 */       if (wherestr.length() > 0) {
/*  687 */         if (wherestr.toLowerCase().startsWith("WHERE"))
/*  688 */           wherestr = wherestr.substring(5);
/*  689 */         if (wherestr.length() > 0)
/*  690 */           sql = sql + " WHERE " + wherestr;
/*      */       }
/*      */     }
/*  693 */     if (params == null) {
/*  694 */       return this.session.executeUpdate(sql);
/*      */     }
/*  696 */     return this.session.executeUpdate(sql, params);
/*      */   }
/*      */   
/*      */   public Collection retrieveByCorp(Class c, String pkCorp) throws DbException
/*      */   {
/*  701 */     return retrieveByCorp(c, pkCorp, null);
/*      */   }
/*      */   
/*      */   public Collection retrieveByCorp(Class c, String pkCorp, String[] selectedFields)
/*      */     throws DbException
/*      */   {
/*  707 */     if ((pkCorp.equals("0001")) || (pkCorp.equals("@@@@"))) {
/*  708 */       SQLParameter param = new SQLParameter();
/*  709 */       param.addParam("0001");
/*  710 */       param.addParam("@@@@");
/*  711 */       return retrieveByClause(c, "pk_corp=? or pk_corp=?", selectedFields, param);
/*      */     }
/*      */     
/*  714 */     SQLParameter param = new SQLParameter();
/*  715 */     param.addParam(pkCorp);
/*  716 */     return retrieveByClause(c, "pk_corp=?", selectedFields, param);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection retrieveByCorp(Class c, IMappingMeta meta, String pkCorp)
/*      */     throws DbException
/*      */   {
/*  728 */     return retrieveByCorp(c, meta, pkCorp, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection retrieveByCorp(Class c, IMappingMeta meta, String pkCorp, String[] selectedFields)
/*      */     throws DbException
/*      */   {
/*  740 */     if ((pkCorp.equals("0001")) || (pkCorp.equals("@@@@"))) {
/*  741 */       SQLParameter param = new SQLParameter();
/*  742 */       param.addParam("0001");
/*  743 */       param.addParam("@@@@");
/*  744 */       return retrieveByClause(c, meta, "pk_corp=? or pk_corp=?", selectedFields, param);
/*      */     }
/*      */     
/*  747 */     SQLParameter param = new SQLParameter();
/*  748 */     param.addParam(pkCorp);
/*  749 */     return retrieveByClause(c, meta, "pk_corp=?", selectedFields, param);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object retrieveByPK(Class className, String pk)
/*      */     throws DbException
/*      */   {
/*  757 */     return retrieveByPK(className, pk, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object retrieveByPK(Class className, String pk, String[] selectedFields)
/*      */     throws DbException
/*      */   {
/*  766 */     SuperVO vo = initSuperVOClass(className);
/*  767 */     if (pk == null)
/*  768 */       throw new IllegalArgumentException("pk is null");
/*  769 */     SQLParameter param = new SQLParameter();
/*  770 */     param.addParam(pk.trim());
/*  771 */     List results = (List)retrieveByClause(className, vo.getPKFieldName() + "=?", selectedFields, param);
/*      */     
/*  773 */     if (results.size() >= 1)
/*  774 */       return results.get(0);
/*  775 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object retrieveByPK(Class className, IMappingMeta meta, String pk)
/*      */     throws DbException
/*      */   {
/*  787 */     return retrieveByPK(className, meta, pk, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object retrieveByPK(Class className, IMappingMeta meta, String pk, String[] selectedFields)
/*      */     throws DbException
/*      */   {
/*  799 */     if (pk == null)
/*  800 */       throw new IllegalArgumentException("pk is null");
/*  801 */     SQLParameter param = new SQLParameter();
/*  802 */     param.addParam(pk.trim());
/*  803 */     List results = (List)retrieveByClause(className, meta, meta.getPrimaryKey() + "=?", selectedFields, param);
/*      */     
/*  805 */     if (results.size() >= 1)
/*  806 */       return results.get(0);
/*  807 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   public Collection retrieve(SuperVO vo, boolean isAnd)
/*      */     throws DbException
/*      */   {
/*  814 */     return retrieve(vo, isAnd, null);
/*      */   }
/*      */   
/*      */   public Collection retrieve(Object vo, IMappingMeta meta) throws DbException
/*      */   {
/*  819 */     isNull(vo);
/*  820 */     BeanMapping mapping = new BeanMapping(vo, meta);
/*      */     
/*  822 */     String sql = mapping.getSelectwithParamSQL();
/*  823 */     SQLParameter param = mapping.getSelectParameter();
/*      */     
/*  825 */     return (Collection)this.session.executeQuery(sql, param, new BeanMappingListProcessor(vo.getClass(), meta));
/*      */   }
/*      */   
/*      */   public Collection retrieve(SuperVO vo, boolean isAnd, String[] fields)
/*      */     throws DbException
/*      */   {
/*  831 */     return (Collection)retrieve(vo, isAnd, fields, new BeanListProcessor(vo.getClass()));
/*      */   }
/*      */   
/*      */   public Collection retrieve(SuperVO vo, boolean isAnd, String[] fields, String[] orderbyFields)
/*      */     throws DbException
/*      */   {
/*  837 */     isNull(vo);
/*  838 */     String tableName = vo.getTableName();
/*      */     
/*  840 */     Map types = getColmnTypes(tableName);
/*      */     
/*  842 */     String[] names = getNotNullValidNames(vo, types);
/*      */     
/*  844 */     String sql = SQLHelper.getSelectSQL(tableName, names, isAnd, fields);
/*      */     
/*  846 */     sql = appendOrderBy(sql, orderbyFields);
/*      */     
/*  848 */     SQLParameter param = getSQLParam(vo, names);
/*      */     
/*  850 */     return (Collection)this.session.executeQuery(sql, param, new BeanListProcessor(vo.getClass()));
/*      */   }
/*      */   
/*      */   private String appendOrderBy(String sql, String[] orderBys)
/*      */   {
/*  855 */     if (sql == null) {
/*  856 */       throw new RuntimeException("sql is null");
/*      */     }
/*      */     
/*  859 */     if ((orderBys == null) || (orderBys.length == 0)) {
/*  860 */       return sql;
/*      */     }
/*      */     
/*  863 */     StringBuffer orderClause = new StringBuffer(" ORDER BY ");
/*      */     
/*  865 */     int len = orderClause.length();
/*      */     
/*  867 */     for (String s : orderBys) {
/*  868 */       if (s != null) {
/*  869 */         orderClause.append(s).append(',');
/*      */       }
/*      */     }
/*      */     
/*  873 */     if (orderClause.length() > len) {
/*  874 */       orderClause.setLength(orderClause.length() - 1);
/*      */       
/*  876 */       return sql + orderClause;
/*      */     }
/*  878 */     return sql;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object retrieve(SuperVO vo, boolean isAnd, String[] fields, ResultSetProcessor processor)
/*      */     throws DbException
/*      */   {
/*  885 */     isNull(vo);
/*  886 */     String tableName = vo.getTableName();
/*      */     
/*  888 */     Map types = getColmnTypes(tableName);
/*      */     
/*  890 */     String[] names = getNotNullValidNames(vo, types);
/*      */     
/*  892 */     String sql = SQLHelper.getSelectSQL(tableName, names, isAnd, fields);
/*  893 */     SQLParameter param = getSQLParam(vo, names);
/*      */     
/*  895 */     return this.session.executeQuery(sql, param, processor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection retrieveAll(Class className, IMappingMeta meta)
/*      */     throws DbException
/*      */   {
/*  906 */     Object vo = InitClass(className);
/*  907 */     BeanMapping mapping = new BeanMapping(vo, meta);
/*      */     
/*  909 */     return (Collection)this.session.executeQuery(mapping.getSelectSQL(), new BeanMappingListProcessor(className, meta));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection retrieveAll(Class className)
/*      */     throws DbException
/*      */   {
/*  919 */     SuperVO vo = initSuperVOClass(className);
/*  920 */     String tableName = vo.getTableName();
/*  921 */     String sql = "SELECT * FROM " + tableName;
/*      */     
/*  923 */     return (Collection)this.session.executeQuery(sql, new BeanListProcessor(className));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection retrieveByClause(Class className, String condition)
/*      */     throws DbException
/*      */   {
/*  933 */     return retrieveByClause(className, condition, null);
/*      */   }
/*      */   
/*      */   public Collection retrieveByClause(Class className, String condition, String[] fields, SQLParameter parameters) throws DbException
/*      */   {
/*  938 */     BaseProcessor processor = new BeanListProcessor(className);
/*  939 */     return (Collection)this.session.executeQuery(buildSql(className, condition, fields), parameters, processor);
/*      */   }
/*      */   
/*      */ 
/*      */   public Collection retrieveByClause(Class className, String condition, String[] fields)
/*      */     throws DbException
/*      */   {
/*  946 */     return retrieveByClause(className, condition, fields, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection retrieveByClause(Class className, IMappingMeta meta, String condition, String[] fields)
/*      */     throws DbException
/*      */   {
/*  959 */     return retrieveByClause(className, meta, condition, fields, null);
/*      */   }
/*      */   
/*      */   public int getDBType() {
/*  963 */     return this.session.getDbType();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public DatabaseMetaData getMetaData()
/*      */   {
/*  971 */     if (this.dbmd == null)
/*  972 */       this.dbmd = getJdbcSession().getMetaData();
/*  973 */     return this.dbmd;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAddTimeStamp(boolean isAddTimeStamp)
/*      */   {
/*  980 */     this.session.setAddTimeStamp(isAddTimeStamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSQLTranslator(boolean isTranslator)
/*      */   {
/*  987 */     this.session.setSQLTranslator(isTranslator);
/*      */   }
/*      */   
/*      */   public String getCatalog() {
/*  991 */     String catalog = null;
/*  992 */     switch (getDBType()) {
/*      */     case 7: 
/*      */     case 8: 
/*      */       try {
/*  996 */         catalog = getConnection().getCatalog();
/*      */       }
/*      */       catch (SQLException e) {}
/*      */     
/*      */ 
/*      */     case 0: 
/*      */     case 2: 
/* 1003 */       catalog = null;
/* 1004 */       break;
/*      */     case 1: 
/* 1006 */       catalog = null;
/* 1007 */       break;
/*      */     
/*      */     case 6: 
/* 1010 */       catalog = "";
/*      */     }
/*      */     
/* 1013 */     return catalog;
/*      */   }
/*      */   
/*      */   public String getSchema() {
/* 1017 */     String strSche = null;
/*      */     try {
/* 1019 */       String schema = getMetaData().getUserName();
/* 1020 */       switch (getDBType()) {
/*      */       case 7: 
/* 1022 */         strSche = null;
/* 1023 */         break;
/*      */       case 2: 
/* 1025 */         strSche = "dbo";
/* 1026 */         break;
/*      */       case 0: 
/*      */       case 1: 
/*      */       case 6: 
/* 1030 */         if ((schema == null) || (schema.length() == 0)) {
/* 1031 */           throw new IllegalArgumentException("ORACLE Database mode does not allow to be null!!");
/*      */         }
/*      */         
/* 1034 */         strSche = schema.toUpperCase();
/*      */       }
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/* 1039 */       Logger.error(e.getMessage(), e);
/*      */     }
/* 1041 */     return strSche;
/*      */   }
/*      */   
/*      */   public void setReadOnly(boolean isReadOnly) throws DbException {
/* 1045 */     this.session.setReadOnly(isReadOnly);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void init()
/*      */     throws DbException
/*      */   {
/* 1054 */     if (this.dataSource == null) {
/* 1055 */       this.session = new JdbcSession();
/*      */     } else {
/* 1057 */       this.session = new JdbcSession(this.dataSource);
/*      */     }
/* 1059 */     this.session.setMaxRows(this.maxRows);
/*      */   }
/*      */   
/*      */   private void isNull(Object vo) {
/* 1063 */     if (vo == null) {
/* 1064 */       throw new IllegalArgumentException("vo object parameter is null!!");
/*      */     }
/*      */   }
/*      */   
/*      */   public void setMaxRows(int maxRows) {
/* 1069 */     super.setMaxRows(maxRows);
/* 1070 */     this.session.setMaxRows(maxRows);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection retrieveByClause(Class className, IMappingMeta meta, String condition)
/*      */     throws DbException
/*      */   {
/* 1082 */     return retrieveByClause(className, meta, condition, meta.getColumns());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private SQLParameter getSQLParam(SuperVO vo, String[] names, Map<String, Integer> types, Map<String, Integer> size)
/*      */   {
/* 1095 */     SQLParameter params = new SQLParameter();
/* 1096 */     for (int i = 0; i < names.length; i++) {
/* 1097 */       if (!names[i].equalsIgnoreCase("ts"))
/*      */       {
/* 1099 */         int type = ((Integer)types.get(names[i].toUpperCase())).intValue();
/* 1100 */         Object value = vo.getAttributeValue(names[i]);
/* 1101 */         if ((value == null) && (type == 12)) {
/* 1102 */           Integer length = (Integer)size.get(names[i].toUpperCase());
/* 1103 */           if ((length != null) && ((length.intValue() == 20) || (length.intValue() == 36) || (length.intValue() == 101)))
/*      */           {
/* 1105 */             params.addParam("~");
/* 1106 */             continue;
/*      */           }
/*      */         }
/* 1109 */         if ((value == null) && (type == -9)) {
/* 1110 */           Integer length = (Integer)size.get(names[i].toUpperCase());
/* 1111 */           if ((length != null) && ((length.intValue() == 20) || (length.intValue() == 36) || (length.intValue() == 101)))
/*      */           {
/* 1113 */             params.addParam("~");
/* 1114 */             continue;
/*      */           }
/*      */         }
/* 1117 */         if (value == null) {
/* 1118 */           params.addNullParam(type);
/*      */ 
/*      */         }
/* 1121 */         else if ((type == 2004) || (type == -4) || (type == -3) || (type == -2))
/*      */         {
/* 1123 */           params.addBlobParam(value);
/*      */ 
/*      */         }
/* 1126 */         else if ((type == 2005) || (type == -1)) {
/* 1127 */           params.addClobParam(String.valueOf(value));
/*      */         }
/*      */         else
/* 1130 */           params.addParam(value);
/*      */       }
/*      */     }
/* 1133 */     return params;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String[] getValidNames(SuperVO vo, Map types)
/*      */   {
/* 1144 */     String[] names = vo.getAttributeNames();
/* 1145 */     List nameList = new ArrayList();
/* 1146 */     for (int i = 0; i < names.length; i++) {
/* 1147 */       if ((types.get(names[i].toUpperCase()) != null) && (!names[i].equalsIgnoreCase("ts")))
/*      */       {
/* 1149 */         nameList.add(names[i]);
/*      */       }
/*      */     }
/* 1152 */     return (String[])nameList.toArray(new String[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String[] getUpdateValidNames(SuperVO vo, Map types, String pkName)
/*      */   {
/* 1164 */     String[] names = vo.getAttributeNames();
/* 1165 */     List nameList = new ArrayList();
/* 1166 */     for (int i = 0; i < names.length; i++) {
/* 1167 */       if ((types.get(names[i].toUpperCase()) != null) && (!names[i].equalsIgnoreCase(pkName)) && (!names[i].equalsIgnoreCase("ts")))
/*      */       {
/*      */ 
/* 1170 */         nameList.add(names[i]);
/*      */       }
/*      */     }
/* 1173 */     return (String[])nameList.toArray(new String[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String[] getNotNullValidNames(SuperVO vo, Map type)
/*      */   {
/* 1182 */     String[] names = vo.getAttributeNames();
/* 1183 */     List nameList = new ArrayList();
/* 1184 */     for (int i = 0; i < names.length; i++) {
/* 1185 */       if ((type.get(names[i].toUpperCase()) != null) && (vo.getAttributeValue(names[i]) != null))
/*      */       {
/* 1187 */         nameList.add(names[i]);
/*      */       }
/*      */     }
/* 1190 */     if (nameList.size() == 0)
/* 1191 */       return new String[0];
/* 1192 */     return (String[])nameList.toArray(new String[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private SQLParameter getSQLParam(SuperVO vo, String[] names)
/*      */   {
/* 1201 */     if ((names == null) || (names.length == 0)) {
/* 1202 */       return null;
/*      */     }
/* 1204 */     SQLParameter parameter = new SQLParameter();
/* 1205 */     for (int i = 0; i < names.length; i++) {
/* 1206 */       parameter.addParam(vo.getAttributeValue(names[i]));
/*      */     }
/* 1208 */     return parameter;
/*      */   }
/*      */   
/*      */   public Connection getConnection() {
/* 1212 */     if (this.session != null)
/* 1213 */       return this.session.getConnection();
/* 1214 */     return null;
/*      */   }
/*      */   
/*      */   private ColCache getColCache() {
/* 1218 */     String ds = ds();
/* 1219 */     synchronized (colCacheMap) {
/* 1220 */       ColCache colCache = (ColCache)colCacheMap.get(ds);
/* 1221 */       if (colCache == null) {
/* 1222 */         colCache = new ColCache();
/* 1223 */         colCacheMap.put(ds, colCache);
/*      */       }
/* 1225 */       return colCache;
/*      */     }
/*      */   }
/*      */   
/*      */   private String ds() {
/* 1230 */     return this.dataSource == null ? DataSourceCenter.getInstance().getSourceName() : this.dataSource;
/*      */   }
/*      */   
/*      */   private Map<String, Integer> getColmnSize(String table) throws DbException
/*      */   {
/* 1235 */     ColCache cache = getColCache();
/*      */     
/* 1237 */     Map<String, Integer> result = (Map)cache.sizeCache.get(table);
/* 1238 */     if ((result == null) || (result.size() == 0)) {
/* 1239 */       result = new HashMap();
/* 1240 */       ResultSet rsColumns = null;
/*      */       try {
/* 1242 */         if ((getDBType() == 2) && (table.startsWith("#"))) {
/* 1243 */           Statement stmt = null;
/*      */           try {
/* 1245 */             stmt = getConnection().createStatement();
/* 1246 */             rsColumns = stmt.executeQuery("select top 0 * from " + table);
/*      */             
/* 1248 */             ResultSetMetaData rsMeta = rsColumns.getMetaData();
/* 1249 */             int count = rsMeta.getColumnCount();
/*      */             
/* 1251 */             for (int i = 1; i < count + 1; i++) {
/* 1252 */               result.put(rsMeta.getColumnName(i).toUpperCase(), Integer.valueOf(rsMeta.getPrecision(i)));
/*      */             }
/*      */             
/* 1255 */             if (result.size() >= 0) {
/* 1256 */               cache.sizeCache.put(table, result);
/*      */             } else {
/* 1258 */               throw new UnKnownException("no column info for: " + table + " at datasource: " + ds());
/*      */             }
/*      */             
/*      */ 
/*      */ 
/* 1263 */             Map<String, Integer> i = result;
/*      */             
/* 1265 */             if (stmt != null) {
/* 1266 */               stmt.close();
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1304 */             return i;
/*      */           }
/*      */           finally
/*      */           {
/* 1265 */             if (stmt != null) {
/* 1266 */               stmt.close();
/*      */             }
/*      */           }
/*      */         }
/* 1270 */         DatabaseMetaData dmd = getConnection().getMetaData();
/* 1271 */         if ((getDBType() == 1) || (getDBType() == 6) || (getDBType() == 0))
/*      */         {
/*      */ 
/* 1274 */           rsColumns = dmd.getColumns(null, dmd.getUserName().toUpperCase(), table.toUpperCase(), "%");
/*      */         }
/* 1276 */         else if (getDBType() == 7) {
/* 1277 */           rsColumns = getMetaData().getColumns(null, null, table.toLowerCase(), "%");
/*      */         }
/* 1279 */         else if (getDBType() == 8) {
/* 1280 */           rsColumns = dmd.getColumns(getCatalog(), dmd.getUserName(), table, "%");
/*      */         }
/*      */         else {
/* 1283 */           rsColumns = dmd.getColumns(null, null, table.toUpperCase(), "%");
/*      */         }
/*      */         
/* 1286 */         while (rsColumns.next()) {
/* 1287 */           result.put(rsColumns.getString("COLUMN_NAME").toUpperCase(), Integer.valueOf(rsColumns.getInt("COLUMN_SIZE")));
/*      */         }
/*      */         
/*      */ 
/* 1291 */         if (result.size() >= 0) {
/* 1292 */           cache.sizeCache.put(table, result);
/*      */         } else {
/* 1294 */           throw new UnKnownException("no column info for: " + table + " at datasource: " + ds());
/*      */         }
/*      */         
/*      */       }
/*      */       catch (SQLException e)
/*      */       {
/* 1300 */         Logger.error("get table metadata error", e);
/* 1301 */         throw ExceptionFactory.getException(getDBType(), "get table metadata error", e);
/*      */       }
/*      */       finally {
/* 1304 */         DBUtil.closeRs(rsColumns);
/*      */       }
/*      */     }
/* 1307 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Map<String, Integer> getColmnTypes(String table)
/*      */     throws DbException
/*      */   {
/* 1318 */     ColCache cache = getColCache();
/*      */     
/* 1320 */     Map<String, Integer> result = (Map)cache.typeCache.get(table);
/*      */     
/* 1322 */     Map<String, Integer> sizeMap = (Map)cache.sizeCache.get(table);
/* 1323 */     if ((result == null) || (result.size() == 0)) {
/* 1324 */       boolean querySize = false;
/* 1325 */       if (sizeMap == null) {
/* 1326 */         sizeMap = new HashMap();
/* 1327 */         querySize = true;
/*      */       }
/* 1329 */       Map<String, Integer> typeMap = new HashMap();
/* 1330 */       ResultSet rsColumns = null;
/*      */       try {
/* 1332 */         if ((getDBType() == 2) && (table.startsWith("#"))) {
/* 1333 */           Statement stmt = null;
/*      */           try {
/* 1335 */             stmt = getConnection().createStatement();
/* 1336 */             rsColumns = stmt.executeQuery("select top 0 * from " + table);
/*      */             
/* 1338 */             ResultSetMetaData rsMeta = rsColumns.getMetaData();
/* 1339 */             int count = rsMeta.getColumnCount();
/*      */             
/* 1341 */             for (int i = 1; i < count + 1; i++) {
/* 1342 */               typeMap.put(rsMeta.getColumnName(i), Integer.valueOf(rsMeta.getColumnType(i)));
/*      */               
/* 1344 */               if (querySize) {
/* 1345 */                 sizeMap.put(rsMeta.getColumnName(i).toUpperCase(), Integer.valueOf(rsMeta.getPrecision(i)));
/*      */               }
/*      */             }
/*      */             
/* 1349 */             if (typeMap.size() > 0) {
/* 1350 */               cache.typeCache.put(table, typeMap);
/* 1351 */               cache.sizeCache.put(table, sizeMap);
/*      */             } else {
/* 1353 */               throw new UnKnownException("no column info for: " + table + " at datasource: " + ds());
/*      */             }
/*      */             
/*      */ 
/*      */ 
/* 1358 */             Map<String, Integer> i = typeMap;
/*      */             
/* 1360 */             if (stmt != null) {
/* 1361 */               stmt.close();
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1404 */             return i;
/*      */           }
/*      */           finally
/*      */           {
/* 1360 */             if (stmt != null) {
/* 1361 */               stmt.close();
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1367 */         if (getDBType() == 2) {
/* 1368 */           rsColumns = getMetaData().getColumns(null, null, table.toUpperCase(), "%");
/*      */         }
/* 1370 */         else if (getDBType() == 7) {
/* 1371 */           rsColumns = getMetaData().getColumns(null, null, table.toLowerCase(), "%");
/*      */         }
/*      */         else
/* 1374 */           rsColumns = getMetaData().getColumns(null, getSchema(), table.toUpperCase(), "%");
/*      */         String columnName;
/* 1376 */         while (rsColumns.next()) {
/* 1377 */           columnName = rsColumns.getString("COLUMN_NAME").toUpperCase();
/*      */           
/* 1379 */           int columnType = rsColumns.getShort("DATA_TYPE");
/* 1380 */           typeMap.put(columnName, Integer.valueOf(columnType));
/* 1381 */           if (querySize) {
/* 1382 */             sizeMap.put(rsColumns.getString("COLUMN_NAME").toUpperCase(), Integer.valueOf(rsColumns.getInt("COLUMN_SIZE")));
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1388 */         if (typeMap.size() > 0) {
/* 1389 */           cache.typeCache.put(table, typeMap);
/* 1390 */           cache.sizeCache.put(table, sizeMap);
/*      */         } else {
/* 1392 */           throw new UnKnownException("no column info for: " + table + " at datasource: " + ds());
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1397 */         return typeMap;
/*      */       }
/*      */       catch (SQLException e) {
/* 1400 */         Logger.error("get table metadata error", e);
/* 1401 */         throw ExceptionFactory.getException(getDBType(), "get table metadata error", e);
/*      */       }
/*      */       finally {
/* 1404 */         DBUtil.closeRs(rsColumns);
/*      */       }
/*      */     }
/* 1407 */     return result;
/*      */   }
/*      */   
/*      */   public static void clearColumnTypes(String table) {
/* 1411 */     if (colCacheMap.size() == 0) {
/* 1412 */       return;
/*      */     }
/* 1414 */     for (ColCache colCache : colCacheMap.values()) {
/* 1415 */       colCache.typeCache.remove(table);
/* 1416 */       colCache.sizeCache.remove(table);
/*      */     }
/*      */   }
/*      */   
/*      */   private Object InitClass(Class className) {
/*      */     try {
/* 1422 */       return className.newInstance();
/*      */     } catch (InstantiationException e) {
/* 1424 */       throw new IllegalArgumentException("Parameter Class can not be instantiated!!");
/*      */     }
/*      */     catch (IllegalAccessException e) {
/* 1427 */       throw new IllegalArgumentException("Illegal Parameter!!");
/*      */     }
/*      */   }
/*      */   
/*      */   private SuperVO initSuperVOClass(Class className) {
/*      */     Object vo;
/*      */     try {
/* 1434 */       vo = className.newInstance();
/*      */     } catch (InstantiationException e) {
/* 1436 */       throw new IllegalArgumentException("Parameter Class can not be instantiated!!");
/*      */     }
/*      */     catch (IllegalAccessException e) {
/* 1439 */       throw new IllegalArgumentException(" Illegal Parameter!!");
/*      */     }
/* 1441 */     if (!(vo instanceof SuperVO))
/* 1442 */       throw new IllegalArgumentException("Parameter Class is not SuperVO");
/* 1443 */     return (SuperVO)vo;
/*      */   }
/*      */   
/*      */   private String buildSql(Class className, String condition, String[] fields) {
/* 1447 */     SuperVO vo = (SuperVO)InitClass(className);
/* 1448 */     String pkName = vo.getPKFieldName();
/* 1449 */     boolean hasPKField = false;
/* 1450 */     StringBuffer buffer = new StringBuffer();
/* 1451 */     String tableName = vo.getTableName();
/* 1452 */     if (fields == null) {
/* 1453 */       buffer.append("SELECT * FROM ").append(tableName);
/*      */     } else {
/* 1455 */       buffer.append("SELECT ");
/* 1456 */       for (int i = 0; i < fields.length; i++) {
/* 1457 */         if (fields[i] != null) {
/* 1458 */           buffer.append(fields[i]).append(",");
/* 1459 */           if (fields[i].equalsIgnoreCase(pkName))
/* 1460 */             hasPKField = true;
/*      */         }
/*      */       }
/* 1463 */       if (!hasPKField)
/* 1464 */         buffer.append(pkName).append(",");
/* 1465 */       buffer.setLength(buffer.length() - 1);
/* 1466 */       buffer.append(" FROM ").append(tableName);
/*      */     }
/* 1468 */     if ((condition != null) && (condition.length() != 0)) {
/* 1469 */       if (condition.toUpperCase().trim().startsWith("ORDER ")) {
/* 1470 */         buffer.append(" ").append(condition);
/*      */       } else {
/* 1472 */         buffer.append(" WHERE ").append(condition);
/*      */       }
/*      */     }
/* 1475 */     return buffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */   public Collection retrieveByClause(Class className, IMappingMeta meta, String condition, String[] fields, SQLParameter params)
/*      */     throws DbException
/*      */   {
/* 1482 */     String sql = SQLHelper.getSelectSQL(meta.getTableName(), fields);
/* 1483 */     if ((condition != null) && (condition.length() != 0)) {
/* 1484 */       if (condition.trim().toUpperCase().startsWith("ORDER ")) {
/* 1485 */         sql = sql + " " + condition;
/*      */       }
/*      */       else {
/* 1488 */         sql = sql + " WHERE " + condition;
/*      */       }
/*      */     }
/* 1491 */     BaseProcessor processor = new BeanMappingListProcessor(className, meta, fields);
/*      */     
/* 1493 */     if (params != null) {
/* 1494 */       return (Collection)this.session.executeQuery(sql, params, processor);
/*      */     }
/* 1496 */     return (Collection)this.session.executeQuery(sql, processor);
/*      */   }
/*      */   
/*      */ 
/*      */   public int deleteByClause(IMappingMeta meta, String wherestr, SQLParameter params)
/*      */     throws DbException
/*      */   {
/* 1503 */     String sql = "DELETE FROM " + meta.getTableName();
/*      */     
/* 1505 */     if (wherestr != null) {
/* 1506 */       wherestr = wherestr.trim();
/* 1507 */       if (wherestr.length() > 0) {
/* 1508 */         if (wherestr.toLowerCase().startsWith("WHERE"))
/* 1509 */           wherestr = wherestr.substring(5);
/* 1510 */         if (wherestr.length() > 0)
/* 1511 */           sql = sql + " WHERE " + wherestr;
/*      */       }
/*      */     }
/* 1514 */     if (params == null) {
/* 1515 */       return this.session.executeUpdate(sql);
/*      */     }
/* 1517 */     return this.session.executeUpdate(sql, params);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private String[] insert(SuperVO[] vos, IMappingMeta meta, boolean withPK)
/*      */     throws DbException
/*      */   {
/* 1526 */     String[] pks = null;
/*      */     try {
/* 1528 */       String tableName = meta.getTableName();
/*      */       
/* 1530 */       Map<String, Integer> types = getColmnTypes(tableName);
/* 1531 */       Map<String, Integer> sizes = getColmnSize(tableName);
/* 1532 */       String sql = SQLHelper.getInsertSQL(tableName, meta.getColumns());
/*      */       
/* 1534 */       pks = preparePK(vos, withPK);
/*      */       
/* 1536 */       if (vos.length == 1) {
/* 1537 */         SQLParameter parameter = getSQLParam(vos[0], meta.getAttributes(), meta.getColumns(), types, sizes);
/*      */         
/* 1539 */         this.session.executeUpdate(sql, parameter);
/*      */       } else {
/* 1541 */         SQLParameter[] parameters = new SQLParameter[vos.length];
/* 1542 */         for (int i = 0; i < vos.length; i++) {
/* 1543 */           if (vos[i] != null)
/*      */           {
/* 1545 */             parameters[i] = getSQLParam(vos[i], meta.getAttributes(), meta.getColumns(), types, sizes);
/*      */           }
/*      */         }
/* 1548 */         this.session.addBatch(sql, parameters);
/* 1549 */         this.session.executeBatch();
/*      */       }
/*      */     }
/*      */     finally {}
/*      */     
/*      */ 
/* 1555 */     return pks;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int update(SuperVO[] vo, IMappingMeta meta, String whereClause, SQLParameter param)
/*      */     throws DbException
/*      */   {
/* 1564 */     isNull(vo);
/* 1565 */     if (vo.length == 0)
/* 1566 */       return 0;
/* 1567 */     int row = 0;
/* 1568 */     String tableName = meta.getTableName();
/* 1569 */     String pkName = meta.getPrimaryKey();
/* 1570 */     Map<String, Integer> types = getColmnTypes(tableName);
/* 1571 */     Map<String, Integer> sizes = getColmnSize(tableName);
/* 1572 */     String sql = SQLHelper.getUpdateSQL(tableName, meta.getColumns(), pkName);
/*      */     
/* 1574 */     if (vo.length == 1) {
/* 1575 */       SQLParameter parameter = getSQLParam(vo[0], meta.getAttributes(), meta.getColumns(), types, sizes);
/*      */       
/* 1577 */       parameter.addParam(vo[0].getAttributeValue(pkName));
/* 1578 */       if (whereClause == null) {
/* 1579 */         row = this.session.executeUpdate(sql, parameter);
/*      */       } else {
/* 1581 */         addParameter(parameter, param);
/* 1582 */         row = this.session.executeUpdate(sql + " and " + whereClause, parameter);
/*      */       }
/*      */     }
/*      */     else {
/* 1586 */       for (int i = 0; i < vo.length; i++)
/* 1587 */         if (vo[i] != null)
/*      */         {
/* 1589 */           SQLParameter parameter = getSQLParam(vo[i], meta.getAttributes(), meta.getColumns(), types, sizes);
/*      */           
/* 1591 */           parameter.addParam(vo[i].getAttributeValue(pkName));
/* 1592 */           if (whereClause == null) {
/* 1593 */             this.session.addBatch(sql, parameter);
/*      */           } else {
/* 1595 */             addParameter(parameter, param);
/* 1596 */             this.session.addBatch(sql + " and " + whereClause, parameter);
/*      */           }
/*      */         }
/* 1599 */       row = this.session.executeBatch();
/*      */     }
/* 1601 */     return row;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private SQLParameter getSQLParam(SuperVO vo, String[] attribNames, String[] columnNames, Map<String, Integer> types, Map<String, Integer> size)
/*      */   {
/* 1610 */     SQLParameter params = new SQLParameter();
/* 1611 */     for (int i = 0; i < attribNames.length; i++) {
/* 1612 */       if (!attribNames[i].equalsIgnoreCase("ts"))
/*      */       {
/*      */ 
/* 1615 */         int type = -1;
/*      */         try {
/* 1617 */           type = ((Integer)types.get(columnNames[i].toUpperCase())).intValue();
/*      */         } catch (NullPointerException e) {
/* 1619 */           Logger.error("get param type error, types=" + types + ",columnNames=" + columnNames[i] + ",i=" + i);
/*      */           
/* 1621 */           throw e;
/*      */         }
/* 1623 */         Object value = vo.getAttributeValue(attribNames[i]);
/* 1624 */         if ((value == null) && (type == 12)) {
/* 1625 */           int length = ((Integer)size.get(columnNames[i].toUpperCase())).intValue();
/* 1626 */           if ((length == 20) || (length == 36) || (length == 101)) {
/* 1627 */             params.addParam("~");
/* 1628 */             continue;
/*      */           }
/*      */         }
/* 1631 */         if ((value == null) && (type == -9)) {
/* 1632 */           int length = ((Integer)size.get(columnNames[i].toUpperCase())).intValue();
/* 1633 */           if ((length == 20) || (length == 36) || (length == 101)) {
/* 1634 */             params.addParam("~");
/* 1635 */             continue;
/*      */           }
/*      */         }
/* 1638 */         if (value == null) {
/* 1639 */           params.addNullParam(type);
/*      */ 
/*      */         }
/* 1642 */         else if ((type == 2004) || (type == -4) || (type == -3) || (type == -2))
/*      */         {
/* 1644 */           params.addBlobParam(value);
/*      */ 
/*      */         }
/* 1647 */         else if ((type == 2005) || (type == -1)) {
/* 1648 */           params.addClobParam(String.valueOf(value));
/*      */         }
/*      */         else
/* 1651 */           params.addParam(value);
/*      */       }
/*      */     }
/* 1654 */     return params;
/*      */   }
/*      */ }

