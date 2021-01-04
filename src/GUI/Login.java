package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import LoginInfo.UserInfo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	private static JFrame frmJ;
	private static JTextField txtID;
	private static JTextField txtPW;
	private JButton btnNewButton;
	private JButton button;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmJ.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJ = new JFrame();
		frmJ.setTitle("Jmusic Login");
		frmJ.setBounds(100, 100, 268, 171);
		frmJ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJ.getContentPane().setLayout(null);
		
		txtID = new JTextField();
		txtID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField j = (JTextField) e.getSource();
				if(j.getText().equals("ID")) {
					txtID.setText("");
				}
			}
		});
		txtID.setText("ID");
		txtID.setBounds(12, 35, 116, 21);
		frmJ.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtPW = new JTextField();
		txtPW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField j = (JTextField) e.getSource();
				if(j.getText().equals("비밀번호")) {
					txtPW.setText("");
				}
			}
		});
		txtPW.setText("비밀번호");
		txtPW.setColumns(10);
		txtPW.setBounds(12, 66, 116, 21);
		frmJ.getContentPane().add(txtPW);
		
		btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int info = UserInfo.IDPW(txtID.getText(), txtPW.getText());
				if(info != -1) {
					new Main(info);
					frmJ.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null,"ID/PW를 다시한번 확인해 주세요", "" 
							+ "오류",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(140, 34, 97, 23);
		frmJ.getContentPane().add(btnNewButton);
		
		button = new JButton("회원가입");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignUp();
			}
		});
		button.setBounds(140, 65, 97, 23);
		frmJ.getContentPane().add(button);
		
		lblNewLabel = new JLabel("J뮤직에 오신것을 환영합니다.");
		lblNewLabel.setBounds(40, 10, 167, 15);
		frmJ.getContentPane().add(lblNewLabel);
		
		btnNewButton_1 = new JButton("ID,암호 찾기");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Find();
			}
		});
		btnNewButton_1.setBounds(121, 97, 116, 22);
		frmJ.getContentPane().add(btnNewButton_1);
		
		JButton button_1 = new JButton("종료");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(12, 97, 97, 23);
		frmJ.getContentPane().add(button_1);
		frmJ.setVisible(true);
	}
	
	public static void Visible() {
		frmJ.setVisible(true);
		setclear();
	}
	static void setclear() {
		txtID.setText("ID");
		txtPW.setText("비밀번호");
	}
}
