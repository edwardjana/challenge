package com.edward.challenge;

import java.util.Set;

public class EmailScraper {

    public static void main(String[] args) {
        System.out.println("\n\nEmail Scrapper");
        System.out.println("------------------");

        WebPageScraper scraper = new WebPageScraper();
        String url;
        if (args.length > 0) {
            url = args[0];
        }else{
            System.out.println("please provide a URL as an argument!");
            return;
        }

        url = URLUtil.sanitizeHost(url);
        Set<String> emails = scraper.getAllEmailsFromURL(url);

        System.out.println("Found these email addresses:");
        for (String email : emails) {
            System.out.println(email);
        }
    }
}
