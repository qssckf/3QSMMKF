package nc.bs.so.fq.maintain.rule.insert;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import nc.bs.framework.common.NCLocator;
import nc.bs.so.fq.maintain.util.RewriteBillUtil;
 import nc.impl.pubapp.bill.rewrite.BillRewriter;
 import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
 import nc.impl.pubapp.bill.rewrite.RewritePara;
 import nc.impl.pubapp.pattern.rule.IRule;
 import nc.pubitf.ct.saledaily.so.IReWriteZ3For30;
 import nc.vo.ct.entity.CtWriteBackForOrderVO;
 import nc.vo.ct.saledaily.entity.CtSaleBVO;
 import nc.vo.ct.saledaily.entity.CtSaleVO;
 import nc.vo.pub.BusinessException;
 import nc.vo.pub.lang.UFDouble;
 import nc.vo.pubapp.pattern.exception.ExceptionUtils;
 import nc.vo.scmpub.res.billtype.CTBillType;
 import nc.vo.scmpub.res.billtype.ICBillType;
 import nc.vo.scmpub.res.billtype.OPCBillType;
 import nc.vo.scmpub.res.billtype.POBillType;
 import nc.vo.scmpub.res.billtype.SOBillType;
 import nc.vo.so.m30.entity.SaleOrderBVO;
 import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.trade.checkrule.VOChecker;
 

 public class RewriteBillInsertRule
   implements IRule<AggShipmentsVO>
 {
   public RewriteBillInsertRule() {}
   
   public void process(AggShipmentsVO[] vos)
   {
     RewriteBillUtil rewriteUtil = new RewriteBillUtil();
     BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
     Map<String, List<RewritePara>> srcParaIndex = srctool.splitForInsert(vos);
     
     List<RewritePara> srcpara = (List)srcParaIndex.get(SOBillType.PreOrder.getCode());
     
     if (!VOChecker.isEmpty(srcpara)) {
        rewriteUtil.reWriteSrc38(srcpara);
     }
     
    
   }
   
 }
