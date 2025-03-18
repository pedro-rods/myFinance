package com.myfinance.app.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message = "Email vazio")
	@NotNull(message = "Email é nulo")
	private String email;

	@NotBlank(message = "Senha vazia")
	@NotNull(message = "Senha é nula")
	private String senha;

}
