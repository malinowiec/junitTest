package zad;

import java.util.ArrayList;

public class Song2 {

	
	 public static void main(String[] args) {
	        ArrayList<Song> songs = new ArrayList<>();
	
	public Song2(String name){
		
	}
	
	public void addSong(Song2 song){
		songs.add(song);
	}
	
	public void getAll(){
		for (int i=0; i<Song2.size; i++)  
		{  
		 System.out.println(songs.get(i).getName);  
		} 
	}
	

	
}
