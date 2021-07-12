package com.safecornerscoffee.medium.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Profile {

    private String name;
    private String bio;
    private String image;

    public Profile(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
