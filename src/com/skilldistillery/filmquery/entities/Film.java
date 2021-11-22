package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

import javax.swing.text.AsyncBoxView;

public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageID;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String features;
	private List<Actor> actors;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Film(int id, String title, String description, int releaseYear, int languageID, int rentalDuration,
			double rentalRate, int length, double replacementCost, String rating, String features, List<Actor> cast) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageID = languageID;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.features = features;
		this.actors = cast;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, description, id, languageID, length, rating, releaseYear, rentalDuration,
				rentalRate, replacementCost, title);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description) && id == other.id
				&& languageID == other.languageID && length == other.length && rating == other.rating
				&& releaseYear == other.releaseYear && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(title, other.title);
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Title: " + title);
		sb.append("\nDescription: " + description);
		sb.append("\nYear released: " + releaseYear);
		sb.append("\nRating: " + rating);
		sb.append("\nLanguage: " + Language.values()[languageID].toString());
		
		sb.append("\nCast: ");
		for (Actor actor : actors) {
			sb.append(actor.toString() + ", ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append("\n");
		
		return sb.toString();
	}
	
	enum Language {
		ENGLISH("English"), ITALIAN("Italian"), JAPANESE("Japanese"), MANDARIN("Mandarin"), FRENCH("French"), GERMAN("German");
		
		public String name;
		
		private Language(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
}
