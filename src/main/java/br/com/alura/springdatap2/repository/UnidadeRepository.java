package br.com.alura.springdatap2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.springdatap2.orm.UnidadeTrabalho;

@Repository
public interface UnidadeRepository extends CrudRepository<UnidadeTrabalho, Integer>{

}
