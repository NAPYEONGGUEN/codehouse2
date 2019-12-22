package com.itbuddy.codehouse.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import com.itbuddy.codehouse.DTO.Comment;



public interface ICommentDAO {

    public List<Comment> select(Comment comment);
    
    public List<Comment> select_start_count_sortParm(
    		@Param("start") int start,
			@Param("count") int count,
			@Nullable @Param("sortParam") String sortParam);

    
    public int insert(Comment comment);
    
    
    public int update(Comment comment);
}
