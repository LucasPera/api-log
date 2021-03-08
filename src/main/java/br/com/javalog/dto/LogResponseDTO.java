package br.com.javalog.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LogResponseDTO {
	
	private Long id;
	private LocalDateTime data;
	private String ip;
	private String request;
	private Integer status;
	private String userAgent;

}
