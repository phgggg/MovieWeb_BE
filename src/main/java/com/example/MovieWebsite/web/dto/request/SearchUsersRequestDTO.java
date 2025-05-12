package com.example.MovieWebsite.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUsersRequestDTO {
    //    protected Integer pageSize;
    //    protected Integer page;
    //    protected String sort;
    private String userName;
    private String fullName;
}
