package GUI;

import SendEmail.SendEmail;
import LoginInfo.UserInfo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SignUp {

	private JFrame frame;
	private JTextField NameF;
	private JTextField BirthF;
	private JTextField IDF;
	private JTextField PWF;
	private JTextField EmailF;
	private String[] InfoF = new String[5];
	private JTextField KeyF;
	private static int key = 0;
	private static boolean pass = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 330, 258);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(117, 12, 80, 21);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(12, 74, 30, 15);
		frame.getContentPane().add(lblNewLabel_1);

		NameF = new JTextField();
		NameF.setBounds(65, 71, 116, 21);
		frame.getContentPane().add(NameF);
		NameF.setColumns(10);

		JLabel label = new JLabel("생년월일");
		label.setBounds(12, 102, 48, 15);
		frame.getContentPane().add(label);

		BirthF = new JTextField();
		BirthF.setColumns(10);
		BirthF.setBounds(65, 99, 116, 21);
		frame.getContentPane().add(BirthF);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(12, 130, 30, 15);
		frame.getContentPane().add(lblId);

		IDF = new JTextField();
		IDF.setColumns(10);
		IDF.setBounds(65, 127, 116, 21);
		frame.getContentPane().add(IDF);

		JLabel lblPw = new JLabel("PW");
		lblPw.setBounds(12, 158, 30, 15);
		frame.getContentPane().add(lblPw);

		PWF = new JTextField();
		PWF.setColumns(10);
		PWF.setBounds(65, 155, 116, 21);
		frame.getContentPane().add(PWF);

		EmailF = new JTextField();
		EmailF.setColumns(10);
		EmailF.setBounds(65, 43, 225, 21);
		frame.getContentPane().add(EmailF);

		JLabel label_1 = new JLabel("이메일");
		label_1.setBounds(12, 46, 48, 15);
		frame.getContentPane().add(label_1);

		JButton Submit = new JButton("제출");
		Submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InfoF[0] = NameF.getText();
				InfoF[1] = BirthF.getText();
				InfoF[2] = IDF.getText();
				InfoF[3] = PWF.getText();
				InfoF[4] = EmailF.getText();

				int count = 0;

				for (int i = 0; i < 5; i++) {
					if (InfoF[i].equals("")) {
						count++;
					}
				}
				if (count != 0) {
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해 주세요", "" + "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					if (UserInfo.AddInfo(InfoF[0], InfoF[1], InfoF[2], InfoF[3], InfoF[4],pass)) {
	
							JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다 감사합니다.", "" + "회원가입 완료",
									JOptionPane.PLAIN_MESSAGE);
							frame.setVisible(false);
					}
					else {
						if(pass) {
							JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.", "" + "오류", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "이메일 인증 바랍니다.", "" + "오류", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				}
			}
		});
		Submit.setBounds(52, 186, 97, 23);
		frame.getContentPane().add(Submit);

		JButton Close = new JButton("취소");
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		Close.setBounds(171, 186, 97, 23);
		frame.getContentPane().add(Close);
		
		JLabel lblNewLabel_2 = new JLabel("이메일을 입력후");
		lblNewLabel_2.setBounds(193, 71, 109, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("인증키를 받아 입력");
		label_2.setBounds(193, 84, 109, 15);
		frame.getContentPane().add(label_2);
		
		
		JButton btnNewButton = new JButton("이메일인증");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				key = SendEmail.keyMail(EmailF.getText());
				if(key != -1) {
					JOptionPane.showMessageDialog(null, "인증키 발급 완료", "" + "인증키 발급 완료",
							JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "인증키 발급 실패", "" + "오류", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(193, 99, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		KeyF = new JTextField();
		KeyF.setBounds(230, 124, 75, 21);
		frame.getContentPane().add(KeyF);
		KeyF.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("인증키");
		lblNewLabel_3.setBounds(193, 127, 36, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("인증확인");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String keyString = Integer.toString(key);
				String enter = KeyF.getText();
				if(enter.equals(keyString)) {
					JOptionPane.showMessageDialog(null, "인증 완료", "" + "인증 완료",
							JOptionPane.PLAIN_MESSAGE);
					pass = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "인증번호 오류입니다.", "" + "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(206, 150, 84, 23);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}
}
