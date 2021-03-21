package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.OperatorDaoImpl;
import com.course_project.data_access.model.Operator;

import java.util.List;

public class OperatorManager extends Manager {
    public static Operator transfer;
    private OperatorDaoImpl operatorDao;
    private Operator operator;

    public OperatorManager() {
        operatorDao = new OperatorDaoImpl(dataSource);
        operator = new Operator();
    }

    public OperatorDaoImpl getOperatorDao() {
        return operatorDao;
    }

    public List<Operator> getOperators() {
        return operatorDao.findByAll();
    }

    public Operator getOperator(Long id) {
        return operatorDao.findById(id);
    }

    public Operator getOperator(String login) {
        return operatorDao.findByLogin(login);
    }

    public boolean create(Operator operator) {
        return operatorDao.insert(operator);
    }

    public boolean delete(Operator operator) {
        return operatorDao.delete(operator);
    }

    public boolean update(Operator operator) {
        return operatorDao.update(operator);
    }


}
