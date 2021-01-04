package PlayList;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import PlayMusic.Music;
import PlayMusic.MusicInfo;

public class PlayListInfo {
	public static void addlist(String id, String listname) {
	}

	public static void serialize(ArrayList<PlayList> l) {
		try {
			FileOutputStream fos = new FileOutputStream("PlayListInfo");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(l);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static ArrayList<PlayList> deserialize(ArrayList<PlayList> l) {
		try {
			FileInputStream fis = new FileInputStream("PlayListInfo");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			l = (ArrayList<PlayList>) ois.readObject();
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

	public static boolean addPlayList(String id, String listname) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		while (it.hasNext()) {
			PlayList p = it.next();
			if (p.Id.equals(id)) {
				if (p.Listname.equals(listname)) {
					return false;
				}
			}
		}
		PlayList p = new PlayList(id, listname);
		l.add(p);
		serialize(l);
		return true;
	}
	
	public static boolean removePlayList(String id, String listname) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		while (it.hasNext()) {
			PlayList p = it.next();
			if (p.Id.equals(id)) {
				if (p.Listname.equals(listname)) {
					l.remove(p);
					serialize(l);
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean changePlayList(String id, String listname, String change) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> it1 = l.iterator();
		while(it1.hasNext()){
			PlayList p = it1.next();
			if (p.Id.equals(id)) {
				if (p.Listname.equals(change)) {
					return false;
				}
			}
		}
		//change가 기존에 있던 list와 겹치지 않는다면,
		
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		while (it.hasNext()) {
			PlayList p = it.next();
			if (p.Id.equals(id)) {
				if (p.Listname.equals(listname)) {
					l.remove(p);//해당되는 것 삭제.
					p.Listname = change;//리스트 네임 변경
					l.add(p);//리스트네임 변경 후 add
					serialize(l);
					return true;
				}
			}
		}
		return false;
	}

	public static boolean addmusic(String id, String listname, String title) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		while (it.hasNext()) {
			PlayList p = it.next();
			if (p.Id.equals(id)) {
				if (p.Listname.equals(listname)) {
					if(MusicInfo.titleexist(title)) {//제목이 없는 내용이면 false를 반환합니다.
						p.Musiclist.add(title); // 따라서 제목으로 노래를 기억합니다.
						serialize(l);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String showlist(String id) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		ArrayList<String> arr = new ArrayList<String>();
		while (it.hasNext()) {
			PlayList p = it.next();
			if (p.Id.equals(id)) {
				arr.add(p.Listname);
			}
		}
		Collections.sort(arr);//이름순으로 리스트가 나옵니다.
		String re;
		if (arr.isEmpty()) {
			re = "리스트 없음";
		} else {
			re = arr.get(0);
			for (int i = 1; i < arr.size(); i++) {
				re = re + "\r\n" + arr.get(i);
			}
		}
		return re;
	}
	
	public static ArrayList<String> showmusicArray(String id, String listname){
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		ArrayList<String> arr = new ArrayList<String>();
		while (it.hasNext()) {
			PlayList p = it.next();
			if (p.Id.equals(id)) {
				if (p.Listname.equals(listname)) {
					arr = p.Musiclist;
				}
			}
		}
		return arr;
	}

	public static String showmusic(String id, String listname) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		ArrayList<String> arr = new ArrayList<String>();
		while (it.hasNext()) {
			PlayList p = it.next();
			if (p.Id.equals(id)) {
				if (p.Listname.equals(listname)) {
					arr = p.Musiclist;
				}
			}
		}
		String re;
		if (arr.isEmpty()) {
			re = "리스트에 음악 없음";
		} else {
			re = arr.get(0);
			for (int i = 1; i < arr.size(); i++) {
				re = re + "\r\n" + arr.get(i);
			}
		}
		return re;
	}
	
	public static boolean deletemusic(String id, String listname, String title) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		int index = 0;
		l = deserialize(l);
		Iterator<PlayList> it = l.iterator();
		while (it.hasNext()) {
			PlayList p = it.next();
			if(p.Id.equals(id)) {
				if(p.Listname.equals(listname)) {
					ArrayList <String> ml = p.Musiclist;
					Iterator<String> i = ml.iterator();
					while(i.hasNext()) {
						String str = i.next();
						if(str.equals(title)) {
							p.Musiclist.remove(index);
							serialize(l);
							return true;
						}
						index++;
					}
				}
			}
		}
		return false;
	}
	
	public static int shuffle(String id, String listname , String nowplaying) {
		ArrayList<PlayList> l = new ArrayList<PlayList>();
		l = deserialize(l);
		Iterator<PlayList> i = l.iterator();
		int count = 0;
		while(i.hasNext()) {
			PlayList p = i.next();
			if(p.Id.equals(id)) {
				if(p.Listname.equals(listname)) {
					if(nowplaying.equals("재생중인 곡 없음")) {
						Collections.shuffle(p.Musiclist);
						serialize(l);
						return 0;
					}
					Collections.shuffle(p.Musiclist);
					Iterator<String> iter = p.Musiclist.iterator();
					while(iter.hasNext()) {
						String ti = iter.next();
						if(ti.equals(nowplaying)) {
							serialize(l);
							return count;
						}
						count++;
					}
				}
			}
		}
		return -2;
	}

	public static void main(String[] args) {
		System.out.println(showlist("ad"));
		System.out.println();
		System.out.println(showmusic("ad", "list1"));
	}

}
