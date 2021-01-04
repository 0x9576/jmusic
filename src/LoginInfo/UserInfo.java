package LoginInfo;

import SendEmail.SendEmail;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UserInfo {
	public static boolean AddInfo(String a, String b, String c, String d, String e, boolean pass) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> i = l.iterator();
		while (i.hasNext()) {
			User u = i.next();
			if (u.ID.equals(c))
				return false;
		}
		if (pass) {
		} else
			return false;
		User u = new User(a, b, c, d, e);
		l.add(u);
		serialize(l);
		return true;
	}

	public static int IDPW(String id, String pw) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> iter = l.iterator();
		int count = -1;
		int sq = -1;
		while (iter.hasNext()) {
			count++;
			User u = iter.next();
			if (u.ID.equals(id)) {
				if (u.PW.equals(pw)) {
					sq = count;
					return sq;
				}

			}
		}
		return sq;
	}

	public static User GetUser(int sq) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		User u = l.get(sq);
		return u;
	}

	public static void serialize(ArrayList<User> l) {
		try {
			FileOutputStream fos = new FileOutputStream("UserInfo");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(l);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static ArrayList<User> deserialize(ArrayList<User> l) {
		try {
			FileInputStream fis = new FileInputStream("UserInfo");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			l = (ArrayList<User>) ois.readObject();
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

	public static String findID(String name, String birth) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.Name.equals(name)) {
				if (u.Birth.equals(birth)) {
					return u.ID;
				}
			}
		}
		return "ID 찾기 실패";
	}

	public static boolean findPW(String id, String email) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.ID.equals(id)) {
				if (u.Email.equals(email)) {
					changePW(email);
					return true;
				}
			}
		}
		return false;
	}

	private static void changePW(String email) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.Email.equals(email)) {
				int pw = SendEmail.pwMail(email);
				u.PW = Integer.toString(pw);
			}
		}
		serialize(l);// 데이터 저장
	}

	public static void changeProf(String email, String birth, String name, String pw) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.Email.equals(email)) {
				u.Birth = birth;
				u.Name = name;
				u.PW = pw;
			}
		}
		serialize(l);
	}

	public static void withdrawal(String email) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();
		int count = -1;
		while (it.hasNext()) {
			count++;
			User u = it.next();
			if (u.Email.equals(email))
				break;
		}
		l.remove(count);
		serialize(l);
	}

	public static boolean changeOpen(String email) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.Email.equals(email)) {
				if (u.Open.equals("No")) {
					u.Open = "Yes";
					serialize(l);
					return true;
				} else {
					u.Open = "No";
					serialize(l);
					return false;
				}
			}
		}
		serialize(l);
		return false;
	}
	
	public static boolean idToOpen(String id) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(u.ID.equals(id)) {
				if(u.Open.equals("No")) {
					return false;
				}else {
					return true;
				}

			}
		}
		return true;
	}

	// main function for test

	public static void main(String[] args) {
		ArrayList<User> l = new ArrayList<User>();
		l = deserialize(l);
		Iterator<User> it = l.iterator();

		while (it.hasNext()) {
			User u = it.next();
			System.out.println(u.Birth + " " + u.Email + " " + u.ID + " " + u.Name + " " + u.PW + " " + u.Open);
		}
	}
}
