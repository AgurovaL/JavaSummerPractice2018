package com.agurova.services.impl;

import com.agurova.models.Image;
import com.agurova.services.StockImagesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.log4j.Logger;
import org.springframework.data.repository.init.Jackson2ResourceReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class UnsplashStockImagesService implements StockImagesService {
    private static final Logger log = Logger.getLogger(UnsplashStockImagesService.class);

    final static String randomImageURL = "https://api.unsplash.com/photos/random/?client_id=";
    final static String listImagesURL = "https://api.unsplash.com/photos/?";

    private String accessKey;
    private String secretKey;
    private int imagesNumber;


    public List<Image> getAllImages() {
        List<Image> resultList = new ArrayList<Image>();
        //get the keys from unsplash.properties
        loadProperties();

        try {
            String urlString = listImagesURL + "per_page=" + imagesNumber + ";client_id=" + accessKey;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                try (
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream(), "utf8"));
                ) {
                    ObjectMapper mapper = new ObjectMapper();
                    // get Json list from reader and convert to Images list
                    resultList = Arrays.asList(mapper.readValue(reader, Image[].class));
                } catch (IOException e) {
                    log.error("IOException", e);
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            log.error("IOException", e);
        }
        return resultList;
    }

    public List<String> postAllImages(List<Image> images) {
        List<String> resultStrings = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try{
            for (Image image: images){
                //adding to result as Json string
                resultStrings.add(mapper.writeValueAsString(image));
            }
        }catch (JsonProcessingException e){
            log.error("Converting to Json string error", e);
        }
        return resultStrings;
    }

    public Image getRandomImage() {
        Image resultImage = new Image();
        //get the keys from unsplash.properties
        loadProperties();

        try {
            String urlString = randomImageURL + accessKey;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);

            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                try (
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream(), "utf8"));
                ) {
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

                } catch (IOException e) {
                    log.error("IOException", e);
                }
            }

            connection.disconnect();

        } catch (Exception e) {
            log.error("IOException", e);
        }
        return resultImage;
    }

    private void loadProperties() {
        String path = "src/main/resources/unsplash.properties";

        try (FileInputStream inputReader = new FileInputStream(path);
        ) {
            Properties properties = new Properties();
            properties.load(inputReader);

            this.accessKey = properties.getProperty("AccessKey");
            this.secretKey = properties.getProperty("SecretKey");
            this.imagesNumber = Integer.parseInt(properties.getProperty("per_page"));

            log.info("AccessKey: " + this.accessKey);
            log.info("SecretKey: " + this.secretKey);
            log.info("imagesNumber: " + this.imagesNumber);

        } catch (IOException e) {
            log.error("File " + path + " doesn't exist!");
        }
    }
}
