package nc.bs.so.qs.sc.readyplan.bp.opera;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.qs.sc.intoprod.bp.IntoProdReleasePMOBP;
import nc.bs.so.qs.sc.readyplan.bp.RdReleasePMOBP;
import nc.pubitf.uapbd.IMaterialPubService;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.bd.material.prod.MaterialProdModeEnum;
import nc.vo.bd.material.prod.MaterialProdVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;


public class IpReleaseOperator implements nc.impl.pubapp.pattern.rule.template.IOperator<IntoProdDetailVO>{

	@Override
	public IntoProdDetailVO[] operate(IntoProdDetailVO[] obj) {
		// TODO 自动生成的方法存根
		
		try{
			
			doRelease(obj);
			
		}catch(Exception e){
			
			ExceptionUtils.wrappException(e);
			
		}
		
		return null;
	}

	private void doRelease(IntoProdDetailVO[] obj) throws BusinessException {
		// TODO 自动生成的方法存根
		
		List<IntoProdDetailVO> tpmoaggs = new ArrayList();
		List<IntoProdDetailVO> tdmoaggs = new ArrayList();
		
		for(IntoProdDetailVO vo:obj){
			
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
			
			IntoProdDetailVO[] rdvos=tpmoaggs.toArray(new IntoProdDetailVO[tpmoaggs.size()]);
			
			IntoProdReleasePMOBP action=new IntoProdReleasePMOBP();
			
			action.doPMORelease(rdvos);
		}
		
		if(!tdmoaggs.isEmpty()){
			
			ExceptionUtils.wrappBusinessException("不支持生成离散生产订单，操作不能继续！");
			
		}
		
	}

}
