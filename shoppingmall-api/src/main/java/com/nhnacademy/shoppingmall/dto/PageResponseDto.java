package com.nhnacademy.shoppingmall.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PageResponseDto<T> {

    private List<T> content;

    private int totalPages;

    private int number;

    private boolean previous;

    private boolean next;

    public static <T> PageResponseDto<T> fromPage(Page<T> page){
        PageResponseDto<T> pageResponseDto = new PageResponseDto<>();
        pageResponseDto.content = page.getContent();
        pageResponseDto.totalPages = page.getTotalPages();
        pageResponseDto.number = page.getNumber();
        pageResponseDto.previous = page.hasPrevious();
        pageResponseDto.next = page.hasNext();
        return pageResponseDto;
    }


}