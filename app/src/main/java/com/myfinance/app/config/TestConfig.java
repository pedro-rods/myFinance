package com.myfinance.app.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.myfinance.app.entitiy.Gasto;
import com.myfinance.app.entitiy.Usuario;
import com.myfinance.app.enums.EnumTipoCategoria;
import com.myfinance.app.repository.GastoRepository;
import com.myfinance.app.repository.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GastoRepository gastoRepository;

	private Random random = new Random();

	private static final List<String> NECESSIDADES = Arrays.asList("aluguel", "condominio", "conta de agua",
			"conta de luz", "gás", "plano de saude", "remedios", "transporte", "combustivel", "parcela de carro",
			"seguro de carro", "seguro de vida", "supermercado", "faculdade", "internet");

	private static final List<String> DESEJOS = Arrays.asList("assinaturas", "cinema", "delivery", "eletronicos",
			"massagem", "presentes", "restaurante", "roupa", "salão de beleza", "shows", "streaming", "cursos livres",
			"transporte por aplicativo", "servicos", "academia", "hobby");

	private static final List<String> INVESTIMENTOS = Arrays.asList("fundo de emergência", "investimento", "poupança",
			"previdência");

	@Override
	public void run(String... args) throws Exception {
		System.out.println("rodando");
		Usuario user1 = new Usuario(null, "Alice", "alice@email.com", "123", 8000.0);
		Usuario user2 = new Usuario(null, "Bruno", "bruno@email.com", "123", 6000.0);
		Usuario user3 = new Usuario(null, "Carla", "carla@email.com", "123", 7000.0);

		user1 = usuarioRepository.save(user1);
		user2 = usuarioRepository.save(user2);
		user3 = usuarioRepository.save(user3);

		gerarGastosParaUsuario(user1);
		gerarGastosParaUsuario(user2);
		gerarGastosParaUsuario(user3);
	}

	private void gerarGastosParaUsuario(Usuario usuario) {
		// Cada usuário terá entre 10 e 20 gastos variados
		int quantidadeGastos = random.nextInt(10) + 10;

		for (int i = 0; i < quantidadeGastos; i++) {
			EnumTipoCategoria macroCategoria = escolherMacroCategoria();
			String subCategoria = escolherSubCategoria(macroCategoria);
			double valor = 50 + (5000 * random.nextDouble()); // Gastos entre 50 e 5000

			Gasto gasto = new Gasto(null, usuario, macroCategoria, subCategoria, valor, new Date());
			gastoRepository.save(gasto);
		}
	}

	private EnumTipoCategoria escolherMacroCategoria() {
		int pick = random.nextInt(3);
		switch (pick) {
		case 0:
			return EnumTipoCategoria.NECESSIDADE;
		case 1:
			return EnumTipoCategoria.DESEJOS;
		case 2:
			return EnumTipoCategoria.INVESTIMENTOS_POUPANCA;
		default:
			return EnumTipoCategoria.NECESSIDADE;
		}
	}

	private String escolherSubCategoria(EnumTipoCategoria tipo) {
		switch (tipo) {
		case NECESSIDADE:
			return NECESSIDADES.get(random.nextInt(NECESSIDADES.size()));
		case DESEJOS:
			return DESEJOS.get(random.nextInt(DESEJOS.size()));
		case INVESTIMENTOS_POUPANCA:
			return INVESTIMENTOS.get(random.nextInt(INVESTIMENTOS.size()));
		default:
			return "outro";
		}

	}

}
