package com.da.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiUtils {
    private static final Logger logger = Logger.getLogger(ApiUtils.class.getName());


    /**
     * @param url
     * @param userId parameter to search for
     * @param id parameter to search for
     * @param title parameter to search for
     * Returns a URL with search parameters
     */
    public static String getSearchURL(String url, String userId, String id, String title) {
        userId = userId == null ? "" : ("&userId=" + userId).trim();
        id = id == null ? "" : ("&id=" + id).trim();
        title = title == null ? "" : ("&title=" + title).trim();
        return url = url + "?" + userId + id + title;
    }

    /**
     * @param url the URL to connect to
     * Returns a connection to the specified URL
     * @throws IOException if an I/O error occurs
     */
    public static HttpURLConnection getConnection(String url) {
        try {
            URL obj = new URL(url);
            return (HttpURLConnection) obj.openConnection();
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Error during connection");
        }
        return null;
    }

    /**
     * Shows the response state for the specified connection
     * @param con the connection to show the response state for
     */
    public static void showResponseState(HttpURLConnection con) throws Exception {
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        Map<String, List<String>> headers = con.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Sends a GET request using the specified connection and user agent
     * @param con the connection to use
     * @param user_agent the user agent to use
     * @throws Exception if an I/O error occurs
     */
    public static void getRequest(HttpURLConnection con, String user_agent) throws Exception {
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", user_agent);
        showResponseState(con);
    }
}
