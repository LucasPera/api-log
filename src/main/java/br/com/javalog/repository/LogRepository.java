package br.com.javalog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.javalog.entity.LogEntity;

@Repository
public interface LogRepository extends CrudRepository<LogEntity, Long> {
	
}
