package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
@SuppressWarnings("serial")
public class Usuario implements Serializable {

	@Id
	@Column
	@SequenceGenerator(allocationSize = 1, name = "seq_usuario", sequenceName = "seq_usuario")
	private Long id;

	@Column(length = 100)
	String nome;

	@Column(length = 100)
	private String email;

	@Column
	private String senha;

}
