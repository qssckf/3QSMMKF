package nc.bs.so.wa.piece.pf.bp;

import nc.bs.bd.bp.rule.BDPKLockSuperVORule;
import nc.bs.bd.bp.rule.VersionValidateRule;
import nc.bs.bd.bp.rule.update.UpdateAuditInfoRule;
import nc.bs.so.fq.plugin.PieceProductPlugInPoint;
import nc.bs.so.fq.plugin.ServicePlugInPoint;
import nc.bs.so.qs.sc.maschine.bp.rule.BillCheckVOConsistence;
import nc.bs.so.wa.piece.pf.ace.rule.BillSetDefaultValueRule;
import nc.bs.so.wa.piece.pf.ace.rule.BillVOSetValue;
import nc.bs.so.wa.piece.pf.ace.rule.PDBusLockRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.fq.so.m30.rule.RewriteSetArrInfoRule;
import nc.vo.so.wa.piece.PieceProductVO;

public class PieceProductSetDefaultBP {
	
	public PieceProductSetDefaultBP(){
		
	}
	
	public PieceProductVO SetDefault(PieceProductVO vo){
		
		 PieceProductVO[] vos;
		
		 AroundProcesser<PieceProductVO> processer = new AroundProcesser(PieceProductPlugInPoint.SetDefault);
		 
		 addRule(processer);
		 
		 vos=processer.before(new PieceProductVO[]{vo});
		 
		 vos=processer.after(vos);
		 
		 return vos[0];
		
	}
	
	private void addRule(AroundProcesser<PieceProductVO> processer){
		
		processer.addBeforeRule(new PDBusLockRule());
		
		processer.addBeforeRule(new BDPKLockSuperVORule());
		
		processer.addBeforeRule(new VersionValidateRule());
		
		processer.addBeforeRule(new UpdateAuditInfoRule());
		
		IRule<PieceProductVO> rule = new BillSetDefaultValueRule();
		processer.addBeforeRule(rule);
		
		BillVOSetValue bs=new BillVOSetValue();
		
		bs.setHeadField(new String[]{"isdefault"});
		
		bs.setHeadValue(new String[]{"Y"});

		processer.addAfterRule(bs);
		
	}

}
