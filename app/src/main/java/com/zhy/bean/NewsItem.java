package com.zhy.bean;

import java.io.Serializable;

public class NewsItem implements Serializable{
    private int id;
    private String title;
    private String content;
    private String link;
    private String imgLink;
    private String date;
    private int newsType;

    public int getNewsType() {
        return this.newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgLink() {
        return this.imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return "NewsItem [id=" + this.id + ", title=" + this.title + ", content=" +
                this.content + ", link=" + this.link + ", imgLink=" + this.imgLink +
                ", date=" + this.date + ", newsType=" + this.newsType + "]";
    }
}
