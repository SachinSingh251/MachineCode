package com.MainServiceClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cleartrip.dto.PostDTO;
import com.cleartrip.dto.UsersDTO;
import com.custom.exception.PostNotFoundException;
import com.custom.exception.UserNotFoundException;

public class SocialMediaServiceClass {
	private static final String REGISTRATION ="Registered!!";
	private static final String USER_EXISTS ="User already exists";
	private static final String USER_NOT_FOUND ="User not found ";
	private static final String UPLOAD_SUCCESS ="Upload Successful ";
	private Map<Integer, UsersDTO> users;
	public SocialMediaServiceClass() {
        users = new HashMap<>();
    }
	
	//user registration by unique id and username
	public String RegisterUser(int userId, String userName) {	
		 if (users.containsKey(userId)) {
	            return USER_EXISTS;
	        }
		 UsersDTO newUser = new UsersDTO(userId, userName);
	        users.put(userId, newUser);
	        return userName + REGISTRATION;
	}
	
	//users uploads post by user id
	public String UploadPost(int userId, String post) {
		UsersDTO user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException(USER_NOT_FOUND +" with ID: " + userId);
        }
        PostDTO newPost = new PostDTO(post, user);
        user.getPosts().add(newPost);
        return UPLOAD_SUCCESS + "with post id: " + newPost.getPostId();
	}
	
	//Follow or Unfollow a User
	public String interactWithUser(String interactionType, int userId1, int userId2) throws UserNotFoundException {
        UsersDTO user1 = users.get(userId1);
        UsersDTO user2 = users.get(userId2);
        if (user1 == null || user2 == null) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        switch (interactionType.toUpperCase()) {
            case "FOLLOW":
                user1.follow(user2);
                return "Followed " + user2.getUserName() + "!!";
            case "UNFOLLOW":
                user1.unfollow(user2);
                return "Unfollowed " + user2.getUserName() + "!!";
            default:
                return "Invalid Action!";
        }
    }
	
	//Show feed for a Users
	
	public String showFeed(int userId) throws UserNotFoundException {
        UsersDTO user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException(USER_NOT_FOUND +" with ID: " + userId);
        }

        List<PostDTO> followedPosts = user.getFollowedUsers().stream()
                .flatMap(followedUser -> followedUser.getPosts().stream())
                .collect(Collectors.toList());

        List<PostDTO> nonFollowedPosts = user.getPosts().stream()
                .filter(post -> !user.getFollowedUsers().contains(post.getUser()))
                .collect(Collectors.toList());

        List<PostDTO> allPosts = followedPosts;
        allPosts.addAll(nonFollowedPosts);
        allPosts = allPosts.stream()
                .sorted((p1, p2) -> p2.getPostTime().compareTo(p1.getPostTime()))
                .collect(Collectors.toList());

        StringBuilder feed = new StringBuilder();
        for (PostDTO post : allPosts) {
            feed.append("UserName - ").append(post.getUser().getUserName()).append("\n")
                .append("# of Likes - ").append(post.getLikes()).append("\n")
                .append("# of Dislikes - ").append(post.getDislikes()).append("\n")
                .append("Post - ").append(post.getContent()).append("\n")
                .append("Post time - ").append(post.getRelativeTime()).append("\n\n");
        }

        return feed.toString();
    }
	
	//Like or Dislike Post
	public String interactWithPost(String action, int userId, String postId) throws UserNotFoundException, PostNotFoundException {
        UsersDTO user = users.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        PostDTO post = user.getPosts().stream()
                .filter(p -> p.getPostId().equals(postId))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("Post not found with ID: " + postId));

        switch (action.toUpperCase()) {
            case "LIKE":
                post.like();
                return "Post Liked!!";
            case "DISLIKE":
                post.dislike();
                return "Post Disliked!!";
            default:
                return "Invalid Action!";
        }
    }



}
