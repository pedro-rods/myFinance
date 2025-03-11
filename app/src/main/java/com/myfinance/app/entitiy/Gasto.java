package com.myfinance.app.entitiy;

import java.io.Serializable;
import java.util.Date;

import com.myfinance.app.enums.EnumTipoCategoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "gastos")
@SuppressWarnings("serial")
public class Gasto implements Serializable {
	@Id
	@Column
	@SequenceGenerator(allocationSize = 1, name = "seq_gastos", sequenceName = "seq_gastos")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumTipoCategoria categoria;

	@Column
	private String subcategoria;

	@Column
	private Double valor;

	@Column
	private Date dataHora;
}
