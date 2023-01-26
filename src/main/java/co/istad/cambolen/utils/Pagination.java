package co.istad.cambolen.utils;

import java.util.List;

import lombok.Data;

@Data
public class Pagination<T> {
    
    private List<T> list;
    private Long total;
    private Long pageNum;
    private Long pageSize;
    private Long size;
    private Long startRow;
    private Long endRow;
    private Long pages;
    private Long prePage;
    private Long nextPage;
    private Boolean isFirstPage;
    private Boolean isLastPage;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private Long navigatePages;
    private List<Long> navigatepageNums;
    private Long navigateFirstPage;
    private Long navigateLastPage;

}
