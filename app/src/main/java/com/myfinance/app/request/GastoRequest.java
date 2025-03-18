package com.myfinance.app.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myfinance.app.enums.EnumTipoCategoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoRequest {

	@NotNull(message = "IdUsuario vazio")
	private Long idUsuario;

	@NotNull(message = "Valor é nulo")
	private Double valor;

	@NotBlank(message = "subcategoria vazia")
	@NotNull(message = "subcategoria é nula")
	private String subcategoria;

	@NotNull(message = "categoria é nula")
	private EnumTipoCategoria categoria;

	@NotNull(message = "dataHora é nula")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataHora;

}
