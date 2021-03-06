package cn.zhishush.finance.web.controller.noauth.book;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhishush.finance.web.controller.noauth.book.bookbiz.BookBiz;
import cn.zhishush.finance.web.controller.noauth.book.vo.BookCommentVO;
import cn.zhishush.finance.api.model.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.base.XMap;

/**
 * @program: finance-server
 *
 * @description: 图书操作API
 *
 * @author: MORUIHAI
 *
 * @create: 2018-09-14 09:12
 **/
@RestController
@RequestMapping("book")
public class BookApi {
	@Resource
	private BookBiz bookBiz;

	/**
	 * <pre>
	 * &#64;api {GET} /book/commentList 图书列表
	 * &#64;apiName bookCommentList
	 * &#64;apiGroup BOOK
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 图书列表
	 * &#64;apiParam {String} bookId 图书id
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object} data banner数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": [
	 *          {
	 *              "commentId": "1536905608970",
	 *              "headLink": "xxxx",
	 *              "secPhone": "15821863835",
	 *              "score": "8",
	 *              "likeNum": "1",
	 *              "comment": "一本值得看的好书",
	 *              "like": true
	 *          },
	 *          {
	 *              "commentId": "1536903727109",
	 *              "headLink": "xxxx",
	 *              "secPhone": "15821863835",
	 *              "score": "6",
	 *              "likeNum": "0",
	 *              "comment": "一本好书",
	 *              "like": false
	 *          }
	 *      ],
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping(value = "commentList")
	public ResponseResult<List<BookCommentVO>> bookCommentList(@RequestParam("bookId") String bookId) {
		List<BookCommentVO> returnList = bookBiz.queryBookCommentList(bookId);
		ResponseResult<List<BookCommentVO>> responseResult = ResponseResult.success(returnList);
		return responseResult;
	}

	/**
	 * <pre>
	 * &#64;api {POST} /book/comment 增加评论
	 * &#64;apiName addComment
	 * &#64;apiGroup BOOK
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 增加评论
	 * &#64;apiParam {String} bookId 图书id
	 * &#64;apiParam {String} comment 评论内容
	 * &#64;apiParam {String} score 评分
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object} data banner数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": true,
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author
	 */
	@PostMapping(value = "comment")
	public ResponseResult<Boolean> addComment(HttpServletRequest req, HttpServletResponse resp,
			@RequestBody XMap paramMap) {

		Boolean returnStr = bookBiz.addBookComment(paramMap);
		return ResponseResult.success(returnStr);
	}

	/**
	 * <pre>
	 * &#64;api {POST} /book/comment/like 点赞
	 * &#64;apiName addLike
	 * &#64;apiGroup BOOK
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 点赞
	 * &#64;apiParam {String} commentId  评论id
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object} data banner数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": true,
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author
	 */
	@PostMapping(value = "comment/like")
	public ResponseResult<Boolean> addLike(HttpServletRequest req, HttpServletResponse resp,
			@RequestBody XMap paramMap) {

		Boolean returnStr = bookBiz.addBookLike(paramMap);
		return ResponseResult.success(returnStr);
	}
}
