package nc.ui.so.qs.mmplanmmdetail.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import nc.itf.so.qs.sc.base.IFilterRule;
import nc.so.qs.sc.bs.bd.service.ErrLogElement;
import nc.so.qs.sc.bs.bd.service.ValueObjWithErrLog;

import nc.ui.bd.pub.action.DeleteWithProgressBarAction;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public class BDDeleteAction extends DeleteWithProgressBarAction{
	
	private List<IFilterRule> list=new ArrayList();
	private ValueObjWithErrLog delObj;
	
	public List<IFilterRule> getList() {
		return list;
	}

	public void setList(List<IFilterRule> list) {
		this.list = list;
	}
	
	public ValueObjWithErrLog getDelObj() {
		return delObj;
	}

	public void setDelObj(ValueObjWithErrLog delObj) {
		this.delObj = delObj;
	}

	public void addVaildateRule(IFilterRule rule){
		list.add(rule);
	}

	

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		
		validate();
		
		super.doAction(e);
		
	}

	protected void validate() throws BusinessException {
		// TODO 自动生成的方法存根
		
		List<SuperVO> svo=new ArrayList();
		ArrayList<ErrLogElement> errList = new ArrayList();
		
		for(Object vo:this.getSelectedOperaDatas()){
			
			if(vo instanceof SuperVO){
				svo.add((SuperVO)vo);
			}
			
		}
		
		ValueObjWithErrLog delerrObj=new ValueObjWithErrLog((SuperVO[])svo.toArray(new SuperVO[0]),errList);
		
		Iterator<IFilterRule> iterator = this.getList().iterator();
		
		ValueObjWithErrLog item=delerrObj;
		
		while(iterator.hasNext()){
			
			Object obj = iterator.next();
			
			if(obj instanceof IFilterRule){
				item = ((IFilterRule)obj).process(item);
			}
			
			showErrorLog(item.getErrLogList());
		}
		
		
		
		
	}
	
	protected void showErrorLog(ArrayList<ErrLogElement> errLogList) throws BusinessException {
		// TODO 自动生成的方法存根
		if(!CollectionUtils.isEmpty(errLogList)){
			
			StringBuilder errMsg = new StringBuilder("数据删除效验失败!"+"\n");
			
			for (int i = 0; i < errLogList.size(); i++) {
				ErrLogElement element = (ErrLogElement)errLogList.get(i);
				errMsg.append("错误信息：").append(element.getErrReason()).append("\n");
			}
			
			throw new BusinessException(errMsg.toString());
		}
	}

	protected Object[] getSelectedOperaDatas() {
		
		if ((getModel() instanceof BillManageModel))
			
			return ((BillManageModel)getModel()).getSelectedOperaDatas();
		
		if ((getModel() instanceof HierachicalDataAppModel)) {
			
			return ((HierachicalDataAppModel)getModel()).getSelectedDatas();
		
		}
		
		return new Object[] { getModel().getSelectedData() };
	
	}
	
	protected void directlyDel(SuperVO[] vos) throws Exception{
		
		if ((getModel() instanceof BillManageModel)){
			((BillManageModel)getModel()).directlyDelete(vos);
		}
		
	}

}
