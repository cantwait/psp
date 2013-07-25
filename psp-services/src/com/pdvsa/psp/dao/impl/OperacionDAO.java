package com.pdvsa.psp.dao.impl;

import org.springframework.stereotype.Repository;

import com.pdvsa.psp.dao.IOperacionDAO;
import com.pdvsa.psp.model.Operacion;

@Repository
public class OperacionDAO extends BaseDAO<Operacion, String> implements IOperacionDAO{

}
