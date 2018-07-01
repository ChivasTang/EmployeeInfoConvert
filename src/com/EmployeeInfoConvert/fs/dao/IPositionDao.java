package com.EmployeeInfoConvert.fs.dao;

import com.EmployeeInfoConvert.fs.domain.Position;

import java.util.List;

public interface IPositionDao {
    List<Position> findAllPosition();
    Position findPositionById(int id);
    Position findPositionByName(String name);
    void savePosition(Position position);
}
