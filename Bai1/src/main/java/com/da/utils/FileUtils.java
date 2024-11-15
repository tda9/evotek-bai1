package com.da.utils;

import com.da.model.Album;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {
    static ObjectMapper mapper = new ObjectMapper();
    static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    /**
     * Shows all albums from the specified response
     * @param response the response to show the albums from
     */
    public static void showAllAlbums(String response) throws Exception {
        Album[] todo = mapper.readValue(response, Album[].class);
        for (Album album : todo) {
            System.out.println(album.toString());
        }
    }

    /**
     * Creates a file with the specified name
     * @param filePath the path where the file will be created
     * @param name the name of the file to be created
     * @return the created file
     * @throws IOException if an I/O error occurs
     */
    public static File createFile(String filePath, String name) {
        try {
            File myObj = new File(filePath + name);
            if (myObj.createNewFile()) {
                logger.log(Level.INFO,"File created: " + myObj.getName());
                return myObj;
            } else {
                logger.log(Level.WARNING,"File already exists.");
                return null;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE,"An error occurred during create file");
        }
        return null;
    }

    /**
     * Deletes the specified file
     * @param file the file to delete
     */
    public static void deleteFile(File file) {
        if (file.delete()) {
            logger.log(Level.INFO,"Deleted file: " + file.getName() + " successfully.");
        } else {
            System.out.println("Failed to delete the file.");
            logger.log(Level.SEVERE,"Failed to delete the file.");
        }
    }

    /**
     * Writes the specified response to the specified file
     * @param file the file to write to
     * @param response the response to write
     */
    public static void writeAlbumsToFile(File file,String response) throws Exception {
        Album[] todo = mapper.readValue(response, Album[].class);
        for (Album album : todo) {
            appendToFile(file, album.toString());
        }
    }

    /**
     * Appends the specified content to the specified file
     * @param file the file to append to
     * @param content the content to append
     */
    private static void appendToFile(File file, String content) {
        try {
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE,"An error occurred during write " + content);
        }
    }

    /**
     * Reads the content of the specified connection
     * @param con the connection to read the content from
     * @return the content of the connection
     * @throws IOException if an I/O error occurs
     */
    public static StringBuffer readStream(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }


}
