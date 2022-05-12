package br.com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.domain.Pessoa;
import br.com.springboot.repository.PessoaRepository;

//CAMADA SERVIÇO - Onde fica toda a nossa Regra de Negócio
@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		if (pessoa.getId() != null) {
			throw new RuntimeException("Não precisa de ID");
		}
		return pessoaRepository.save(pessoa);

	}

	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	public Pessoa atualizar(Pessoa pessoa, Long id) {
		if (id == 0) {
			throw new RuntimeException("Precisa de ID");
		}

		Pessoa pessoaId = pessoaRepository.findById(id).orElse(null);

		if (pessoaId == null) {
			throw new RuntimeException("Não existe pessoa com esse ID");

		}

		pessoaId.setNome(pessoa.getNome());
		return pessoaRepository.save(pessoaId);
	}

	public void deletar(Long id) {
		if (id == 0) {
			throw new RuntimeException("Precisa de ID");
		}
		pessoaRepository.deleteById(id);
	}

	public Pessoa listarPorNome(Pessoa pessoa) {
		return pessoaRepository.findByNome(pessoa.getNome());
	}

	public Pessoa listarPorCpf(Pessoa pessoa) {
		return pessoaRepository.buscaPorCpf(pessoa.getCpf());
	}

}
