package zad;







import java.util.List;

public interface ISongManager {
	
	public void add(Song song);
	public void remove(Song song);
	public List<Song> getAll();
}
