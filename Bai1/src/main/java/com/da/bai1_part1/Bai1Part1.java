package com.da.bai1_part1;

import com.da.utils.ApiUtils;
import com.da.utils.FileUtils;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bai1Part1 {
    static Logger logger = Logger.getLogger(Bai1Part1.class.getName());

    /**
     * Retrieves all albums from the specified URL  and create a text file to store them.
     * @param filePath the path where the file will be created
     * @param url the URL to send the GET request to
     * @param user_agent the user agent to be used for the request
     * @param fileName the name of the file to be created
     * @throws Exception if an I/O error occurs
     */
    public static void getAllAlbums(String filePath,String url, String user_agent, String fileName) throws Exception {

        //get connection
        HttpURLConnection con = ApiUtils.getConnection(url);
        if (con == null) {
            return;
        }

        //set and use get request method
        ApiUtils.getRequest(con, user_agent);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            //retrieve content stream
            StringBuffer response = FileUtils.readStream(con);
            String content = response.toString();
            if (content.isEmpty()) {
                logger.log(Level.WARNING,"No content found");
                return;
            }

            //create file and write content to file
            File file = FileUtils.createFile(filePath,fileName);
            if (file != null) {
                FileUtils.writeAlbumsToFile(file, content);
                FileUtils.showAllAlbums(content);
            }
        } else {
            logger.log(Level.SEVERE,"GET request did not work.");
        }
    }

    /**
     * Retrieves all albums from the specified URL  and create a text file to store them.
     * @param filePath the path where the file will be created
     * @param url the URL to send the GET request to
     * @param user_agent the user agent to be used for the request
     * @param fileName the name of the file to be created
     *
     * @param userId the user id to search for
     * @param id the id to search for
     * @param title the title to search for
     *
     * @throws Exception if an I/O error occurs
     */
    public static void getOneAlbum(String filePath,String fileName, String url, String user_agent,String userId, String id, String title) throws Exception {
        //get connection with url that has search parameters
        HttpURLConnection con = ApiUtils.getConnection(ApiUtils.getSearchURL(url, userId, id, title));

        //perform same operations as getAllAlbums
        if (con == null) {
            return;
        }
        ApiUtils.getRequest(con, user_agent);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            StringBuffer response = FileUtils.readStream(con);
            String content = response.toString();
            if (content.isEmpty()) {
                logger.log(Level.WARNING,"No content found");
                return;
            }
            File file = FileUtils.createFile(filePath,fileName);
            if (file != null) {
                FileUtils.writeAlbumsToFile(file, content);
                FileUtils.showAllAlbums(content);
            }
        } else {
            logger.log(Level.SEVERE,"GET request did not work.");
        }
    }
}
