package br.com.codar.servicos;

import java.io.IOException;

import br.com.codar.gerenciador.GerenciadorDeArquivos;
import br.com.codar.menusUi.Aviso;

public class ServicoDeFormulario {

	AlunosService alunosService = new AlunosService();
	
	public void salvarAluno(Integer qtdDeArquivos) throws IOException {
		GerenciadorDeArquivos.salvarAluno(qtdDeArquivos);
		
	}

	public void adicionarPergunta() throws IOException {
		GerenciadorDeArquivos.adicionarPergunta();

	}

	public void removerPergunta(Integer numeroDaPergunta) {
		if (numeroDaPergunta <= 4) {
			Aviso.avisoRemoverQuestoesAdicionadas();
		} else {
			GerenciadorDeArquivos.removerPergunta("formulario.txt", numeroDaPergunta);
		}
	}

}
