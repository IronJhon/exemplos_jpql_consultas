package com.br.consultas.jpql.main.enums;

public enum TipoCliente {

	FISICA("Fisica"), JURIDICA("Juridica");

	private String tipoCliente;

	TipoCliente(String tipoCliente) {

		this.tipoCliente = tipoCliente;
	}

	public String gettipoCliente() {
		return tipoCliente;
	}

	

	/*
	 * @JsonCreator public static TipoCliente fromValueString(String tipoCliente) {
	 * if (tipoCliente == null) { throw new IllegalArgumentException(); } for
	 * (TipoCliente tipoClienteSalved : values()) { if
	 * (tipoCliente.equals(tipoClienteSalved.gettipoCliente())) { return
	 * tipoClienteSalved; } } throw new IllegalArgumentException(); }
	 */
}
