package com.myfinance.app.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCadastroRequest {

	@NotBlank(message = "Nome vazio")
	@NotNull(message = "Nome é nulo")
	private String nome;

	@NotBlank(message = "Email vazio")
	@NotNull(message = "Email é nulo")
	@Email
	private String email;

	@NotBlank(message = "Senha vazia")
	@NotNull(message = "Senha é nula")
	private String senha;

}
