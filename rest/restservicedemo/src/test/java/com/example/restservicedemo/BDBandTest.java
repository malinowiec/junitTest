package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Band;
import com.example.restservicedemo.domain.Song;
import com.example.restservicedemo.service.BandManager;

public class BDBandTest {

	BandManager pm = new BandManager();

	
	@Before
	public void deleteDB(){
		pm.clearSongs();
		pm.clearBands();
	}
	
	//Band1
		@Test
		public void checkBandAddingWithoutId(){
			pm.clearBands();
			Band b = new Band();
			b.setName("BandBezId");
			b.setYoc(1992);
			assertEquals(1,pm.addBand(b));
		}
		
	//Band2
	@Test
	public void checkBandAddingWithId() {
		pm.clearBands();
		Band b = new Band();
		b.setId(1);
		b.setName("Zespol1");
		b.setYoc(1990);
		assertEquals(1, pm.addBandWithId(b));
	}
	

	
	//Band3
	@Test
	public void checkDeleteAllBands(){
		pm.clearBands();
		Band b1 = new Band();
		b1.setId(1);
		b1.setName("Zespol1");
		b1.setYoc(1990);
		assertEquals(1, pm.addBandWithId(b1));
		
		Band b2 = new Band();
		b2.setId(2);
		b2.setName("Zespol1");
		b2.setYoc(1990);
		assertEquals(1, pm.addBandWithId(b2));
		
		List<Band> bands;
		bands = pm.getAllBands();
		assertEquals(2,bands.size());
		pm.clearBands();
		bands = pm.getAllBands();
		assertEquals(0,bands.size());	
	}
	
	//Band4
	@Test
	public void checkAllBands() {
		pm.clearBands();
		
		Band b1 = new Band();
		b1.setId(1);
		b1.setName("Zespol1");
		b1.setYoc(2002);
		
		Band b2 = new Band();
		b2.setId(2);
		b2.setName("Zespol2");
		b2.setYoc(1990);

		assertEquals(1, pm.addBandWithId(b1));
		assertEquals(1, pm.addBandWithId(b2));
		
	}

	//Band5
	@Test
	public void getBandById(){
		pm.clearBands();
		Band b = new Band();
		b.setId(5);
		b.setName("ZespolZespolowy");
		b.setYoc(2001);
		pm.addBandWithId(b);
		
		Band b2 = new Band();
		b2.setId(3);
		b2.setName("ZespolZespolowy2");
		b2.setYoc(2011);
		pm.addBandWithId(b2);
		
		Band first = pm.getBand(b.getId());
		assertEquals(5,first.getId());
		assertEquals("ZespolZespolowy",first.getName());
	}

	//Band6
	@Test
	public void getAllBandsWithSongs(){
		pm.clearBands();
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
		
		Song s3 = new Song();
		s3.setId(3);
		s3.setTitle("nanan3");
		s3.setAlbum("album3");
		pm.addSongWithId(s3);
		
		Band b1 = new Band();
		b1.setId(1);
		b1.setName("Zespol1");
		b1.setYoc(2002);
		pm.addBandWithId(b1);
		
		Band b2 = new Band();
		b2.setId(2);
		b2.setName("Zespol2");
		b2.setYoc(1990);
		pm.addBandWithId(b2);
		
		Band b3 = new Band();
		b3.setId(3);
		b3.setName("Zespol3");
		b3.setYoc(2015);
		pm.addBandWithId(b3);
		
		List<Band> bands = pm.getAllBands();
		List<Song> songs = pm.getAllSongs();
		
		assertEquals(1,pm.giveSong(songs.get(0), bands.get(0)));
		assertEquals(1,pm.giveSong(songs.get(1), bands.get(0)));
		assertEquals(1,pm.giveSong(songs.get(2), bands.get(1)));
		
		Map<Band,List<Song>> m = pm.getBandWithSong();
		
		assertEquals(2,m.size());
		assertEquals(bands.get(0),m.get(bands.get(0)).get(0).getOwner());
		assertEquals(bands.get(0),m.get(bands.get(0)).get(1).getOwner());
		assertEquals(bands.get(1),m.get(bands.get(1)).get(0).getOwner());
	}
	
}
