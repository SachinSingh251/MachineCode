package com.cleartrip.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UsersDTO {
	private Integer userId;
	private String userName;
	private Set<PostDTO> posts;
	private Set<UsersDTO> followedUsers;
	


	public Set<UsersDTO> getFollowedUsers() {
		return followedUsers;
	}


	public void setFollowedUsers(Set<UsersDTO> followedUsers) {
		this.followedUsers = followedUsers;
	}


	public Set<PostDTO> getPosts() {
		return posts;
	}


	public void setPosts(Set<PostDTO> posts) {
		this.posts = posts;
	}


	public UsersDTO(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.followedUsers = new HashSet<>();
        this.posts = new HashSet<>();
	}
	
	public void follow(UsersDTO user) {
        followedUsers.add(user);
    }

    public void unfollow(UsersDTO user) {
        followedUsers.remove(user);
    }
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
