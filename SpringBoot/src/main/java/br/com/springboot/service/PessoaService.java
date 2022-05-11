package br.com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.springboot.domain.Pessoa;

@Service
public class PessoaService {

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public Pessoa salvar(Pessoa pessoa) {
		if (pessoa.getId() != null) {
			throw new RuntimeException("Não precisa de ID");
		}

		if (pessoas.size() == 0) {
			pessoa.setId(1l);
		} else {
			pessoa.setId((pessoas.get(pessoas.size() - 1).getId() + 1));
		}

		pessoas.add(pessoa);
		return null;
	}

	public List<Pessoa> listar() {
		return pessoas;
	}

	public Pessoa atualizar(Pessoa pessoa, Long id) {
		if (id == 0) {
			throw new RuntimeException("Precisa de ID");
		}

		for (Pessoa pessoaLista : pessoas) {
			if (pessoaLista.getId() == id) {
				pessoaLista.setNome(pessoa.getNome());
				pessoa.setId(id);
			}
		}
		return pessoa;
	}

	public void deletar(Long id) {
		if (id == 0) {
			throw new RuntimeException("Precisa de ID");
		}
		for (Pessoa pessoaLista : pessoas) {
			if (pessoaLista.getId() == id) {
				pessoas.remove(pessoaLista);
			}
		}
	}

}
