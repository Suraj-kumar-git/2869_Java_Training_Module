package com.hexaware.mainmod;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.IVirtualArtGallery;
import com.hexaware.dao.VirtualArtGalleryImpl;
import com.hexaware.entity.Artist;
import com.hexaware.entity.Artwork;
import com.hexaware.entity.Gallery;
import com.hexaware.entity.User;
import com.hexaware.exception.ArtWorkAlreadyExist;
import com.hexaware.exception.ArtWorkNotFoundException;
import com.hexaware.exception.UserNotFoundException;

public class MainModule {

	private static IVirtualArtGallery artGalleryService = new VirtualArtGalleryImpl();
	public static void showMainMenu() {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println("\n\nPlease enter your choice: [1-7]\n\n");
			System.out.println("[1] Login");
			System.out.println("[2] Register");
			System.out.println("[3] Browse Artworks");
			System.out.println("[4] Search Artists");
			System.out.println("[5] View Galleries");
			System.out.println("[6] User Profile");
			System.out.println("[7] Logout");
			System.out.println("[8] Exit");

			int choice = sc.nextInt();
			performOperation(choice);
			System.out.println("Do you want to perform another operation? (y/n)");
			String response = sc.next();
			if (response.equalsIgnoreCase("n")) {
				exit = true;
				System.err.println("Exiting Application...");
				System.exit(0);
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("==============================\n");
		System.out.println("Virtual Art Gallery System\n");
		System.out.println("==============================\n\n");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Browse Artworks");
		System.out.println("4. Search Artists");
		System.out.println("5. View Galleries");
		System.out.println("6. User Profile");
		System.out.println("7. Logout");

		showMainMenu();
	}

	private static void performOperation(int choice) {
		Scanner scanner = new Scanner(System.in);
		switch (choice) {
		case 1:
			System.out.println("Enter username: ");
			String username = scanner.next();
			System.out.println("Enter password: ");
			String password = scanner.next();

			try {
				if (artGalleryService.login(username, password)) {
					System.out.println("Login successful!");
				} else {
					System.out.println("Invalid username or password. Please try again.");
				}
			} catch (UserNotFoundException e) {
				e.getMessage();
			}
			break;
		case 2:
			System.out.println("Enter new username: ");
			String newUsername = scanner.next();
			System.out.println("Enter new password: ");
			String newPassword = scanner.next();
			if (artGalleryService.register(newUsername, newPassword)) {
				System.out.println("Registration successful!");
			} else {
				System.out.println("Registration failed. Please try again.");
			}
			break;
		case 3:
			viewArtWorkMenu();
			break;
		case 4:
			System.out.println("Enter artist name or keyword to search: ");
			String searchKeyword = scanner.next();
			List<Artist> artists = artGalleryService.searchArtists(searchKeyword);

			if(artists.size()>0) {
				for (Artist artist : artists) {
					System.out.println("Artist ID: " + artist.getArtistId());
					System.out.println("Name: " + artist.getName());
					System.out.println("Biography: " + artist.getBiography());
					// Display other fields...
					System.out.println("-------------------------------------------");
				}
			}else {
				System.err.println("No artist found with your input...! Try searching for another keyword");
			}
			break;
		case 5:
			System.out.println("Viewing Galleries...\n");

			List<Gallery> galleries = artGalleryService.getAllGalleries();

			for (Gallery gallery : galleries) {
				System.out.println("Gallery ID: " + gallery.getGalleryId());
				System.out.println("Name: " + gallery.getName());
				System.out.println("Description: " + gallery.getDesc());
				// Display other fields...
				System.out.println("-------------------------------------------");
			}
			break;
		case 6:
			System.out.println("Enter user ID: ");
			int userId = scanner.nextInt();

			User userProfile = artGalleryService.getUserProfile(userId);

			if (userProfile != null) {
				System.out.println("User ID: " + userProfile.getUserId());
				System.out.println("Username: " + userProfile.getUsername());
				System.out.println("Email: " + userProfile.getEmail());
				System.out.println("First Name: " + userProfile.getFirstName());
				System.out.println("Last Name: " + userProfile.getLastName());
				System.out.println("Date of Birth: " + userProfile.getDob());
				// Display other fields...
			} else {
				System.out.println("User not found.");
			}
			break;
		case 7:
			artGalleryService.logout();
			break;
		default:
			System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			break;
		}
	}

	public static void viewArtWorkMenu() {
		boolean exit = false;
		while (!exit) {
			System.out.println("==============================\n");
			System.out.println("You can perform the below operations:\n");
			System.out.println("==============================\n\n");
			System.out.println("1. Add Artwork");
			System.out.println("2. Update Artwork");
			System.out.println("3. Remove Artworks");
			System.out.println("4. Search Artwork by Id");
			System.out.println("5. Search Artwork by keyword");
			System.out.println("6. Add Artwork to favorite");
			System.out.println("7. Remove Artwork from favorite");
			System.out.println("83. Get your favorite Artwork");
			System.out.println("9. Move back to the main menu\n\n\n");
			System.out.println("Please Enter your choice.");
			Scanner sc = new Scanner(System.in);

			int choice = sc.nextInt();
			performArtworkOperation(choice);
			System.out.println("Do you want to perform another operation? (y/n)");
			String response = sc.next();
			if (response.equalsIgnoreCase("n")) {
				exit = true;
			}
		}
	}

	private static void performArtworkOperation(int choice) {
		Scanner sc = new Scanner(System.in);
		switch (choice) {
		case 1:
			System.out.println("Enter Artwork details: ");
			System.out.println("Enter Artwork ID:");
			int id = sc.nextInt();
			System.out.println("Enter Artwork title:");
			sc.nextLine();
			String title = sc.nextLine();
			System.out.println("Enter Artwork description:");
			String desc = sc.nextLine();
			System.out.println("Enter Artwork creation Date(YYYY-MM-DD):");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(sc.next(), formatter);
			System.out.println("Enter Artwork Medium:");
			sc.nextLine();
			String medium = sc.nextLine();
			System.out.println("Enter Artwork imageUrl:");
			String url = sc.nextLine();
			Artwork a1 = new Artwork(id, title, desc, date, medium, url);
			try {
				if (artGalleryService.addArtwork(a1)) {
					System.out.println("Artwork added successfully...");
				} else {
					System.out.println("You need to login first!");
				}
			} catch (ArtWorkAlreadyExist e1) {
				System.out.println(e1.getMessage());
			}

			break;
		case 2:
			System.out.println("Enter updated Artwork details: ");
			System.out.println("Enter Artwork ID:");
			int uid = sc.nextInt();
			System.out.println("Enter Artwork title:");
			sc.nextLine();
			String utitle = sc.nextLine();
			System.out.println("Enter Artwork description:");
			String udesc = sc.nextLine();
			System.out.println("Enter Artwork creation Date(YYYY-MM-DD):");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate udate = LocalDate.parse(sc.next(), formatter1);
			System.out.println("Enter Artwork Medium:");
			sc.nextLine();
			String umedium = sc.nextLine();
			System.out.println("Enter Artwork imageUrl:");
			String uurl = sc.nextLine();
			Artwork a2 = new Artwork(uid, utitle, udesc, udate, umedium, uurl);
			if (artGalleryService.updateArtwork(a2)) {
				System.out.println("Update successful!");
			} else {
				System.out.println("Login is required!");
			}
			break;
		case 3:
			System.out.println("Enter Artwork ID to remove corresponding artwork:");
			int rid = sc.nextInt();
			if (artGalleryService.removeArtwork(rid)) {
				System.out.println("Deleted Artwork!");
			} else {
				System.out.println("Login is required!");
			}
			break;
		case 4:
			System.out.println("Enter Artwork ID to see:");
			int sid = sc.nextInt();
			Artwork sa = null;
			try {
				sa = artGalleryService.getArtworkById(sid);
				System.out.println(sa);
			} catch (ArtWorkNotFoundException e) {
				System.out.println(e.getMessage());
//				e.printStackTrace();
			}

			break;
		case 5:
			System.out.println("Enter keyword to search artworks:");
			String keyword = sc.nextLine();
			List<Artwork> artworks = artGalleryService.searchArtworks(keyword);
			System.out.println(artworks);
			break;
		case 6:
			System.out.println("Menu to add artwork to your favorite list.");
			System.out.println("Enter your userId:");
			int userId = sc.nextInt();
			System.out.println("Enter your favorite artworkId: ");
			int artworkId = sc.nextInt();
			if(artGalleryService.addArtworkToFavorite(userId,artworkId)) {
				System.out.println("Your Artwork with id "+artworkId+" is added as favorite Artwork.");
			}else {
				System.err.println("Ooopps..! Are U logged In..? LooksLike something went wrong. Check you have entered the correct userid and artWorkId");
			}
			break;
		case 7:
			System.out.println("Menu to remove artwork from your favorite list.");
			System.out.println("Enter your userId:");
			int ruserId = sc.nextInt();
			System.out.println("Enter your artworkId to remove from favorite list: ");
			int rartworkId = sc.nextInt();
			if(artGalleryService.removeArtworkFromFavorite(ruserId,rartworkId)) {
				System.out.println("Your Artwork with id "+rartworkId+" is removed from favorite Artwork.");
			}else {
				System.err.println("Ooopps..! Are U logged In..? LooksLike something went wrong. Check you have entered the correct userid and artWorkId");
			}
			break;
		case 8:
			System.out.println("Enter your userId to see all your favorite artworks:");
			int fid = sc.nextInt();
			List<Artwork> favArtworks = artGalleryService.getUserFavoriteArtworks(fid);
			if(favArtworks.size()==0) {
				System.out.println("You have not any favorite artworks.");
			}else {
				System.out.println("Here is the list of your favorite artworks:");
			}
			for(Artwork a: favArtworks) {
				System.out.println(a);
			}
			break;
		case 9:
			showMainMenu();
			break;
		default:
			System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			break;
		}

	}
}