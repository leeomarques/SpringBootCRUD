package br.com.springboot.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.domain.Pessoa;
import br.com.springboot.service.PessoaService;

//CAMADA REST - é uma Entrada com comunicação com as outras API's
@RestController
@RequestMapping("/api")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping("/pessoa")
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa) throws URISyntaxException {
		return ResponseEntity.created(new URI("/api/pessoa")).body(pessoaService.salvar(pessoa));
	}

	@GetMapping("/pessoa")
	public ResponseEntity<List<Pessoa>> listarPessoa() {
		return ResponseEntity.ok().body(pessoaService.listar());
	}

	@PutMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody Pessoa pessoa, @PathVariable Long id) {
		return ResponseEntity.ok().body(pessoaService.atualizar(pessoa, id));
	}

	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
		pessoaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/pessoa/nome")
	public ResponseEntity<Pessoa> listarPessoaPorNome(Pessoa pessoa) {
		return ResponseEntity.ok().body(pessoaService.listarPorNome(pessoa));
	}

	@GetMapping("/pessoa/cpf")
	public ResponseEntity<Pessoa> listarPessoaPorCpf(Pessoa pessoa) {
		return ResponseEntity.ok().body(pessoaService.listarPorCpf(pessoa));
	}

}
