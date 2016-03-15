package zad;

import java.util.ArrayList;
import java.util.List;

public class SongManager {
	
	ArrayList<Song> songList = new ArrayList<Song>();
	
	public void add(Song song){
		songList.add(song);
	}
	
	public List<Song> getAll(){
		return songList;
	}
}
