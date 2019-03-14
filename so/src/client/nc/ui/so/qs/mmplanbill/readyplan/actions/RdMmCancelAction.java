package nc.ui.so.qs.mmplanbill.readyplan.actions;

import java.awt.event.ActionEvent;

import org.apache.axis.utils.StringUtils;

import nc.ui.uap.sf.SFClientUtil;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdMmCancelAction extends nc.ui.pubapp.uif2app.actions.CancelAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		
		Object value = this.getEditor().getValue();
				
		RdPorductDetailVO vo=(RdPorductDetailVO)value;
		
		if(StringUtils.isEmpty(vo.getPk_rdpd())){
			SFClientUtil.closeFuncWindow("4006200203");
		}else{
			super.doAction(e);
		}
			
		

		
		
		
	}
	
	
	

}
