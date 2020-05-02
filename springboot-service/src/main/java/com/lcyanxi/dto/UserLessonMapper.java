package com.lcyanxi.dto;

import com.lcyanxi.model.UserLesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserLessonMapper {

    boolean  insertBatch(@Param("recordList") List<UserLesson> userLessons);

    List<UserLesson> findByUserIdLessonIds(@Param("userId") Integer userId,@Param("lessonIds") List<Integer> lessonIds);
}
