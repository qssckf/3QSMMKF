package nc.bs.so.qs.sc.intoprod.bp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdNumChRule<E extends SuperVO> implements IRule<E>{
	
	public static IMDPersistenceQueryService getMDQueryService()
	{
		return MDPersistenceService.lookupPersistenceQueryService();
	}

	@Override
	public void process(E[] objs) {
		// TODO �Զ����ɵķ������
		try{
			
			for(SuperVO obj:objs){
				
				String srcbid=(String) obj.getAttributeValue("vsrcrdid");
				
				RdPorductDetailVO detailvo = this.getMDQueryService().queryBillOfVOByPK(RdPorductDetailVO.class, srcbid, false);
				
				if(detailvo!=null){
					
					if(detailvo.getTintoprodnum().toDouble() >= detailvo.getProdnum().toDouble()){
						throw new BusinessException("Ͷ�������Ѿ������Ų����������ܱ��棡");
					}
					
				}else{
					throw new BusinessException("û���ҵ���Դ���ݣ��Ų������쳣�������ԣ�");
				}
				
			}
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
	}

}
