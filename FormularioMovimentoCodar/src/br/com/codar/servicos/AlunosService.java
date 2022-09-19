package br.com.codar.servicos;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.codar.gerenciador.GerenciadorDeArquivos;
import br.com.codar.menusUi.Aviso;

public class AlunosService {

	public void agruparAlunosPorIdade() throws IOException {
		Map<Integer, List<String>> valueMap = GerenciadorDeArquivos.mapeiaAlunoIdade().keySet().stream().collect(Collectors.groupingBy(GerenciadorDeArquivos.mapeiaAlunoIdade()::get));
		valueMap.forEach((key, value) -> System.out.println(key + "anos:" + value));
	}
	
	public void somarPorIdade() throws IOException {
		Map<Integer, List<String>> valueMap = GerenciadorDeArquivos.mapeiaAlunoIdade().keySet().stream().collect(Collectors.groupingBy(GerenciadorDeArquivos.mapeiaAlunoIdade()::get));
		valueMap.forEach((key, value) -> System.out.println(key + "anos:" + value.size()));
	}
	
	public void pesquisarAlunoPorEmailENome(String nomeBuscado, String emailBuscado) throws IOException {
		List<List<String>> alunos = GerenciadorDeArquivos.mapeiaAluno();
		List<String> alunoEncontrado = null;
	
		
		
		for (List<String> aluno : alunos) {

			String emailCadastrado = aluno.get(1);
			String nomeCadastrado = aluno.get(0);
			String primeiroNome;
			if (aluno.get(0).contains(" ")) {
				primeiroNome = nomeCadastrado.substring(0, nomeCadastrado.indexOf(' '));
			} else {
				primeiroNome = nomeCadastrado.substring(0, nomeCadastrado.length());
			}

			if (primeiroNome.equals(nomeBuscado) && emailCadastrado.equals(emailBuscado)) {
				alunoEncontrado = aluno;
			}
		}
		if (alunoEncontrado != null) {
			System.out.println("\nAluno encontrado:");
			System.out.println("nome: " + alunoEncontrado.get(0) + "\n" + "email: " + alunoEncontrado.get(1)
					+ "\n" + "idade: " + alunoEncontrado.get(2) + "\n" + "telefone: " + alunoEncontrado.get(3)
					+ "\n\n");
		} else {
			Aviso.alunoNaoEncontrado();
		}
	}

	public void verificaUnicidadeDeAlunos() throws IOException {
		List<List<String>> alunos = GerenciadorDeArquivos.mapeiaAluno();
		Boolean encontrouAlunoRepetido = false;
		String aluno1 = null;
		String aluno2 = null;
		for (int i = 0; i < alunos.size(); i++) {
			String email1 = alunos.get(i).get(1);                    

			for (int j = 1; j < alunos.size() - i; j++) {			
				String email2 = alunos.get(i + j).get(1);
				if (email1.equals(email2)) {
					encontrouAlunoRepetido = true;
					aluno1 = alunos.get(i).get(0);
					aluno2 = alunos.get(i + j).get(0);
				}
			}
		}
		if (encontrouAlunoRepetido) {
			System.out.println(
					"Foram encontrados dois alunos com o mesmo email, verifique!\n" + aluno1 + " e " + aluno2);
		} else {
			System.out.println("Nenhum aluno repetido, obrigado por verificar!");
		}		
	}
	
	public boolean emailJaExiste(String email) throws IOException {
		List<List<String>> alunos = GerenciadorDeArquivos.mapeiaAluno();
		Boolean encontrouAlunoRepetido = false;
		String aluno1 = null;
		String aluno2 = null;
		String email1 = email;
		for (int i = 0; i < alunos.size(); i++) {
			String email2 = alunos.get(i).get(1);
			if (email1.equals(email2)) {
				encontrouAlunoRepetido = true;
			}	
		}
		if (encontrouAlunoRepetido) {
			return true;
		} 
		
		return false;
	}
	
}
