package nc.bs.so.qs.readyplan.bp.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class ReWriteByUpdateMmPlanRule implements ICompareRule<RdPorductDetailVO> {

	private BaseDAO dao;
	
	public BaseDAO getDao() {
		
		if(dao==null){
			this.dao=new BaseDAO();
		}
		
		return dao;
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	@Override
	public void process(RdPorductDetailVO[] vo, RdPorductDetailVO[] oldvo) {
		// TODO 自动生成的方法存根
		
		try{
			
			Map<String,UFDouble> updateinfo=getUpdateInfo(vo,oldvo);
			
			for(String bid:updateinfo.keySet()){
				
				PlanProduceDetailVO srcvo=this.getMDQueryService().queryBillOfVOByPK(PlanProduceDetailVO.class,bid,false);
				
				if(srcvo==null){
					throw new BusinessException("没有找到来源单据对象，回写数据失败！");
				}
				
				srcvo.setStatus(1);
				
				String hsl=srcvo.getVchangerate();
				
				UFDouble nnum=(srcvo.getTotalnum()==null?updateinfo.get(bid):srcvo.getTotalnum().add(updateinfo.get(bid)));
				
				UFDouble nastnum=HslParseUtil.hslDivUFDouble(hsl, nnum);
				
				srcvo.setTotalnum(nnum);
				
				srcvo.setTotalnastuum(nastnum);
				
				this.getDao().updateVO(srcvo, new String[]{"totalnum","totalnastuum"});

			}
			
		}catch(Exception e){
			
			ExceptionUtils.wrappException(e);
			
		}
		
		
		
		
		
	}

	private Map<String, UFDouble> getUpdateInfo(RdPorductDetailVO[] vo,RdPorductDetailVO[] oldvo) {
		// TODO 自动生成的方法存根
		
		Map<String,UFDouble> updateinfo=new HashMap();
		
		for(RdPorductDetailVO v:vo){
			
			for(RdPorductDetailVO oldv:oldvo){
				
				if(v.getPk_rdpd().equals(oldv.getPk_rdpd())){
					
					UFDouble dffnum=v.getProdnum().sub(oldv.getProdnum());
					
					if(updateinfo.containsKey(v.getVsrcplanbid())){
						
						updateinfo.put(v.getVsrcplanbid(), updateinfo.get(v.getVsrcplanbid()).add(dffnum));
						
					}else{
						
						updateinfo.put(v.getVsrcplanbid(), dffnum);
						
					}
					
					break;
					
				}
				
				
			}
			
			
		}
		
		
		
		return updateinfo;
	}

}
