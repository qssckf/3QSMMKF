package nc.pubimpl.so.fq.so.m30.rule;
 
 import java.util.Map;
 import nc.bs.pubapp.AppBsContext;
 import nc.impl.pubapp.env.BSContext;
 import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.fq.so.m30.Rewrite30Para;
 import nc.vo.pub.lang.UFDate;
 import nc.vo.pub.lang.UFDouble;
 import nc.vo.pubapp.AppContext;
 import nc.vo.pubapp.pattern.pub.MathTool;
 import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;
 
 
 
 
 
 
 
 
 public class RewriteSetArrInfoRule implements IRule<ShipmentsViewVO>
 {
   public RewriteSetArrInfoRule() {}
   
   public void process(ShipmentsViewVO[] vos)
   {
     Map<String, Rewrite30Para> mParas = (Map)BSContext.getInstance().getSession(Rewrite30Para.class.getName());
     
 
     String carrangeid = AppContext.getInstance().getPkUser();
     
     UFDate darrdate = AppBsContext.getInstance().getBusiDate();
     
     for (ShipmentsViewVO vo : vos) {
    	 
    	 ShipmentsBVO item = vo.getItem();
    	 Rewrite30Para para = (Rewrite30Para)mParas.get(item.getPk_shipments_b());
       
    	 UFDouble narrnum = item.getNarrnum();
       
    	 narrnum = MathTool.add(narrnum, para.getNnum());
    	 item.setNarrnum(narrnum);
       
    	 item.setCarrangeid(carrangeid);
       
    	 item.setDarrdate(darrdate);
     }
   }
 }

