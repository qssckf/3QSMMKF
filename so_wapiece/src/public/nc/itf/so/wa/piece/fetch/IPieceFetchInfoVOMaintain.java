package nc.itf.so.wa.piece.fetch;

import java.util.ArrayList;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.smart.SmartDefVO;
import nc.vo.so.wa.piece.fetch.PieceFetchInfoVO;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.itf.pubapp.pub.smart.ISmartService;

public interface IPieceFetchInfoVOMaintain extends ISmartService{
    
	public PieceFetchInfoVO[] query(IQueryScheme queryScheme) throws BusinessException, Exception;
	
	public SmartDefVO querySmartDefByPK(String pk_def) throws BusinessException, Exception;
	
	public SmartDefVO[] querySmartDef() throws BusinessException, Exception;
	
	public abstract Boolean InsertFecthDataTable(String sql,String classname) throws BusinessException;
	
	public void DeleteExistsRecords(Class c,String condition)  throws BusinessException;
	
	public abstract IRowSet QueryFetchRecord(String code) throws BusinessException;
	
	public abstract boolean CheckPeriodCalcSF(String pk_org,String accountpeirod) throws BusinessException;
	
	public abstract ArrayList<String> QueryTableCodeRecord(Class c,String condtion) throws BusinessException;

	public abstract void BatchVOSave(PieceFetchRecordVO[] vos) throws BusinessException;
	
	public abstract SuperVO[] getDetailInfoVO(Class c,String condition) throws BusinessException;
}
