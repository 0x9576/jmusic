package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import LoginInfo.User;
import LoginInfo.UserInfo;
import PlayList.PlayListInfo;
import PlayMusic.MusicInfo;
import PlayMusic.PlayMusic;
import javazoom.jl.decoder.JavaLayerException;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends Thread {
	int sq;
	public static String listname = null;
	public static int count = 0;
	int time = 0;
	int size1 = 982;
	int size2 = 516;

	Main(int sq) {
		this.sq = sq;
		initialize();
	}

	Main(int sq, String list) {
		this.sq = sq;
		this.listname = list;
		initialize();
	}

	private static JFrame mainJmusic;
	private JTextField SerchField;
	private JTextField addMusicField;
	public static JLabel SingerLabel;
	public static JLabel TitleLabel;
	public static JTextArea playlistArea;
	public static JLabel lblNewLabel_1;
	public static JTextArea ChartArea;
	public static PlayMusic m;
	public static JTextArea LyricsArea;
	public static TimerGUI tg;
	boolean ne = true;
	boolean rp = false;

	ImageIcon[] img = { new ImageIcon("Cover\\1.jpg"), new ImageIcon("Cover\\2.jpg"), new ImageIcon("Cover\\3.jpg"),
			new ImageIcon("Cover\\4.jpg"), new ImageIcon("Cover\\5.gif"), new ImageIcon("Cover\\6.jpg"),
			new ImageIcon("Cover\\7.jpg"), new ImageIcon("Cover\\8.jpg"), new ImageIcon("Cover\\9.jpg"),
			new ImageIcon("Cover\\10.jpg") };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainJmusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		listname = null;
		SingerLabel = new JLabel("재생중인 곡 없음");
		TitleLabel = new JLabel("재생중인 곡 없음");
		LyricsArea = new JTextArea();
		User u = UserInfo.GetUser(sq);
		String[] str = u.getProfile();
		// str[3] = id

		mainJmusic = new JFrame();
		mainJmusic.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()>2) {
						size1 =982;
						size2 =516;
						mainJmusic.setBounds(100, 100, size1, size2);
		          }
			}
		});
		mainJmusic.setTitle("Jmusic");
		mainJmusic.setBounds(100, 100, size1, size2);
		mainJmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJmusic.getContentPane().setLayout(null);

		JLabel ImageLabel = new JLabel();
		ImageLabel.setIcon(new ImageIcon("Cover\\0.jpg"));
		ImageLabel.setBounds(29, 232, 129, 124);
		mainJmusic.getContentPane().add(ImageLabel);

		JLabel lblNewLabel = new JLabel(str[2] + "님");
		lblNewLabel.setBounds(12, 114, 97, 23);
		mainJmusic.getContentPane().add(lblNewLabel);

		JButton ProfileButton = new JButton("프로필 보기/수정");
		ProfileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(str);
			}
		});
		ProfileButton.setBounds(12, 172, 159, 23);
		mainJmusic.getContentPane().add(ProfileButton);

		JButton LogoutButton = new JButton("logout");
		LogoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login.Visible();
				mainJmusic.setVisible(false);
				m.close();
			}
		});

		LogoutButton.setBounds(93, 139, 78, 23);
		mainJmusic.getContentPane().add(LogoutButton);

		JLabel label = new JLabel("환영합니다!");
		label.setBounds(12, 139, 86, 23);
		mainJmusic.getContentPane().add(label);

		playlistArea = new JTextArea();
		playlistArea.setBounds(741, 40, 211, 285);
		mainJmusic.getContentPane().add(playlistArea);
		playlistArea.setText(PlayListInfo.showmusic(str[3], listname));

		lblNewLabel_1 = new JLabel("                 Playlist");
		if (listname == null) {
			lblNewLabel_1.setText("              Playlist");
		} else {
			lblNewLabel_1.setText(listname);
		}
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1.setBounds(741, 13, 211, 20);
		mainJmusic.getContentPane().add(lblNewLabel_1);

		JButton AllPlaylist = new JButton("모든 재생목록");
		AllPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Playlist(sq, str[3]);
			}
		});
		AllPlaylist.setBounds(831, 338, 121, 23);
		mainJmusic.getContentPane().add(AllPlaylist);

		JButton addMusicButton = new JButton("리스트 '음악 추가");
		addMusicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblNewLabel_1.getText().equals("              Playlist")) {
					JOptionPane.showMessageDialog(null, "리스트를 먼저 지정해 주세요", "" + "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					if (PlayListInfo.addmusic(str[3], listname, addMusicField.getText())) {
						playlistArea.setText(PlayListInfo.showmusic(str[3], listname));
						JOptionPane.showMessageDialog(null, "추가 완료", "" + "추가 완료", JOptionPane.PLAIN_MESSAGE);
						count = 0;
						m = MusicInfo.makePlayMusic(str[3], listname, count);
					} else {
						JOptionPane.showMessageDialog(null, "제목을 다시한번 확인해 주세요", "" + "오류", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		addMusicButton.setBounds(741, 399, 129, 23);
		mainJmusic.getContentPane().add(addMusicButton);

		JButton SuffleButton = new JButton("셔플");
		SuffleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = PlayListInfo.shuffle(str[3], listname, TitleLabel.getText());
				if (a == -2) {
					JOptionPane.showMessageDialog(null, "셔플 실패", "" + "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					playlistArea.setText(PlayListInfo.showmusic(str[3], listname));
					count = a;
				}

			}
		});
		SuffleButton.setBounds(741, 338, 76, 23);
		mainJmusic.getContentPane().add(SuffleButton);

		TitleLabel.setBounds(12, 38, 174, 15);
		mainJmusic.getContentPane().add(TitleLabel);

		JButton PreviousButton = new JButton("◁◁");
		PreviousButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m.stop();
				count--;
				m = MusicInfo.makePlayMusic(str[3], listname, count);
				int t = MusicInfo.titlehave() - 1;
				ImageLabel.setIcon(img[t]);
				try {
					m.play();
					tg.next();
				} catch (JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		PreviousButton.setBounds(12, 81, 57, 23);
		mainJmusic.getContentPane().add(PreviousButton);

		JButton NextButton = new JButton("▷▷");
		NextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m.stop();
				count++;
				m = MusicInfo.makePlayMusic(str[3], listname, count);
				int t = MusicInfo.titlehave() - 1;
				ImageLabel.setIcon(img[t]);
				try {
					m.play();
					tg.next();
				} catch (JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NextButton.setBounds(114, 81, 57, 23);
		mainJmusic.getContentPane().add(NextButton);

		JButton PlayButton = new JButton("▶");
		PlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton j = (JButton) e.getSource();
				if (j.getText().equals("▶")) {
					int t = MusicInfo.titlehave() - 1;
					ImageLabel.setIcon(img[t]);
					PlayButton.setText("||");
					try {
						m.play();
						if (ne) {
							tg = new TimerGUI();
							ne = false;
						}
						tg.resume();
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					tg.pause();
					PlayButton.setText("▶");
					m.pause();
				}
			}
		});
		PlayButton.setBounds(69, 81, 45, 23);
		mainJmusic.getContentPane().add(PlayButton);

		SingerLabel.setBounds(12, 55, 159, 15);
		mainJmusic.getContentPane().add(SingerLabel);

		JLabel lblNewLabel_2 = new JLabel("Chart");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel_2.setBounds(462, 120, 45, 19);
		mainJmusic.getContentPane().add(lblNewLabel_2);

		ChartArea = new JTextArea();
		ChartArea.setLineWrap(true);
		ChartArea.setBounds(198, 232, 529, 229);
		mainJmusic.getContentPane().add(ChartArea);

		JLabel label_1 = new JLabel("자막");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label_1.setBounds(462, 11, 45, 19);
		mainJmusic.getContentPane().add(label_1);

		JLabel lblJmusic = new JLabel("JMusic");
		lblJmusic.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblJmusic.setBounds(50, 9, 57, 19);
		mainJmusic.getContentPane().add(lblJmusic);

		JLabel lblNewLabel_3 = new JLabel("음악 제목으로 검색");
		lblNewLabel_3.setBounds(208, 181, 135, 19);
		mainJmusic.getContentPane().add(lblNewLabel_3);

		SerchField = new JTextField();
		SerchField.setBounds(198, 145, 232, 21);
		mainJmusic.getContentPane().add(SerchField);
		SerchField.setColumns(10);

		JButton SerchButton = new JButton("검색");
		SerchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChartArea.setText(MusicInfo.search(SerchField.getText()));
			}
		});
		SerchButton.setBounds(444, 145, 78, 23);
		mainJmusic.getContentPane().add(SerchButton);

		addMusicField = new JTextField();
		addMusicField.setBounds(741, 368, 211, 21);
		mainJmusic.getContentPane().add(addMusicField);
		addMusicField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("플레이 리스트 지정후 차트내");
		lblNewLabel_4.setBounds(741, 427, 211, 15);
		mainJmusic.getContentPane().add(lblNewLabel_4);

		JLabel label_2 = new JLabel("제목을 입력하여 리스트에 추가/삭제");
		label_2.setBounds(741, 446, 211, 15);
		mainJmusic.getContentPane().add(label_2);

		JButton MusicAddButton = new JButton("차트에 음악 추가하기");
		MusicAddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddMusicToChart(str[3]);
			}
		});
		MusicAddButton.setBounds(541, 177, 174, 23);
		mainJmusic.getContentPane().add(MusicAddButton);
		ChartArea.setText(MusicInfo.showChart());

		JButton MusicDeleteButton = new JButton("삭제");
		MusicDeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (MusicInfo.removemusic(Integer.valueOf(SerchField.getText()), str[3])) {
					JOptionPane.showMessageDialog(null, "삭제 완료", "" + "삭제 완료", JOptionPane.PLAIN_MESSAGE);
					ChartArea.setText(MusicInfo.showChart());
				} else {
					JOptionPane.showMessageDialog(null, "삭제 실패. 번호 및 권한을 확인해 주세요(게시자 또는 ad(관리자))", "" + "오류",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		MusicDeleteButton.setBounds(641, 146, 78, 23);
		mainJmusic.getContentPane().add(MusicDeleteButton);

		JLabel lblNewLabel_5 = new JLabel("번호");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_5.setBounds(201, 211, 35, 18);
		mainJmusic.getContentPane().add(lblNewLabel_5);

		JLabel label_3 = new JLabel("제목");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_3.setBounds(285, 211, 35, 18);
		mainJmusic.getContentPane().add(label_3);

		JLabel label_4 = new JLabel("가수");
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_4.setBounds(445, 211, 35, 18);
		mainJmusic.getContentPane().add(label_4);

		JLabel label_5 = new JLabel("게시자");
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_5.setBounds(583, 211, 69, 18);
		mainJmusic.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("번호를 입력하고 삭제");
		label_6.setBounds(376, 179, 150, 19);
		mainJmusic.getContentPane().add(label_6);

		JButton btnNewButton = new JButton("검색 초기화");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChartArea.setText(MusicInfo.showChart());
			}
		});
		btnNewButton.setBounds(528, 145, 109, 25);
		mainJmusic.getContentPane().add(btnNewButton);

		JButton DeleteMusicButton = new JButton("삭제");
		DeleteMusicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (PlayListInfo.deletemusic(str[3], listname, addMusicField.getText())) {
					JOptionPane.showMessageDialog(null, "삭제 완료", "" + "삭제 완료", JOptionPane.PLAIN_MESSAGE);
					playlistArea.setText(PlayListInfo.showmusic(str[3], listname));
					count = 0;
					m = MusicInfo.makePlayMusic(str[3], listname, count);
				} else {
					JOptionPane.showMessageDialog(null, "삭제 실패", "" + "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		DeleteMusicButton.setBounds(883, 399, 69, 23);
		mainJmusic.getContentPane().add(DeleteMusicButton);

		LyricsArea.setBounds(208, 33, 496, 76);
		mainJmusic.getContentPane().add(LyricsArea);

		JScrollPane scrollPane = new JScrollPane(LyricsArea);
		scrollPane.setBounds(208, 33, 517, 76);
		mainJmusic.getContentPane().add(scrollPane);

		JButton btnNewButton_2 = new JButton("X");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(136, 444, 50, 23);
		mainJmusic.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("확 대");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				size1 += 10;
				size2 += 5;
				mainJmusic.setBounds(100, 100, size1, size2);
			}
		});
		btnNewButton_3.setBounds(12, 410, 78, 23);
		mainJmusic.getContentPane().add(btnNewButton_3);

		JButton button = new JButton("축 소");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				size1 -= 10;
				size2 -= 5;
				mainJmusic.setBounds(100, 100, size1, size2);
			}
		});
		button.setBounds(108, 410, 78, 23);
		mainJmusic.getContentPane().add(button);

		JButton button_1 = new JButton("미니");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "미니 모드 on, 더블클릭 시 원래화면으로 돌아옵니다.", "" + "미니 모드",
						JOptionPane.PLAIN_MESSAGE);
				size1 = 196;
				size2 = 150;
				mainJmusic.setBounds(100, 100, size1, size2);
			}
		});
		button_1.setBounds(12, 444, 63, 23);
		mainJmusic.getContentPane().add(button_1);

		JLabel lblNewLabel_6 = new JLabel("더블클릭 시 기본창 사이즈 복귀");
		lblNewLabel_6.setBounds(12, 385, 174, 15);
		mainJmusic.getContentPane().add(lblNewLabel_6);
		
		JButton button_2 = new JButton("_");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainJmusic.setState(JFrame.ICONIFIED);
			}
		});
		button_2.setForeground(Color.BLACK);
		button_2.setBackground(Color.ORANGE);
		button_2.setBounds(80, 444, 50, 23);
		mainJmusic.getContentPane().add(button_2);

		mainJmusic.setVisible(true);
	}
}
