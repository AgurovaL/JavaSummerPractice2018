package com.agurova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.xml.soap.SAAJResult;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
public class Image {
    private Long imageId;

    @JsonProperty("id")
    private String unsplashId;
    @JsonProperty("width")
    private String width;
    @JsonProperty("height")
    private String height;
    @JsonProperty("color")
    private String color;
    @JsonProperty("html")
    private String address;

    private class Address{
        private String self;
        private String html;
        private String download;
    }
}
