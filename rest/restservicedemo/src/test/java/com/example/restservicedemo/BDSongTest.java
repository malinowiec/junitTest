package com.example.restservicedemo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Song;
import com.example.restservicedemo.domain.Band;
import com.example.restservicedemo.service.BandManager;

public class BDSongTest {

	BandManager pm = new BandManager();

	@Test
	public void checkSongAdding() {
		pm.clearSongs();
		Song s = new Song();
		s.setId(1);
		s.setTitle("nanana");
		s.setAlbum("album1");
		assertEquals(1, pm.addSong(s));
	}
	
	@Test
	public void checkDeleteAllSongs() {

		List<Song> songs = pm.getAllSongs();
		assertTrue(songs.size() > 0);
		pm.clearSongs();
	//	assertTrue(songs.size() == 0);
			
	}
	
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

		assertEquals(1, pm.addSong(s1));
		assertEquals(1, pm.addSong(s2));

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

		assertEquals(1, pm.addBand(b1));
		assertEquals(1, pm.addBand(b2));

		List<Band> bands = pm.getAllBands();

		assertTrue(bands.size() > 1);

		Band owner = bands.get(1);
		
		pm.giveSong(songToGive, owner);
		
		Song rSong = pm.getSongWithOwner(songToGive);
		
		assertEquals(owner.getName(), rSong.getOwner().getName());

	}



}
