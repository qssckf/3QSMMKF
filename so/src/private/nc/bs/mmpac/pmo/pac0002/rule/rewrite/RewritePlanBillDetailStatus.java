package nc.bs.mmpac.pmo.pac0002.rule.rewrite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.core.service.TimeService;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.util.mmf.framework.base.MMArrayUtil;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RewritePlanBillDetailStatus implements IRule<PMOAggVO>{

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
	public void process(PMOAggVO[] aggvos) {
		// TODO 自动生成的方法存根
		
		
		try{
			
			if (MMArrayUtil.isEmpty(aggvos)) {
				return;
			}
			
			HashSet<String> rdid=new HashSet();
			
			for (PMOAggVO aggvo : aggvos) {
				
				PMOItemVO[] items = aggvo.getChildrenVO();
				
				if (!MMArrayUtil.isEmpty(items)) {
					 
					for (PMOItemVO item : items) {
						
						if("QS01".equals(item.getVsrctype())){
							
							rdid.add(item.getVsrcbid());
							
						}
						
						
					}
					 
					 
				}
				
			}
			
			
			if(rdid.size()>0){
				
				
				Collection<IntoProdDetailVO> cols=this.getMDQueryService().queryBillOfVOByPKs(IntoProdDetailVO.class, rdid.toArray(new String[rdid.size()]), false);
				
				for(IntoProdDetailVO col:cols){
					
					col.setStatus(1);
					col.setSfmakebill(UFBoolean.FALSE);
					col.setPomvbillcode(null);
					col.setModifier(InvocationInfoProxy.getInstance().getUserId());
					col.setModifiedtime(new UFDateTime(new Date(TimeService.getInstance().getTime())));
					
					
					this.getDao().updateVO(col, new String[]{"sfmakebill","pomvbillcode","modifier","modifiedtime"});
					
				}
				
			
			}
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		
	}
		
	
	
	

}
