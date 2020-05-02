package com.lcyanxi.controller;



import com.lcyanxi.model.UserLesson;
import com.lcyanxi.service.IUserLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserLessonService userLessonService;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "hello world";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String userId){
        String name = "admin";
        List<UserLesson> lessons = new ArrayList<>();
        for (int i = 0;i < 10; i++){
            UserLesson userLesson=new UserLesson();
            userLesson.setParentClassId(1);
            userLesson.setBuyStatus(false);
            userLesson.setOrderNo(System.currentTimeMillis()+"");
            userLesson.setClassId(1);
            userLesson.setBuyTime(new Date());
            userLesson.setClassCourseId(11);
            userLesson.setLessonId(i);
            userLesson.setStatus(2);
            userLesson.setCreateUid(name);
            userLesson.setCreateUsername(name);
            userLesson.setUpdateUid(name);
            userLesson.setUpdateUsername(name);
            userLesson.setProductId(11);
            userLesson.setUserId(Integer.parseInt(userId));
            lessons.add(userLesson);
        }

        boolean result = userLessonService.insertUserLesson(lessons);
        return name+"数据操作"+result;
    }

    @RequestMapping(value = "/getData/{userId}",method = RequestMethod.GET)
    public List<UserLesson> getData(@PathVariable String userId){
        return userLessonService.findByUserIdLessonIds(Integer.parseInt(userId), null);
    }
}
