package com.li.mapper;

import com.li.pojo.PageBean;
import com.li.pojo.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {

    int insertWork(Work work);

    int deleteWorkByWid(int wid);

    int deleteWorksByIds(@Param("ids") int[] ids);

    int updateWork(Work work);

    Work selectWorkByWid(int wid);

    List<Work> selectAllWork();

    List<Work> selectWorksByPageAndCondition(@Param("begin") int begin, @Param("count") int count, @Param("work") Work work);

    int selectWorkCountByCondition(Work work);
}
