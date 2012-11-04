package org.ifes.dominio;


public class Cuchillo implements IArma {
	
	private int danio;
	
	Cuchillo(){}
	
	Cuchillo(int danio)
	{
		this.danio = danio;
	}

	public String getNombre(){ return "Cuchillo"; }
	public void setDanio(int nuevoDanio){ this.danio = nuevoDanio; }
	public int getDanio(){ return this.danio; }
	
}
