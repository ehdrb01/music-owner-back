package kr.co.strato.wrp.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmailVO {
	private int wrAlertId;
	private String sender; 										     	 // 보내는 사람 
	private String receiver; 										     	 // 받는사람 이메일
	private String title;                      							// 제목
	private String content;                                            //내용 
	private String receiverId; 										     	 // 받는사람 아이디
	private String receiverName; 										     	 // 받는사람 이름
}
