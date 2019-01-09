package com.app.android.june.cryptonewsoffers;

public class Post {
    private String Title;
    private String Descr;
    private String Link;
    private String Name;

public Post() {

}
    public Post(String title, String descr, String link, String name) {
        Title = title;
        Descr = descr;
        Link = link;
        Name = name;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescr() {
        return Descr;
    }

    public void setDescr(String descr) {
        Descr = descr;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
