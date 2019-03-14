package nc.pubimpl.so.fq.so.m30.rule;
 
 import java.util.Map;
 import nc.bs.pubapp.AppBsContext;
 import nc.impl.pubapp.env.BSContext;
 import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.fq.so.m30.Rewrite30Para;
import nc.pubitf.so.fq.so.m30.RewritefqPara;
 import nc.vo.pub.lang.UFDate;
 import nc.vo.pub.lang.UFDouble;
 import nc.vo.pubapp.AppContext;
 import nc.vo.pubapp.pattern.pub.MathTool;
 import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;
 
 
 
 
 
 
 
 
 public class Rewrite38SetArrInfoRule implements IRule<PreOrderViewVO>
 {
   public Rewrite38SetArrInfoRule() {}
   
   public void process(PreOrderViewVO[] vos)
   {
     Map<String, RewritefqPara> mParas = (Map)BSContext.getInstance().getSession(RewritefqPara.class.getName());
     
 
     String carrangeid = AppContext.getInstance().getPkUser();
     
     UFDate darrdate = AppBsContext.getInstance().getBusiDate();
     
     for (PreOrderViewVO vo : vos) {
    	 
    	 PreOrderBVO item = vo.getItem();
    	 RewritefqPara para = (RewritefqPara)mParas.get(item.getCpreorderbid());
       
    	 UFDouble narrnum = item.getNarrnum();

    	 narrnum = MathTool.add(narrnum, para.getNnum());
    	 
    	 if(UFDouble.ZERO_DBL.equals(narrnum)){
    		 item.setVbdef20("N");
    	 }else{
    		 item.setVbdef20("Y");
    	 }
    	 
    	 item.setNarrnum(narrnum);
       
    	 item.setCarrangeid(carrangeid);
       
    	 item.setDarrdate(darrdate);
     }
   }
 }

