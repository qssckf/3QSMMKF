package nc.bs.so.qs.sc.readyplan.bp.opera;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.qs.sc.readyplan.bp.RdReleasePMOBP;
import nc.pubitf.uapbd.IMaterialPubService;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.bd.material.prod.MaterialProdModeEnum;
import nc.vo.bd.material.prod.MaterialProdVO;
import nc.vo.mmpps.mpm.res.MpmRes;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdReleaseOperator implements nc.impl.pubapp.pattern.rule.template.IOperator<RdPorductDetailVO>{

	@Override
	public RdPorductDetailVO[] operate(RdPorductDetailVO[] obj) {
		// TODO 自动生成的方法存根
		try{
			
			doRelease(obj);
			
		}catch(Exception e){
			
			ExceptionUtils.wrappException(e);
			
		}
		
		
		return null;
	}

	private void doRelease(RdPorductDetailVO[] obj) throws BusinessException {
		// TODO 自动生成的方法存根
		
		List<RdPorductDetailVO> tpmoaggs = new ArrayList();
		List<RdPorductDetailVO> tdmoaggs = new ArrayList();
		
		for(RdPorductDetailVO vo:obj){
			
			String pk_mat=vo.getPk_material();
			String pk_stockorgid=vo.getPk_org();
			
			IMaterialPubService service = (IMaterialPubService)NCLocator.getInstance().lookup(IMaterialPubService.class);
			Map<String, MaterialProdVO> results = service.queryMaterialProduceInfoByPks(new String[] { pk_mat }, pk_stockorgid, new String[] { "prodmode" });
			
			
			if ((null != results) && (results.size() > 0)) {
				
				MaterialProdVO prodVO = (MaterialProdVO)results.get(pk_mat);
				
				if (null != prodVO) {
					
					Integer prodMode = prodVO.getProdmode();
					
					if (MMValueCheck.isNotEmpty(prodMode)) {
						
						 if (MaterialProdModeEnum.PRODMODE_PROD.toIntValue() == prodMode.intValue()){
							 tpmoaggs.add(vo);
						 }else if (MaterialProdModeEnum.PRODMODE_SEPPROD.toIntValue() == prodMode.intValue()){
							 tdmoaggs.add(vo);
						 }

					}

				}else {
					ExceptionUtils.wrappBusinessException("没有获取到对象，操作不能继续！");
				}
				
				
			}else {
				ExceptionUtils.wrappBusinessException("没有获取到物料生产对象，操作不能继续！");
			}
			
		}
		
		if (!tpmoaggs.isEmpty()) {
			
			RdPorductDetailVO[] rdvos=tpmoaggs.toArray(new RdPorductDetailVO[tpmoaggs.size()]);
			
			new RdReleasePMOBP().doPMORelease(rdvos);
		}
		
		if(!tdmoaggs.isEmpty()){
			
			ExceptionUtils.wrappBusinessException("不支持生成离散生产订单，操作不能继续！");
			
		}
		
		
		
	}

}
