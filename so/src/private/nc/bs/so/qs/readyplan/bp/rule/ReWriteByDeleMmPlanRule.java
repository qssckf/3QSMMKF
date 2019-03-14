package nc.bs.so.qs.readyplan.bp.rule;

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
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class ReWriteByDeleMmPlanRule implements IRule<RdPorductDetailVO>{
	
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
	public void process(RdPorductDetailVO[] objs) {
		// TODO 自动生成的方法存根
		
		try{
			
			Map<String,UFDouble> bids=new HashMap();
			
			for(RdPorductDetailVO vo:objs){
				
				String pk_srcbid=vo.getVsrcplanbid();
				
				if(bids.containsKey(pk_srcbid)){
					
					bids.put(pk_srcbid, bids.get(pk_srcbid).add(vo.getProdnum()));
					
				}else{
					bids.put(pk_srcbid, vo.getProdnum());
				}
				
			}
			
			for(String bid:bids.keySet()){
				
				PlanProduceDetailVO srcvo=this.getMDQueryService().queryBillOfVOByPK(PlanProduceDetailVO.class,bid,false);
				
				if(srcvo==null){
					throw new BusinessException("没有找到来源单据对象，回写数据失败！");
				}
				
				srcvo.setStatus(1);
				
				String hsl=srcvo.getVchangerate();
				
				UFDouble nnum=(srcvo.getTotalnum()==null?bids.get(bid):srcvo.getTotalnum().sub(bids.get(bid)));
				
				UFDouble nastnum=HslParseUtil.hslDivUFDouble(hsl, nnum);
				
				srcvo.setTotalnum(nnum);
				
				srcvo.setTotalnastuum(nastnum);
				
				this.getDao().updateVO(srcvo, new String[]{"totalnum","totalnastuum"});

			}
			
			
		}catch(Exception e){
			
			ExceptionUtils.wrappException(e);
		}
		
		
		
	}
	
}
