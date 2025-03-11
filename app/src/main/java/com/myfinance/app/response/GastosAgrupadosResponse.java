package com.myfinance.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastosAgrupadosResponse {

	private String subcategoria;

	private Double valor;

}
