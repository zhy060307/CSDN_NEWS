package com.zhy.csdn;

public class URLCreator {
    public static final String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
    public static final String NEWS_LIST_URL_YIDONG = "http://mobile.csdn.net/mobile";
    public static final String NEWS_LIST_URL_YANFA = "http://sd.csdn.net/sd";
    public static final String NEWS_LIST_URL_YUNJISUAN = "http://cloud.csdn.net/cloud";
    public static final String NEWS_LIST_URL_ZAZHI = "http://programmer.csdn.net/programmer";
    public static final String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";

    public static String create(int newsType, int page) {
        int currentPage = page > 0 ? page : 1;
        String urlStr;
        switch (newsType) {
            case 1:
                urlStr = "http://news.csdn.net/news";
                break;
            case 3:
                urlStr = "http://sd.csdn.net/sd";
                break;
            case 4:
                urlStr = "http://programmer.csdn.net/programmer";
                break;
            case 5:
                urlStr = "http://cloud.csdn.net/cloud";
                break;
            case 2:
            default:
                urlStr = "http://mobile.csdn.net/mobile";
        }

        urlStr = urlStr + "/" + currentPage;
        return urlStr;
    }
}
