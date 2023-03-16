package com.urise.webapp.model;

public enum SectionType {
    PERSONAL("Personal Qualities"),
    OBJECTIVE("Position"),
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
