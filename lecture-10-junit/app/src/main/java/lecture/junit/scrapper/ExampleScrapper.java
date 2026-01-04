package lecture.junit.scrapper;

public class ExampleScrapper implements Scrapper {
    @Override
    public int crawlStatusCode(String url) {
        return 200;
    }
}
