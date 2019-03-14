package nc.bs.so.qs.sc.maschine.bp;

import java.util.Arrays;

import nc.bs.bd.baseservice.DefaultGetBizInfoByMDUtil;
import nc.bs.bd.baseservice.IGetBizInfoUtil;
//import nc.bs.pd.pd0404.plugin.bpplugin.PDWkPluginPoint;
//import nc.bs.pd.pd0404.rule.PDWkHeadBiggerThanZeroRule;
//import nc.bs.pd.pd0404.rule.PDWkHeadNotNullRule;
//import nc.bs.pd.pd0404.rule.PDWkMaterialPerFactoryRule;
//import nc.bs.pd.pd0404.rule.PDWkPrdinvItemRule;
//import nc.bs.pd.pd0404.rule.PDWkRepltimeItemRule;
//import nc.bs.pd.pd0404.rule.PDWkSetNrationCapacityRule;
//import nc.bs.pd.pd0404.rule.PDWkSubstItemRule;
//import nc.bs.pd.pd0404.rule.PDWkUniqueMaterClassRule;
//import nc.bs.pd.pd0404.rule.PDWkUniqueValidationRule;
//import nc.bs.pd.pd0404.rule.PDWkUpdateAfterEventRule;
//import nc.bs.pd.pd0404.rule.PDWkUpdateBeforeEventRule;
//import nc.bs.pd.pd0404.rule.PDWkUpdateCacheMaintainRule;
//import nc.bs.pd.pd0404.rule.PDWkWipItemRule;
//import nc.bs.pd.pd0404.rule.ParallelnumRule;
//import nc.bs.pd.pd0404.rule.WorktimeRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillUpdateDataRule;
import nc.bs.pubapp.pub.rule.OrgDisabledCheckRule;
import nc.bs.so.qs.sc.maschine.plugin.bpplugin.MaschinePluginPoint;
import nc.bs.so.qs.sc.maschine.rule.MaschineHeadNotNullRule;
import nc.bs.so.qs.sc.maschine.rule.MaschineUpdateAfterEventRule;
import nc.bs.so.qs.sc.maschine.rule.MaschineUpdateBeforeEventRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.util.mmf.busi.rule.MMRowNoCheckRule;
import nc.util.mmf.framework.base.MMArrayUtil;
//import nc.vo.pd.pd0404.entity.AggWkVO;
//import nc.vo.pd.pd0404.entity.WkProdinvVO;
//import nc.vo.pd.pd0404.entity.WkVO;
//import nc.vo.pd.pd0404.entity.WkWipVO;
import nc.vo.pub.ISuperVO;
import nc.vo.so.qs.sc.AggMaschineVO;






public class MaschineUpdateBP
{
  protected IGetBizInfoUtil bizInfoUtil = new DefaultGetBizInfoByMDUtil();
  


  public MaschineUpdateBP() {}
  

  public AggMaschineVO[] update(AggMaschineVO[] vos, AggMaschineVO[] originvos)
  {
    if (MMArrayUtil.isEmpty(vos)) {
      return null;
    }
    for (AggMaschineVO vo : vos) {
      if (vo.getParent().getStatus() != 1) {
        vo.getParent().setStatus(1);
      }
    }
    UpdateBPTemplate<AggMaschineVO> bp = new UpdateBPTemplate(MaschinePluginPoint.UPDATE);
    
    addBeforeRule(bp.getAroundProcesser());
    
    addAfterRule(bp.getAroundProcesser());
    return (AggMaschineVO[])bp.update(vos, originvos);
  }
  





  private void addBeforeRule(CompareAroundProcesser<AggMaschineVO> processer)
  {
    IRule<AggMaschineVO> orgdisabled = new OrgDisabledCheckRule();
    processer.addBeforeRule(orgdisabled);
//    
//    IRule<AggMaschineVO> fieldLengthCheckRule = new FieldLengthCheckRule();
//    processer.addBeforeRule(fieldLengthCheckRule);
//    
//    IRule<AggMaschineVO> pdWkUniqueValidationRule = new PDWkUniqueValidationRule();
//    processer.addBeforeRule(pdWkUniqueValidationRule);
//    
    IRule<AggMaschineVO> aduitInfoRule = new FillUpdateDataRule();
    processer.addBeforeRule(aduitInfoRule);
//    
//
//
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
//    IRule<AggMaschineVO> pdWkHeadNotNullRule = new MaschineHeadNotNullRule();
//    processer.addBeforeRule(pdWkHeadNotNullRule);
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
//
//
//
//
//    IRule<AggMaschineVO> pdWkRepltimeItemRule = new PDWkRepltimeItemRule();
//    processer.addBeforeRule(pdWkRepltimeItemRule);
//    
//    IRule<AggMaschineVO> pdWkMaterialPerFactoryRule = new PDWkMaterialPerFactoryRule();
//    processer.addBeforeRule(pdWkMaterialPerFactoryRule);
//    
//    IRule<AggMaschineVO> pdWkSetNrationCapacityRule = new PDWkSetNrationCapacityRule();
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
    IRule<AggMaschineVO> beforeventrule = new MaschineUpdateBeforeEventRule();
    processer.addBeforeRule(beforeventrule);
//    
//    IRule<AggMaschineVO> rownocheckrule = new MMRowNoCheckRule();
//    processer.addBeforeRule(rownocheckrule);
 
  }
  





  private void addAfterRule(CompareAroundProcesser<AggMaschineVO> processer)
  {
    IRule<AggMaschineVO> aftereventrule = new MaschineUpdateAfterEventRule();
    processer.addAfterRule(aftereventrule);
//    IRule<AggMaschineVO> cacherule = new PDWkUpdateCacheMaintainRule();
//    processer.addAfterRule(cacherule);
	  
  }
}