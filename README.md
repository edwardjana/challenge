# Web Scraper Challenge

## Run it
`gradle run -Dexec.args='www.jana.com'`

You can change the exec.args to any url, with or without a protocol (HTTP/HTTPS).

## Design Considerations

### EmailScraper
This class is the entry point.  I wanted to keep it as simple as possible, which allows this feature to be integrated into a larger product.

### WebPageScraper
Awkward class naming aside, this class will recusiverly scan a webpage and it's linked pages for any links.  If those links are a `mailto`, then it will flag that link as an email.  The recursive traversial maintains two sets -- one of visited URLs, and one of any found email addresses.

### URLUtil
This utility class provides static funcitons for parsing and sanitizing urls in various ways.  There are many ways for a webpage to represent links to another page, and this utility provides support for:
  * Relative Protocols -> a link which starts with a `//`, and doesn't include "HTTP:".
  * Relative Paths -> a link which starts with a `/`, and the browser knows it's on the same host as the current page.
  * Protocol-less URLS -> This tool uses `JSoup`, which requires all URLs to be fully qualified.

## Depedencies
  * JSoup 1.10.3
     - The MIT License
     - https://jsoup.org/license
