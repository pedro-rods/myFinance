package com.myfinance.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum EnumTipoCategoria {

	NECESSARIO("NECESSARIO"), FUTIL("FUTIL");

	@Getter
	@Setter
	private String id;
}
