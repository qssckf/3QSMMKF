package nc.ui.so.qs.intoprod.input.handevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;

public class HeadAfterEditHander implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	private ShowUpableBillForm billfrom;

	public ShowUpableBillForm getBillfrom() {
		return billfrom;
	}

	public void setBillfrom(ShowUpableBillForm billfrom) {
		this.billfrom = billfrom;
	}
	
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO 自动生成的方法存根
		if("prodnum".equals(e.getKey()) ){
			
			String hsl=this.getBillfrom().getBillCardPanel().getHeadItem("rate").getValue();
			
			UFDouble num=(UFDouble) e.getValue();
			
			UFDouble nastnum= HslParseUtil.hslDivUFDouble(hsl, num);
			
			this.getBillfrom().getBillCardPanel().setHeadItem("prodnastnum", nastnum);
			
			
		}
		
		if("prodnastnum".equals(e.getKey())){
			
			String hsl=this.getBillfrom().getBillCardPanel().getHeadItem("rate").getValue();
			
			UFDouble nastnum=(UFDouble) e.getValue();
			
			UFDouble num= HslParseUtil.hslMultiplyUFDouble(hsl, nastnum);
			
			this.getBillfrom().getBillCardPanel().setHeadItem("prodnum", num);
			
		}
	}

}
