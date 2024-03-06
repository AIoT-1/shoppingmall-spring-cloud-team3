package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.Category;
import lombok.Getter;

import java.util.List;


@Getter
public class CategoryDto {

    private final Long id;
    private final String name;
    public CategoryDto (Long id, String name){
        this.id = id;
        this.name = name;
    }
    @Getter
    public static class RegisterRequest{
        private String name;

        public Category toEntity(){
            return Category.builder()
                    .name(name)
                    .build();
        }
    }

    @Getter
    public static class RegisterResponse{
        private Long id;
        private String name;

        public static RegisterResponse fromEntity(Category savedCategory) {
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.id = savedCategory.getId();
            registerResponse.name = savedCategory.getName();
            return registerResponse;
        }
    }

    @Getter
    public static class ListResponse{
        private final List<CategoryDto> categories;

        public ListResponse(List<CategoryDto> categories){
            this.categories = categories;
        }

    }
    @Getter
    public static class UpdateRequest {
        private String name;
    }

    @Getter
    public static class UpdateResponse {
        private Long id;
        private String name;

        public static UpdateResponse fromEntity(Category category) {
            UpdateResponse updateResponse = new UpdateResponse();
            updateResponse.id = category.getId();
            updateResponse.name = category.getName();
            return updateResponse;
        }
    }
}
