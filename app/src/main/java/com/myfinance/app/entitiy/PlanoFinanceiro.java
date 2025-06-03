package com.myfinance.app.entitiy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "plano")
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public class PlanoFinanceiro implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "planoFinanceiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Ajuste> ajustes;

	@OneToMany(mappedBy = "planoFinanceiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Risco> riscos;

	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy ", timezone = "America/Sao_Paulo")
	private Date dataAlteracao;

	public Double getValorTotalAjustes() {
		if (ajustes == null) {
			return 0.0;
		}

		return ajustes.stream().filter(ajuste -> ajuste.getValor() != null).mapToDouble(Ajuste::getValor).sum();
	}
}
