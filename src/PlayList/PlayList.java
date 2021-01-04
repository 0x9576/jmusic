package PlayList;

import java.io.Serializable;
import java.util.ArrayList;

//PlayList는 하나의 리스트를 나타냅니다. id정보를 포함하고 있어, 다른 회원의 목록이 중복됨을 방지합니다.
public class PlayList implements Serializable {
	public String Id;
	public String Listname;
	public ArrayList <String> Musiclist;
	
	PlayList(String id, String listname){
		this.Id =id;
		this.Listname =listname;
		this.Musiclist = new ArrayList<String> ();
	}//makelist
	
	

}
