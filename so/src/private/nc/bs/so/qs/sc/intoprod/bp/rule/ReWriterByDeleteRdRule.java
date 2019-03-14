package nc.bs.so.qs.sc.intoprod.bp.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class ReWriterByDeleteRdRule implements IRule<IntoProdDetailVO>{

	private BaseDAO dao;
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	public BaseDAO getDao() {
		
		if(dao==null){
			this.dao=new BaseDAO();
		}
		
		return dao;
	}
	
	@Override
	public void process(IntoProdDetailVO[] objs) {
		// TODO 自动生成的方法存根
		try{
			
			Map<String,UFDouble> bids=new HashMap();
			
			for(IntoProdDetailVO obj:objs){
				
				String pk_srcid = obj.getVsrcrdid();
				
				if(bids.containsKey(pk_srcid)){
					
					bids.put(pk_srcid, bids.get(pk_srcid).add(obj.getProdnum()));
					
				}else{
					bids.put(pk_srcid, obj.getProdnum());
				}		
				
			}
			
			for(String bid:bids.keySet()){
				
				RdPorductDetailVO srcvo=this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class,bid,false);
				
				if(srcvo==null){
					throw new BusinessException("没有找到来源单据对象，回写数据失败！");
				}
				
				srcvo.setStatus(1);
				
				String hsl=srcvo.getRate();
				
				UFDouble nnum=(srcvo.getTintoprodnum()==null?bids.get(bid):srcvo.getTintoprodnum().sub(bids.get(bid)));
				
				UFDouble nastnum=HslParseUtil.hslDivUFDouble(hsl, nnum);
				
				srcvo.setTintoprodnum(nnum);
				
				srcvo.setTintopronastnum(nastnum);
				
				if(srcvo.getTintoprodnum().toDouble() >= srcvo.getProdnum().toDouble()){
					srcvo.setBillstatus(3);
				}else{
					srcvo.setBillstatus(2);
				}
				
				this.getDao().updateVO(srcvo,new String[]{"tintoprodnum","tintopronastnum","billstatus"});

			}
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
	}

}
