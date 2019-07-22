package com.br.consultas.jpql.main.enums;

public enum Idioma {

	ESPANHOL("Espanhol"), INGLES("Ingles"), ARABE("Arabe"), PORTUGUES("Portugues"),
	BENGALI("Bengali"), RUSSO("Russo"), JAPONES("Japones"), PUNJABI("Punjabi");

	private String idioma;

	Idioma(String idioma) {

		this.idioma = idioma;
	}

	public String getIdioma() {
		return idioma;
	}

	

	

}
