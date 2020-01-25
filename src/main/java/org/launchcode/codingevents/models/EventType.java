package org.launchcode.codingevents.models;

public enum EventType {

    CONFERENCE("Conference"),
    PROFESSIONAL("Professional"),
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social"),
    FAMILY("Family"),
    PRIVATE("Private"),
    MANDATORY("Mandatory");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
