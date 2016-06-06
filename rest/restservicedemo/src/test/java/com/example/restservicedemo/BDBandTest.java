package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import com.example.restservicedemo.domain.Band;
import com.example.restservicedemo.service.BandManager;

public class BDBandTest {

	BandManager pm = new BandManager();

	@Test
	public void checkBandAdding() {
		pm.clearBands();
		
		Band b = new Band();
		b.setId(1);
		b.setName("Zespol1");
		b.setYoc(1990);
		assertEquals(1, pm.addBand(b));
		
	}
	
	@Test
	@Ignore
	public void checkDeleteAllBands() {
		pm.clearBands();
		
		Band b = new Band();
		b.setId(2);
		b.setName("Zespol1");
		b.setYoc(1990);
		assertEquals(1, pm.addBand(b));
		
		Band b1 = new Band();
		b1.setId(3);
		b1.setName("Zespol2");
		b1.setYoc(2000);
		assertEquals(1, pm.addBand(b1));
		
		List<Band> bands = pm.getAllBands();
		assertTrue(bands.size() > 0);
		pm.clearBands();
		//assertNull(bands);

	}
	
	@Test
	public void checkAllBands() {
		pm.clearBands();
		
		Band b1 = new Band();
		b1.setId(1);
		b1.setName("Zespol2");
		b1.setYoc(2002);
		
		Band b2 = new Band();
		b2.setId(2);
		b2.setName("Zespol2");
		b2.setYoc(2002);

		assertEquals(1, pm.addBand(b1));
		assertEquals(1, pm.addBand(b2));
		
	}



}
