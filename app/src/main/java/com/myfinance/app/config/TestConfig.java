package com.myfinance.app.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.myfinance.app.entitiy.Ajuste;
import com.myfinance.app.entitiy.Gasto;
import com.myfinance.app.entitiy.PlanoFinanceiro;
import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.enums.EnumTipoCategoria;
import com.myfinance.app.repository.AjusteRepository;
import com.myfinance.app.repository.GastoRepository;
import com.myfinance.app.repository.PlanoRepository;
import com.myfinance.app.repository.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GastoRepository gastoRepository;

	@Autowired
	private PlanoRepository planoRepository;

	@Autowired
	private AjusteRepository ajusteRepository;

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario(null, "Teste", "teste@email.com", "123", 5000.0);
		usuario = usuarioRepository.save(usuario);

		Gasto gasto = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "restaurante", 678.98, new Date());
		Gasto gasto1 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "conta de luz", 618.98, new Date());
		Gasto gasto13 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "conta de agua", 618.98, new Date());
		Gasto gasto14 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "internet", 788.98, new Date());
		Gasto gasto17 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "veiculo", 427.23, new Date());
		Gasto gasto18 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "condominio", 128.94, new Date());
		Gasto gasto11 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "academia", 488.98, new Date());
		Gasto gasto5 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "faculdade", 695.98, new Date());
		Gasto gasto7 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "aluguel", 1077.98, new Date());
		Gasto gasto8 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "transporte por aplicativo", 678.98,
				new Date());
		Gasto gasto9 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "transporte por aplicativo", 678.98,
				new Date());
		Gasto gasto21 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "roupa", 568.98, new Date());
		Gasto gasto22 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "restaurante", 390.0, new Date());
		Gasto gasto23 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "cinema", 150.32, new Date());
		Gasto gasto24 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "assinaturas", 70.65, new Date());
		Gasto gasto12 = new Gasto(null, usuario, EnumTipoCategoria.INVESTIMENTOS_POUPANCA, "investimento", 900.98,
				new Date());
		gastoRepository.save(gasto);
		gastoRepository.save(gasto1);
		gastoRepository.save(gasto5);
		gastoRepository.save(gasto7);
		gastoRepository.save(gasto8);
		gastoRepository.save(gasto9);
		gastoRepository.save(gasto11);
		gastoRepository.save(gasto12);
		gastoRepository.save(gasto13);
		gastoRepository.save(gasto14);
		gastoRepository.save(gasto17);
		gastoRepository.save(gasto18);
		gastoRepository.save(gasto21);
		gastoRepository.save(gasto22);
		gastoRepository.save(gasto23);
		gastoRepository.save(gasto24);

		PlanoFinanceiro plano = new PlanoFinanceiro(null, usuario, null, new Date());

		plano = planoRepository.save(plano);

		Ajuste ajuste = new Ajuste(null, plano, EnumTipoCategoria.DESEJOS, "cigarro", -1000.98);
		Ajuste ajuste1 = new Ajuste(null, plano, EnumTipoCategoria.DESEJOS, "transporte por aplicativo", -1000.98);
		Ajuste ajuste2 = new Ajuste(null, plano, EnumTipoCategoria.INVESTIMENTOS_POUPANCA, "investimento", 2001.56);
		Ajuste ajuste3 = new Ajuste(null, plano, EnumTipoCategoria.DESEJOS, "restaurante", -1000.98);

		ajusteRepository.save(ajuste);
		ajusteRepository.save(ajuste1);
		ajusteRepository.save(ajuste2);
		ajusteRepository.save(ajuste3);

	}

}
