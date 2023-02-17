package com.li.service;

import com.li.pojo.PageBean;
import com.li.pojo.Work;
import com.li.service.WorkService;
import com.li.service.impl.WorkServiceImpl;
import org.junit.Test;

public class TestWorkService {

    private WorkService workService = new WorkServiceImpl();

    @Test
    public void testSelectWorksByPageAndCondition() {

        Work work = new Work();
        work.setWorkName("以父");
        work.setWriter("杰");
        work.setType("音乐");
        work.setThemeStr(" 剧情 ; 悬疑 ");

        PageBean<Work> workPageBean = workService.selectWorksByPageAndCondition(1, 2, work);

        System.out.println(workPageBean);
    }


    @Test
    public void testSelectWorkByWid() {

        Work work = workService.selectWorkByWid(8);

        System.out.println(work);
    }
}
