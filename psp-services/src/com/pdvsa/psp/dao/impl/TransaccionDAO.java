package com.pdvsa.psp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pdvsa.psp.dao.ITransaccionDAO;
import com.pdvsa.psp.model.Transaccion;

@Repository
public class TransaccionDAO extends BaseDAO<Transaccion, Integer> implements ITransaccionDAO{

}
