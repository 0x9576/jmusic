package SendEmail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	static int gsend(String title, String adress) {
		String user = ""; //계정
		String password = ""; // 패스워드
		Random r = new Random();

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		int ran = r.nextInt(8999) + 1000;

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(adress));

			// Subject
			message.setSubject("Jmusic "+title); // 메일 제목을 입력

			// Text
			message.setText("다음을 입력해 주세요 \n --" + Integer.toString(ran)+"--"); // 메일 내용을 입력

			// send the message
			Transport.send(message); //// 전송
		} catch (AddressException e) {
			return -1;
		} catch (MessagingException e) {
			return -1;
		}

		return ran;
	}

	public static int keyMail(String adress) {
		return gsend("인증메일 입니다.", adress);
	}

	public static int pwMail(String adress) {
		return gsend("임시 비밀번호입니다.", adress);
	}

	// main for test
	public static void main(String[] args) {
		System.out.println("message sent successfully..." + keyMail("dhvmfhej@koreatech.ac.kr"));
	}
}
