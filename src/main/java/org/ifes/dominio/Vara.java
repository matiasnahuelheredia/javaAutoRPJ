package org.ifes.dominio;


public class Vara implements IArma {
	
	private int danio;
	
	Vara(){}
	
	Vara(int danio)
	{
		this.danio = danio;
	}

	public String getNombre(){ return "Vara"; }
	public void setDanio(int nuevoDanio){ this.danio = nuevoDanio; }
	public int getDanio(){ return this.danio; }
	
}
