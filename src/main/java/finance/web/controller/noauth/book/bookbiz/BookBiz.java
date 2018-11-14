package finance.web.controller.noauth.book.bookbiz;

import finance.web.controller.noauth.book.vo.BookCommentVO;
import finance.api.model.base.XMap;

import java.util.List;

/**
 * @program: finance-server
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-09-14 09:22
 **/
public interface BookBiz {
    /**
      *功能描述:根据图书id查询评论
      * @author: moruihai
      * @date: 2018/9/14 9:27
      * @param: [bookId]
      * @return: java.util.List<finance.api.oauth.book.vo.BookCommentVO>
      */
    List<BookCommentVO>  queryBookCommentList(String bookId);

    /**
      *功能描述:添加评论
      * @author: moruihai
      * @date: 2018/9/14 10:56
      * @param: [paramMap]
      * @return: java.lang.Boolean
      */
    Boolean addBookComment(XMap paramMap);
    /**
      *功能描述:点赞
      * @author: moruihai
      * @date: 2018/9/14 11:18
      * @param: [paramMap]
      * @return: java.lang.Boolean
      */
    Boolean addBookLike(XMap paramMap);
}
