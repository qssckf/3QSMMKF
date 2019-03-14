package nc.ui.so.wa.piece.fetch.uidataset;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.util.mmf.framework.base.MMCollectionUtil;
import nc.util.mmf.framework.db.MMSqlBuilder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.wa.piece.fetch.PieceFetchInfoVO;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;
import nc.vo.so.wa.piece.fetch.UIAllDataVO;

public class PieceBusFetchService {
	
	public UIAllDataVO getVosUiDataSet(PieceFetchRecordVO param) throws BusinessException,SQLException{
		
		UIAllDataVO allVO=new UIAllDataVO();
		
		allVO.setDaccountperiod(param.getAccountperiod());
		
		List<PieceFetchRecordVO> result=(List<PieceFetchRecordVO>) this.getAlreadyExistFetchInfos(param);
		
		if(MMCollectionUtil.isNotEmpty(result)){
			CheckFetchInfosDiff(result, param);
			allVO.setVos(result.toArray(new PieceFetchRecordVO[0]));
		}
		
		return null;
		
	}
	
	private void CheckFetchInfosDiff(List<PieceFetchRecordVO> result,PieceFetchRecordVO param) throws SQLException{
		
		String startdate,enddate;
		
		Calendar cal=Calendar.getInstance();
		List<PieceFetchInfoVO> fetchruleVO=(List<PieceFetchInfoVO>) this.getFetchRules(param);
		
		if(result.size()==fetchruleVO.size()){
			return;
		}else if(fetchruleVO.size()>result.size()){
			
			Set<String> keySet = new HashSet();
			
			for(PieceFetchRecordVO vo:result){
				keySet.add(vo.getCode());
			}
			
			for(PieceFetchInfoVO vo:fetchruleVO){
				
				if(!keySet.contains(vo.getCode())){
					
					PieceFetchRecordVO pfvo=new PieceFetchRecordVO();
					
					pfvo.setAccountperiod(param.getAccountperiod());
					pfvo.setPk_group(vo.getPk_group());
					pfvo.setPk_org(vo.getPk_org());
					pfvo.setCode(vo.getCode());
					pfvo.setName(vo.getName());
					pfvo.setFetchscheme(vo.getFetchscheme());
					pfvo.setFetchsf(new UFBoolean(false));
					pfvo.setSmartdef1(vo.getSmartdef1());
					pfvo.setSmartdef2(vo.getSmartdef2());
					pfvo.setVoclass1(vo.getVoclass1());
					pfvo.setVoclass2(vo.getVoclass2());
					pfvo.setFetchtype(vo.getFetchtype());
					if(vo.getFetchscheme()==1){
						
						startdate=param.getAccountperiod()+"-01";
						pfvo.setStartdate(new UFDate(startdate));
						
						cal.clear();
						cal.set(cal.YEAR, Integer.valueOf(param.getAccountperiod().substring(0,4)));
						cal.set(cal.MONTH,  Integer.valueOf(param.getAccountperiod().substring(5,7)));
						cal.set(cal.DATE,1);
						cal.add(cal.DATE, -1);
						enddate=new UFDate(cal.getTime()).toString().substring(0, 10);	
						pfvo.setEnddate(new UFDate(enddate));
					}else if(vo.getFetchscheme()==2){
						
						if(vo.getStartperiod()==2){
							cal.clear();
							cal.set(cal.YEAR, Integer.valueOf(param.getAccountperiod().substring(0,4)));
							cal.set(cal.MONTH,  Integer.valueOf(param.getAccountperiod().toString().substring(5, 7)));
							cal.set(cal.DATE,  Integer.valueOf(vo.getStartday()));
							pfvo.setStartdate(new UFDate(cal.getTime()));
						}else{
							cal.clear();
							cal.set(cal.YEAR, Integer.valueOf(param.getAccountperiod().substring(0,4)));
							cal.set(cal.MONTH,  Integer.valueOf(param.getAccountperiod().toString().substring(5, 7))-1);
							cal.set(cal.DATE,  Integer.valueOf(vo.getStartday()));
							pfvo.setStartdate(new UFDate(cal.getTime()));
						}
						
						
						if(vo.getEndperiod()==2){
							if(vo.getEndday()==31){
								cal.clear();
								cal.set(cal.YEAR, Integer.valueOf(param.getAccountperiod().substring(0,4)));
								cal.set(cal.MONTH,  Integer.valueOf(param.getAccountperiod().substring(5,7)));
								cal.set(cal.DATE,1);
								cal.add(cal.DATE, -1);
								enddate=new UFDate(cal.getTime()).toString().substring(0, 10);	
								pfvo.setEnddate(new UFDate(enddate));
							}else{
								cal.clear();
								cal.set(cal.YEAR, Integer.valueOf(param.getAccountperiod().substring(0,4)));
								cal.set(cal.MONTH,  Integer.valueOf(param.getAccountperiod().toString().substring(5, 7)));
								cal.set(cal.DATE,  Integer.valueOf(vo.getEndday()));
								pfvo.setEnddate(new UFDate(cal.getTime()));	
							}
						}else{
							
							if(vo.getEndday()==31){
								cal.clear();
								cal.set(cal.YEAR, Integer.valueOf(param.getAccountperiod().substring(0,4)));
								cal.set(cal.MONTH,  Integer.valueOf(param.getAccountperiod().substring(5,7))+1);
								cal.set(cal.DATE,1);
								cal.add(cal.DATE, -1);
								enddate=new UFDate(cal.getTime()).toString().substring(0, 10);	
								pfvo.setEnddate(new UFDate(enddate));
							}else{
								cal.clear();
								cal.set(cal.YEAR, Integer.valueOf(param.getAccountperiod().substring(0,4)));
								cal.set(cal.MONTH,  Integer.valueOf(param.getAccountperiod().toString().substring(5, 7))+1);
								cal.set(cal.DATE,  Integer.valueOf(vo.getEndday()));
								pfvo.setEnddate(new UFDate(cal.getTime()));	
							}
							
							
						}
						
						
						
					}
					result.add(pfvo);
				}
				
				
			}
			
		}
		
	}
	
	private Collection<PieceFetchRecordVO> getAlreadyExistFetchInfos(PieceFetchRecordVO param){
		
		String sql=getComonSql(param);
		Collection<PieceFetchRecordVO> fetchVos=null;
		
		try{
			
			fetchVos=MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(PieceFetchRecordVO.class, sql, false);
			
		}catch(MetaDataException e){
			ExceptionUtils.wrappException(e);
		}
		
		return fetchVos;
	}

	private String getComonSql(PieceFetchRecordVO param) {
		// TODO 自动生成的方法存根
		
		MMSqlBuilder sb=new MMSqlBuilder();
		
		sb.append("pk_group",param.getPk_group());
		sb.append(" and ");
		sb.append("pk_org",param.getPk_org());
		sb.append("and ");
		sb.append("fetchtype",param.getFetchtype());
		sb.append(" and ");
		sb.append("accountperiodid", param.getAccountperiod());
		sb.append(" and ");
		sb.append("dr=0");
		
		return sb.toString();
	}
	
	private Collection<PieceFetchInfoVO> getFetchRules(PieceFetchRecordVO param){
		
		String sql=getComonSql1(param);
		
		Collection<PieceFetchInfoVO> fetchruleVos = null;
		
		try{
			
			fetchruleVos=MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(PieceFetchInfoVO.class, sql, false);
			
		}catch(MetaDataException e){
			
			ExceptionUtils.wrappException(e);
			
		}
		 
		 return fetchruleVos;
	}
	
	private String getComonSql1(PieceFetchRecordVO param){
		
		MMSqlBuilder sb=new MMSqlBuilder();
		
		sb.append("pk_group",param.getPk_group());
		sb.append(" and ");
		sb.append("pk_org",param.getPk_org());
		sb.append(" and ");
		sb.append("fetchtype", param.getFetchtype());
		sb.append(" and ");
		sb.append(" dr = 0 ");
		
		return sb.toString();
	}

}
