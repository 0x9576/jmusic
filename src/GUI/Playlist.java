package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import PlayList.PlayListInfo;
import PlayMusic.MusicInfo;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Playlist {
	int sq;
	String id;
	boolean con = false;
	private JFrame frame;
	private JTextField enterField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Playlist window = new Playlist();
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
	public Playlist() {
		initialize();
	}

	public Playlist(int sq, String id) {
		this.sq = sq;
		this.id = id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("모든 플레이리스트");
		frame.setBounds(100, 100, 268, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea listField = new JTextArea();
		listField.setBounds(12, 135, 226, 163);
		frame.getContentPane().add(listField);

		JButton btnNewButton = new JButton("추가");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String enter = enterField.getText();
				if (enter.equals("")) {
					JOptionPane.showMessageDialog(null, "타이틀을 입력해 주세요", "" + "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					if (PlayListInfo.addPlayList(id, enter)) {
						JOptionPane.showMessageDialog(null, "리스트 생성 완료.", "" + "리스트 생성완료", JOptionPane.PLAIN_MESSAGE);
						listField.setText(PlayListInfo.showlist(id));
					} else {
						JOptionPane.showMessageDialog(null, "이미 존재하는 타이틀입니다.", "" + "오류", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnNewButton.setBounds(78, 41, 63, 23);
		frame.getContentPane().add(btnNewButton);

		JButton button = new JButton("삭제");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String enter = enterField.getText();
				if(PlayListInfo.removePlayList(id,enter)) {
					JOptionPane.showMessageDialog(null, "리스트 삭제 완료.", "" + "리스트 삭제 완료", JOptionPane.PLAIN_MESSAGE);
					listField.setText(PlayListInfo.showlist(id));
				}else {
					JOptionPane.showMessageDialog(null, "존재하지 않는 타이틀입니다.", "" + "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button.setBounds(12, 41, 63, 23);
		frame.getContentPane().add(button);

		enterField = new JTextField();
		enterField.setBounds(12, 10, 226, 21);
		frame.getContentPane().add(enterField);
		enterField.setColumns(10);

		JLabel label = new JLabel("또는 수정, 추가, 리스트 지정");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label.setBounds(12, 113, 230, 15);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("제목을 입력후, 삭제");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_1.setBounds(12, 98, 133, 15);
		frame.getContentPane().add(label_1);

		String str = PlayListInfo.showlist(id);
		listField.setText(str);

		JButton button_1 = new JButton("리스트 지정");
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String que = listField.getText();
				String enter = enterField.getText();
				if (que.contains(enter)) {
					if (enter.equals("")) {
						JOptionPane.showMessageDialog(null, "리스트 지정 실패", "" + "오류", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "리스트 지정 완료.", "" + "리스트 지정 완료", JOptionPane.PLAIN_MESSAGE);
						con = true;
					}
				} else
					JOptionPane.showMessageDialog(null, "리스트 지정 실패", "" + "오류", JOptionPane.ERROR_MESSAGE);
			}
		});
		button_1.setBounds(12, 70, 157, 23);
		frame.getContentPane().add(button_1);

		JButton btnNewButton_1 = new JButton("닫기");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				String que = enterField.getText();
				if (con) {
					if (que.contains(enterField.getText())) {
						if (que.equals("")) {
						} else {
							Main.lblNewLabel_1.setText(que);
							Main.listname = que;
							Main.playlistArea.setText(PlayListInfo.showmusic(id, que));
							Main.m = MusicInfo.makePlayMusic(id, que, Main.count);
						}
					}
				} else {
				}

			}
		});
		btnNewButton_1.setBounds(171, 70, 67, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton button_2 = new JButton("이름 수정");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String enter = enterField.getText();
				String name = JOptionPane.showInputDialog("바꿀 이름을 입력해 주세요");
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요", "" + "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					if (PlayListInfo.changePlayList(id, enter, name)) {
						JOptionPane.showMessageDialog(null, "리스트이름 수정 완료", "" + "리스트이름 수정 완료",
								JOptionPane.PLAIN_MESSAGE);
						listField.setText(PlayListInfo.showlist(id));
					} else {
						JOptionPane.showMessageDialog(null, "리스트 수정 실패", "" + "오류", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button_2.setBounds(143, 41, 95, 23);
		frame.getContentPane().add(button_2);

		frame.setVisible(true);
	}
}
