package com.da.bai1_part1;

import junit.framework.TestCase;

import java.io.File;
import java.net.HttpURLConnection;

public class Bai1Part1Test extends TestCase {

    /**
     * Test file exists after getAllAlbums
     */
    public void testGetAllAlbums() throws Exception {
        String filePath = "D:\\Evotek\\BaiTap\\Bai1_v2\\evotek-bai1\\";
        String url = "https://jsonplaceholder.typicode.com/albums";
        String user_agent = "Mozilla/5.0";
        String fileName = "TestAllAlbums.txt";

        assertFalse(new File(filePath + fileName).exists());
        Bai1Part1.getAllAlbums(filePath, url, user_agent, fileName);
        assertTrue(new File(filePath + fileName).exists());
    }
}
