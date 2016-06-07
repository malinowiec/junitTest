package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Band {
	
	private long id;
	
	private String name;
	private int yoc;
	
	public Band() {
	}

	public Band(String name, int yoc) {
		this.name = name;
		this.yoc = yoc;
	}
	
	public Band(long id, String name, int yoc) {
		super();
		this.id = id;
		this.name = name;
		this.yoc = yoc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYoc() {
		return yoc;
	}

	public void setYoc(int yoc) {
		this.yoc = yoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + yoc;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Band other = (Band) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id != other.id)
			return false;
		if (yoc != other.yoc)
			return false;
		return true;
	}
	
}
