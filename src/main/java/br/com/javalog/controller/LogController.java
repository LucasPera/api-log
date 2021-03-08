package br.com.javalog.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javalog.dto.LogRequestDTO;
import br.com.javalog.entity.LogEntity;
import br.com.javalog.service.LogService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("log/")
public class LogController {
	
	@Autowired
	LogService logService;
	
	@CrossOrigin
	@PostMapping()
	@ApiOperation("Cadastrar log")
	public ResponseEntity<LogEntity> inserirLog(@RequestBody LogRequestDTO logRequestDTO ) {
		
		LogEntity logEntity = logService.inserir(logRequestDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(logEntity);
		
	}
		

	@CrossOrigin
	@GetMapping()
	@ApiOperation("Consultar todos os logs")
	public ResponseEntity<List<LogEntity>> listarLog() {
		
		List<LogEntity> logEntityList = logService.listarLog();
		
		return ResponseEntity.status(HttpStatus.OK).body(logEntityList);
	}
	
	@CrossOrigin
	@GetMapping("filtrar")
	@ApiOperation("Consulta com filtros")
	public ResponseEntity<List<LogEntity>> consultarLog(
			@RequestParam(name =  "data", required = false) LocalDateTime data,
			@RequestParam(name = "ip", required = false ) String ip,
			@RequestParam(name = "request", required = false) String request ,
			@RequestParam(name = "status", required = false) Integer status,
			@RequestParam(name = "userAgent", required = false) String userAgent) {
		
			List<LogEntity> logList = logService.consultarLog(data, ip, request, status, userAgent);
			
			return ResponseEntity.status(HttpStatus.OK).body(logList);
		
	}
	
	@CrossOrigin
	@DeleteMapping("{id}")
	@ApiOperation("Deletar log por id")
	public void deleteLog(@PathVariable Long id) {
		
		logService.deletarLog(id);
		
	}
		
	@CrossOrigin
	@PutMapping("{id}")
	@ApiOperation("Alterar log")
	public ResponseEntity<LogEntity> alterarLog(@RequestBody LogRequestDTO logRequestDTO, @PathVariable Long id) {
		
		return logService.atualizarLog(logRequestDTO, id);
		
	}
	
	
	
	
}
