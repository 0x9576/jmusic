package PlayMusic;

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.text.View;

import java.util.Scanner;

import javazoom.jl.*;
import javazoom.jl.decoder.Equalizer.EQFunction;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;

public class PlayMusic{
	public double volume = 0.0;
	private final static int NOTSTARTED = 0;
	private final static int PLAYING = 1;
	private final static int PAUSED = 2;
	private final static int FINISHED = 3;
	boolean isloop = true;

	// the player actually doing all the work
	private final Player player;

	// locking object used to communicate with player thread
	private final Object playerLock = new Object();

	// status variable what player thread is doing/supposed to do
	private int playerStatus = NOTSTARTED;

	public PlayMusic(final InputStream inputStream) throws JavaLayerException {
		this.player = new Player(inputStream);
	}

	public PlayMusic(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
		this.player = new Player(inputStream, audioDevice);
	}

	/**
	 * Starts playback (resumes if paused)
	 */

	public void play() throws JavaLayerException {
		synchronized (playerLock) {
			switch (playerStatus) {
			case NOTSTARTED:
				final Runnable r = new Runnable() {
					public void run() {
						playInternal();
					}
				};
				final Thread t = new Thread(r);
				t.setDaemon(true);
				t.setPriority(Thread.MAX_PRIORITY);
				playerStatus = PLAYING;
				t.start();
				break;
			case PAUSED:
				resume();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Pauses playback. Returns true if new state is PAUSED.
	 */
	public boolean pause() {
		synchronized (playerLock) {
			if (playerStatus == PLAYING) {
				playerStatus = PAUSED;
			}
			return playerStatus == PAUSED;
		}
	}

	/**
	 * Resumes playback. Returns true if the new state is PLAYING.
	 */
	public boolean resume() {
		synchronized (playerLock) {
			if (playerStatus == PAUSED) {
				playerStatus = PLAYING;
				playerLock.notifyAll();
			}
			return playerStatus == PLAYING;
		}
	}

	/**
	 * Stops playback. If not playing, does nothing
	 */
	public void stop() {
		synchronized (playerLock) {
			playerStatus = FINISHED;
			playerLock.notifyAll();
		}
	}

	private void playInternal() {
		while (playerStatus != FINISHED) {
			try {
				if (!player.play(1)) {
					break;
				}
			} catch (final JavaLayerException e) {
				break;
			}
			// check if paused or terminated
			synchronized (playerLock) {
				while (playerStatus == PAUSED) {
					try {
						playerLock.wait();
					} catch (final InterruptedException e) {
						// terminate player
						break;
					}
				}
			}
		}
		close();
	}

	/**
	 * Closes the player, regardless of current state.
	 */
	public void close() {
		synchronized (playerLock) {
			playerStatus = FINISHED;
		}
		try {
			player.close();
		} catch (final Exception e) {
			// ignore, we are terminating anyway
		}
	}

	public static void down() throws Exception {
		DataLine.Info info = null;
		Clip clip = (Clip) AudioSystem.getLine(info);

		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		double gain = .5D; // number between 0 and 1 (loudest)
		float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		gainControl.setValue(dB);

		BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
	}

	// demo how to use
	public static void main(String[] arg) {
		Scanner s = new Scanner(System.in);

		try {
			FileInputStream input = new FileInputStream("Music\\1.mp3");
			PlayMusic player = new PlayMusic(input);

			while (true) {
				int enter = s.nextInt();
				if (enter == 0)
					break;
				if (enter == 1)
					player.play();
				if (enter == 2)
					player.pause();
				if (enter == 3)
					player.stop();
				if (enter == 4) {
					player.playInternal();
				}
			}
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}