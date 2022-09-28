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
	 * Teste b�sico da transfer�ncia de um valor da conta de um cliente para outro,
	 * estando ambos os clientes arivos e havendo saldo o suficiente para tal
	 * transfer�ncia ocorrer com sucesso.
	 */
	
	@Test
	public void testTransfereValor() {
		
		/* Montagem do cen�rio */
		
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
		
		/* Execu��o */
		boolean sucesso = gerContas.transfereValor(idConta01, 100, idConta02);
		
		/* Verifica��es */
		assertTrue(sucesso);
		assertThat(conta02.getSaldo(), is(100.0));
		assertThat(conta01.getSaldo(), is(100.0));
		
		
	}

}
