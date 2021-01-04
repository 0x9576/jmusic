package PlayMusic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread{
	private Player player;
	private boolean isloop; // 무한 반복인가 아닌가?
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public MusicPlayer(String name, boolean isloop) {
		try {
			this.isloop = isloop;
			fis = new FileInputStream("Music\\" + name + ".mp3");
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
		}
	}

	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		isloop = false;
		player.close();
		this.interrupt();
	}
	
	public void pause() {
		try {
			this.sleep(131);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);

			} while (isloop);

		} catch (Exception e) {
			//
		}
	}
	
	
	public static void main (String [] args) {
		Scanner s = new Scanner (System.in);
		MusicPlayer m = new MusicPlayer("1", true);
		m.start();
		s.next();
		m.pause();
	}

}
