package com.myfinance.app.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterarSenhaRequest {

	@NotBlank(message = "Email vazio")
	@NotNull(message = "Email é nulo")
	private String email;

	@NotBlank(message = "Senha antiga vazia")
	@NotNull(message = "Senha antiga é nula")
	private String senhaAntiga;

	@NotBlank(message = "Senha nova vazia")
	@NotNull(message = "Senha nova é nula")
	private String senhaNova;
}
