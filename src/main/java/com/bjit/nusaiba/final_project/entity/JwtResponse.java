package com.bjit.nusaiba.final_project.entity;

import java.io.Serializable;
import java.util.ArrayList;



public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String accessToken;
	
	private final String refreshToken;
	
	private ArrayList<String> role;
	
	private String username;
	
	private int id;

	public ArrayList<String> getRole() {
		return role;
	}

	public void setRole(ArrayList<String> role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public JwtResponse(String access_token, String refresh_token, ArrayList<String> role, String username,int id) {
		super();
		this.accessToken = access_token;
		this.refreshToken = refresh_token;
		this.role = role;
		this.username = username;
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	


}