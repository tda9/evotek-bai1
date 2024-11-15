package com.da.bai1_part2;

import com.da.bai1_part1.Bai1Part1;
import com.da.utils.ApiUtils;
import com.da.utils.FileUtils;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class Bai1Part2 {

    static Logger logger = Logger.getLogger(Bai1Part1.class.getName());

    /**
     * Retrieves all albums from the specified URL  and create a text file to store them.
     * @param filePath the path where the file will be created
     * @param url the URL to send the GET request to
     * @param fileName the name of the file to be created
     * @throws Exception if an I/O error occurs
     */
    public static void getAllAlbums(String filePath,String url, String fileName) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String content = response.body();

        //create file and write content to file
        File file = FileUtils.createFile(filePath,fileName);
        FileUtils.writeAlbumsToFile(file,content);
        FileUtils.showAllAlbums(content);
    }

    /**
     * Retrieves all albums from the specified URL  and create a text file to store them.
     * @param filePath the path where the file will be created
     * @param url the URL to send the GET request to
     * @param fileName the name of the file to be created
     *
     * @param userId the user id to search for
     * @param id the id to search for
     * @param title the title to search for
     *
     *  @throws Exception if an I/O error occurs
     */
    public static void getOneAlbums(String filePath,String fileName,String url,String userId, String id, String title) throws Exception {
        //get url with search parameters
        url = ApiUtils.getSearchURL(url,userId,id,title);

        //perform same as getAllAlbums
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String content = response.body();
        File file = FileUtils.createFile(filePath,fileName);
        FileUtils.writeAlbumsToFile(file,content);
        FileUtils.showAllAlbums(content);
    }



}
