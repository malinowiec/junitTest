package zad;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SongTest {

	
	
		ISongManager manager = new SongManager();
		Song song1 = new Song("song1", "BandName1");
		Song song2 = new Song("song2", "BandName2");
		Song song3 = new Song("song3", "BandName3");

		/**
		 * 
		 */
		@Test
		public void checkAdd(){
			assertEquals(0, manager.getAll().size());
			
			manager.add(song1);
			assertEquals(1, manager.getAll().size());
			assertEquals("song1", manager.getAll().get(0).getTitle());
			assertEquals("BandName1", manager.getAll().get(0).getBand());
			System.out.println("Title1:" + manager.getAll().get(0).getTitle());
			System.out.println("Band2:" + manager.getAll().get(0).getBand());
			
			manager.add(song2);
			assertEquals(2, manager.getAll().size());
			assertEquals("song2", manager.getAll().get(1).getTitle());
			assertEquals("BandName2", manager.getAll().get(1).getBand());
			System.out.println("Title3:" + manager.getAll().get(1).getTitle());
			System.out.println("Band2:" + manager.getAll().get(1).getBand());
			
			manager.add(song3);
			assertEquals(3, manager.getAll().size());
			assertEquals("song3", manager.getAll().get(2).getTitle());
			assertEquals("BandName3", manager.getAll().get(2).getBand());
			System.out.println("Title3:" + manager.getAll().get(2).getTitle());
			System.out.println("Band3:" + manager.getAll().get(2).getBand());		
			
		}
		
		
		@Test
		public void checkGetAll(){
			assertTrue(manager.getAll().isEmpty());
			
			manager.add(song1);
			assertEquals(1, manager.getAll().size());
			assertNotNull(manager.getAll());

			manager.add(song2);
			assertEquals(2, manager.getAll().size());
			assertNotNull(manager.getAll());
			
			manager.add(song3);
			assertEquals(3, manager.getAll().size());
			assertNotNull(manager.getAll());
			
			assertFalse(manager.getAll().isEmpty());

//			for(int i=0; i<manager.songList.size(); i++) {
//				System.out.println("Piosenka " + i + "" + manager.songList.get(i));
//			}
			
		}
		
		@Test
		public void checkRemove(){
			assertEquals(0, manager.getAll().size());
			
			manager.add(song1);
			manager.add(song2);
			manager.add(song3);
			
			assertEquals(3, manager.getAll().size());
			
			manager.remove(song3);
			assertEquals(2, manager.getAll().size());
			manager.remove(song2);
			assertEquals(1, manager.getAll().size());
			manager.remove(song1);
			assertEquals(0, manager.getAll().size());
			
		//	System.out.println("usuwanie dzia�a!");
		}
}
