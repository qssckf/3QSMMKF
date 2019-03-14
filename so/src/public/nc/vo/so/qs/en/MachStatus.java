package nc.vo.so.qs.en;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class MachStatus extends MDEnum{
	public MachStatus(IEnumValue enumvalue){
		super(enumvalue);
	}

	public static final MachStatus UnEnable = MDEnum.valueOf(MachStatus.class, String.valueOf("0"));
	
	
	public static final MachStatus Enable= MDEnum.valueOf(MachStatus.class, String.valueOf("1"));
	
	
	public static final MachStatus Disable = MDEnum.valueOf(MachStatus.class, String.valueOf("2"));
	

}