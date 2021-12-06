package com.epam.brest.service.impl;

import com.epam.brest.dao.DepartmentDao;
import com.epam.brest.model.Department;
import com.epam.brest.service.DepartmentService;
import com.epam.brest.service.exceptions.DepartmentNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        logger.trace("findAll()");
        return departmentDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(Integer departmentId) {
        logger.debug("Get department by id = {}", departmentId);
        try {
            return this.departmentDao.getDepartmentById(departmentId);
        } catch (EmptyResultDataAccessException e) {
            throw new DepartmentNotFoundException(departmentId);
        }
    }

    @Override
    @Transactional
    public Integer create(Department department) {
        logger.debug("create({})", department);
        return this.departmentDao.create(department);
    }

    @Override
    @Transactional
    public Integer update(Department department) {
        logger.debug("update({})", department);
        return this.departmentDao.update(department);
    }

    @Override
    @Transactional
    public Integer delete(Integer departmentId) {
        logger.debug("delete department with id = {}", departmentId);
        return this.departmentDao.delete(departmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        logger.debug("count()");
        return this.departmentDao.count();
    }
}