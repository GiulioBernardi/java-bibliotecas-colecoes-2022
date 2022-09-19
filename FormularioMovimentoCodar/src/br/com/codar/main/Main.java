package br.com.codar.main;

import java.io.IOException;
import java.util.Scanner;

import br.com.codar.menusUi.Aviso;
import br.com.codar.menusUi.Menus;
import br.com.codar.servicos.AlunosService;
import br.com.codar.servicos.ServicoDeFormulario;


public class Main {

	public static void main(String[] args) throws IOException {
		AlunosService alunosService = new AlunosService();
		ServicoDeFormulario servicoDeFormulario = new ServicoDeFormulario();
		Scanner scan = new Scanner(System.in);
		Integer qtdDeArquivos = 0;
		boolean usuarioSaiu = false;
		
		while (!usuarioSaiu) {
			Menus.menuPrincipal();
			int opcao = scan.nextInt();

			if (opcao == 1) {
				servicoDeFormulario.salvarAluno(qtdDeArquivos);
				qtdDeArquivos++;
			}
			if (opcao == 2) {
				servicoDeFormulario.adicionarPergunta();
			}
			if (opcao == 3) {
				Aviso.avisoDelecao();
				int numeroDaPergunta = scan.nextInt();
				servicoDeFormulario.removerPergunta(numeroDaPergunta);
			}
			if (opcao == 4) {
				Menus.menuListagem();
				int opcaoListagem = scan.nextInt();
				if (opcaoListagem == 1) {
					alunosService.agruparAlunosPorIdade();
				}
				if (opcaoListagem == 2) {
					alunosService.somarPorIdade();
				}
				if (opcaoListagem == 3) {
					System.out.println("Voltando...");
				}
				if (opcaoListagem > 3 || opcaoListagem < 1) {
					System.out.println("Opção inválida");
				}
			}
			if (opcao == 5) {
				System.out.println("Digite o email do candidato procurado");
				String emailBuscado = scan.next().toLowerCase();
				System.out.println("Digite o primeiro nome do candidato buscado");
				String nomeBuscado = scan.next().toLowerCase();
				alunosService.pesquisarAlunoPorEmailENome(nomeBuscado, emailBuscado);
			}
			if (opcao == 6) {
				System.out.println("Verificando alunos repetidos");
				alunosService.verificaUnicidadeDeAlunos();
				

			}
			if (opcao == 7) {
				usuarioSaiu = true;

			}
		}

	}

}
