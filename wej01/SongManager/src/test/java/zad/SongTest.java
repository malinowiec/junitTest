package zad;

import static org.junit.Assert.*;

import org.junit.Test;

public class SongTest {
	//under test
		SongManager manager = new SongManager();
		Song song1 = new Song("song1");
		Song song2 = new Song("song2");


		
		@Test
		public void checkAdd(){
			assertEquals(0, manager.songList.size());
			
			manager.add(song1);
			assertEquals(2, manager.songList.size());
			assertEquals("Song2", manager.songList.get(0).title);
			
			manager.add(song2);
			assertEquals(2, manager.songList.size());
			assertEquals("Song2", manager.songList.get(1).title);

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

		}
}
