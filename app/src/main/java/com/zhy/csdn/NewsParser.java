package com.zhy.csdn;

import com.zhy.bean.NetworkException;
import com.zhy.bean.NewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class NewsParser {
    public List<NewsItem> parserByPage(int newsType, int curPage)
            throws NetworkException {
        String url = URLCreator.create(newsType, curPage);
        String html = HttpUtils.doGet(url);
        List<NewsItem> newsList = parse(html);
        for (NewsItem newsItem : newsList) {
            newsItem.setNewsType(newsType);
        }
        return newsList;
    }

    public List<NewsItem> parse(String html) {
        List itemList = new ArrayList();

        NewsItem item;
        Document doc = Jsoup.parse(html);
        Elements units = doc.getElementsByClass("unit");

        for (int i = 0; i < units.size(); i++) {
            item = new NewsItem();
            Element e = units.get(i);
            Element e_h1 = e.getElementsByTag("h1").get(0);
            Element titleTag = e_h1.getElementsByTag("a").get(0);
            String link = titleTag.attr("href");
            String title = titleTag.text();

            item.setTitle(title);
            item.setLink(link);

            Element e_h4 = e.getElementsByTag("h4").get(0);
            Element dateTag = e_h4.getElementsByClass("ago").get(0);
            String date = dateTag.text();
            item.setDate(date);

            Element e_dl = e.getElementsByTag("dl").get(0);
            try {
                Element e_dt = e_dl.child(0);
                Element imgTag = e_dt.getElementsByTag("img").get(0);
                String imgLink = imgTag.attr("src");

                item.setImgLink(imgLink);
            } catch (Exception localException) {
                localException.printStackTrace();
            }
            Element contentTag = e_dl.getElementsByTag("dd").get(0);
            String content = contentTag.text();
            item.setContent(content);
            itemList.add(item);
        }
        return itemList;
    }
}
