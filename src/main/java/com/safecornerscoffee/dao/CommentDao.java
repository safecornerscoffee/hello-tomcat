package com.safecornerscoffee.dao;


import com.safecornerscoffee.domain.Comment;

import java.util.List;

public interface CommentDao {
    public Comment SelectCommentById(String commentId);
    public List<Comment> SelectCommentsByArticleId(Long articleId);
    public void insertComment(Comment comment);
    public void deleteComment(Comment comment);
}
