package nc.ui.so.qs.sc.maschine.actions.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.maschine.service.IMaschineMaintain;
import nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.EditAction;
//import nc.vo.dm.en.TranLineStatus;
//import nc.vo.so.xlx.tran.TranLinePriceVO;
import nc.vo.so.qs.en.MachStatus;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;

public class MaschineCardShowUpComponentInterceptor extends ShowUpComponentInterceptor {
	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		// TODO 自动生成的方法存根
	
		Integer status=null;
	
		if(action instanceof EditAction){
			
			Object vo=((EditAction)action).getModel().getSelectedData();
			
			
			if(vo instanceof AggMaschineVO){
				MaschineVO ss=(MaschineVO)((AggMaschineVO) vo).getParent();
//				status=((MaschineVO)vo).getMstatus();
				status=ss.getMstatus();
			}			
		}
		
		if(status==null){
			return super.beforeDoAction(action, e);
		}else{
			if(status.toString().equals(MachStatus.Disable.value().toString())){
				ShowStatusBarMsgUtil.showStatusBarMsg("已停用数据不能修改！",((EditAction)action).getModel().getContext());
				return false;
			}
		}
		return true;
	}		
}
