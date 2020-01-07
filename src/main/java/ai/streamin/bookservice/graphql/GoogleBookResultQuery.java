package ai.streamin.bookservice.graphql;

import ai.streamin.bookservice.book.BookInfo;
import ai.streamin.bookservice.book.GoogleBookResult;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class GoogleBookResultQuery implements GraphQLQueryResolver {

    private final GoogleBookResultService bookInfoService;

    public GoogleBookResultQuery(final GoogleBookResultService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    public GoogleBookResult getGoogleBookResultByISBN(String isbn) {
        return this.bookInfoService.getGoogleBookResultByISBN(isbn);
    }

}
