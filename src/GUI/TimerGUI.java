package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

class TimerThread extends Thread {
	static boolean play = true;
	private JLabel timerLabel; // 타이머 값이 출력되는 레이블
	public static int n = 0; // 타이머 카운트 값

	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	@Override
	public void run() {
		while (play) {
			timerLabel.setText(Integer.toString(n));
			try {
				Thread.sleep(1000); // 1초동안 잠을 잔다.
			} catch (InterruptedException e) {
				return;
			}
			n++;
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			return;
		}
		run();
	}

	public void pause() {
		play = false;
	}

	public void resu() {
		play = true;
	}
	
	public void nex() {
		n = 0;
	}
}

public class TimerGUI extends JFrame {
	static TimerThread th = null;
	static JLabel timerLabel = null;

	public TimerGUI() {
		setTitle("timer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		// 타이머 값을 출력할 레이블 생성
		timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		th = new TimerThread(timerLabel);
		
		JLabel lblNewLabel = new JLabel("초");
		getContentPane().add(lblNewLabel);
		setSize(250, 171);
		setVisible(true);
		th.start(); // 타이머 스레드의 실행을 시작하게 한다
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		new TimerGUI();
		while(true) {
			String a = s.next();
			if(a.equals("a")) {
				resume();
			}else if(a.equals("b")){
				pause();
			}else {
				next();
			}
		}

	}

	public static void pause() {
		th.pause();
	}

	public static void resume() {
		th.resu();
	}
	
	public static void next() {
		th.nex();
	}
}
