package br.com.codar.menusUi;

public class Aviso {
	public static void avisoDelecao() {
		System.out.println("ATENÇÃO\n"+
				"Por segurança só é possível apagar questões adicionadas. "
				+ "Não é possível apagar as 4 questões padrões do formulário\n"+
				"Digite o nº da pergunta: "
				+ "(Exemplo: Se a pergunta for P|19, escreva: 19)");
	}
	
	public static void avisoRemoverQuestoesAdicionadas() {
		System.out.println("Só é possível remover questões adicionadas");
	}
	
	public static void alunoNaoEncontrado() {
		System.out.println("Aluno com esse nome e email não encontrado. Verifique os valores informados");

	}

	public static void emailJaExistente() {
		System.out.println("Esse aluno já está cadastrado");
		
	}
}
