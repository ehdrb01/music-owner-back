package kr.co.strato.wrp.adapter.silhouette.dto;


import java.util.Date;

import lombok.*;

@Getter
@ToString
public class FortifyResultDto {
	
	private int critical;
	
	private int high;
	
	private int medium;
	
	private int row;
	
	private String fortifyId;
	
	private String scanId;
	
	private Date scanDate;
	
	private String rstFileNm;
}
