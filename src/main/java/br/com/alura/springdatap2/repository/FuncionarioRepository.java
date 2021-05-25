package br.com.alura.springdatap2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.springdatap2.orm.Funcionario;
import br.com.alura.springdatap2.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, 
													JpaSpecificationExecutor<Funcionario>{

	List<Funcionario> findByNome(String nome); 
	List<Funcionario> findByNomeLike(String nome); 
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			 +" AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findByNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data); 
	
	@Query(value = "SELECT * FROM FUNCIONARIOS F WHERE F.DATA_CONTRATACAO >= :data", 
			nativeQuery = true)
	List<Funcionario> findDataCotratacaoMaior(LocalDate data); 
	
	@Query(value = "SELECT F.id, F.nome, F.salario FROM FUNCIONARIOS F", 
			nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario(); 
}
