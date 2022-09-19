package br.com.codar.gerenciador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import br.com.codar.servicos.AlunosService;
import br.com.codar.servicos.FormatadorDeString;



public class GerenciadorDeArquivos {

	
	
	public static void salvarAluno(Integer qtdDeArquivos) throws IOException {
		AlunosService alunosService = new AlunosService();
		List<String> respostas = new ArrayList<String>();
		File formulario = new File("formulario.txt");
		BufferedReader br = new BufferedReader(new FileReader(formulario));
		Scanner respostaScan = new Scanner(System.in);
		String st;
		while((st = br.readLine()) != null) {
			System.out.println(st);
			String resposta = respostaScan.nextLine();
			respostas.add(resposta.trim());
		}
	
		boolean alunoJaExiste = alunosService.emailJaExiste(respostas.get(1));
		if(!alunoJaExiste) {
			int idade = Integer.parseInt(respostas.get(2));
			if (idade >= 16) {
				qtdDeArquivos++;
				
				File respostasDoForm = new File(String.format("%s-%s.txt", qtdDeArquivos.toString(), FormatadorDeString.converterNomeParaCamelCase(respostas.get(0))));
				FileWriter writer = new FileWriter(respostasDoForm);
				
				respostas.forEach((r) -> {
					try {
						writer.write(r + System.getProperty("line.separator"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				writer.flush();
				writer.close();
				System.out.println("Aluno cadastrado");

			} else {
				System.out.println(
						"A idade mínima para participar do movimento codar é 16 anos. Obrigado pela inscrição! Volte assim que completar 16 anos de idade");
			}
		}else {
			System.out.println("Aluno já existe");
		}
		
		br.close();
	}
	
	public static void adicionarPergunta() throws IOException {
		System.out.println("Insira uma pergunta no formulário:");
		Scanner novaPerguntaScan = new Scanner(System.in);
		String novaPergunta = novaPerguntaScan.nextLine();
		
		Integer linhas = 0;
		
		
		File formulario = new File("formulario.txt");
		BufferedReader br = new BufferedReader(new FileReader(formulario));
		
		while(br.readLine() != null) {
			linhas++;
		}
							
		PrintWriter out = new PrintWriter(new FileWriter("formulario.txt", true));
	    out.append(System.getProperty("line.separator") + "P" + (linhas+1) + "|" + novaPergunta);
	    System.out.println("Pergunta inserida");
	    out.close();
		br.close();
	}
	
	
	public static void removerPergunta(String arquivo, int linhaParaDeletar) {

		String tempFile = "temp.txt";
		File newFile = new File(tempFile);

		int line = 0;
		String currentLine;

		try {
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);

			while ((currentLine = br.readLine()) != null) {
				line++;

				if (linhaParaDeletar != line) {
					pw.println(currentLine);
				}
			}

			pw.flush();
			pw.close();
			fr.close();
			br.close();
			bw.close();
			fw.close();

			FileWriter writer = new FileWriter("formulario.txt", false);
			
			Scanner leituraTemp = new Scanner(new File("temp.txt"));
			int i=0;
			while(leituraTemp.hasNextLine()) {
				
				i++;
				writer.write(leituraTemp.nextLine() + System.getProperty("line.separator"));

			}
			
			writer.close();
			newFile.delete();
			System.out.println("Questão apagada");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, Integer> mapeiaAlunoIdade() throws IOException {
		File dir = new File(System.getProperty("user.dir"));
		HashMap<String, Integer> alunos = new HashMap<>();

		FileFilter filter = new FileFilter() {

			public boolean accept(File f) {
				String nomeDoArquivo = f.getName();
				if (Character.isDigit(nomeDoArquivo.charAt(0))) {
					String a = nomeDoArquivo.substring(0, 1);
					return f.getName().startsWith(a);

				}
				return f.getName().endsWith("nada");
			}
		};

		File[] files = dir.listFiles(filter);
		for (File f : files) {
			if (f.isFile()) {
				BufferedReader inputStream = null;

				inputStream = new BufferedReader(new FileReader(f));
				String linha;
				List<String> linhas = new ArrayList<>();

				while ((linha = inputStream.readLine()) != null) {
					linhas.add(linha);
				}	
				alunos.put(linhas.get(0), Integer.parseInt(linhas.get(2)));
				inputStream.close();
			}
		}
		return alunos;
	}
	
	public static List<List<String>> mapeiaAluno() throws IOException {
		File dir = new File(System.getProperty("user.dir"));
		List<List<String>> alunos = new ArrayList<>();

		FileFilter filter = new FileFilter() {

			public boolean accept(File f) {
				String nomeDoArquivo = f.getName();
				if (Character.isDigit(nomeDoArquivo.charAt(0))) {
					String a = nomeDoArquivo.substring(0, 1);
					return f.getName().startsWith(a);

				}
				return f.getName().endsWith("nada");
			}
		};

		File[] files = dir.listFiles(filter);
		for (File f : files) {
			if (f.isFile()) {
				BufferedReader inputStream = null;

				inputStream = new BufferedReader(new FileReader(f));
				String linha;
				List<String> linhas = new ArrayList<>();

				while ((linha = inputStream.readLine()) != null) {
					linhas.add(linha);
				}	
				alunos.add(linhas);
				inputStream.close();
			}
		}
		return alunos;
	}
}