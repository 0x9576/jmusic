package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import PlayList.PlayListInfo;
import PlayMusic.MusicInfo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddMusicToChart {
	String id;

	private JFrame frame;
	private JTextField filename;
	private JTextField title;
	private JTextField singer;
	private JTextArea lyrics;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMusicToChart window = new AddMusicToChart();
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
	public AddMusicToChart(String id) {
		this.id = id;
		initialize();
	}
	
	public AddMusicToChart() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("차트에 음악 추가하기");
		frame.setBounds(100, 100, 252, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(MusicInfo.storeMusic(filename.getText(), title.getText(), singer.getText(), id, lyrics.getText())) {
					JOptionPane.showMessageDialog(null, "차트에 저장 완료", "" + "차트에 저장 완료", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "자트 저장 실패 : 없거나 중복되는 파일명, 중복되는 제목, 가사를 제외한 입력사항 필수", "" + "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(14, 253, 89, 27);
		frame.getContentPane().add(btnNewButton);
		
		filename = new JTextField();
		filename.setBounds(85, 6, 130, 24);
		frame.getContentPane().add(filename);
		filename.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("파일명");
		lblNewLabel.setBounds(14, 9, 59, 18);
		frame.getContentPane().add(lblNewLabel);
		
		title = new JTextField();
		title.setColumns(10);
		title.setBounds(85, 43, 130, 24);
		frame.getContentPane().add(title);
		
		JLabel label = new JLabel("음악 제목");
		label.setBounds(14, 46, 68, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("가수");
		label_1.setBounds(14, 80, 68, 18);
		frame.getContentPane().add(label_1);
		
		singer = new JTextField();
		singer.setColumns(10);
		singer.setBounds(85, 77, 130, 24);
		frame.getContentPane().add(singer);
		
		lyrics = new JTextArea();
		lyrics.setBounds(14, 131, 201, 77);
		frame.getContentPane().add(lyrics);
		
		JLabel lblNewLabel_1 = new JLabel("가사");
		lblNewLabel_1.setBounds(95, 112, 34, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Music폴더에 파일을 추가하고");
		lblNewLabel_2.setBounds(14, 215, 201, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton button = new JButton("닫기");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				Main.ChartArea.setText(MusicInfo.showChart());
			}
		});
		button.setBounds(126, 253, 89, 27);
		frame.getContentPane().add(button);
		
		JLabel label_2 = new JLabel("입력 후 확인 후 닫기");
		label_2.setBounds(14, 233, 201, 18);
		frame.getContentPane().add(label_2);
		frame.setVisible(true);
	}
}
