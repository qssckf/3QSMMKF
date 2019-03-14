package nc.bs.so.qs.sc.maschine.rule;
/*    */ 
/*    */ import nc.bs.uif2.validation.IValidationService;
/*    */ import nc.bs.uif2.validation.ValidationException;
/*    */ import nc.bs.uif2.validation.ValidationFrameworkUtil;
/*    */ import nc.bs.uif2.validation.Validator;
/*    */ import nc.impl.pubapp.pattern.rule.IRule;
/*    */ import nc.util.mmf.framework.base.MMArrayUtil;
///*    */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*    */ import nc.vo.pd.pd0404.entity.WkVO;
/*    */ import nc.vo.pub.BusinessException;
/*    */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*    */ import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;
/*    */ import nc.vo.util.BDPKLockUtil;
import nc.vo.util.BDUniqueRuleValidate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineUniqueValidationRule
/*    */   implements IRule<AggMaschineVO>
/*    */ {
/*    */   public MaschineUniqueValidationRule() {}
/*    */   
/*    */   public void process(AggMaschineVO[] vos)
/*    */   {
/* 29 */     if (MMArrayUtil.isEmpty(vos)) {
/* 30 */       return;
/*    */     }
/* 32 */     for (AggMaschineVO vo : vos) {
/*    */       try
/*    */       {
/* 35 */         BDPKLockUtil.lockString(new String[] {((MaschineVO)vo.getParentVO()).getMachcode()});
/*    */         
/* 37 */         doUniqueValidation(vo);
/*    */       }
/*    */       catch (BusinessException e) {
/* 40 */         ExceptionUtils.wrappException(e);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void doUniqueValidation(IBill object)
/*    */   {
/* 52 */     if (object == null) {
/* 53 */       return;
/*    */     }
/* 55 */     IValidationService validateService = ValidationFrameworkUtil.createValidationService(new Validator[] { new BDUniqueRuleValidate() });
/*    */     
/*    */     try
/*    */     {
/* 59 */       validateService.validate(object.getParent());
/*    */     } catch (ValidationException e) {
/* 61 */       ExceptionUtils.wrappException(e);
/*    */     }
/*    */   }
/*    */ }