package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraContasTest {

	private GerenciadoraContas gerContas;

	/*
	 * Teste básico da transferência de um valor da conta de um cliente para outro,
	 * estando ambos os clientes arivos e havendo saldo o suficiente para tal
	 * transferência ocorrer com sucesso.
	 */
	
	@Test
	public void testTransfereValor() {
		
		/* Montagem do cenário */
		
		// Criando alguns clientes 
		int idConta01 = 1;
		int idConta02 = 2;
		ContaCorrente conta01 = new ContaCorrente(idConta01, 200, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);
		
		//inserindo os clientes criados na lista de clientes do banco
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);
		
		/* Execução */
		boolean sucesso = gerContas.transfereValor(idConta01, 100, idConta02);
		
		/* Verificações */
		assertTrue(sucesso);
		assertThat(conta02.getSaldo(), is(100.0));
		assertThat(conta01.getSaldo(), is(100.0));
		
		
	}

}
