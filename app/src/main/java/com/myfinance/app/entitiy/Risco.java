package com.myfinance.app.entitiy;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myfinance.app.enums.EnumTipoCategoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "riscos")
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public class Risco implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_plano_financeiro")
	@JsonBackReference
	private PlanoFinanceiro planoFinanceiro;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumTipoCategoria categoria;

	@Column
	private String subcategoria;

	@Column
	private Double valor;

}
