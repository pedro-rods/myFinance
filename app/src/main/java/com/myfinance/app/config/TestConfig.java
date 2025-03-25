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
		Usuario usuario = new Usuario(null, "Teste", "teste@email.com", "123", 8000.0);
		usuario = usuarioRepository.save(usuario);

		Gasto gasto = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "restaurante", 678.98, new Date());
		Gasto gasto1 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "mercado", 618.98, new Date());
		Gasto gasto2 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "transporte", 6678.98, new Date());
		Gasto gasto3 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "cigarro", 678.98, new Date());
		Gasto gasto4 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "cigarro", 388.98, new Date());
		Gasto gasto11 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "academia", 488.98, new Date());
		Gasto gasto5 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "faculdade", 678.98, new Date());
		Gasto gasto7 = new Gasto(null, usuario, EnumTipoCategoria.NECESSIDADE, "aluguel", 1077.98, new Date());
		Gasto gasto8 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "transporte por aplicativo", 678.98,
				new Date());
		Gasto gasto9 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "transporte por aplicativo", 678.98,
				new Date());
		Gasto gasto10 = new Gasto(null, usuario, EnumTipoCategoria.DESEJOS, "cigarro", 678.98, new Date());
		Gasto gasto12 = new Gasto(null, usuario, EnumTipoCategoria.INVESTIMENTOS_POUPANCA, "investimento", 178.98, new Date());
		gastoRepository.save(gasto);
		gastoRepository.save(gasto1);
		gastoRepository.save(gasto2);
		gastoRepository.save(gasto3);
		gastoRepository.save(gasto4);
		gastoRepository.save(gasto5);
		gastoRepository.save(gasto7);
		gastoRepository.save(gasto8);
		gastoRepository.save(gasto9);
		gastoRepository.save(gasto10);
		gastoRepository.save(gasto11);
		gastoRepository.save(gasto12);

		PlanoFinanceiro plano = new PlanoFinanceiro(null, usuario, null, new Date());

		plano = planoRepository.save(plano);

		Ajuste ajuste = new Ajuste(null, plano, EnumTipoCategoria.DESEJOS, "cigarro", -1000.98);
		Ajuste ajuste1 = new Ajuste(null, plano, EnumTipoCategoria.DESEJOS, "transporte por aplicativo",
				-1000.98);
		Ajuste ajuste2 = new Ajuste(null, plano, EnumTipoCategoria.INVESTIMENTOS_POUPANCA, "investimento", 2001.56);
		Ajuste ajuste3 = new Ajuste(null, plano, EnumTipoCategoria.DESEJOS, "restaurante", -1000.98);

		ajusteRepository.save(ajuste);
		ajusteRepository.save(ajuste1);
		ajusteRepository.save(ajuste2);
		ajusteRepository.save(ajuste3);

	}

}
