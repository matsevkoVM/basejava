package com.urise.webapp.model;

public enum SectionType {
    OBJECTIVE("Position"),
    PERSONAL("Personal Qualities"),
    ACHIEVEMENTS("Achievements"),
    QUALIFICATIONS("Qualifications"),
    EXPERIENCE("Experience"),
    EDUCATION("Education");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
