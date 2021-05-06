package it.polito.tdp.extflightdelays.model;

public class MediaVoli {

	Integer id_arrivo;
	Integer id_partenza;
	Double media;
	
	public MediaVoli(Integer id_arrivo, Integer id_partenza, Double media) {
		super();
		this.id_arrivo = id_arrivo;
		this.id_partenza = id_partenza;
		this.media = media;
	}

	public Integer getId_arrivo() {
		return id_arrivo;
	}

	public Integer getId_partenza() {
		return id_partenza;
	}

	public Double getMedia() {
		return media;
	}
}
