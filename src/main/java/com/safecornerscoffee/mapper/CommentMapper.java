package com.safecornerscoffee.mapper;


import com.safecornerscoffee.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    Comment SelectCommentById(String commentId);
    List<Comment> SelectCommentsByArticleId(Long articleId);
    void insertComment(Comment comment);
    void deleteComment(Comment comment);
}
