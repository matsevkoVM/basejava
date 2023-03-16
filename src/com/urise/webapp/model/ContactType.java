package com.urise.webapp.model;

public enum ContactType{
    PHONE("Phone number"),
    MOBILE("Mobile phone number"),
    HOME_PHONE("Home phone number"),
    SKYPE("Skype"),
    MAIL("E-Mail"),
    LINKED_IN("LinkedIn Profile"),
    GITHUB("GitHub Profile"),
    STACKOVERFLOW("Stackoverflow Profile"),
    HOME_PAGE("Home Page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
