package com.nhnacademy.shoppingmall.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RequestBuilder {
    private String modelNumber;
    private String modelName;
    private String description;
    private Integer unitCost;
    private Integer quantity;
    private List<Long> categoryIds;
    private MultipartFile thumbnail;
    private List<MultipartFile> productImages;

    public RequestBuilder setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
        return this;
    }

    public RequestBuilder setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public RequestBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public RequestBuilder setUnitCost(Integer unitCost) {
        this.unitCost = unitCost;
        return this;
    }

    public RequestBuilder setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public RequestBuilder setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
        return this;
    }

    public RequestBuilder setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public RequestBuilder setProductImages(List<MultipartFile> productImages) {
        this.productImages = productImages;
        return this;
    }

    public ProductDto.Create.Request createRequest() {
        return new ProductDto.Create.Request(modelNumber, modelName, description, unitCost, quantity, categoryIds, thumbnail, productImages);
    }
}