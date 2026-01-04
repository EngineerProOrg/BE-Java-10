package lecture.junit;

import lecture.junit.exception.InvalidStatusCodeException;
import lecture.junit.scrapper.ExampleScrapper;
import lecture.junit.scrapper.Scrapper;

public class CrawlerService {
  private final Scrapper scrapper;

  public CrawlerService(Scrapper scrapper) {
    this.scrapper = scrapper;
  }

  // public CrawlerService() {
  // this.scrapper = new ExampleScrapper();
  // }

  public int showStatusCodeOrRaiseException(String url) throws InvalidStatusCodeException {
    int statusCode = scrapper.crawlStatusCode(url); // need fake to test
    if (statusCode == 200) {
      System.out.println("Happy status code");
      return statusCode;
    }
    throw new InvalidStatusCodeException();
  }
}
