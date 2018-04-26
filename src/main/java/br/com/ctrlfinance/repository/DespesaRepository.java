package br.com.ctrlfinance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.ctrlfinance.model.Despesa;

public interface DespesaRepository extends CrudRepository<Despesa, Long>{
	
	@Query(value = "select * from despesa where usuario_email = :usuario order by data desc", nativeQuery=true)
	public List<Despesa> findByTipo(@Param("usuario")String usuario);

}
