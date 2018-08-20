package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ClientDao;

import com.capgemini.domain.ClientEntity;

@Repository
public class ClientDaoImpl extends AbstractDao<ClientEntity, Long> implements ClientDao {

}
