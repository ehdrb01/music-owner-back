package kr.co.strato.wrp.util;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailSender {

	public boolean sendEmail(EmailVO vo, JavaMailSender mailSender){
    	
    	if( StringUtils.isBlank(vo.getReceiver()) ||  StringUtils.isBlank(vo.getTitle()) || StringUtils.isBlank(vo.getContent())) {
    		log.debug("메일발송 필수값 누락");
    		return false;
    		
    	}else {
    		
    		log.debug("############   Email  start ");
    		//메시지 만들기
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"UTF-8");
					//if(StringUtils.isNoneBlank(vo.getSender())) message.setFrom(vo.getSender());  //보낸사람
					message.setFrom("devops@goldwing.shinhan.com");           //보낸사람 고정
					message.setTo(vo.getReceiver());                    //받는 사람   
					message.setSubject(vo.getTitle());           //제목
					message.setText(vo.getContent(), true);  //내용, 두번째 인자 true이면 html 형식
				}
			};
				
			try{
					//발송			
					mailSender.send(preparator);
						
			} catch (MailException e){
				log.debug("############   JavaMailSender  Error---------- :   " + e.getMessage());
				return false;
			} 
				
			log.debug("############   Email  end ");
			return true;
    	}
	}

}
