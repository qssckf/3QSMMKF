package nc.bs.so.qs.sc.maschine.bp;
 
//import nc.bs.pd.pd0404.plugin.bpplugin.PDWkPluginPoint;
//import nc.bs.pd.pd0404.rule.PDWkAddAfterEventRule;
//import nc.bs.pd.pd0404.rule.PDWkAddBeforeEventRule;
//import nc.bs.pd.pd0404.rule.PDWkHeadBiggerThanZeroRule;
//import nc.bs.pd.pd0404.rule.PDWkHeadNotNullRule;
//import nc.bs.pd.pd0404.rule.PDWkInsertCacheMaintainRule;
//import nc.bs.pd.pd0404.rule.PDWkMaterialPerFactoryRule;
//import nc.bs.pd.pd0404.rule.PDWkPrdinvItemRule;
//import nc.bs.pd.pd0404.rule.PDWkRepltimeItemRule;
//import nc.bs.pd.pd0404.rule.PDWkSetNrationCapacityRule;
//import nc.bs.pd.pd0404.rule.PDWkSubstItemRule;
//import nc.bs.pd.pd0404.rule.PDWkUniqueMaterClassRule;
//import nc.bs.pd.pd0404.rule.PDWkUniqueValidationRule;
//import nc.bs.pd.pd0404.rule.PDWkWipItemRule;
//import nc.bs.pd.pd0404.rule.ParallelnumRule;
//import nc.bs.pd.pd0404.rule.WorktimeRule;
import java.util.Arrays;
import nc.bs.bd.bp.rule.BDPKLockSuperVORule;
import nc.bs.bd.bp.rule.BDUniqueRule;
import nc.bs.bd.bp.rule.BizLockRule;
import nc.bs.bd.bp.rule.FireEventRule;
import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.rule.StringFieldTrimRule;
import nc.bs.bd.bp.rule.WriteBusiLogRule;
import nc.bs.bd.bp.rule.insert.InsertAuditInfoRule;
import nc.bs.bd.bp.rule.insert.NotifyVersionChangeWhenDataInsertedRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillInsertDataRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.util.mmf.busi.rule.MMRowNoCheckRule;
import nc.util.mmf.framework.base.MMArrayUtil;
//import nc.vo.pd.pd0404.entity.AggWkVO;
//import nc.vo.pd.pd0404.entity.WkProdinvVO;
//import nc.vo.pd.pd0404.entity.WkVO;
//import nc.vo.pd.pd0404.entity.WkWipVO;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.bs.so.qs.sc.maschine.plugin.bpplugin.MaschinePluginPoint;
import nc.bs.so.qs.sc.maschine.rule.MaschineAddAfterEventRule;
import nc.bs.so.qs.sc.maschine.rule.MaschineAddBeforeEventRule;
import nc.bs.so.qs.sc.maschine.rule.MaschineBDUniqueRule;
import nc.bs.so.qs.sc.maschine.rule.MaschineHeadNotNullRule;
import nc.bs.so.qs.sc.maschine.rule.MaschineInsertCacheMaintainRule;
import nc.bs.so.qs.sc.maschine.rule.MaschineUniqueValidationRule;








public class MaschineInsertBP
{
  public MaschineInsertBP() {}
  
  public AggMaschineVO[] insert(AggMaschineVO[] vos)
  {
    if (MMArrayUtil.isEmpty(vos)) {
      return null;
    }
    InsertBPTemplate<AggMaschineVO> bp = new InsertBPTemplate(MaschinePluginPoint.INSERT);
    
    addBeforeRule(bp.getAroundProcesser());
    
    addAfterRule(bp.getAroundProcesser());

    return (AggMaschineVO[])bp.insert(vos);
  }
  





  private void addBeforeRule(AroundProcesser<AggMaschineVO> processer)
  {
    IRule<AggMaschineVO> orgdisabled = new OrgDisabledCheckRule();
    processer.addBeforeRule(orgdisabled);
    
//    IRule<AggMaschineVO> fieldLengthCheckRule = new FieldLengthCheckRule();
//    processer.addBeforeRule(fieldLengthCheckRule);
//    
    IRule<AggMaschineVO> maschineUniqueValidationRule = new MaschineUniqueValidationRule();
    processer.addBeforeRule(maschineUniqueValidationRule);
//    
    IRule<AggMaschineVO> aduitInfoRule = new FillInsertDataRule();
    processer.addBeforeRule(aduitInfoRule);
//    
//    IRule<AggMaschineVO> parallelnumRule = new ParallelnumRule();
//    processer.addBeforeRule(parallelnumRule);
//    
//    IRule<AggMaschineVO> worktimeRule = new WorktimeRule();
//    processer.addBeforeRule(worktimeRule);
//    
//    IRule<AggMaschineVO> pdWkHeadBiggerThanZeroRule = new PDWkHeadBiggerThanZeroRule();
//    processer.addBeforeRule(pdWkHeadBiggerThanZeroRule);
//    
    IRule<AggMaschineVO> maschineHeadNotNullRule = new MaschineHeadNotNullRule();
    processer.addBeforeRule(maschineHeadNotNullRule);
//    
//    IRule<AggMaschineVO> pdWkSubstItemRule = new PDWkSubstItemRule();
//    processer.addBeforeRule(pdWkSubstItemRule);
//    
//    IRule<AggMaschineVO> pdWkWipItemRule = new PDWkWipItemRule();
//    processer.addBeforeRule(pdWkWipItemRule);
//    
//    IRule<AggMaschineVO> pdWkPrdinvItemRule = new PDWkPrdinvItemRule();
//    processer.addBeforeRule(pdWkPrdinvItemRule);
//    
//    IRule<AggMaschineVO> pdWkuniquermaterclass = new PDWkUniqueMaterClassRule();
//    processer.addBeforeRule(pdWkuniquermaterclass);
//
//
//    IRule<AggMaschineVO> pdWkRepltimeItemRule = new PDWkRepltimeItemRule();
//    processer.addBeforeRule(pdWkRepltimeItemRule);
//    
//    IRule<AggWkVO> pdWkMaterialPerFactoryRule = new PDWkMaterialPerFactoryRule();
//    processer.addBeforeRule(pdWkMaterialPerFactoryRule);
//    
//    IRule<AggWkVO> pdWkSetNrationCapacityRule = new PDWkSetNrationCapacityRule();
//    processer.addBeforeRule(pdWkSetNrationCapacityRule);
//    
//    String[] prefixs = { "vdef", "vdef", "vdef" };
//    
//
//    Class<?>[] voClazzs = { WkVO.class, WkWipVO.class, WkProdinvVO.class };
//    
//
//
//
//
//    IRule<AggMaschineVO> defRule = new UserDefSaveRule(prefixs, voClazzs);
//    processer.addBeforeRule(defRule);
//    
    IRule<AggMaschineVO> beforeventrule = new MaschineAddBeforeEventRule();
    processer.addBeforeRule(beforeventrule);
//    
//    IRule<AggMaschineVO> rownocheckrule = new MMRowNoCheckRule();
//    processer.addBeforeRule(rownocheckrule);
    
    
	  
//	  processer.addBeforeRule(new StringFieldTrimRule<AggMaschineVO>());
//	
//	  processer.addBeforeRule(new BDPKLockSuperVORule());
//	
//	  processer.addBeforeRule(new BizLockRule());
//	
//	  processer.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] { "code", "name" })));
//	 唯一性重复
//    IRule<AggMaschineVO> bduniquerule = new MaschineBDUniqueRule();
//    processer.addBeforeRule(bduniquerule);
    
//    processer.addBeforeRule(new BDUniqueRule());
//	
//	  processer.addBeforeRule(new InsertAuditInfoRule());
//	
//	  processer.addBeforeRule(new FireEventRule("1001"));
	  
  }
  






  private void addAfterRule(AroundProcesser<AggMaschineVO> processer)
  {
    IRule<AggMaschineVO> aftereventrule = new MaschineAddAfterEventRule();
    processer.addAfterRule(aftereventrule);
    IRule<AggMaschineVO> cacherule = new MaschineInsertCacheMaintainRule();
    processer.addAfterRule(cacherule);
	  
    //注册
//	  processer.addAfterRule(new NotifyVersionChangeWhenDataInsertedRule());
		
//	  processer.addAfterRule(new FireEventRule("1002"));
		
	  //建立业务日志，没啥用
//	  processer.addAfterRule(new WriteBusiLogRule("add"));
  }
}