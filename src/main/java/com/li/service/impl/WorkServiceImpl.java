package com.li.service.impl;

import com.li.mapper.WorkMapper;
import com.li.pojo.PageBean;
import com.li.pojo.Work;
import com.li.service.WorkService;
import com.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class WorkServiceImpl implements WorkService {

    /**
     * 添加作品
     *
     * @param work
     * @return 添加成功的条数
     */
    public int addWork(Work work) {

        //通过SqlSessionFactoryUtils工具类获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //再通过sqlSessionFactory获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        //调用mapper对象添加作品
        int count = workMapper.insertWork(work);
        //提交事务
        sqlSession.commit();
        //关闭连接
        sqlSession.close();
        //返回添加成功的条数
        return count;
    }

    /**
     * 根据wid删除作品
     *
     * @param wid
     * @return 删除成功的条数
     */
    public int deleteWorkByWid(int wid) {

        //通过SqlSessionFactoryUtils工具类获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //再通过sqlSessionFactory获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        //调用mapper对象删除作品
        int count = workMapper.deleteWorkByWid(wid);
        //提交事务
        sqlSession.commit();
        //关闭连接
        sqlSession.close();
        //返回删除成功的条数
        return count;
    }

    /**
     * 根据ids批量删除作品
     *
     * @param ids
     * @return 删除成功的条数
     */
    public int deleteWorksByIds(int[] ids) {
        //通过SqlSessionFactoryUtils工具类获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //再通过sqlSessionFactory获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        //调用mapper对象批量删除作品
        int count = workMapper.deleteWorksByIds(ids);
        //提交事务
        sqlSession.commit();
        //关闭连接
        sqlSession.close();
        //返回删除成功的条数
        return count;
    }

    /**
     * 根据wid修改作品详情
     *
     * @param work
     * @return 修改成功的条数
     */
    public int updateWork(Work work) {

        //通过SqlSessionFactoryUtils工具类获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //再通过sqlSessionFactory获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        //调用mapper对象批量修改作品详情
        int count = workMapper.updateWork(work);
        //提交事务
        sqlSession.commit();
        //关闭连接
        sqlSession.close();
        //返回修改成功的条数
        return count;
    }


    /**
     * 根据wid查询作品
     *
     * @param wid
     * @return 返回作品信息
     */
    public Work selectWorkByWid(int wid) {

        //通过SqlSessionFactoryUtils工具类获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //再通过sqlSessionFactory获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        //调用mapper对象批量查询作品信息
        Work work = workMapper.selectWorkByWid(wid);
        //提交事务
        sqlSession.commit();
        //关闭连接
        sqlSession.close();
        //返回作品信息
        return work;
    }


    /**
     * 根据分页和条件查询作品
     *
     * @param currentPage
     * @param pageCount
     * @param work
     * @return 返回作品和作品总条数
     */
    public PageBean<Work> selectWorksByPageAndCondition(int currentPage, int pageCount, Work work) {

        //通过SqlSessionFactoryUtils工具类获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //再通过sqlSessionFactory获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        //模糊查询处理
        String workName = work.getWorkName();
        if (workName != null && workName.length() > 0) {
            work.setWorkName("%" + workName + "%");
        }

        String writer = work.getWriter();
        if (writer != null && writer.length() > 0) {
            work.setWriter("%" + writer + "%");
        }

        String themeStr = work.getThemeStr();
        if (themeStr != null && themeStr.length() > 0) {
            //以;分隔得到theme数组，但是有空格需要处理
            String[] split = themeStr.split(";");
            //处理空格并添加%以便进行模糊查询
            String[] newSplit = new String[]{"", "", "", "", "", "", "", "", "", ""};
            for (int i = 0; i < split.length; i++) {
                newSplit[i] = "%" + split[i].trim() + "%";
            }
            //将处理好的数组赋值给theme
            work.setThemes(newSplit);
        }

        String[] themes = work.getThemes();
        if ( themes != null && themes.length > 0) {

            //添加%以便进行模糊查询
            String[] newThemes = new String[]{"", "", "", "", "", "", "", "", "", ""};
            for (int i = 0; i < themes.length; i++) {
                newThemes[i] = "%" + themes[i].trim() + "%";
            }
            //将处理好的数组赋值给theme
            work.setThemes(newThemes);
        }

        //调用mapper对象查询作品
        int begin = (currentPage - 1) * pageCount;
        List<Work> works = workMapper.selectWorksByPageAndCondition(begin, pageCount, work);
        int count = workMapper.selectWorkCountByCondition(work);

        //创建pageBean对象用于存储查询信息
        PageBean<Work> workPageBean = new PageBean<Work>();
        workPageBean.setRows(works);
        workPageBean.setTotalCount(count);

        //提交事务
        sqlSession.commit();
        //关闭连接
        sqlSession.close();
        //返回作品和作品总条数
        return workPageBean;
    }
}
