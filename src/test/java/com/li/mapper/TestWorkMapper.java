package com.li.mapper;

import com.li.mapper.WorkMapper;
import com.li.pojo.Work;
import com.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

public class TestWorkMapper {

    @Test
    public void testSelectAllWork() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        List<Work> works = workMapper.selectAllWork();

        sqlSession.close();

        System.out.println(works);
    }

    @Test
    public void testInsertWork() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        Work work = new Work();

        work.setWorkName("以父之名");
        work.setWriter("周杰伦");
        work.setType("音乐");
        work.setThemes(new String[]{"剧情","悬疑","教父"});
        work.setWorkDescription("《以父之名》故事性强，歌词用了很多不同的景物去呈现歌中“教父”那懊恼与痛苦的心境，令听者能有更立体更实在的感受。此歌的编曲方面亦富歌剧味道，与歌曲的整体感觉与主题配合。");

        int count = workMapper.insertWork(work);

        sqlSession.commit();

        sqlSession.close();

        System.out.println(count);
    }


    @Test
    public void testDeleteWork() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        int count = workMapper.deleteWorkByWid(2);

        sqlSession.commit();

        sqlSession.close();

        System.out.println(count);
    }


    @Test
    public void testDeleteWorksByIds() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        int[] ids = {4,5,6};

        int count = workMapper.deleteWorksByIds(ids);

        sqlSession.commit();

        sqlSession.close();

        System.out.println(count);
    }



    @Test
    public void testUpdateWork() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        Work work = new Work();

        work.setWid(8);
        work.setWorkName("以父之名");
        work.setWriter("周杰伦");
        work.setType("音乐");
        work.setThemes(new String[]{"剧情","悬疑"});
        work.setWorkDescription("《以父之名》故事性强，歌词用了很多不同的景物去呈现歌中“教父”那懊恼与痛苦的心境，令听者能有更立体更实在的感受。此歌的编曲方面亦富歌剧味道，与歌曲的整体感觉与主题配合。");

        int count = workMapper.updateWork(work);

        sqlSession.commit();

        sqlSession.close();

        System.out.println(count);
    }


    @Test
    public void testSelectWorksByPageAndCondition() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        Work work = new Work();
        //work.setWorkName("以父之名");
        //work.setWriter("周杰伦");
        //work.setType("音乐");
        //work.setThemes(new String[]{"%剧情%","%悬疑%"});

        List<Work> list = workMapper.selectWorksByPageAndCondition(0, 10, work);

        sqlSession.close();

        System.out.println(list);
    }

    @Test
    public void testSelectWorkCountByCondition() {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);

        Work work = new Work();
        work.setWorkName("以父之名");
        //work.setWriter("周杰伦");
        work.setType("音乐");
        work.setThemes(new String[]{"%剧情%","%悬疑%"});

        int count = workMapper.selectWorkCountByCondition(work);

        sqlSession.close();

        System.out.println(count);
    }
}
