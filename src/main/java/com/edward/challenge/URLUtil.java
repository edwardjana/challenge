package com.edward.challenge;

public class URLUtil {
    protected static final String HTTPS = "https:";
    protected static final String HTTP = "http:";
    protected static final String RELATIVE_PROTOCOL = "//";
    protected static final String RELATIVE_PATH = "/";
    protected static final String MAIL_TO = "mailto:";

    public static String sanitizeURL(String path, String host) {
        if (path.startsWith(RELATIVE_PROTOCOL)) {
            return HTTP + path;
        }
        if (path.startsWith(RELATIVE_PATH)){
            return host + path;
        }
        if (!path.startsWith(HTTP) && !path.startsWith(HTTPS)) {
            return HTTP + RELATIVE_PROTOCOL + path;
        }
        return path;
    }

    public static String parseEmail(String email) {
        return email.substring(email.indexOf(MAIL_TO) + MAIL_TO.length(), email.length());
    }

    public static String sanitizeHost(String host){
        if (!host.startsWith(HTTP) && !host.startsWith(HTTPS)) {
            return HTTP + RELATIVE_PROTOCOL + host;
        }
        return host;
    }
}
