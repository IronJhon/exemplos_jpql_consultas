package com.br.consultas.jpql.main.enums;

public enum Genero {

	MASCULINO("MASCULINO"), FEMININO("FEMININO");

	private String genero;

	Genero(String genero) {

		this.genero = genero;
	}

	public String getGenero() {
		return genero;
	}

	

	/*
	 * @JsonCreator public static Genero fromValueString(String genero) { if (genero
	 * == null) { throw new IllegalArgumentException("Genero Informado Incorreto");
	 * } for (Genero generoSalved : values()) { if
	 * (genero.equals(generoSalved.getGenero())) { return generoSalved; } } throw
	 * new IllegalArgumentException(); }
	 */

}
