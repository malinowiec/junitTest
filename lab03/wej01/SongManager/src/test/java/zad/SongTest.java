package zad;

import static org.junit.Assert.*;

import org.junit.Test;

public class SongTest {
	//under test
		SongManager manager = new SongManager();
		Song song1 = new Song("song1", "Band1");
		Song song2 = new Song("song2", "Band2");
		Song song3 = new Song("song3", "Band3");

		@Test
		public void checkAdd(){
			assertEquals(0, manager.songList.size());
			
			manager.add(song1);
			assertEquals(1, manager.songList.size());
			assertEquals("song1", manager.songList.get(0).title);
			assertEquals("Band1", manager.songList.get(0).band);
			
			manager.add(song2);
			assertEquals(2, manager.songList.size());
			assertEquals("song2", manager.songList.get(1).title);
			assertEquals("Band2", manager.songList.get(1).band);

			manager.add(song3);
			assertEquals(3, manager.songList.size());
			assertEquals("song3", manager.songList.get(2).title);
			assertEquals("Band3", manager.songList.get(2).band);
		}
		
		@Test
		public void checkGetAll(){
			assertTrue(manager.getAll().isEmpty());
			
			manager.add(song1);
			assertEquals(1, manager.songList.size());
			assertNotNull(manager.getAll());

			manager.add(song2);
			assertEquals(2, manager.songList.size());
			assertNotNull(manager.getAll());
			
			manager.add(song3);
			assertEquals(3, manager.songList.size());
			assertNotNull(manager.getAll());
			
			assertFalse(manager.getAll().isEmpty());

		}
}
