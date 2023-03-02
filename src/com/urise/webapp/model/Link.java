package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Link {

    private final String name;
    private final String url;

    public Link(String name, String url) {
        Objects.requireNonNull(name, "name must be not null");
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Link (" + name + ", " + url + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!getName().equals(link.getName())) return false;
        return getUrl() != null ? getUrl().equals(link.getUrl()) : link.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }
}
