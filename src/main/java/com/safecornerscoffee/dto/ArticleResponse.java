package com.safecornerscoffee.dto;

import com.safecornerscoffee.domain.Tag;

import java.util.List;

public class ArticleResponse {
    private Long id;
    private String title;
    private String body;
    private List<Tag> tags;

    private Long userId;
    private String username;
    private String profileName;
    private String profileImage;
}
