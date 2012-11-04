package org.ifes.dominio;

public class Velocidad implements IHechizo {
	
	private int impacto; //daño que hacía
	private int golpe;
	private boolean expansible=false;
	
	public Velocidad(){
	}
	public Velocidad(int impacto, int golpe, boolean expansible){
		this.impacto=impacto;
		this.golpe=golpe;
		this.expansible = expansible;
	}
	
	public String getNombre(){ return "Velocidad"; }
	
	public int getImpacto() {
		return this.impacto;
	}
	public void setImpacto(int impacto) {
		this.impacto=impacto;
	}
	public int getGolpe() {
		
		return this.golpe;
	}
	public void setGolpe(int golpe) {
		this.golpe=golpe;
	}

	public boolean getExpansible() {
		return this.expansible;
	}

	public void setExpansible(boolean expansible) {
		this.expansible=expansible;	
	}	
	
}
