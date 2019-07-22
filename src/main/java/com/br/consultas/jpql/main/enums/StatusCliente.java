package com.br.consultas.jpql.main.enums;

public enum StatusCliente {

	INADIMPLENTE("Inadiplente"), ATIVO("Ativo"), INATIVO("Inativo");

	private String statusCliente;

	StatusCliente(String statusCliente) {

		this.statusCliente = statusCliente;
	}

	public String getstatusCliente() {
		return statusCliente;
	}

	public void setstatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}

	/*
	 * @JsonCreator public static StatusCliente fromValueString(String
	 * statusCliente) { if (statusCliente == null) { throw new
	 * IllegalArgumentException(); } for (StatusCliente statusClienteSalved :
	 * values()) { if (statusCliente.equals(statusClienteSalved.getstatusCliente()))
	 * { return statusClienteSalved; } } throw new IllegalArgumentException(); }
	 */

}
