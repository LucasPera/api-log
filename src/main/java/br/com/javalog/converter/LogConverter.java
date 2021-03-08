package br.com.javalog.converter;

import br.com.javalog.dto.LogRequestDTO;
import br.com.javalog.entity.LogEntity;

public class LogConverter {
	
	public static LogEntity dtoToEntity(LogRequestDTO logRequestDTO) {
		
		LogEntity logEntity = new LogEntity();
		
		logEntity.setData(logRequestDTO.getData());
		logEntity.setIp(logRequestDTO.getIp());
		logEntity.setRequest(logRequestDTO.getRequest());
		logEntity.setStatus(logRequestDTO.getStatus());
		logEntity.setUserAgent(logRequestDTO.getUserAgent());
		
		return logEntity;
		
	}

}
