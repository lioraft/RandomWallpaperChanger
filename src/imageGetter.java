import org.json.JSONException;
import org.json.JSONObject;

/* this is the class of the object that will get the image url from the api and save the image in temporary file*/
public class imageGetter {
    String url; // the url of the api
    String image_url_var; // the variable that will hold the image url

    // constructor - takes the url of the api, and the name of the variable in the json that holds the image url,
    // and set them in image getter object
    public imageGetter(String url, String image_url_var) {
        this.url = url;
        this.image_url_var = image_url_var;
    }

    // this method creates json object from the api url, gets the image url based on the variable name, and returns the url
    public String getRandomImage() throws JSONException {
        JSONObject json = new JSONObject(url); // create json object from the api url
        return json.getString(image_url_var); // return the image url
    }
}
