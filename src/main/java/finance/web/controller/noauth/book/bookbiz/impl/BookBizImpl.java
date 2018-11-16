package finance.web.controller.noauth.book.bookbiz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import finance.core.dal.dao.FinanceUserInfoDAO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import finance.web.controller.noauth.book.bookbiz.BookBiz;
import finance.web.controller.noauth.book.vo.BookCommentVO;
import finance.api.model.base.XMap;
import finance.core.dal.dataobject.FinanceUserInfo;

/**
 * @program: finance-server
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-09-14 09:23
 **/
@Service
public class BookBizImpl implements BookBiz {
    private static final Long     USER_ID = 10L;
    //@Autowired
    //private JwtService          jwtService;
    @Resource
    private StringRedisTemplate   stringRedisTemplate;

    @Resource
    private FinanceUserInfoDAO financeUserInfoMapper;

    @Override
    public List<BookCommentVO> queryBookCommentList(String bookId) {
        /**1.jwt 获取用户user_id **/
        // Long userId = jwtService.getUserInfo().getId();
        String bookCommentRedisKey = "bookComment" + bookId;

        List<BookCommentVO> bookCommentVOS = new ArrayList<>();

        List<String> bookCommentList = stringRedisTemplate.opsForList().range(bookCommentRedisKey,
            0, -1);
        if (bookCommentList != null && bookCommentList.size() > 0) {
            BookCommentVO bookCommentVO = null;
            for (String bookComment : bookCommentList) {
                bookCommentVO = JSON.parseObject(bookComment, new TypeReference<BookCommentVO>() {
                });

                String commentId = bookCommentVO.getCommentId();
                String bookCommentLikeRedisKey = "bookCommentLike" + commentId;
                List<String> commentLikeList = stringRedisTemplate.opsForList()
                    .range(bookCommentLikeRedisKey, 0, -1);
                if (commentLikeList != null && commentLikeList.size() > 0) {
                    bookCommentVO.setLikeNum(commentLikeList.size() + "");
                    bookCommentVO.setLike(commentLikeList.contains(USER_ID + ""));
                } else {
                    bookCommentVO.setLikeNum("0");
                    bookCommentVO.setLike(false);
                }
                bookCommentVOS.add(bookCommentVO);

            }
        }
        return bookCommentVOS;
    }

    @Override
    public Boolean addBookComment(XMap paramMap) {

        String bookId = (String) paramMap.get("bookId");
        String comment = (String) paramMap.get("comment");
        String score = (String) paramMap.get("score");

        /**1.jwt 获取用户user_id **/
        FinanceUserInfo financeUserInfo = financeUserInfoMapper.selectByPrimaryKey(USER_ID);
        //jwtService.getUserInfo();
        String mobileNum = financeUserInfo.getMobileNum();
        mobileNum = mobileNum.substring(0, 3) + "****" + mobileNum.substring(7, 11);
        String bookCommentRedisKey = "bookComment" + bookId;

        BookCommentVO bookCommentVO = new BookCommentVO();
        bookCommentVO.setComment(comment);
        bookCommentVO.setCommentId(System.currentTimeMillis() + "");
        bookCommentVO.setHeadLink(
            "http://jingrongjia-oss.oss-cn-hangzhou.aliyuncs.com/C%3A%5CUsers%5CAdministrator%5CDesktop%5C%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180914153638.png?Expires=5136910824&OSSAccessKeyId=LTAIEEZtE9nJG6UW&Signature=%2F06eatWNerH6tJpwZYXziUTjvCI%3D");
        bookCommentVO.setSecPhone(mobileNum);
        bookCommentVO.setScore(score);

        String bookCommentJson = JSON.toJSONString(bookCommentVO);
        stringRedisTemplate.opsForList().leftPush(bookCommentRedisKey, bookCommentJson);
        stringRedisTemplate.expire(bookCommentRedisKey, 60, TimeUnit.DAYS);

        return true;
    }

    @Override
    public Boolean addBookLike(XMap paramMap) {
        String commentId = (String) paramMap.get("commentId");
        /**1.jwt 获取用户user_id **/
        FinanceUserInfo financeUserInfo = financeUserInfoMapper.selectByPrimaryKey(USER_ID);
        //jwtService.getUserInfo();
        Long userId = financeUserInfo.getId();
        String bookCommentLikeRedisKey = "bookCommentLike" + commentId;

        stringRedisTemplate.opsForList().leftPush(bookCommentLikeRedisKey, userId + "");
        stringRedisTemplate.expire(bookCommentLikeRedisKey, 60, TimeUnit.DAYS);
        return true;
    }
}
