package entities;

import java.util.Objects;

import enums.Genero;

public class Livro {
	
	private Id id;
	private String nome;
	private String autor;
	private Genero genero;
	private int numPaginas;
	private boolean status;

	public Livro(Id id, String nome, String autor, Genero genero, int numPaginas, boolean status) {
		this.id = id;
		this.nome = nome;
		this.autor = autor;
		this.genero = genero;
		this.numPaginas = numPaginas;
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(autor, genero, nome, numPaginas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(autor, other.autor) && genero == other.genero && Objects.equals(nome, other.nome)
				&& numPaginas == other.numPaginas;
	}
	
	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public boolean isStatus() {
		return status;
	}
	
	public void alugarLivro() {
		this.status = false;
	}
	
	public void devolverLivro() {
		this.status = true;
	}

	@Override
	public String toString() {
		String textoStatus;
		if (status == true) {
			textoStatus = "Disponível";
		} else {
			textoStatus = "Alugado";
		}
		return "ID: " + id.getId() + " | " + nome + " | " + autor + " | " + genero + " | " + numPaginas + " | " + textoStatus;
	}
	
}
