import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONException;

import javax.imageio.ImageIO;

/* this is the class of the object that performs the background change. it is a singleton class */
public class wallpaperSetter {
    Desktop myDesktop; // the desktop object that will be used to set the wallpaper
    static wallpaperSetter instance; // the instance of the wallpaper setter object
    HashMap<String, imageGetter> imageGetters; // the hash map that will hold the image getters


    // constructor that initializes the wallpaper setter object
    private wallpaperSetter() {
        // check if desktop is supported on this platform
        if (Desktop.isDesktopSupported()) {
            myDesktop = Desktop.getDesktop(); // if supported, get the desktop object
        }
        else {
            throw new UnsupportedOperationException("Desktop is not supported on this platform!"); // if not supported, throw exception
        }
        imageGetters = new HashMap<>();
    }


    // this method returns instance of the wallpaper setter object
    public static wallpaperSetter getInstance() {
        if (instance == null) {
            instance = new wallpaperSetter();
        }
        return instance;
    }

    // function that takes the api name, it's url and the image url variable name, and creates new image getter object for it
    public String addNewAPI(String apiName, String url, String image_url_var) {
        if (imageGetters.containsKey(apiName)) {
            throw new IllegalArgumentException("API name already exists!");
        }
        imageGetters.put(apiName, new imageGetter(url, image_url_var));
        return "API added successfully!";
    }

    // function that takes the api name, and removes it from the hash map
    public String removeAPI(String apiName) {
        if (!imageGetters.containsKey(apiName)) {
            throw new IllegalArgumentException("API name does not exist!");
        }
        imageGetters.remove(apiName);
        return "API removed successfully!";
    }

    // function that takes the api name, and sets the wallpaper to a random image from the api
    public String setNewWallpaper(String apiName) throws JSONException, IOException {
        // check if api name exists
        if (!imageGetters.containsKey(apiName)) {
            throw new IllegalArgumentException("API name does not exist!");
        }
        // get new image url from the api
        String strImageURL = imageGetters.get(apiName).getRandomImage();
        // convert to URL object
        URL imageURL = new URL(strImageURL);
        // create buffered image object from url
        BufferedImage image = ImageIO.read(imageURL);
        // create temporary file
        File temp = File.createTempFile("newWallpaper", ".jpg");
        // save image to temporary file
        ImageIO.write(image, "jpg", temp);
        // set wallpaper to temporary file
        User32.INSTANCE.SystemParametersInfo(0x0014, 0, temp.getAbsolutePath(), 0x0001);
        return "Wallpaper changed successfully!"; // return success message
    }

    // this is a function that loads the default APIs to the hash map. it is called when the program starts.
    // if you want to disable this, just comment out the call to this function in src\GUI.java
    public String LoadDefaultAPIs() {
        // ad defaults
        this.addNewAPI("Dogs", "https://dog.ceo/api/breeds/image/random", "message");
        this.addNewAPI("Ducks", "https://random-d.uk/api/random", "url");
        this.addNewAPI("Foxes", "https://randomfox.ca/floof/", "image");
        this.addNewAPI("NASA's Astronomy Picture of the Day", "https://api.nasa.gov/planetary/apod?api_key=Ixhm5579hQXhLFZfIqeZWwFxQFcjPrWavJ1oyXjJ", "hdurl");
        this.addNewAPI("Coffee", "https://coffee.alexflipnote.dev/random.json", "file");
        return "Loaded default APIs successfully!"; // return success message
    }

    // this is a function that returns string array of the api names
    public String[] getAPIs() {
        return imageGetters.keySet().toArray(new String[0]);
    }
}
