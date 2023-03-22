package com.urise.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> items;

    public ListSection() {
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must be not null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (i == items.size() - 1) {
                formatted.append(String.format("%s", items.get(i)));
            } else {
                formatted.append(String.format("%s%n", items.get(i)));
            }
        }
        return formatted.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return getItems().equals(that.getItems());
    }

    @Override
    public int hashCode() {
        return getItems().hashCode();
    }
}
