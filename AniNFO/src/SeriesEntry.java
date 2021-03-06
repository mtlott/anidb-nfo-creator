import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.JOptionPane;

/***
 * SeriesEntry - Contains data on an individual series entry. The known list of these is stored
 * in the SeriesList.
 * 
 * Copyright (C) 2011  Chris Workman
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *   This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class SeriesEntry implements Serializable {
	
	/*** CONSTANTS ***/
	private static final long serialVersionUID = -3161937020365620069L;			// Serial ID
	private static final String 	PIC_URL		= "http://img7.anidb.net/pics/anime/";
	
	/*** CLASS DATA MEMBERS ***/
	private int						aid;			// AniDB ID
	private String 					title;			// The series title
	private String					plot;			// Series plot
	private LinkedList<String>		genre;			// Series Genre List
	private int						genreCount;		// Count of genre entries
	private float					rating;			// Series rating
	private EpisodeList				episodes;		// Listing of episodes sin the series
	private String					picname;		// thumbnail file name
	
	public SeriesEntry(String nTitle, int nAid) {
		aid = nAid;								// Set aid
		title = nTitle;							// Set the title
		plot = null;							// null plot
		genre = new LinkedList<String>();		// empty list of genres
		genreCount = 0;							// Initialize count of genre entries
		rating = 0.0f;							// Set rating to 0 by default
		episodes = new EpisodeList();			// Generate an empty episode list
	} // end SeriesEntry(title)
	
	public int getAID() {
		return aid;
	} // end getAID
	
	public String getTitle() {
		return title;
	} // end getTitle
	
	public String getPlot() {
		return plot;
	} // end getPlot
	
	public String getThumb() {
		return picname;
	}
	
	public String getGenre() {
		String result = null;
		
		// If we do not have an empty list
		// Create a comma separated string of all entries
		if (!genre.isEmpty()) {
			result = genre.getFirst();
			for (int i = 1; i < genreCount; i++)
				result = result + ", " + genre.get(i);
		} // end if
		
		// Return the genre list (null if empty
		return result;
	} // end getGenre
	
	public float getRating() {
		return rating;
	}
	
	public void setRating(int new_rating) {
		float temp = new_rating/100.0f;
		
		if (temp < 0.0f)
			temp = 0.0f;
		
		if (temp > 10.0f)
			temp = 10.0f;
		
		rating = temp;
	}
	
	public void setThumb(String pic) {
		picname = PIC_URL + pic;
	}
	
	public void setPlot(String nPlot) {
		plot = nPlot;
	} // end setPlot
	
	public void addGenre(String nGenre) {
		// Validate that genre hasn't already been added
		if (genre.indexOf(nGenre) == -1) {
			genre.add(nGenre);
			genreCount++;
		} // end if
	} // end addGenre

	public EpisodeList getEpisodes() {
		return episodes;
	}
	
	public void addEpisode(EpisodeEntry nEpisode) {
		int result = episodes.addEpisode(nEpisode);
		
		if (result == EpisodeList.NO_EID)
			JOptionPane.showMessageDialog(null, "Unable to add the episode entry for " + title + " Episode " + nEpisode.getEpno(), 
					"Error - Failed to add Episode", JOptionPane.ERROR_MESSAGE);
	}
} // end class SeriesEntry
