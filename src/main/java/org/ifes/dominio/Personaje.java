package org.ifes.dominio;

abstract class Personaje implements IPersonaje {

	private int hv;
	private IArma arma;
	private boolean magico;
	private boolean ultimo = false;
	private int golpes;
	private int limiteVida;
	
	public String getNombre() { return this.getClass().getSimpleName(); }
	public int getVida(){ return this.hv; }
	public void setVida(int nuevaVida){ this.hv=nuevaVida; }
	public IArma getArma(){ return this.arma; }
	public void setArma(IArma nuevaArma){ this.arma = nuevaArma; }
	public boolean ultimoGolpe() { return ultimo; }
	public void setUltimoGolpe(boolean last) { this.ultimo = last; }
	public boolean isMagia(){ return this.magico; }	
	public void cargarVida(int vida) 
	{ 
		int operacion = this.hv+vida;
		if((operacion) < 0)
							this.hv = 0;		
		else
		{
			if(!(operacion > limiteVida))
								this.hv = this.hv+vida;
		}
	}
	public int cantGolpe(){ return this.golpes; }
	public void setCantGolpe(int numGolpes){ this.golpes = numGolpes; }
	/**
	 * Mediante este metodo 
	 * El personaje ataca a un moustruo 
	 * @return devuelve el moustruo que ataco 
	 */
	public IMonstruo ataca(IMonstruo mous)
	{
		mous.cargarVida(this.getArma().getDanio());
		return mous;
	}
	public Personaje(int hv, IArma arma, boolean magico,
			int golpes) {
		super();
		this.hv = hv;
		this.arma = arma;
		this.magico = magico;
		this.golpes = golpes;
		this.limiteVida = hv;
	}
	
}
