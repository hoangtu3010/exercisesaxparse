import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ArticleHandle extends DefaultHandler {
    private String content;
    private Article article;
    private List<Article> articleList = new ArrayList<>();
    private Boolean articleExist = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Tạo đối tượng article khi bắt đầu thẻ "item"
        if (qName.equalsIgnoreCase("item")) {
            article = new Article();
            articleExist = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Add article nếu gặp thẻ đóng là "item"
        if ("item".equals(qName)) {
            articleList.add(article);
        }

        if (articleExist) {
            switch (qName) {
                case "title":
                    article.setTitle(content);
                    break;
                case "pubDate":
                    article.setPubDate(content);
                    break;
                case "link":
                    article.setLink(content);
                    break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Đọc nội dung của thẻ hiện tại
        content = String.copyValueOf(ch, start, length).trim();
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
