package me.yoon.blogproject.config.error.exception;

import me.yoon.blogproject.config.error.ErrorCode;

public class ArticleNotFoundException extends NotFountException{
    public ArticleNotFoundException() {
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }

}
