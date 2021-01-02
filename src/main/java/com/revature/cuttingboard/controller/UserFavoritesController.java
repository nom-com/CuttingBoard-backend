package com.revature.cuttingboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.annotation.AuthenticatedEndpoint;
import com.revature.cuttingboard.dto.UserFavoritesDTO;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.model.UserFavorites;
import com.revature.cuttingboard.service.UserFavoritesService;

/**
 * Controller class to handle HTTP requests dealing with user favorites.
 * @author nom.com
 * @since 1.0
 */

@RestController
@RequestMapping("/favorites")
public class UserFavoritesController {

	@Autowired
	HttpServletResponse resp;
	@Autowired
	UserFavoritesService userFavService;
	
	@GetMapping("")
	@AuthenticatedEndpoint
	public List<UserFavoritesDTO> getUserFavorites(SystemUser user) {
		try {
			resp.setStatus(200);
			return userFavService.getUserFavorites(user.getId());
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	@AuthenticatedEndpoint
	public void deleteUserFavorites(@PathVariable("id") int id, SystemUser user) {
		try {
			resp.setStatus(204);
			userFavService.deleteUserFavorites(id);
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
		}
	}
	
	@PostMapping("/{id}")
	@AuthenticatedEndpoint
	public UserFavoritesDTO insertUserFavorites(@PathVariable("id") int id, UserFavorites userFavorites, SystemUser user) {
		try {
			resp.setStatus(201);
			return userFavService.insertUserFavorites(id, userFavorites, user);
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
}
