package nc.ui.so.qs.intoprod.input.action;

import java.awt.event.ActionEvent;

import nc.ui.uap.sf.SFClientUtil;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

import org.apache.axis.utils.StringUtils;

public class IntoProdCancelAction extends nc.ui.pubapp.uif2app.actions.CancelAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		
		Object value = this.getEditor().getValue();
		
		IntoProdDetailVO vo=(IntoProdDetailVO)value;
		
		if(StringUtils.isEmpty(vo.getPk_itpd())){
			SFClientUtil.closeFuncWindow("4006200205");
		}else{
			super.doAction(e);
		}
	}
	
	

}
