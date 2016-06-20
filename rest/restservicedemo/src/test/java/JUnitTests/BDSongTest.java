package JUnitTests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Song;
import com.example.restservicedemo.domain.Band;
import com.example.restservicedemo.service.BandManager;

public class BDSongTest {

	BandManager pm = new BandManager();

	
//	@Before
//	public void deleteDB(){
//		pm.clearSongs();
//		pm.clearBands();
//	}
	

	
	//Song1
		@Test
		
		public void checkSongAddingWithoutId(){
			Song s = new Song();
			s.setTitle("SongBezId");
			s.setAlbum("Album");
			assertEquals(1,pm.addSong(s));
		}
		
		//Song2
		@Test
		
		public void checkSongAddingWithId() {
			pm.clearSongs();
			Song s = new Song();
			s.setId(1);
			s.setTitle("nanana");
			s.setAlbum("album1");
			assertEquals(1, pm.addSongWithId(s));
		}
		
		//Song3
		@Test
		
		public void checkDeleteAllSongs(){
			pm.clearSongs();
			
			Song s1 = new Song();
			s1.setId(1);
			s1.setTitle("nanana1");
			s1.setAlbum("album1");
			pm.addSongWithId(s1);
			
			Song s2 = new Song();
			s2.setId(2);
			s2.setTitle("nanana2");
			s2.setAlbum("album2");
			pm.addSongWithId(s2);
			
			List<Song> songs;
			songs = pm.getAllSongs();
			assertEquals(2,songs.size());
			pm.clearSongs();
			songs = pm.getAllSongs();
			assertEquals(0,songs.size());	
		}
		
		//Song4
		@Test
		
		public void checkAllSongs() {
			pm.clearSongs();
			
			Song s1 = new Song();
			s1.setId(1);
			s1.setTitle("TitleSong");
			s1.setAlbum("Albuuuum");
			
			Song s2 = new Song();
			s2.setId(2);
			s2.setTitle("TitleSong2");
			s2.setAlbum("Albuuum2");

			assertEquals(1, pm.addSongWithId(s1));
			assertEquals(1, pm.addSongWithId(s2));
			
		}	
		
		//Song5
		@Test
		
		public void getSongById(){
			pm.clearSongs();
			Song s = new Song();
			s.setId(2);
			s.setTitle("TytulTytulowy");
			s.setAlbum("2001");
			pm.addSongWithId(s);
			
			Song first = pm.getSong(s.getId());
			assertEquals(2,first.getId());
			assertEquals("TytulTytulowy",first.getTitle());
		}
		
	//Song6
	@Test
	
	public void checkGiveSongToBand() {
		pm.clearSongs();
		pm.clearBands();
		
		Song s1 = new Song();
		s1.setId(1);
		s1.setTitle("nanana");
		s1.setAlbum("album1");
		
		
		Song s2 = new Song();
		s2.setId(2);
		s2.setTitle("babababa");
		s2.setAlbum("Album2");
	
		
		assertEquals(1, pm.addSongWithId(s1));
		assertEquals(1, pm.addSongWithId(s2));

		List<Song> songs = pm.getAllSongs();

		assertTrue(songs.size() > 1);

		Song songToGive = songs.get(1);

		Band b1 = new Band();
		b1.setId(1);
		b1.setName("Zespol1");
		b1.setYoc(1978);
		

		Band b2 = new Band();
		b2.setId(2);
		b2.setName("Zespol2");
		b2.setYoc(2002);
		
		
		assertEquals(1, pm.addBandWithId(b1));
		assertEquals(1, pm.addBandWithId(b2));

		List<Band> bands = pm.getAllBands();

		assertTrue(bands.size() > 1);

		Band owner = bands.get(1);
		
		pm.giveSong(songToGive, owner);
		
		Song rSong = pm.getSongWithOwner(songToGive);
		
		assertEquals(owner.getName(), rSong.getOwner().getName());

	}

	
	//Song7
	@Test
	
	public void getSongWithOwner(){
		
		pm.clearSongs();
		pm.clearBands();
		
		Song s1 = new Song();
		s1.setId(1);
		s1.setTitle("nanana");
		s1.setAlbum("album1");
		pm.addSongWithId(s1);
		
		Band b2 = new Band();
		b2.setId(2);
		b2.setName("Zespol2");
		b2.setYoc(2002);
		pm.addBandWithId(b2);
		
		List<Band> bands = pm.getAllBands();
		List<Song> songs = pm.getAllSongs();
		assertEquals(1,pm.giveSong(songs.get(0), bands.get(0)));
		Song s = pm.getSongWithOwner(songs.get(0));
		assertEquals(bands.get(0),s.getOwner());
	}

}
