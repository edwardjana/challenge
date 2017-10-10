package com.edward.challenge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebPageScraper {

    private static final String LINK_CSS = "a[href]";
    private static final String LINK_ATTRIBUTE = "href";

    private String baseUrl;

    public Set<String> getAllEmailsFromURL(String baseUrl) {
        Set<String> allEmails = new HashSet<>();
        Set<String> checkedURLS = new HashSet<>();
        this.baseUrl = baseUrl;
        try {
            return recursivelyCheckForEmails(baseUrl, allEmails, checkedURLS);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return new HashSet<>();
    }

    private Set<String> recursivelyCheckForEmails(String url, Set<String> allEmails, Set<String> checkedUrls) throws IOException {
        url = URLUtil.sanitizeURL(url, baseUrl);
        if (checkedUrls.contains(url)) {
            return null;
        }
        checkedUrls.add(url);
        Document doc = Jsoup.connect(url).get();

        Set<String> linksOnPage = scrapeForLinks(doc, allEmails);

        for (String link : linksOnPage) {
            recursivelyCheckForEmails(link, allEmails, checkedUrls);
        }

        return allEmails;
    }

    private Set<String> scrapeForLinks(Document document, Set<String> emails) {
        Set<String> links = new HashSet<>();

        Elements elements = document.select(LINK_CSS);

        for (Element e : elements) {
            String linkAttribute = e.attr(LINK_ATTRIBUTE);
            if (linkAttribute.contains(URLUtil.MAIL_TO)){
                emails.add(URLUtil.parseEmail(linkAttribute));
            }else {
                linkAttribute = URLUtil.sanitizeURL(e.attr(LINK_ATTRIBUTE), baseUrl);
                if (linkAttribute.contains(baseUrl)) {
                    links.add(linkAttribute);
                }
            }
        }
        return links;
    }
}
