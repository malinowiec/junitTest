package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Song {
	
	private long id;
	private String title;	
	private String album;
	
	private Band owner;
	
	public Song(long id, String title, String album) {
		super();
		this.id = id;
		this.title = title;
		this.album = album;
	}
	
	public Song() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}


	public void setAlbum(String album) {
		this.album = album;
	}

	public Band getOwner() {
		return owner;
	}

	public void setOwner(Band owner) {
		this.owner = owner;
	}
	
}
