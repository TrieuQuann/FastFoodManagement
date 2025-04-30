package DTO;

public class Feature {
    private String icon_url;
    private String feature_name;

    public Feature(String icon_url, String feature_name) {
        this.icon_url = icon_url;
        this.feature_name = feature_name;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public String getFeature_name() {
        return feature_name;
    }
}