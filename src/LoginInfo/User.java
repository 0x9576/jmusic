package LoginInfo;

import java.io.Serializable;

public class User implements Serializable {
	String Name;
	String Birth;
	String ID;
	String PW;
	String Email;
	String Open;
	String [] PlayList;
	User(String a, String b, String c, String d, String e) {
		Name = a;
		Birth = b;
		ID = c;
		PW = d;
		Email = e;
		Open = "No";
		PlayList = null;
	}
	
	public String [] getProfile() {
		String [] arr = {Birth,Email,Name,ID,PW,Open};
		return arr;
	}
	
	public String [] getPlayList() {
		return PlayList;
	}

}
