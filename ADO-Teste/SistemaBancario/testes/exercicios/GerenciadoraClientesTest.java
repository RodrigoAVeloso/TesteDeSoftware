package exercicios;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.Cliente;
import negocio.GerenciadoraClientes;
import negocio.IdadeNaoPermitidaException;

/**
 * 
 * ALUNOS: Giovana Sim�es Santos / Rodrigo Assun��o Veloso DATA: 28/09/2022
 *
 */

public class GerenciadoraClientesTest {

	private GerenciadoraClientes gerClientes;
	private int idCliente01 = 1;
	private int idCliente02 = 2;

	@Before
	public void setUp() {

		/* Montagem do cen�rio */

		// Criando alguns clientes
		Cliente cliente01 = new Cliente(idCliente01, "Jo�o", 31, "joao@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Jo�o", 31, "joao@gmail.com", 1, true);

		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}

	/*
	 * Teste para pesquisar cliente a partir do seu ID.
	 */

	@Test
	public void testPesquisaCliente() {

		/* Execu��o */
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		/* Verifica��es */
		assertThat(cliente.getId(), is(idCliente01));
	}

	/*
	 * Teste b�sico da remo��o de um cliente a partir do seu ID.
	 */

	@Test
	public void testeRemoveCliente() {

		/* Execu��o */
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);

		/* Verifica��es */
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente02));
	}

	/* Teste de esquivalencia */

	@Test
	public void testeIdadeValidaMin() throws IdadeNaoPermitidaException {

		/* Montagem do cen�rio */
		Cliente cliente = new Cliente(idCliente01, "Jo�o", 18, "joao@gmail.com", 1, true);

		/* Execu��o */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* Verifica��es */
		assertTrue(idadeValida);
	}

	@Test
	public void testeIdadeValidaMax() throws IdadeNaoPermitidaException {

		/* Montagem do cen�rio */
		Cliente cliente = new Cliente(idCliente01, "Jo�o", 65, "joao@gmail.com", 1, true);

		/* Execu��o */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* Verifica��es */
		assertTrue(idadeValida);
	}

	@Test(expected = IdadeNaoPermitidaException.class)
	public void testeIdadeInvalidaAcima() throws IdadeNaoPermitidaException {

		/* Montagem do cen�rio */
		Cliente cliente = new Cliente(idCliente01, "Jo�o", 66, "joao@gmail.com", 1, true);

		/* Execu��o */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* Verifica��es */
		assertTrue(idadeValida);
	}

	@Test(expected = IdadeNaoPermitidaException.class)
	public void testeIdadeInvalidaAbaixo() throws IdadeNaoPermitidaException {

		/* Montagem do cen�rio */
		Cliente cliente = new Cliente(idCliente01, "Jo�o", 17, "joao@gmail.com", 1, true);

		/* Execu��o */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		/* Verifica��es */
		assertTrue(idadeValida);
	}

}
