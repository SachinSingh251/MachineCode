package com.cleartrip.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

	public class PostDTO {
	    private static int postIdCounter = 1;
	    private String postId;
	    private String content;
	    private UsersDTO user;
	    private LocalDateTime postTime;
	    private int likes;
	    private int dislikes;

	    public PostDTO(String content, UsersDTO user) {
	        this.postId = String.format("%03d", postIdCounter++);
	        this.content = content;
	        this.user = user;
	        this.postTime = LocalDateTime.now();
	        this.likes = 0;
	        this.dislikes = 0;
	    }

	    public String getPostId() {
	        return postId;
	    }

	    public String getContent() {
	        return content;
	    }

	    public UsersDTO getUser() {
	        return user;
	    }

	    public LocalDateTime getPostTime() {
	        return postTime;
	    }

	    public int getLikes() {
	        return likes;
	    }

	    public int getDislikes() {
	        return dislikes;
	    }

	    public void like() {
	        likes++;
	    }

	    public void dislike() {
	        dislikes++;
	    }
        
	    //Display Relative Time
	    public String getRelativeTime() {
	    	Duration duration = Duration.between(postTime, LocalDateTime.now());
	        long seconds = duration.getSeconds();
	        
	        if (seconds < 60) {
	            return seconds + "s ago";  // Seconds
	        } else if (seconds < 3600) {
	            return (seconds / 60) + "m ago";  // Minutes
	        } else if (seconds < 86400) {
	            return (seconds / 3600) + "hr ago";  // Hours
	        } else {
	            return (seconds / 86400) + "d ago";  // Days
	        }
	    }
	}
