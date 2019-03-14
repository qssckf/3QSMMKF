package nc.ui.so.wa.piece.fetch.action;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.pub.smart.provider.impl.SqlProvider;
import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.fetch.IPieceFetchInfoVOMaintain;
import nc.pub.smart.provider.Provider;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.progress.DefaultProgressMonitor;
import nc.ui.so.wa.piece.fetch.ace.view.BusFetchDataMainPanel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.components.progress.TPAProgressUtil;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.smart.SmartDefVO;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;

public class FetchDataAction extends NCAction{

	private BusFetchDataMainPanel DataMainPnl;
	private IPieceFetchInfoVOMaintain fetchService;
	private Map<String,ArrayList<String>> TableRecords=new HashMap();
	private Map<String,SmartDefVO> SmartDef=new HashMap();
	
	public IPieceFetchInfoVOMaintain getFetchService(){
		if(this.fetchService==null){
			this.fetchService=NCLocator.getInstance().lookup(IPieceFetchInfoVOMaintain.class);
		}
		return this.fetchService;
	}

	public BusFetchDataMainPanel getDataMainPnl() {
		return DataMainPnl;
	}

	public void setDataMainPnl(BusFetchDataMainPanel dataMainPnl) {
		DataMainPnl = dataMainPnl;
	}

	public Map<String, ArrayList<String>> getTableRecords() {
		return TableRecords;
	}

	public void setTableRecords(Map<String, ArrayList<String>> tableRecords) {
		TableRecords = tableRecords;
	}

	public Map<String, SmartDefVO> getSmartDef() {
		return SmartDef;
	}

	public void setSmartDef(Map<String, SmartDefVO> smartDef) {
		SmartDef = smartDef;
	}

	public FetchDataAction(){
		
		this.setBtnName("ҵ��ȡ��");
		SmartDefVO[] smart;
		
		try{
			
			smart=this.getFetchService().querySmartDef();
			
			for(SmartDefVO vo:smart){
				this.SmartDef.put(vo.getPk_def(), vo);
			}
			
		}catch(Exception e){
			ExceptionUtils.wrapException(e);
		}
		
		
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		startThread();
	}

	private void startThread() {
		// TODO �Զ����ɵķ������
		
		new Thread(){

			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				
				TPAProgressUtil tpaProgressUtil = new TPAProgressUtil();
				
				tpaProgressUtil.setContext(FetchDataAction.this.getDataMainPnl().getModel().getContext());
				
				DefaultProgressMonitor progressMonitor =tpaProgressUtil.getTPAProgressMonitor();
				
				String name = NCLangRes.getInstance().getStrByID("5001003_0", "05001003-0104");
				progressMonitor.beginTask(name, -1);
				progressMonitor.setProcessInfo("ȡ����.....");
				 
				try{
					 
					FetchDataAction.this.fetchdata();
					 
				}catch(Exception ex){
					
					ExceptionUtils.wrapException(ex);
				}finally{
					progressMonitor.done();
				}
			}
			
			
			
		}.start();
		
		
	}

	protected void fetchdata() throws Exception {
		// TODO �Զ����ɵķ������
		
		PieceFetchRecordVO[] vos=this.getFetchdataSelectVO();
		
		PieceFetchRecordVO[] allVO=this.getFetchDataMainAllVO();
		
		if(MMValueCheck.isEmpty(this.getDataMainPnl().getWaPeriodRefPane().getRefPK())){
			
			throw new BusinessException("û��ָ������ڼ䣡");
			
		}
		
		if(MMValueCheck.isEmpty(vos)){
			throw new BusinessException("��ѡ����Ҫȡ���ļ�¼��");
		}
		
		if(!CreateFetchData(vos)){
			return;
		}else{
			
			setFetchedStatusTOallVO(vos, allVO);
			
			this.getDataMainPnl().refreshDates();
			
			MessageDialog.showErrorDlg(this.getDataMainPnl(), "�ɹ�", "ȡ���ɹ���");
			
		}
		
	}
	
	private void setFetchedStatusTOallVO(PieceFetchRecordVO[] vos,PieceFetchRecordVO[] allVOs){

		// TODO �Զ����ɵķ������
		 Set<String> keySet = new HashSet();
		 
		 List<PieceFetchRecordVO> savevo=new ArrayList<PieceFetchRecordVO>();
		 
		 for (PieceFetchRecordVO vo : vos) {
			 keySet.add(getKey(vo));
		 }
		 
		 for (PieceFetchRecordVO allVo : allVOs) {
			 if (keySet.contains(getKey(allVo))) {
				 if(allVo.getFetchsf().equals(UFBoolean.FALSE)){
					 allVo.setFetchsf(UFBoolean.TRUE);
					 savevo.add(allVo);
				 }
			
				 
			 }
		 }
		 
		 if (savevo.size()>0){
			 try {
				this.getFetchService().BatchVOSave(savevo.toArray(new PieceFetchRecordVO[savevo.size()]));
			} catch (BusinessException e) {
				// TODO �Զ����ɵ� catch ��
				ExceptionUtils.wrapException(e);
			}
		 }
	
	}
	
	private String getKey(PieceFetchRecordVO vo) {
		// TODO �Զ����ɵķ������
		StringBuffer strbuffer = new StringBuffer();
		if (MMValueCheck.isNotEmpty(vo.getCode())) {
			strbuffer.append(vo.getCode());
		}
		
		if (MMValueCheck.isNotEmpty(vo.getFetchtype())) {
			strbuffer.append(vo.getFetchtype());
		}
		
		if (MMValueCheck.isNotEmpty(vo.getFetchscheme())) {
			strbuffer.append(vo.getFetchscheme());
		}
		
		if (MMValueCheck.isNotEmpty(vo.getAccountperiod())) {
			strbuffer.append(vo.getAccountperiod());
		}
		
		return strbuffer.toString();
		
	}
	
	private boolean CreateFetchData(PieceFetchRecordVO[] vos) throws BusinessException{
		// TODO �Զ����ɵķ������
		
		String[] table;
		String[] sqltext;
		List<String> rulecode=new ArrayList<String>();
		String sql;
		String voName="";
		List<String> param=new ArrayList<String>();
		SmartDefVO smart=null;
		int addopera;
		Provider[] provider=null;
		Class clazz1=null,clazz2=null;
		
		try{
			
			if(!this.CheckOutsf(this.getDataMainPnl(), this.getDataMainPnl().getWaPeriodRefPane().getRefPK())){
				return false;
			}
			
			for(PieceFetchRecordVO vo:vos){
				
				clazz1=Class.forName(vo.getVoclass1());
				
				if(this.SmartDef.get(vo.getSmartdef1())==null){
					smart=this.getFetchService().querySmartDefByPK(vo.getSmartdef1());
					this.SmartDef.put(vo.getSmartdef1(), smart);
				}else{
					smart=this.SmartDef.get(vo.getSmartdef1());
				}
				
				addopera=0;
				
				//���sql����ж�Ӧ�Ĳ�������
				addopera=this.getAddOperatorNum(((SqlProvider)((smart.getSmartmodel().getProviders())[0])).getScript());
				
				//����ѡ���¼��ʼ����ѯ����
				param.clear();
				
				if(addopera>0){
					
					for(int i=0;i<addopera;i++){
						
						if(i==0){
							param.add(vo.getCode());
						}
						
						if(i!=0&&i%2==0){
							param.add(vo.getEnddate().toString().substring(0,10));
						}
						
						if(i!=0&&i%2!=0){
							param.add(vo.getStartdate().toString().substring(0,10));
						}
						
					}
				}
				
				
				//����vo���б��ڼ������ݿ����Ƿ���ڣ����������ɾ����������
				rulecode=this.getTableMapRecord(clazz1, this.CreateTableCodeQueryCondition(vo.getAccountperiod())).get(clazz1.toString());
				
				if (rulecode.contains(vo.getCode())||vo.getFetchsf().booleanValue()){
					this.getFetchService().DeleteExistsRecords(clazz1, this.CreateCodeDeleteCondition(vo.getAccountperiod(), vo.getCode()));
				}
				
				sql=((SqlProvider)((smart.getSmartmodel().getProviders())[0])).getScript();
				
				if (addopera>0){
					sql=this.CreateParaSQL(sql, param.toArray(new String[param.size()]), addopera);
				}
				
				if(!this.getFetchService().InsertFecthDataTable(sql, vo.getVoclass1())){
					throw new BusinessException(vo.getCode()+"û�в�ѯ���κ�ҵ�����ݣ��޷����ȡ�����������飡");
				}
				
				//2.���Ƚ���vo��¼����ϸ��ȡ������
				
	
				clazz2=Class.forName(vo.getVoclass2());
	
				if (this.SmartDef.get(vo.getSmartdef2())==null){
					smart=this.getFetchService().querySmartDefByPK(vo.getSmartdef2());
					this.SmartDef.put(vo.getSmartdef2(), smart);
				}else{
					smart=this.SmartDef.get(vo.getSmartdef2());
				}
				
				addopera=0;
				//���sql����ж�Ӧ�Ĳ�������
				addopera=this.getAddOperatorNum(((SqlProvider)((smart.getSmartmodel().getProviders())[0])).getScript());
				
				//����ѡ���¼��ʼ����ѯ����
				param.clear();
				
				if (addopera>0){
					//���ݲ���������ʼ����ѯ�����ṹ
					for(int i=0;i<addopera;i++){
						if (i==0){
							param.add(vo.getCode());
						}
						
						if(i!=0&&i%2==0){
							param.add(vo.getEnddate().toString().substring(0,10));
						}
						
						if(i!=0&&i%2!=0){
							param.add(vo.getStartdate().toString().substring(0,10));
						}
					}
				}
				
				//����vo���б��ڼ������ݿ����Ƿ���ڣ����������ɾ����������
				rulecode=this.getTableMapRecord(clazz2, this.CreateTableCodeQueryCondition(vo.getAccountperiod())).get(clazz2.toString());
				if (rulecode.contains(vo.getCode())||vo.getFetchsf().booleanValue()){
					this.getFetchService().DeleteExistsRecords(clazz2, this.CreateCodeDeleteCondition(vo.getAccountperiod(), vo.getCode()));
				}
				
				sql=((SqlProvider)((smart.getSmartmodel().getProviders())[0])).getScript();
				
				if (addopera>0){
					sql=this.CreateParaSQL(sql, param.toArray(new String[param.size()]), addopera);
				}
					
				
				if(!this.getFetchService().InsertFecthDataTable(sql, vo.getVoclass2())){
						throw new BusinessException(vo.getCode()+"û�в�ѯ���κ�ҵ�����ݣ��޷����ȡ�����������飡");
				}
				
			}
			
		}catch(Exception e){
			
			throw new BusinessException(e);
			
		}
		
		
		return true;
	}
	
	private String CreateParaSQL(String sql,String[] param,int AddOperaNum){
		String resultSQL;
		int pos=0;
		
		for(int i=0;i<AddOperaNum;i++){
			pos=sql.indexOf("?");
			sql=sql.substring(0, pos)+param[i]+sql.substring(pos+1,sql.length());
		}
		
		return sql;
		
	}
	
	private String CreateCodeDeleteCondition(String accountpeorid,String code) {
		// TODO �Զ����ɵķ������
		return " period='"+accountpeorid+"' and code='"+code+"'";
	}
	
	private String CreateTableCodeQueryCondition(String accountpeorid) {
		// TODO �Զ����ɵķ������
		return " and period='"+accountpeorid+"'";
	}
	
	private Map<String, ArrayList<String>> getTableMapRecord(Class c,String condition) throws BusinessException {
		// TODO �Զ����ɵķ������
		if(this.TableRecords.get(c.toString())==null){
			this.TableRecords.put(c.toString(), this.getFetchService().QueryTableCodeRecord(c,condition));
		}
		return this.TableRecords;
	}
	
	private int getAddOperatorNum(String sql){
		
		int AddOperator=0;
		int startL = sql.indexOf("?");
		
        if (startL != -1) {
            sql = sql.substring(startL + 1, sql.length());
        }
        
        while (startL != -1) {
        	AddOperator++;
            startL = sql.indexOf("?");
            sql = sql.substring(startL + 1, sql.length());
        }
        
        return AddOperator;
		
	}
	
	private Boolean CheckOutsf(BusFetchDataMainPanel MainPnl, String accountpeirod) throws BusinessException{
		
		String pk_org=MainPnl.getRefFactory().getRefPK();
		accountpeirod=accountpeirod.substring(0,4)+accountpeirod.substring(4);
		
		if(!this.getFetchService().CheckPeriodCalcSF(pk_org, accountpeirod)){
			
			throw new BusinessException("���ڼ�"+accountpeirod+"�����Ѿ����������,����ȡ����");
		}
		
		return true;
	}

	private PieceFetchRecordVO[] getFetchDataMainAllVO() {
		
		return (PieceFetchRecordVO[]) this.getDataMainPnl().getBillListView().getBillListPanel().getBodyBillModel().getBodyValueVOs(PieceFetchRecordVO.class.getName());
		
	}
	
	
	private PieceFetchRecordVO[] getFetchdataSelectVO(){
		
		return (PieceFetchRecordVO[]) this.getDataMainPnl().getBillListView().getBillListPanel().getBodyBillModel().getBodySelectedVOs(PieceFetchRecordVO.class.getName());
		
	}
	
	
	
}
