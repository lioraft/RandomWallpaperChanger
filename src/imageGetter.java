import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
    public String getRandomImage() throws JSONException, IOException {
        URL urlObj = new URL(url); // create url object from the api url
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection(); // make connection to the api
        conn.setRequestMethod("GET"); // set request method to GET
        // check if response code is 200 (OK)
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Connection to API failed: HTTP error code - " + conn.getResponseCode()); // if not, throw exception
        }
        else {
            BufferedReader readerFromStream = new BufferedReader(new InputStreamReader(conn.getInputStream())); // create buffered reader object from the connection input stream
            StringBuilder jsonString = new StringBuilder(); // create string builder object to read the response
            String line;
            // read the response
            while ((line = readerFromStream.readLine()) != null) {
                jsonString.append(line);
            }
            // close the connection and the reader
            readerFromStream.close();
            // create json object from the response
            JSONObject json = new JSONObject(jsonString.toString());
            // return the image url
            return json.getString(image_url_var);
        }
    }
}
