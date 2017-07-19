package org.abhi.spring.model;

public class Movie implements Comparable<Movie> {

	private String name, plot, votes, year, rating, metascore;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getVotes() {
		return votes;
	}

	public void setVotes(String votes) {
		this.votes = votes;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String object) {
		this.year = object;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}

	@Override
	public int compareTo(Movie o) {
		return Float.parseFloat(this.getRating()) <= Float.parseFloat(o.getRating()) ? 1 : -1;
	}

}