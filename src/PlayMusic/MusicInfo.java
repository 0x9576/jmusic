package PlayMusic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GUI.Main;
import LoginInfo.UserInfo;
import PlayList.PlayList;
import PlayList.PlayListInfo;



public class MusicInfo {
	static String TITLE;

	public static void serialize(ArrayList<Music> l) {
		try {
			FileOutputStream fos = new FileOutputStream("MusicInfo");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(l);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static ArrayList<Music> deserialize(ArrayList<Music> l) {
		try {
			FileInputStream fis = new FileInputStream("MusicInfo");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			l = (ArrayList<Music>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c1) {
			System.out.println("Class not found");
			c1.printStackTrace();
		}
		return l;
	}

	// 파일은 반드시 Music 폴더에 있어야 합니다.
	public static boolean storeMusic(String filename, String title, String singer, String uploader ,String lyrics) {
		ArrayList<Music> l = new ArrayList<Music>();
		l = deserialize(l);
		try {
			FileInputStream fis = new FileInputStream("Music\\" + filename + ".mp3");
		} catch (FileNotFoundException e) {
			return false;
		}
		if(filename.equals(""))
			return false;
		if(title.equals(""))
			return false;
		if(singer.equals(""))
			return false;
		Iterator<Music> it = l.iterator();
		while (it.hasNext()) {
			Music m = it.next();
			if(m.Filename.equals(filename)){
				return false;
			}
			
			if(m.Title.equals(title)) {
				return false;
			}
		}
		Music m = new Music(filename, title, singer,uploader, lyrics);
		l.add(m);
		serialize(l);
		return true;
	}
	
	public static String showChart() {
		ArrayList<Music> l = new ArrayList<Music>();
		l = deserialize(l);
		Iterator<Music> it = l.iterator();
		ArrayList<String> arr = new ArrayList<String> ();
		while (it.hasNext()) {
			Music m = it.next();
			arr.add(m.Title);
			arr.add(m.Singer);
			String loader = null;
			if(UserInfo.idToOpen(m.Uploader)) {
				loader = m.Uploader;
			}else {
				loader = "**";
			}
			arr.add(loader);
		}
		String re;
		int count =0;
		int inte = 2;
		if (arr.isEmpty()) {
			re = "차트에 음악 없음";
		} else {
			re = "1" + "          ";
			re = re + arr.get(0);
			for (int i = 1; i < arr.size(); i++) {
				count++;
				if(count<3) {
					re = re +"\t"+ arr.get(i);
				}
				if(count>=3) {
					re = re+"\r\n"+Integer.toString(inte)+"          "+arr.get(i);
					inte++;
					count = 0;
				}
			}
		}
		return re;
	}
	
	public static boolean removemusic(int enter, String id) {
		if(enter <1)
			return false;
		else {
			ArrayList<Music> l = new ArrayList<Music>();
			l = deserialize(l);
			if(id.equals("ad")) {
				l.remove(enter-1);
				serialize(l);
				return true;
			}
			int count =0;
			Iterator<Music> it = l.iterator();
			while (it.hasNext()) {
				Music m = it.next();
				if(count ==enter-1){
					if(m.Uploader.equals(id)) {
						l.remove(enter-1);
						serialize(l);
						return true;	
					}
				}
				count++;
			}
			return false;
		}
	}
	
	public static String search (String title) {
		if(title.equals("")) {
			return "해당되는 음악이 없습니다.";
		}
		ArrayList<Music> l = new ArrayList<Music>();
		l = deserialize(l);
		Iterator<Music> it = l.iterator();
		ArrayList<String> arr = new ArrayList<String> ();
		int a =0;
		while (it.hasNext()) {
			a++;
			Music m = it.next();
			if(m.Title.contains(title)) {
				arr.add(Integer.toString(a));
				arr.add(m.Title);
				arr.add(m.Singer);
				arr.add(m.Uploader);
			}
		}
		String re = "";
		int count =0;
		if (arr.isEmpty()) {
			re = "해당되는 음악이 없습니다.";
		} else {
			for (int i = 0; i < arr.size(); i++) {
				count++;
				if(count ==1) {
					re = re + arr.get(i) + "          ";
				}else if(count ==2) {
					re = re + arr.get(i);
				}else if(count <5) {
					re = re + "\t" + arr.get(i);
				}
				else {
					re =re+"\r\n"+arr.get(i)+"          ";
					count = 0;
				}
			}
		}
		return re;
		
	}
	
	public static boolean titleexist (String title){
		ArrayList<Music> l = new ArrayList<Music>();
		l = deserialize(l);
		Iterator<Music> it = l.iterator();
		while(it.hasNext()) {
			Music m = it.next();
			if(m.Title.equals(title))
				return true;
		}
		return false;
	}
	
	public static PlayMusic musicplay(String title) {
		String filename = titleToFile(title);
		try {
			FileInputStream input = new FileInputStream("Music\\"+filename+".mp3");
			PlayMusic player = new PlayMusic(input);
			return player;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static int titlehave () {
		ArrayList<Music> l = new ArrayList<Music>();
		l = deserialize(l);
		Iterator<Music> it = l.iterator();
		while (it.hasNext()) {
			Music m = it.next();
			if(m.Title.equals(TITLE)){
				Main.LyricsArea.setText(m.Lyrics);
				Main.SingerLabel.setText(m.Singer);
				Main.TitleLabel.setText(m.Title);
				String filename = m.Filename;
				return Integer.valueOf(filename);
			}
		}
		return 0;
	}
	
	
	
	public static PlayMusic makePlayMusic (String id, String listname, int count) {
		if(listname.equals(null)) {
		}
		else {
			ArrayList <String> arr = PlayListInfo.showmusicArray(id, listname);
			if(Main.count>arr.size()-1)
				Main.count = 0;
			if(Main.count <0)
				Main.count = arr.size()-1;
			String musictitle = arr.get(Main.count);
			TITLE = musictitle;
			return musicplay(musictitle);
		}
		return null;
	}
	
	public static String titleToFile(String title) {
		ArrayList<Music> l = new ArrayList<Music>();
		l = deserialize(l);
		Iterator<Music> it = l.iterator();
		while (it.hasNext()) {
			Music m = it.next();
			if(m.Title.equals(title)){
				return m.Filename;
			}
		}
		return null;
	}
	
	
	public static void print() {
		ArrayList<Music> l = new ArrayList<Music>();
		l = deserialize(l);
		Iterator<Music> it = l.iterator();
		while (it.hasNext()) {
			Music m = it.next();
			System.out.println(m.Filename + " " + m.Title);
		}
	}
	
	
	
	public static void main (String [] args) {
		print();
		System.out.println(showChart());
		musicplay("Coffee");
	}

}
