package zad;

import java.util.ArrayList;
import java.util.List;






public class SongManager implements ISongManager{
	List<Song> songList = new ArrayList<Song>();

	@Override
	public void add(Song song) {
		songList.add(song);		
	}

	@Override
	public void remove(Song song) {
		songList.remove(song);
	}

	@Override
	public List<Song> getAll() {
		return songList;
	}
	



}
