package com.li.service;

import com.li.pojo.PageBean;
import com.li.pojo.Work;

import java.util.List;

public interface WorkService {

    //添加作品，返回添加成功的条数
    int addWork(Work work);

    //根据wid删除作品，返回删除成功的条数
    int deleteWorkByWid(int wid);

    //根据ids批量删除作品，返回删除成功的条数
    int deleteWorksByIds(int[] ids);

    //根据wid修改作品详情，返回修改成功的条数
    int updateWork(Work work);

    //根据wid查询作品，并返回作品信息
    Work selectWorkByWid(int wid);

    //根据分页和条件查询作品，并返回该情况下的作品和作品总条数
    PageBean<Work> selectWorksByPageAndCondition(int currentPage, int pageCount, Work work);
}
