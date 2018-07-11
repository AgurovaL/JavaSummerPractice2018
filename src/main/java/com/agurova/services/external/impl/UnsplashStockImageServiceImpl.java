package com.agurova.services.external.impl;

import com.agurova.models.Image;
import com.agurova.services.external.StockImagesService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class UnsplashStockImageServiceImpl implements StockImagesService {
    private static final Logger LOG = Logger.getLogger(UnsplashStockImageServiceImpl.class);

    private static final String PROPERTIES_PATH = "src/main/resources/unsplash.properties";
    private static final String RANDOM_IMAGE_URL = "https://api.unsplash.com/photos/random/?client_id=";
    private static final String LIST_IMAGES_URL = "https://api.unsplash.com/photos/?";

    private static String accessKey;
    private static String secretKey;
    private static int imagesNumber;

    static {
        try (FileInputStream inputReader = new FileInputStream(PROPERTIES_PATH);) {
            Properties properties = new Properties();
            properties.load(inputReader);

            accessKey = properties.getProperty("accessKey");
            secretKey = properties.getProperty("secretKey");
            imagesNumber = Integer.parseInt(properties.getProperty("perPage"));

            LOG.info("AccessKey: " + accessKey);
            LOG.info("SecretKey: " + secretKey);
            LOG.info("imagesNumber: " + imagesNumber);

        } catch (IOException e) {
            LOG.error("File " + PROPERTIES_PATH + " doesn't exist!");
        }
    }

    public List<Image> getImagesByTag(String tag) {
        return new ArrayList<>();
    }

    public List<Image> getAllImages() {
        List<Image> resultList = new ArrayList<>();

        try {
            String urlString = LIST_IMAGES_URL + "per_page=" + imagesNumber + ";client_id=" + accessKey;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));) {
                    ObjectMapper mapper = new ObjectMapper();
                    // get Json list from reader and convert to Images list
                    resultList = Arrays.asList(mapper.readValue(reader, Image[].class));
                } catch (IOException e) {
                    LOG.error("IOException", e);
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            LOG.error("IOException", e);
        }
        return resultList;
    }

    public Image getRandomImage() {
        Image resultImage = new Image();

        try {
            String urlString = RANDOM_IMAGE_URL + accessKey;
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
                    String line;
                    String jsonInString = "";
                    while ((line = reader.readLine()) != null) {
                        jsonInString += line;
                    }

                    LOG.info(jsonInString);

                    JsonNode productNode = new ObjectMapper().readTree(jsonInString);

                    resultImage = new Image();
                    resultImage.setUnsplashId(productNode.get("id").textValue());
                    resultImage.setWidth(productNode.get("width").textValue());
                    resultImage.setHeight(productNode.get("height").textValue());
                    resultImage.setColor(productNode.get("color").textValue());
                    resultImage.setAddress(productNode.get("urls")
                            .get("small").textValue());

//                    ObjectMapper mapper = new ObjectMapper();
//                    // Convert JSON string to Object
//                    resultImage = mapper.readValue(jsonInString, Image.class);
                    LOG.info(resultImage);

                } catch (IOException e) {
                    LOG.error("Error reading json string", e);
                }
            }

            connection.disconnect();

        } catch (Exception e) {
            LOG.error("Connection error", e);
        }
        return resultImage;
    }
}
