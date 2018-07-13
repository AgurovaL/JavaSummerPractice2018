package com.agurova.services.image.external.impl;

import com.agurova.models.Image;
import com.agurova.services.image.external.StockImagesService;
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

    private static final String PROPERTIES_PATH = "/unsplash.properties";
    private static final String RANDOM_IMAGE_URL = "https://api.unsplash.com/photos/random/?client_id=";
    private static final String LIST_IMAGES_URL = "https://api.unsplash.com/photos/?";

    private static String accessKey;
    private static String secretKey;
    private static int imagesNumber;

    static {
        try (InputStream inputStream =
                     UnsplashStockImageServiceImpl.class.getResourceAsStream(PROPERTIES_PATH);) {
            Properties properties = new Properties();
            properties.load(inputStream);

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
        List<Image> resultList = new ArrayList<>();
        HttpURLConnection connection = null;
        try {
            String urlString = LIST_IMAGES_URL + "query=" + tag + "&client_id=" + accessKey;
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));) {

                    ObjectMapper mapper = new ObjectMapper();
                    List<JsonNode> jsonNodes = Arrays.asList((mapper.readValue(reader, JsonNode[].class)));

                    for (JsonNode jsonNode : jsonNodes) {
                        Image resultImage = new Image();
                        resultImage.setUnsplashId(jsonNode.get("id").textValue());
                        resultImage.setWidth(jsonNode.get("width").textValue());
                        resultImage.setHeight(jsonNode.get("height").textValue());
                        resultImage.setColor(jsonNode.get("color").textValue());
                        resultImage.setAddress(jsonNode.get("urls")
                                .get("small").textValue());
                        resultList.add(resultImage);
                    }
                } catch (IOException e) {
                    LOG.error("IOException", e);
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            LOG.error("IOException", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return resultList;
    }

    public List<Image> getAllImages() {
        List<Image> resultList = new ArrayList<>();
        HttpURLConnection connection = null;
        try {
            String urlString = LIST_IMAGES_URL + "per_page=" + imagesNumber + ";client_id=" + accessKey;
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));) {

                    ObjectMapper mapper = new ObjectMapper();
                    List<JsonNode> jsonNodes = Arrays.asList((mapper.readValue(reader, JsonNode[].class)));

                    for (JsonNode jsonNode : jsonNodes) {
                        Image resultImage = new Image();
                        resultImage.setUnsplashId(jsonNode.get("id").textValue());
                        resultImage.setWidth(jsonNode.get("width").textValue());
                        resultImage.setHeight(jsonNode.get("height").textValue());
                        resultImage.setColor(jsonNode.get("color").textValue());
                        resultImage.setAddress(jsonNode.get("urls")
                                .get("small").textValue());
                        resultList.add(resultImage);
                    }
                } catch (IOException e) {
                    LOG.error("IOException", e);
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            LOG.error("IOException", e);
        } finally {
            connection.disconnect();
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

                    JsonNode jsonNode = new ObjectMapper().readTree(jsonInString);

                    resultImage = new Image();
                    resultImage.setUnsplashId(jsonNode.get("id").textValue());
                    resultImage.setWidth(jsonNode.get("width").textValue());
                    resultImage.setHeight(jsonNode.get("height").textValue());
                    resultImage.setColor(jsonNode.get("color").textValue());
                    resultImage.setAddress(jsonNode.get("urls")
                            .get("small").textValue());

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
