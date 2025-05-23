package com.myfinance.app.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

	@NotBlank(message = "Nome vazio")
	@NotNull(message = "Nome é nulo")
	private String nome;

	@NotNull(message = "renda não pode ser nulo")
	private Double renda;

}
