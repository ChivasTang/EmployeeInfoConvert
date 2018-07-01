package com.EmployeeInfoConvert.fs.dao;

import com.EmployeeInfoConvert.fs.db.DBAccess;
import com.EmployeeInfoConvert.fs.domain.Position;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PositionDao implements IPositionDao {
    private static Logger logger = Logger.getLogger(PositionDao.class.getName());

    @Override
    public List<Position> findAllPosition() {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        List<Position> positionList=new ArrayList<>();
        try {
            Position position=new Position();
            sqlSession=dbAccess.getSqlSession();
            positionList=sqlSession.selectList("Position.findAllPosition",position);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return positionList;
    }

    @Override
    public Position findPositionById(int id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        Position position=new Position();
        try {
            sqlSession=dbAccess.getSqlSession();
            position=sqlSession.selectOne("Position.findPositionById", id);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return position;
    }

    @Override
    public Position findPositionByName(String name) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        Position position=new Position();
        try {
            sqlSession=dbAccess.getSqlSession();
            position=sqlSession.selectOne("Position.findPositionByName", name);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return position;
    }

    @Override
    public void savePosition(Position position) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession;
        try {
            sqlSession=dbAccess.getSqlSession();
            sqlSession.insert("Position.savePosition", position);
            sqlSession.commit();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
