package br.com.alura.school.section;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SectionResponse {

    @JsonProperty
    private final String courseName;

    @JsonProperty
    private final String sectionTitle;

    @JsonProperty
    private final String authorName;

    @JsonProperty
    private final int totalVideos;

    public SectionResponse(String courseName, String sectionTitle, String authorName, int totalVideos) {
        this.courseName = courseName;
        this.sectionTitle = sectionTitle;
        this.authorName = authorName;
        this.totalVideos = totalVideos;
    }

    public SectionResponse(Section obj) {
        this.courseName = obj.getCourse().getName();
        this.sectionTitle = obj.getTitle();
        this.authorName = obj.getAuthorUsername();
        this.totalVideos = obj.getVideos().size();
    }

    public int getTotalVideos() {
        return totalVideos;
    }

    private String abbreviateDescription(String description) {
        if (description.length() <= 13) return description;
        return description.substring(0, 10) + "...";
    }

    public int compareTo(SectionResponse section) {
        int compareQuantity = section.getTotalVideos();
        return compareQuantity - this.totalVideos;
    }

}
