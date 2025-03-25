package com.myfinance.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum EnumTipoCategoria {

	NECESSIDADE("NECESSIDADE"), INVESTIMENTOS_POUPANCA("INVESTIMENTOS_POUPANCA"), DESEJOS("DESEJOS");

	@Getter
	@Setter
	private String id;
}
