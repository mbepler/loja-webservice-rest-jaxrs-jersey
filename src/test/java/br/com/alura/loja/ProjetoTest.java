package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {
	private HttpServer server;
	
	@Before
	public void startaServidor() {
		server =Servidor.inicializaServidor();
	}
	@After
	public void mataServidor() {
		server.stop();
	}

	@Test
	public void validaBuscaPrimeiroProjeto() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/");
		
		String response = target.path("projetos/1").request().get(String.class);
		

		Projeto projeto = (Projeto) new XStream().fromXML(response);
		
		assertEquals("Minha loja", projeto.getNome());
	}
	
}