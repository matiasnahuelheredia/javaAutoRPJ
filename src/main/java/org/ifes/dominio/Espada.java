package org.ifes.dominio;


public class Espada implements IArma {
	
	private int danio;
	
	Espada(){}
	
	Espada(int danio)
	{
		this.danio = danio;
	}

	public String getNombre(){ return "Espada"; }
	public void setDanio(int nuevoDanio){ this.danio = nuevoDanio; }
	public int getDanio(){ return this.danio; }
	
}
