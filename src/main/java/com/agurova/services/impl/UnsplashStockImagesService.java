package com.agurova.services.impl;

import com.agurova.models.Image;
import com.agurova.services.StockImagesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UnsplashStockImagesService implements StockImagesService {
    final static String AccessKey = "e3bd60933a617c0a3d4ee21224814eb919cb9ef22f05b4d421e27ff4020e0c52";

    public void getImages() {
        //get list of images
    }

    public Image getRandomImage() {
        Image resultImage = new Image();

        try {
            String urlString = "https://api.unsplash.com/photos/random/?client_id=" + AccessKey;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);

            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));

                String line = null;
                String jsonInString = "";
                while ((line = reader.readLine()) != null) {
                    jsonInString += line;
                }

                System.out.println(jsonInString);
                ObjectMapper mapper = new ObjectMapper();
                // Convert JSON string to Object
                resultImage = mapper.readValue(jsonInString, Image.class);
                System.out.println(resultImage);

                reader.close();
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultImage;
    }
}
