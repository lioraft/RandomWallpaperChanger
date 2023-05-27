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
    wallpaperSetter instance; // the instance of the wallpaper setter object
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
    public wallpaperSetter getInstance() {
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
    public void setNewWallpaper(String apiName) throws JSONException, IOException {
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
        // delete the temporary file
        temp.delete();
    }
}
