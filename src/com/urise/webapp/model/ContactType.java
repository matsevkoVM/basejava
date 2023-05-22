package com.urise.webapp.model;

public enum ContactType {
    PHONE("Phone number"),
    MOBILE("Mobile phone number"),
    HOME_PHONE("Home phone number"),
    SKYPE("Skype") {
        @Override
        protected String toHtml0(String str) {
            return "<a href='skype:" + str + "'>" + str + "</a>";
        }
    },
    MAIL("E-Mail") {
        @Override
        public String toHtml0(String str) {
            return "<a href='mailto:" + str + "'>" + str + "</a>";
        }
    },
    LINKED_IN("LinkedIn Profile") {
        @Override
        public String toHtml(String str) {
            return "<a href='" + str + "'>" + str + "</a>";
        }
    },
    GITHUB("GitHub Profile"){
        @Override
        public String toHtml(String str) {
            return "<a href='" + str + "'>" + str + "</a>";
        }
    },
    STACKOVERFLOW("Stackoverflow Profile"){
        @Override
        public String toHtml(String str) {
            return "<a href='" + str + "'>" + str + "</a>";
        }
    },
    HOME_PAGE("Home Page"){
        @Override
        public String toHtml(String str) {
            return "<a href='" + str + "'>" + str + "</a>";
        }
    };

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String str) {
        return str;
    }

    public String toHtml(String str) {
        return (str == null) ? "" : toHtml0(str);
    }
}
