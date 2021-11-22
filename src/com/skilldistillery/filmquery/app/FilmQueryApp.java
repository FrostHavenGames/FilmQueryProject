package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		//app.test();
		app.launch();
	}

	private void test() {
		List<Film> films = db.findFilmByKeyword("data");
		System.out.println(films);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		String answer = "";

		do {

			System.out.println("1. Search for film by ID\n2. Look for film by keyword\n3. Exit");

			answer = input.nextLine();

			switch (answer) {
			case "1":
				findFilmFromID(input);
				break;
				
			case "2":
				findFilmFromKeyword(input);
				break;
				
			case "exit":
				System.out.println("Bye.");
				break;

			default:
				System.out.println("Please enter a valid input");
				break;
			}

		} while (!answer.equals("3"));
	}

	private void findFilmFromID(Scanner input) {
		System.out.println("What's the ID for the film?");
		int id = input.nextInt();
		input.nextLine();
		
		Film film = db.findFilmById(id);
		
		if (film == null) {
			System.out.println("That film ID doesn't exist.\n");
		} else {
			System.out.println(film);
		}
	}
	
	private void findFilmFromKeyword(Scanner input) {
		System.out.println("What's the keyword for the film?");
		String keyword = input.nextLine();
		
		List<Film> films = db.findFilmByKeyword(keyword);
		
		if (films.size() <= 0) {
			System.out.println("No films found using that keyword.\n");
		} else {
			for (Film film : films) {
				System.out.println(film);
			}
		}
	}
	
}
