package br.com.javalog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.javalog.converter.LogConverter;
import br.com.javalog.dto.LogRequestDTO;
import br.com.javalog.entity.LogEntity;
import br.com.javalog.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	LogRepository logRepository;

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	Session session;
	
	public LogEntity inserir(LogRequestDTO logRequestDTO) {
		
		return logRepository.save(LogConverter.dtoToEntity(logRequestDTO));
		
	}

	public List<LogEntity> listarLog() {
		
		Iterable<LogEntity> response = logRepository.findAll();
		
		return StreamSupport.stream(response.spliterator(), false).collect(Collectors.toList());
		
	}

	public ResponseEntity<?> deletarLog(Long id) {
		
		return logRepository.findById(id)
				.map(log -> {
					logRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
		
	}

	public ResponseEntity<LogEntity> atualizarLog(LogRequestDTO logRequestDTO, Long id) {
		
		return logRepository.findById(id)
				.map( log -> {
					log.setData(logRequestDTO.getData());
					log.setId(id);
					log.setIp(logRequestDTO.getIp());
					log.setRequest(logRequestDTO.getRequest()	);
					log.setStatus(logRequestDTO.getStatus());
					log.setUserAgent(logRequestDTO.getUserAgent());
					
					LogEntity logAtualizado = logRepository.save(log);
					
					return ResponseEntity.ok().body(logAtualizado);
				}).orElse(ResponseEntity.notFound().build());
		
	}

	public List<LogEntity> consultarLog(LocalDateTime data, String ip, String request, Integer status,
			String userAgent) {
		
		LogEntity logEntity = new LogEntity();
		
		logEntity.setData(data);
		logEntity.setIp(ip);
		logEntity.setRequest(request);
		logEntity.setStatus(status);
		logEntity.setUserAgent(userAgent);
		
		Example example = Example.create(logEntity);
		List<LogEntity> logList = session.createCriteria(LogEntity.class).add(example).list();
		
		return logList;
	}
	
	

}
