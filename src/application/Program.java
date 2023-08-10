package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Id;
import entities.Livro;
import enums.Genero;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Livro> catalogo = new ArrayList<>();

		Livro l1 = new Livro(new Id(), "Aaa", "Maria", Genero.FANTASIA, 234, false);
		catalogo.add(l1);
		Livro l2 = new Livro(new Id(), "Bbb", "Joao", Genero.BIOGRAFIA, 345, true);
		catalogo.add(l2);
		Livro l3 = new Livro(new Id(), "Ccc", "Ana", Genero.FANTASIA, 400, false);
		catalogo.add(l3);

		System.out.println("Insira o número do processo que você deseja realizar:\n(1) Filtrar\n(2) Adicionar um exemplar\n(3) Remover um exemplar\n(4) Atualizar dados de um exemplar\n(5) Alugar um exemplar\n(6) Devolver um exemplar\nCaso você deseje encerrar o programa, digite (0).");
		int processo = sc.nextInt();

		while (processo != 0) {
			// FILTRAR
			if (processo == 1) {
				System.out.print("Digite (1) para filtrar por gênero, (2) para filtrar por número de páginas ou (3) para filtrar por disponibilidade: ");
				int opcaoFiltrar = sc.nextInt();

				if (opcaoFiltrar == 1) {
					System.out.print("Qual o gênero do exemplar que você deseja encontrar? ");
					String genero = sc.next();
					System.out.println();

					List<Livro> listGenero = new ArrayList<>();
					for (Livro elemento : catalogo) {
						if (elemento.getGenero() == Genero.valueOf(genero)) {
							listGenero.add(elemento);
						}
					}
					System.out.println("Lista de exemplares do gênero " + Genero.valueOf(genero) + ":");
					for (Livro elemento : listGenero) {
						System.out.println(elemento);
					}
				}

				if (opcaoFiltrar == 2) {
					System.out.print("Qual o limite de número de páginas dos exemplares que você deseja encontrar? ");
					int numPaginas = sc.nextInt();
					System.out.println();

					List<Livro> listNumPag = new ArrayList<>();
					for (Livro elemento : catalogo) {
						if (elemento.getNumPaginas() <= numPaginas) {
							listNumPag.add(elemento);
						}
					}

					System.out.println("Lista de exemplares com até " + numPaginas + " páginas: ");
					for (Livro elemento : listNumPag) {
						System.out.println(elemento);
					}
				}

				if (opcaoFiltrar == 3) {
					System.out.print("Digite (1) para visualizar os exemplares disponíveis e (2) para visualizar os exemplares alugados: ");
					int opcaoStatus = sc.nextInt();
					System.out.println();

					if (opcaoStatus == 1) {

						List<Livro> listStatusTrue = new ArrayList<>();

						for (Livro elemento : catalogo) {
							if (elemento.isStatus() == true) {
								listStatusTrue.add(elemento);
							}
						}

						System.out.println("Lista de exemplares disponíveis:");
						for (Livro elemento : listStatusTrue) {
							System.out.println(elemento);
						}
					}

					if (opcaoStatus == 2) {

						List<Livro> listStatusTrue = new ArrayList<>();

						for (Livro elemento : catalogo) {
							if (elemento.isStatus() == false) {
								listStatusTrue.add(elemento);
							}
						}
						System.out.println("Lista de exemplares alugados:");
						for (Livro elemento : listStatusTrue) {
							System.out.println(elemento);
						}
					}
				}
			}

			// ADICIONAR
			if (processo == 2) {
				System.out.println("Insira os dados do exemplar a ser adicionado no catálogo:");
				System.out.print("Nome: ");
				sc.nextLine();
				String nome = sc.nextLine();
				System.out.print("Autor: ");
				String autor = sc.nextLine();
				System.out.print("Gênero: ");
				String genero = sc.nextLine();
				System.out.print("Número de páginas: ");
				int numPaginas = sc.nextInt();

				Livro novoLivro = new Livro(new Id(), nome, autor, Genero.valueOf(genero), numPaginas, true);
				System.out.println();

				boolean livroJaExiste = false;

				for (Livro elemento : catalogo) {
					if (elemento.equals(novoLivro)) {
						livroJaExiste = true;
						System.out.println("Esse exemplar já existe.");
					}
				}

				if (livroJaExiste == false) {
					catalogo.add(novoLivro);
					System.out.println("Exemplar adicionado: " + novoLivro);
				}
			}

			// REMOVER
			if (processo == 3) {
				System.out.print("Insira o id do exemplar a ser removido do catálogo: ");
				int idRemover = sc.nextInt();

				boolean estaDisponivel = false;

				for (int i = 0; i < catalogo.size(); i++) {
					Livro livro = catalogo.get(i);
					if (livro.getId().getId() == idRemover && livro.isStatus() == true) {
						estaDisponivel = true;
						catalogo.remove(i);
						System.out.println("Exemplar removido: " + livro);
						i--;
					}
				}

				if (estaDisponivel == false) {
					System.out.println("Impossível realizar o processo. Esse exemplar está alugado.");
				}
			}

			// ATUALIZAR
			if (processo == 4) {
				System.out.print("Insira o id do livro a ser atualizado: ");
				int idAtualizar = sc.nextInt();

				System.out.print("Digite (1) par atualizar o nome do exemplar, (2) para atualizar o autor, (3) para atualizar o gênero ou (4) par atualizar o número de páginas: ");
				int opcaoAtualizar = sc.nextInt();
				// ATUALIZAR NOME
				if (opcaoAtualizar == 1) {
					System.out.print("Insira o nome atualizado do exemplar: ");
					sc.nextLine();
					String nomeAtual = sc.nextLine();

					for (int i = 0; i < catalogo.size(); i++) {
						Livro livro = catalogo.get(i);
						if (livro.getId().getId() == idAtualizar) {
							livro.setNome(nomeAtual);
							System.out.println();
							System.out.println("Exemplar atualizado: " + livro);
						}
					}
				}
				// ATUALIZAR AUTOR
				if (opcaoAtualizar == 2) {
					System.out.print("Insira o autor atualizado do exemplar: ");
					sc.nextLine();
					String autorAtual = sc.nextLine();

					for (int i = 0; i < catalogo.size(); i++) {
						Livro livro = catalogo.get(i);
						if (livro.getId().getId() == idAtualizar) {
							livro.setAutor(autorAtual);
							System.out.println();
							System.out.println("Exemplar atualizado: " + livro);
						}
					}
				}
				// ATUALIZAR GENERO
				if (opcaoAtualizar == 3) {
					System.out.print("Insira o gênero atualizado do exemplar: ");
					sc.nextLine();
					String generoAtual = sc.nextLine();

					for (int i = 0; i < catalogo.size(); i++) {
						Livro livro = catalogo.get(i);
						if (livro.getId().getId() == idAtualizar) {
							livro.setGenero(Genero.valueOf(generoAtual));
							System.out.println();
							System.out.println("Exemplar atualizado: " + livro);
						}
					}
				}
				// ATUALIZAR NÚMERO DE PÁGINAS
				if (opcaoAtualizar == 4) {
					System.out.print("Insira o número de páginas atualizado do exemplar: ");
					int numPaginasAtual = sc.nextInt();

					for (int i = 0; i < catalogo.size(); i++) {
						Livro livro = catalogo.get(i);
						if (livro.getId().getId() == idAtualizar) {
							livro.setNumPaginas(numPaginasAtual);
							System.out.println();
							System.out.println("Exemplar atualizado: " + livro);
						}
					}
				}
			}

			// ALUGAR
			if (processo == 5) {
				System.out.print("Insira o Id do exemplar que você deseja alugar: ");
				int idAlugar = sc.nextInt();

				System.out.println();

				boolean estaDisponivel = false;

				for (Livro elemento : catalogo) {
					if (elemento.getId().getId() == idAlugar && elemento.isStatus() == true) {
						estaDisponivel = true;
						elemento.alugarLivro();
						System.out.println("Aluguel do exemplar abaixo, realizado com sucesso!");
						System.out.println(elemento);
					}
				}

				if (estaDisponivel == false) {
					System.out.println("Impossível realizar o processo. Esse exemplar já está alugado.");
				}

			}

			// DEVOLVER
			if (processo == 6) {
				System.out.print("Insira o Id do exemplar que você deseja devolver: ");
				int idDevolver = sc.nextInt();

				System.out.println();

				boolean verificacao = false;

				for (Livro elemento : catalogo) {
					if (elemento.getId().getId() == idDevolver && elemento.isStatus() == false) {
						verificacao = true;
						elemento.devolverLivro();
						System.out.println("Devolução do exemplar abaixo, realizada com sucesso!");
						System.out.println(elemento);
					}
				}

				if (verificacao == false) {
					System.out.println("Impossível realizar o processo. Verifique os dados inseridos.");
				}
			}
		}
		
		if (processo == 0) {
			System.out.println("Programa encerrado!");
		}

		sc.close();
	}
}