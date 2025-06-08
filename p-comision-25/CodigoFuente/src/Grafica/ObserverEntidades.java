package Grafica;

import Juego.EntidadLogica;

public class ObserverEntidades extends ObserverGrafico {

	private static final long serialVersionUID = 1L;
	private EntidadLogica entidadObservada;

	public ObserverEntidades(EntidadLogica entidadObservada) {
		super(entidadObservada);
		this.entidadObservada = entidadObservada;
		actualizar();
	}
	
	public void actualizar() {
		super.actualizar();
	}
}
