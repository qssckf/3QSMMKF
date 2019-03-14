/*     */ package nc.bs.mmpac.pmo.pac0002.bp;
/*     */ 
/*     */ import nc.bs.mmpac.pmo.pac0002.pluginpoint.PMOPluginPoint;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.PMOATPUpdateWithParaRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.PMODeleteAutoDeleteMosRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.PMODeleteAutoDeletePutPlanRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.PMODeleteBatchCodeRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.PMODeletePickmRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.check.PMOCheckDeleteHaveSubRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.check.PMOCheckDeleteStatusRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.check.PMOCheckTurnedDeleteRule;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4INVP;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4MPS;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4PMO;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4PSCRecive;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4PSM;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4Renovate;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4SFC;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4SO;
/*     */ import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewriteDeleteNum4TO;
import nc.bs.mmpac.pmo.pac0002.rule.rewrite.RewritePlanBillDetailStatus;
/*     */ import nc.bs.pubapp.pub.rule.ReturnBillCodeRule;
/*     */ import nc.impl.pubapp.pattern.rule.IRule;
/*     */ import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
/*     */ import nc.util.mmf.framework.gc.GCDeleteBPTemplate;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
/*     */ 
/*     */ public class PMODeleteBP
/*     */ {
/*     */   public PMODeleteBP() {}
/*     */   
/*     */   public void delete(PMOAggVO[] bills)
/*     */   {
/*  33 */     GCDeleteBPTemplate<PMOAggVO> bp = new GCDeleteBPTemplate(PMOPluginPoint.DELETE);
/*     */     
/*  35 */     addBeforeRule(bp.getAroundProcesser());
/*     */     
/*  37 */     addAfterRule(bp.getAroundProcesser());
/*  38 */     bp.delete(bills);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void addBeforeRule(AroundProcesser<PMOAggVO> processer)
/*     */   {
/*  47 */     IRule<PMOAggVO> statusRule = new PMOCheckDeleteStatusRule();
/*  48 */     processer.addBeforeRule(statusRule);
/*     */     
/*  50 */     IRule<PMOAggVO> checkturnrule = new PMOCheckTurnedDeleteRule();
/*  51 */     processer.addBeforeRule(checkturnrule);
/*     */     
/*     */ 
/*  54 */     IRule<PMOAggVO> checkFactoryrule = new PMOATPUpdateWithParaRule(true, "55A2");
/*  55 */     processer.addBeforeRule(checkFactoryrule);
/*     */     
/*     */ 
/*  58 */     IRule<PMOAggVO> rewritePSCRecive = new RewriteDeleteNum4PSCRecive();
/*  59 */     processer.addBeforeRule(rewritePSCRecive);
/*     */     
/*     */ 
/*  62 */     IRule<PMOAggVO> rewritewr = new RewriteDeleteNum4Renovate();
/*  63 */     processer.addBeforeRule(rewritewr);
/*     */     
/*  65 */     IRule<PMOAggVO> rewriteso = new RewriteDeleteNum4SO();
/*  66 */     processer.addBeforeRule(rewriteso);
/*     */     
/*  68 */     IRule<PMOAggVO> rewritepsm = new RewriteDeleteNum4PSM();
/*  69 */     processer.addBeforeRule(rewritepsm);
/*     */     
/*  71 */     IRule<PMOAggVO> rewritepo = new RewriteDeleteNum4MPS();
/*  72 */     processer.addBeforeRule(rewritepo);
/*     */     
/*  74 */     IRule<PMOAggVO> rewriteinvp = new RewriteDeleteNum4INVP();
/*  75 */     processer.addBeforeRule(rewriteinvp);
/*     */     
/*  77 */     IRule<PMOAggVO> rewriteto = new RewriteDeleteNum4TO();
/*  78 */     processer.addBeforeRule(rewriteto);
/*     */     
/*  80 */     IRule<PMOAggVO> rewritesfc = new RewriteDeleteNum4SFC();
/*  81 */     processer.addBeforeRule(rewritesfc);

			  IRule<PMOAggVO> rewritepb = new RewritePlanBillDetailStatus();
/*  81 */     processer.addBeforeRule(rewritepb);


/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void addAfterRule(AroundProcesser<PMOAggVO> processer)
/*     */   {
/*  94 */     IRule<PMOAggVO> checksubbill = new PMOCheckDeleteHaveSubRule();
/*  95 */     processer.addAfterRule(checksubbill);
/*     */     
/*  97 */     IRule<PMOAggVO> pmorule = new RewriteDeleteNum4PMO();
/*  98 */     processer.addAfterRule(pmorule);
/*     */     
/* 100 */     IRule<PMOAggVO> deletePickmRule = new PMODeletePickmRule();
/* 101 */     processer.addAfterRule(deletePickmRule);
/*     */     
/* 103 */     IRule<PMOAggVO> deletebatchbill = new PMODeleteBatchCodeRule();
/* 104 */     processer.addAfterRule(deletebatchbill);
/*     */     
/* 106 */     IRule<PMOAggVO> autoDeletePutPlan = new PMODeleteAutoDeletePutPlanRule();
/* 107 */     processer.addAfterRule(autoDeletePutPlan);
/*     */     
/* 109 */     IRule<PMOAggVO> autoDeleteMos = new PMODeleteAutoDeleteMosRule();
/* 110 */     processer.addAfterRule(autoDeleteMos);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 116 */     IRule<PMOAggVO> checkFactoryrule = new PMOATPUpdateWithParaRule(false, "55A2");
/* 117 */     processer.addAfterRule(checkFactoryrule);
/*     */     
/*     */ 
/* 120 */     IRule<PMOAggVO> billcoderule = new ReturnBillCodeRule("55A2", "vbillcode", "pk_group", "pk_org");
/*     */     
/* 122 */     processer.addAfterRule(billcoderule);
/*     */   }
/*     */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\mmpac\META-INF\lib\mmpac_pmo.jar
 * Qualified Name:     nc.bs.mmpac.pmo.pac0002.bp.PMODeleteBP
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */