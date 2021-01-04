package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import LoginInfo.UserInfo;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Find {

	private JFrame frmIdpw;
	private JTextField IDname;
	private JTextField IDbirth;
	private JTextField ShowID;
	private JTextField pwID;
	private JTextField pwEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Find window = new Find();
					window.frmIdpw.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Find() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIdpw = new JFrame();
		frmIdpw.setTitle("ID/PW 찾기");
		frmIdpw.setBounds(100, 100, 450, 270);
		frmIdpw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIdpw.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID/비밀번호 찾기");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setBounds(142, 10, 152, 22);
		frmIdpw.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(12, 72, 30, 15);
		frmIdpw.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("생년월일");
		label.setBounds(12, 97, 48, 15);
		frmIdpw.getContentPane().add(label);
		
		IDname = new JTextField();
		IDname.setBounds(65, 72, 116, 21);
		frmIdpw.getContentPane().add(IDname);
		IDname.setColumns(10);
		
		IDbirth = new JTextField();
		IDbirth.setColumns(10);
		IDbirth.setBounds(65, 94, 116, 21);
		frmIdpw.getContentPane().add(IDbirth);
		
		JButton IDButton = new JButton("ID찾기");
		IDButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowID.setText(UserInfo.findID(IDname.getText(),IDbirth.getText()));
			}
		});
		IDButton.setBounds(65, 125, 97, 23);
		frmIdpw.getContentPane().add(IDButton);
		
		JLabel lblNewLabel_2 = new JLabel("ID찾기");
		lblNewLabel_2.setBounds(94, 47, 48, 15);
		frmIdpw.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ID :");
		lblNewLabel_3.setBounds(25, 161, 30, 15);
		frmIdpw.getContentPane().add(lblNewLabel_3);
		
		ShowID = new JTextField();
		ShowID.setBounds(65, 158, 116, 21);
		frmIdpw.getContentPane().add(ShowID);
		ShowID.setColumns(10);
		
		pwID = new JTextField();
		pwID.setColumns(10);
		pwID.setBounds(256, 69, 116, 21);
		frmIdpw.getContentPane().add(pwID);
		
		pwEmail = new JTextField();
		pwEmail.setColumns(10);
		pwEmail.setBounds(256, 93, 162, 22);
		frmIdpw.getContentPane().add(pwEmail);
		
		JLabel label_1 = new JLabel("이메일");
		label_1.setBounds(210, 96, 41, 15);
		frmIdpw.getContentPane().add(label_1);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(214, 72, 30, 15);
		frmIdpw.getContentPane().add(lblId);
		
		JButton pwButton = new JButton("임시PW발급");
		pwButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(UserInfo.findPW(pwID.getText(), pwEmail.getText())) {
					JOptionPane.showMessageDialog(null, "임시 비밀번호를 보냈습니다.", "" + "전송완료",
							JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"이메일 및 ID를 다시한번 확인 해 주세요", "" 
							+ "오류",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pwButton.setBounds(272, 125, 116, 23);
		frmIdpw.getContentPane().add(pwButton);
		
		JLabel lblNewLabel_4 = new JLabel("이메일로 임시비밀번호를");
		lblNewLabel_4.setBounds(256, 161, 162, 15);
		frmIdpw.getContentPane().add(lblNewLabel_4);
		
		JLabel label_2 = new JLabel("보내드립니다.");
		label_2.setBounds(256, 178, 162, 15);
		frmIdpw.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("비밀번호 찾기");
		label_3.setBounds(272, 47, 97, 15);
		frmIdpw.getContentPane().add(label_3);
		
		JButton close = new JButton("닫기");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmIdpw.setVisible(false);
			}
		});
		close.setBounds(172, 200, 91, 23);
		frmIdpw.getContentPane().add(close);
		frmIdpw.setVisible(true);
	}
}
