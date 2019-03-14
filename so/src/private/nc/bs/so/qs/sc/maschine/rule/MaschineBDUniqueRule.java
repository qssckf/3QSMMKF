package nc.bs.so.qs.sc.maschine.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.util.mmf.framework.base.MMArrayUtil;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;
import nc.bs.bd.bp.rule.BDUniqueRule;

public class MaschineBDUniqueRule implements IRule<AggMaschineVO> {
	@Override
	public void process(AggMaschineVO[] vos) {
		// TODO 自动生成的方法存根
		if (MMArrayUtil.isEmpty(vos)) {
			return;
		}
		
		BDUniqueRule hbd=new BDUniqueRule();
		
		List hlist = new ArrayList();
		
		for (AggMaschineVO vo : vos){
			MaschineVO hvo=(MaschineVO)vo.getParentVO();
			hlist.add(hvo);
		}
		hbd.process(hlist.toArray(new MaschineVO[hlist.size()]));
	}
}
