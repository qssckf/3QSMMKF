package nc.vo.so.qs.sc.FQ01.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.keyvalue.AbstractKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

public class HeadTotalCalculateRule {
	private IKeyValue keyValue;

	public HeadTotalCalculateRule(IKeyValue keyValue) {
		this.keyValue = keyValue;
	}
  

	public void calculateHeadTotal() {
		
		UFDouble totalastnum = UFDouble.ZERO_DBL;
		UFDouble totalorigmny = UFDouble.ZERO_DBL;

		BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
		int[] rows = countutil.getMarNotNullRows();
   
		for (int row : rows) {
			if (AbstractKeyValue.RowStatus.DELETED != this.keyValue.getRowStatus(row)) {
				
				UFDouble astnum = this.keyValue.getBodyUFDoubleValue(row, "nastnum");
        
				totalastnum = MathTool.add(totalastnum, astnum);

				UFBoolean largessflag = this.keyValue.getBodyUFBooleanValue(row, "blargessflag");

				if ((null == largessflag) || (!largessflag.booleanValue())) {

					UFDouble origtaxmny = this.keyValue.getBodyUFDoubleValue(row, "norigtaxmny");
         
					totalorigmny = MathTool.add(totalorigmny, origtaxmny);
				}
			} 
		}
		this.keyValue.setHeadValue("ntotalnum", totalastnum);
		this.keyValue.setHeadValue("ntotalorigmny", totalorigmny);
	}
}