package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		String sql = "SELECT * FROM film WHERE id = ?";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, filmId);
			ResultSet filmResult = statement.executeQuery();

			if (filmResult.next()) {
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				short releaseYear = filmResult.getShort("release_year");
				int langId = filmResult.getInt("language_id");
				int rentDur = filmResult.getInt("rental_duration");
				double rate = filmResult.getDouble("rental_rate");
				int length = filmResult.getInt("length");
				double repCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String features = filmResult.getString("special_features");
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating, features, findActorsByFilmId(filmId));

			}

			filmResult.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		String user = "student";
		String pass = "student";
		String sql = "SELECT * FROM actor WHERE id = ?";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet actorResult = statement.executeQuery();

			if (actorResult.next()) {
				actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
						actorResult.getString("last_name"));
			}

			actorResult.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmID) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT DISTINCT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor fa on a.id = fa.actor_id JOIN film f on fa.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmID);
			ResultSet actorResult = stmt.executeQuery();

			while (actorResult.next()) {
				actors.add(new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
						actorResult.getString("last_name")));
			}

			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<Film>();

		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, "%" + keyword + "%");
			statement.setString(2, "%" + keyword + "%");

			ResultSet filmResult = statement.executeQuery();

			while (filmResult.next()) {
				int filmId = filmResult.getInt("id");
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				short releaseYear = filmResult.getShort("release_year");
				int langId = filmResult.getInt("language_id");
				int rentDur = filmResult.getInt("rental_duration");
				double rate = filmResult.getDouble("rental_rate");
				int length = filmResult.getInt("length");
				double repCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String features = filmResult.getString("special_features");
				films.add(new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating, features, findActorsByFilmId(filmId)));
			}

			filmResult.close();
			statement.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Film>();
		}

		return films;
	}

}
