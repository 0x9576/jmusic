package GUI;

import LoginInfo.UserInfo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Profile {
	String [] str;
	
	Profile(String [] str){
		this.str = str;
		initialize();
	}
	

	private JFrame frame;
	private JTextField BirthF1;
	private JTextField NameF1;
	private JTextField PWF1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile window = new Profile();
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
	public Profile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("프로필 창");
		frame.setBounds(100, 100, 361, 286);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Name = new JLabel("생년월일");
		Name.setBounds(12, 37, 57, 15);
		frame.getContentPane().add(Name);
		
		JButton submit = new JButton("제출");
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserInfo.changeProf(str[1], BirthF1.getText(),NameF1.getText(),PWF1.getText());
				JOptionPane.showMessageDialog(null, "정보변경이 완료되었습니다. 재 로그인시 반영됩니다.", "" + "변경 완료",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		submit.setBounds(12, 214, 97, 23);
		frame.getContentPane().add(submit);
		
		JButton close = new JButton("닫기");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		close.setBounds(121, 214, 97, 23);
		frame.getContentPane().add(close);
		
		JLabel lblNewLabel = new JLabel("정보변경을 원하시면 변경 후 제출을 눌러주세요");
		lblNewLabel.setBounds(42, 10, 262, 15);
		frame.getContentPane().add(lblNewLabel);
		
		BirthF1 = new JTextField();
		BirthF1.setBounds(81, 34, 246, 21);
		frame.getContentPane().add(BirthF1);
		BirthF1.setColumns(10);
		
		JLabel label = new JLabel("이메일");
		label.setBounds(12, 65, 57, 15);
		frame.getContentPane().add(label);
		
		NameF1 = new JTextField();
		NameF1.setColumns(10);
		NameF1.setBounds(81, 90, 246, 21);
		frame.getContentPane().add(NameF1);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(12, 121, 57, 15);
		frame.getContentPane().add(lblId);
		
		JLabel ID = new JLabel("이름");
		ID.setBounds(12, 93, 57, 15);
		frame.getContentPane().add(ID);
		
		JLabel label_4 = new JLabel("비밀번호");
		label_4.setBounds(12, 152, 57, 15);
		frame.getContentPane().add(label_4);
		
		PWF1 = new JTextField();
		PWF1.setColumns(10);
		PWF1.setBounds(81, 149, 246, 21);
		frame.getContentPane().add(PWF1);
		
		JButton Withdrawal = new JButton("탈퇴");
		Withdrawal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserInfo.withdrawal(str[1]);
				JOptionPane.showMessageDialog(null, "탈퇴가 완료되었습니다. 시스템이 종료됩니다.", "" + "탈퇴 완료",
						JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
		});
		Withdrawal.setBackground(Color.RED);
		Withdrawal.setBounds(230, 214, 97, 23);
		frame.getContentPane().add(Withdrawal);
		frame.setVisible(true);
		JLabel EmailF1 = new JLabel("New label");
		EmailF1.setBounds(81, 65, 246, 15);
		frame.getContentPane().add(EmailF1);
		
		JButton btnNewButton = new JButton("공개상태 변경 (제출필요없음)");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(UserInfo.changeOpen(str[1])) {
					JOptionPane.showMessageDialog(null, "이제 프로필이 공개됩니다. 재 로그인시 반영됩니다.", "" + "공개 상태 변경",
							JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "이제 프로필이 비공개됩니다. 재 로그인시 반영됩니다.", "" + "공개 상태 변경",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(134, 180, 193, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label_1 = new JLabel("공개여부");
		label_1.setBounds(12, 184, 57, 15);
		frame.getContentPane().add(label_1);
		
		JLabel OpenF1 = new JLabel("공개");
		OpenF1.setBounds(81, 184, 57, 15);
		frame.getContentPane().add(OpenF1);
		
		JLabel IDF1 = new JLabel("New label");
		IDF1.setBounds(81, 121, 246, 15);
		frame.getContentPane().add(IDF1);
		
		BirthF1.setText(str[0]);
		EmailF1.setText(str[1]);
		NameF1.setText(str[2]);
		IDF1.setText(str[3]);
		PWF1.setText(str[4]);
		OpenF1.setText(str[5]);

	}
}
