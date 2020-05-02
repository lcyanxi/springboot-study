package com.lcyanxi.service;


import com.lcyanxi.model.UserLesson;

import java.util.List;

public interface IUserLessonService {
    /**
     *  批量保存数据
     * @param userLessonList 参数实体
     * @return
     */
    Boolean insertUserLesson(List<UserLesson> userLessonList);


    /**
     * 通过用户ID和班级ID集合查找数据
     * @param userId 用户ID
     * @param lessonIds 课次ID
     * @return
     */
    List<UserLesson> findByUserIdLessonIds(Integer userId, List<Integer> lessonIds);
}
