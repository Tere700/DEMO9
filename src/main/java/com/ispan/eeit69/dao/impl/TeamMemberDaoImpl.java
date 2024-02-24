package com.ispan.eeit69.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ispan.eeit69.dao.TeamMemberDao;
import com.ispan.eeit69.model.TeamMember;

@Repository
public class TeamMemberDaoImpl implements TeamMemberDao {

	private static final Logger logger = LoggerFactory.getLogger(TeamMemberDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TeamMember> findAll() {
		String hql = "FROM TeamMember";
		List<TeamMember> TeamMembers = entityManager.createQuery(hql, TeamMember.class).getResultList();// 返回一個結果列表，其中包含所有優惠券的對象。
		logger.info("Method findAll is called");
		System.out.println(TeamMembers);
		return TeamMembers;

	}

}
