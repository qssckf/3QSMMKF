package nc.bs.so.qs.sc.maschine.rule;

import java.util.ArrayList;
import java.util.List;
import nc.bs.bd.baseservice.DefaultGetBizInfoByMDUtil;
import nc.bs.bd.baseservice.IGetBizInfoUtil;
import nc.bs.bd.cache.CacheProxy;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.util.mmf.framework.base.MMArrayUtil;
import nc.util.mmf.framework.base.MMValueCheck;
//import nc.vo.pd.pd0404.entity.AggWkVO;
//import nc.vo.pd.pd0404.entity.WkProdinvVO;
//import nc.vo.pd.pd0404.entity.WkRepltimeVO;
//import nc.vo.pd.pd0404.entity.WkSubstVO;
//import nc.vo.pd.pd0404.entity.WkVO;
//import nc.vo.pd.pd0404.entity.WkWipVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MachineCustomer;
import nc.vo.so.qs.sc.MachineDept;
import nc.vo.so.qs.sc.MachineMater_Mater;
import nc.vo.so.qs.sc.MaschineVO;
import nc.vo.trade.voutils.VOUtil;





public class MaschineDeleteCacheMaintainRule implements IRule<AggMaschineVO> {
  public MaschineDeleteCacheMaintainRule() {}
  
  public void process(AggMaschineVO[] vos)
  {
    try
    {
      List<MaschineVO> maschineVOs = new ArrayList();
      List<MachineCustomer> maschineCustomerVOs = new ArrayList();
      List<MachineDept> maschineDeptVOs = new ArrayList();
      List<MachineMater_Mater> maschineMaterVOs = new ArrayList();
      if (MMArrayUtil.isEmpty(vos)) {
        return;
      }
      for (AggMaschineVO aggVO : vos) {
        if (!MMValueCheck.isEmpty(aggVO.getParentVO())) {
          maschineVOs.add((MaschineVO)aggVO.getParentVO());
        }
        if (!MMArrayUtil.isEmpty(aggVO.getChildren(MachineCustomer.class))) {
          for (int i = 0; i < aggVO.getChildren(MachineCustomer.class).length; i++) {
        	  maschineCustomerVOs.add((MachineCustomer)aggVO.getChildren(MachineCustomer.class)[i]);
          }
        }
        if (!MMArrayUtil.isEmpty(aggVO.getChildren(MachineDept.class))) {
          for (int i = 0; i < aggVO.getChildren(MachineDept.class).length; i++) {
        	  maschineDeptVOs.add((MachineDept)aggVO.getChildren(MachineDept.class)[i]);
          }
        }
        if (!MMArrayUtil.isEmpty(aggVO.getChildren(MachineMater_Mater.class))) {
          for (int i = 0; i < aggVO.getChildren(MachineMater_Mater.class).length; i++) {
        	  maschineMaterVOs.add((MachineMater_Mater)aggVO.getChildren(MachineMater_Mater.class)[i]);
          }
        }
      }
      
      IGetBizInfoUtil bizInfoUtil = new DefaultGetBizInfoByMDUtil();
      List<String> maschineVOpks = VOUtil.extractFieldValues((CircularlyAccessibleValueObject[])maschineVOs.toArray(new MaschineVO[0]), bizInfoUtil.getFieldName(new MaschineVO(), "id"), null);
      

      List<String> maschineCustomerVOpks = VOUtil.extractFieldValues((CircularlyAccessibleValueObject[])maschineCustomerVOs.toArray(new MachineCustomer[0]), bizInfoUtil.getFieldName(new MachineCustomer(), "id"), null);
      

      List<String> maschineDeptVOpks = VOUtil.extractFieldValues((CircularlyAccessibleValueObject[])maschineDeptVOs.toArray(new MachineDept[0]), bizInfoUtil.getFieldName(new MachineDept(), "id"), null);
      

      List<String> maschineMaterVOpks = VOUtil.extractFieldValues((CircularlyAccessibleValueObject[])maschineMaterVOs.toArray(new MachineMater_Mater[0]), bizInfoUtil.getFieldName(new MachineMater_Mater(), "id"), null);
      
      



      CacheProxy.fireDataDeletedBatch(bizInfoUtil.getTableName(new MaschineVO()), (String[])maschineVOpks.toArray(new String[0]));
      
      CacheProxy.fireDataDeletedBatch(bizInfoUtil.getTableName(new MachineCustomer()), (String[])maschineCustomerVOpks.toArray(new String[0]));
      

      CacheProxy.fireDataDeletedBatch(bizInfoUtil.getTableName(new MachineDept()), (String[])maschineDeptVOpks.toArray(new String[0]));
      
      CacheProxy.fireDataDeletedBatch(bizInfoUtil.getTableName(new MachineMater_Mater()), (String[])maschineMaterVOpks.toArray(new String[0]));
      
    }
    catch (BusinessException e)
    {
      ExceptionUtils.wrappException(e);
    }
  }
}