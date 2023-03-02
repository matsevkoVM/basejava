package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final LocalDate[] startDate;
    private final LocalDate[] endDate;
    private final String[] title;
    private final String[] description;

    public Organization(String name, String url, LocalDate[] startDate, LocalDate[] endDate, String[] title, String[] description) {
        Objects.requireNonNull(startDate, "startDate must be not null");
        Objects.requireNonNull(endDate, "endDate  must be not null");
        Objects.requireNonNull(title, "title must be not null");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        String org = "Organization{ homePage=" + homePage + "\n";
        String tmp;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < startDate.length; i++) {
            tmp = "startDate = " + startDate[i] + ", endDate = " + endDate[i] +
                    ", title=" + title[i] + '\'' + ", description=" + description[i] + '\'' + '}';
            result.append(tmp).append("\n");
        }
        return org + result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(startDate, that.startDate)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(endDate, that.endDate)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(title, that.title)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + Arrays.hashCode(startDate);
        result = 31 * result + Arrays.hashCode(endDate);
        result = 31 * result + Arrays.hashCode(title);
        result = 31 * result + Arrays.hashCode(description);
        return result;
    }
}
