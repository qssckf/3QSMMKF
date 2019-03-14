/*     */ package nc.pubimpl.mmpac.pmo.pps;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import nc.bs.mmpac.pmo.pac0002.bp.PMOInsertBP;
/*     */ import nc.bs.mmpac.pmo.pac0002.bp.util.PMOBPCalUtil;
/*     */ import nc.pubitf.mmpac.pmo.pps.IPublicPMOService4PPS;
/*     */ import nc.pubitf.mmpub.pub.mmpps.calc.IDemandResultForCalc;
/*     */ import nc.ui.querytemplate.querytree.FromWhereSQL;
/*     */ import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
/*     */ import nc.ui.querytemplate.querytree.IQueryScheme;
/*     */ import nc.ui.querytemplate.querytree.QueryScheme;
/*     */ import nc.util.mmf.framework.base.MMArrayUtil;
/*     */ import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
/*     */ import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
/*     */ import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
/*     */ 
/*     */ public class PublicPMOService4PPSImpl implements IPublicPMOService4PPS
/*     */ {
/*     */   public PublicPMOService4PPSImpl() {}
/*     */   
/*     */   public void pushMO(PMOAggVO[] aggvos) throws BusinessException
/*     */   {
/*  27 */     if (MMArrayUtil.isEmpty(aggvos)) {
/*  28 */       return;
/*     */     }
/*     */     try
/*     */     {
/*  32 */       MapList<String, PMOAggVO> mapAggvo = new MapList();
/*  33 */       PMOBPCalUtil.fillNumberByNum(aggvos);
/*  34 */       for (PMOAggVO aggvo : aggvos) {
/*  35 */         mapAggvo.put(aggvo.getParentVO().getPk_org(), aggvo);
/*     */       }
/*     */       
/*  38 */       for (Entry<String, List<PMOAggVO>> set : mapAggvo.entrySet())
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  48 */         new PMOInsertBP().pushInsert((PMOAggVO[])((List)set.getValue()).toArray(new PMOAggVO[0]), false, "PPS");
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  52 */       ExceptionUtils.marsh(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public IQueryScheme getSql(String pkOrg, String temp) throws BusinessException
/*     */   {
/*  58 */     StringBuffer fromSB = new StringBuffer();
/*  59 */     PMOItemVO item = new PMOItemVO();
/*  60 */     PMOHeadVO head = new PMOHeadVO();
/*  61 */     String headtablename = head.getTableName();
/*  62 */     String itemtablename = item.getTableName();
/*  63 */     fromSB.append(itemtablename);
/*  64 */     fromSB.append(",");
/*  65 */     fromSB.append(temp);
/*  66 */     StringBuffer whereSB = new StringBuffer();
/*  67 */     whereSB.append(headtablename + ".dr=0 and ");
/*  68 */     whereSB.append(headtablename + "." + "pk_org" + "='" + pkOrg + "' and ");
/*  69 */     whereSB.append(headtablename + "." + "fbillstatus" + " in (0,1) and ");
/*  70 */     whereSB.append(itemtablename + "." + "cmaterialid" + "=" + temp + ".pk_material and ");
/*  71 */     whereSB.append(itemtablename + "." + "tplanstarttime" + ">=" + temp + ".dstart and ");
/*  72 */     whereSB.append(itemtablename + "." + "tplanendtime" + "<=" + temp + ".dend");
/*     */     
/*     */ 
/*  75 */     QueryScheme queryScheme = new QueryScheme();
/*  76 */     FromWhereSQL sql = new FromWhereSQLImpl(fromSB.toString(), whereSB.toString());
/*  77 */     queryScheme.putTableJoinFromWhereSQL(sql);
/*  78 */     return queryScheme;
/*     */   }
/*     */   
/*     */   public IDemandResultForCalc getSupply()
/*     */     throws BusinessException
/*     */   {
/* 153 */     return null;
/*     */   }
/*     */
			@Override
			public PMOAggVO[] RdpushMO(PMOAggVO[] paramArrayOfPMOAggVO) throws BusinessException {
	// TODO 自动生成的方法存根
				PMOInsertBP bp=new PMOInsertBP();
				
				return bp.pushInsert(paramArrayOfPMOAggVO,false, "PPS");
			}
			
			}

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\mmpac\META-INF\lib\mmpac_pmo.jar
 * Qualified Name:     nc.pubimpl.mmpac.pmo.pps.PublicPMOService4PPSImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */