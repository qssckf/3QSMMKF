package nc.bs.so.qs.sc.maschine.bp.rule;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ecpubapp.pub.bill.DefaultSuperVO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.AggMaschineVO;
//import nc.vo.so.xlx.tranflow.AggTranFlowVO;


public class BillCheckVOConsistence implements IRule<AggMaschineVO>{

	private BaseDAO bao;
	
	public BaseDAO getBao() {
		
		if(bao==null){
			this.bao=new BaseDAO();
		}
		
		return bao;
	}

	@Override
	public void process(AggMaschineVO[] objs) {
		// TODO 自动生成的方法存根
		
		if ((objs instanceof AggregatedValueObject[])) {
			
			AggregatedValueObject[] aggvos = (AggregatedValueObject[])objs;
			for (AggregatedValueObject aggregatedValueObject : aggvos) {
				AggregatedValueObject aggVo = aggregatedValueObject;
				SuperVO defSupVo = (SuperVO)aggVo.getParentVO();
				String voTs = null;
				if (null != defSupVo.getAttributeValue("ts")) {
					voTs = ((UFDateTime)defSupVo.getAttributeValue("ts")).toString();
				}
				String tsInDb = getTsInDB(defSupVo);
				if (tsInDb == null) {
					ExceptionUtils.wrappException(new BusinessException("时间戳效验错误，时间戳为空！"));
				}
				if (!tsInDb.equals(voTs)) {
					ExceptionUtils.wrappException(new BusinessException("单据已经被修改，请重新刷新单据！"));
				}
			}
	
		}
		
		
	}

	private String getTsInDB(SuperVO defSuperVo){
		// TODO 自动生成的方法存根
		 String tsInDB = null;
		 if (null != defSuperVo) {
		   String tableName = defSuperVo.getTableName();
		   String pkField = defSuperVo.getPKFieldName();
		   String tsSql = "select ts from " + tableName + " where " + pkField + "='" + defSuperVo.getPrimaryKey() + "'";
		   
		   Object objTs = null;
		   try {
			   
			   objTs = getBao().executeQuery(tsSql, new ColumnProcessor());
			   
		   } catch (DAOException e) {
			// TODO 自动生成的 catch 块
			   ExceptionUtils.wrappException(e);
		   }
		   
		   tsInDB = objTs == null ? null : objTs.toString();
		 }
		 return tsInDB;
	}
	
}
