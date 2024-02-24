package com.ispan.eeit69.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit69.dao.TeamMemberDao;
import com.ispan.eeit69.model.TeamMember;
import com.ispan.eeit69.service.Impl.member.TeamMemberService;

@Service
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {
	
	
	TeamMemberDao teamMemberDao;
	
//	@Autowired
	public TeamMemberServiceImpl(TeamMemberDao teamMemberDao) {
		this.teamMemberDao = teamMemberDao;
	}
	

	@Override
	public List<TeamMember> findAll() {
		List<TeamMember> TeamMembers = teamMemberDao.findAll();
		//System.out.println("Coupons fetched:TT " + coupons);  // 或使用Logger
		return TeamMembers;
		
	}

	

}
