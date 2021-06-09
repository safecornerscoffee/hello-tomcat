package com.safecornerscoffee.dto;

import com.safecornerscoffee.domain.Tag;

import java.util.Set;

public class ArticleResponse {
    private Long id;
    private String title;
    private String body;
    private Set<Tag> tags;

    private Long userId;
    private String username;
    private String profileName;
    private String profileImage;
}
