package com.hexaware.dao;

import java.util.List;

import com.hexaware.entity.Artist;
import com.hexaware.entity.Artwork;
import com.hexaware.entity.Gallery;
import com.hexaware.entity.User;
import com.hexaware.exception.UserNotFoundException;

public interface IVirtualArtGallery {
	boolean addArtwork(Artwork artwork);
	boolean updateArtwork(Artwork artwork);
	boolean removeArtwork(int artworkId);
	Artwork getArtworkById(int artworkId);
	List<Artwork> searchArtworks(String keyword);
	boolean addArtworkToFavorite(int userId, int artworkId);
	boolean removeArtworkFromFavorite(int userId, int artworkId);
	List<Artwork> getUserFavoriteArtworks(int userId);
	boolean login(String username, String password) throws UserNotFoundException;
	boolean register(String username, String password);
	List<Artist> searchArtists(String keyword);
	List<Gallery> getAllGalleries();
	User getUserProfile(int userId);
	void logout();
}
