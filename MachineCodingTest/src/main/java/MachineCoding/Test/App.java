package MachineCoding.Test;

import com.MainServiceClass.SocialMediaServiceClass;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	SocialMediaServiceClass service = new SocialMediaServiceClass();
    	System.out.println(service.RegisterUser(1, "Akash"));
        System.out.println(service.RegisterUser(2, "Hemant"));

        System.out.println(service.UploadPost(1, "This is my first post."));
        System.out.println(service.UploadPost(1, "I work at Flipkart as an SDE1."));
        System.out.println(service.UploadPost(2, "I too worked at Flipkart as an SDE1."));

        System.out.println(service.interactWithUser("FOLLOW", 2, 1));

        System.out.println(service.showFeed(2));

        System.out.println(service.interactWithPost("LIKE", 1, "001"));
        System.out.println(service.interactWithPost("DISLIKE", 2, "003"));
        System.out.println(service.showFeed(2));
        
    }
}
