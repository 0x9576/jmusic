package PlayMusic;

import java.io.Serializable;

public class Music implements Serializable {
	String Filename;
	String Title;
	String Singer;
	String Uploader;
	String Lyrics;

	Music(String f, String t, String s, String u,String l) {
		Filename = f;
		Title = t;
		Singer = s;
		Lyrics = l;
		Uploader = u;
	}
}
