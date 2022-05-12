package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.domain.Pessoa;

//CAMADA Repository - Onde fica nossa comunicação com o Banco
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public Pessoa findByNome(String nome);

	@Query(nativeQuery = true, value = "select * from Pessoa where cpf like ?1")
	public Pessoa buscaPorCpf(String cpf);

}
