package com.myfinance.app.config;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

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

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		String senhaPadrao = passwordEncoder.encode("123");
		Usuario user1 = new Usuario(null, "Alice", "alice@email.com", senhaPadrao, 8000.0);
		Usuario user2 = new Usuario(null, "Bruno", "bruno@email.com", senhaPadrao, 6000.0);
		Usuario user3 = new Usuario(null, "Carla", "carla@email.com", senhaPadrao, 7000.0);

		user1 = usuarioRepository.save(user1);
		user2 = usuarioRepository.save(user2);
		user3 = usuarioRepository.save(user3);

		gerarGastosParaUsuario(user1);
		gerarGastosParaUsuario(user2);
		gerarGastosParaUsuario(user3);
	}

	private void gerarGastosParaUsuario(Usuario usuario) {
	    double salarioDisponivel = usuario.getRenda();
	    double totalGastos = 0.0;

	    while (totalGastos < salarioDisponivel) {
	        EnumTipoCategoria macroCategoria = escolherMacroCategoria();
	        String subCategoria = escolherSubCategoria(macroCategoria);

	        // Valor do gasto entre 50 e o restante do salário disponível (ou no máximo 3000)
	        double maxGasto = Math.min(3000.0, salarioDisponivel - totalGastos);
	        if (maxGasto < 50) {
	            break; // Não vale a pena gerar gastos abaixo de 50
	        }

	        double valor = 50 + (random.nextDouble() * (maxGasto - 50));

	        Date dataAleatoria = gerarDataAleatoriaUltimos30Dias();
	        Gasto gasto = new Gasto(null, usuario, macroCategoria, subCategoria, valor, dataAleatoria);
	        gastoRepository.save(gasto);

	        totalGastos += valor;
	    }
	}


	private EnumTipoCategoria escolherMacroCategoria() {
		int pick = random.nextInt(3);
		switch (pick) {
		case 0:
			return EnumTipoCategoria.NECESSIDADES;
		case 1:
			return EnumTipoCategoria.DESEJOS;
		case 2:
			return EnumTipoCategoria.INVESTIMENTO_E_POUPANCA;
		default:
			return EnumTipoCategoria.NECESSIDADES;
		}
	}

	private String escolherSubCategoria(EnumTipoCategoria tipo) {
		switch (tipo) {
		case NECESSIDADES:
			return NECESSIDADES.get(random.nextInt(NECESSIDADES.size()));
		case DESEJOS:
			return DESEJOS.get(random.nextInt(DESEJOS.size()));
		case INVESTIMENTO_E_POUPANCA:
			return INVESTIMENTOS.get(random.nextInt(INVESTIMENTOS.size()));
		default:
			return "outro";
		}

	}

	private Date gerarDataAleatoriaUltimos30Dias() {
		ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
		ZonedDateTime agora = ZonedDateTime.now(zoneId);
		ZonedDateTime trintaDiasAtras = agora.minusDays(30);

		long segundosAleatorios = (long) (random.nextDouble()
				* (agora.toEpochSecond() - trintaDiasAtras.toEpochSecond()));
		ZonedDateTime dataAleatoria = trintaDiasAtras.plusSeconds(segundosAleatorios);

		// Convertendo para java.util.Date (caso o seu Gasto ainda use Date)
		return Date.from(dataAleatoria.toInstant());
	}

}
