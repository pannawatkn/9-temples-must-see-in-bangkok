package project.test.mh.a9must_seetemplesinbangkok.Class;

public class Model {
    String title;
    String description;
    String icon;

    public Model(String title, String description, String icon) {
        this.title = title;
        this.description = description.replace("a", "\t\t\t\t\t")
                                .replace("b", "\n\n\t\t\t\t\t");
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }

    public String getIcon() {
        return this.icon;
    }
}