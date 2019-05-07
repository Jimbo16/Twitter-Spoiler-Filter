package PageHandlers;
import static spark.Spark.*;


import PageHandlers.FilteredFeed;
import PageHandlers.HiddenFeed;
import PageHandlers.WelcomePage;

/**
 * This is the main runner for the program. It will consolidate the back-end methods and push 
 * it to the front end web app.
 * @authors Jhon Valencia, Melissa Amaya, James Botwina
 *
 */

/**
 * Launches the app.
 * Handles all routes for app
 * sample comment
 * @author mamaya
 *
 */
public class Main {
    public static void main(String[] args) {
    	port(8080);
    	
//    	BackEndHandler backEndHandler = new BackEndHandler();
    	WelcomePage welcomePage = new WelcomePage();
    	FullFeedDisplay fullFeed = new FullFeedDisplay();
    	FilteredFeed filteredFeed = new FilteredFeed();
    	HiddenFeed hiddenFeed = new HiddenFeed();
      	
    	/**
    	 * Input: String
    	 * Output: string
    	 * Practice get request to the local page, first argument is the page to make the request to
    	 * Second argument is what is being placed on the page.
    	 */ 
        
    	get("/", welcomePage); //user interface
    	
    	get("/filteredFeed", (req, res) -> {	
    		String keyword = req.queryParams("keyword");
    		String keywordDisplay = "<div><span style=\"font-weight:600;\">We are filtering for the following word(s): </span>" + keyword  + "</div><br/>" ;
    		return "<div class = \"container\">" + filteredFeed.getHeader() + keywordDisplay + filteredFeed.getHomeButton() + "<div><br>" + filteredFeed.getHiddenFeedButton() + "</div>" + filteredFeed.getBootstrapJS() + "</div>" + filteredFeed.getTweetInfo(keyword);
    	});
    	
    	get("/fullFeed", (req, res) -> {
//    		FullFeedDisplay fullFeed = new FullFeedDisplay();
    		String tweets = fullFeed.PullTweets();
    		return "<div class = \"container\">" + fullFeed.getHeader() + fullFeed.getHomeButton() + "<div><br>"+fullFeed.getRefreshButton() + "</div>" + fullFeed.getBootstrapJS() + "</div>" + tweets;
    	});
    	
//    	get("/fullFeed", fullFeed); //unfiltered
    	get("/hiddenFeed", hiddenFeed); //what I missed
    }
}