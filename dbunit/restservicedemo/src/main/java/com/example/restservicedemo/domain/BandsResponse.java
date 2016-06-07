package com.example.restservicedemo.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BandsResponse {
	
	private List<Band> band;

	public List<Band> getBand() {
		return band;
	}

	public void setBand(List<Band> band) {
		this.band = band;
	}

}
