package com.myfinance.app.entitiy;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuario")
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(allocationSize = 1, name = "seq_usuario", sequenceName = "seq_usuario")
	private Long id;

	@Column(length = 100)
	String nome;

	@Column(length = 100)
	private String email;

	@Column
	private String senha;

	@Column
	private Double renda;

}