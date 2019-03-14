package nc.bs.so.qs.sc.intoprod.bp.rule;

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
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class ReWriteByUpdateRdRule implements ICompareRule<IntoProdDetailVO>{
	
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
	public void process(IntoProdDetailVO[] vo, IntoProdDetailVO[] oldvo) {
		// TODO 自动生成的方法存根
		
		try{
			
			Map<String,UFDouble> updateinfo=getUpdateInfo(vo,oldvo);
			
			for(String bid:updateinfo.keySet()){
				
				RdPorductDetailVO srcvo = this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class,bid,false);
				
				if(srcvo==null){
					throw new BusinessException("没有找到来源单据对象，回写数据失败！");
				}
				
				srcvo.setStatus(1);
				
				String hsl=srcvo.getRate();
				
				UFDouble nnum=(srcvo.getTintoprodnum()==null?updateinfo.get(bid):srcvo.getTintoprodnum().add(updateinfo.get(bid)));
				
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
	
	private Map<String, UFDouble> getUpdateInfo(IntoProdDetailVO[] vo, IntoProdDetailVO[] oldvo){
		
		Map<String,UFDouble> updateinfo=new HashMap();
		
		for(IntoProdDetailVO v:vo){
			
			for(IntoProdDetailVO oldv:oldvo){
				
				if(v.getPk_itpd().equals(oldv.getPk_itpd())){
					
					UFDouble dffnum=v.getProdnum().sub(oldv.getProdnum());
					
					if(updateinfo.containsKey(v.getVsrcrdid())){
						
						updateinfo.put(v.getVsrcrdid(), updateinfo.get(v.getVsrcrdid()).add(dffnum));
						
					}else{
						
						updateinfo.put(v.getVsrcrdid(), dffnum);
						
					}
					
					break;
					
				}
				
			}
			
		}
		
		return updateinfo;
		
		
	}

}
