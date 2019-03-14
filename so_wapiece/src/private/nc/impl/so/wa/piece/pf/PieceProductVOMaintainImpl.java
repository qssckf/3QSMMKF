package nc.impl.so.wa.piece.pf;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import nc.impl.pub.ace.AcePieceProductVOPubServiceImpl;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.so.wa.piece.pf.ace.rule.DataUniqueCheckRule;
import nc.bs.so.wa.piece.pf.bp.PieceProductSetDefaultBP;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.bd.meta.BatchOperateVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.wa.piece.PieceProductVO;

public class PieceProductVOMaintainImpl extends AcePieceProductVOPubServiceImpl implements nc.itf.so.wa.piece.pf.IPieceProductVOMaintain {

	private DataAccessUtils dataacc;
	
	private DataAccessUtils getDataAcc(){
		if (this.dataacc==null){
			this.dataacc= new DataAccessUtils();
		}
		return this.dataacc;
	}
  
  @Override
  public PieceProductVO[] query(IQueryScheme queryScheme)
      throws BusinessException {
      return super.pubquerybasedoc(queryScheme);
  }


  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
    
	  BatchSaveAction<PieceProductVO> saveAction = new BatchSaveAction<PieceProductVO>();
	  BatchOperateVO retData = saveAction.batchSave(batchVO);
	  //调用编码、名称唯一性校验规则
	  new DataUniqueCheckRule().process(new BatchOperateVO[]{batchVO});
	  return retData;
  }


@Override
public String isHaveDefault(String pk_org) throws BusinessException {
	// TODO 自动生成的方法存根
	
	if (StringUtils.isEmpty(pk_org)) {
		return null;
	}
	
	StringBuffer sql = new StringBuffer(" select ");
	sql.append("pk_ppid");
	sql.append(" from ").append("so_pd");
	sql.append(" where ").append("pk_org");
	sql.append("='").append(pk_org).append("' ");
	sql.append(" and ").append("isdefault");
	sql.append("='").append(UFBoolean.TRUE).append("'");
	sql.append(" and dr=0 ");
	
	
	try{
		
		IRowSet row=this.getDataAcc().query(sql.toString());
		
		if(row.next()){
			
			return row.getString(0);
			
		}else{
			
			return null;
			
		}
		
	}catch (Exception e) {
		
		throw new BusinessException(e.getMessage());
	}

	
}


@Override
public PieceProductVO setDefault(PieceProductVO pd) throws BusinessException {
	// TODO 自动生成的方法存根
	
	
	PieceProductSetDefaultBP action=new PieceProductSetDefaultBP();
	

	return action.SetDefault(pd);
}



}
