package com.lemonde.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemonde.web.domains.TeamMembers;
import com.lemonde.web.repositories.TeamMembersRepository;

@Service
public class TeamMemberServiceImpl implements TeamMembersService {

	@Autowired
	private TeamMembersRepository teamMembersRepositoriy;

	@Override
	public void save(TeamMembers teamMember) {
		// TODO Auto-generated method stub
		this.teamMembersRepositoriy.save(teamMember);
	}

	@Override
	public Iterable<TeamMembers> findAll() {
		// TODO Auto-generated method stub
		return this.teamMembersRepositoriy.findAll();
	}

	@Override
	public void deleteById(long id) {
		this.teamMembersRepositoriy.deleteById(id);

	}

	@Override
	public TeamMembers findById(long id) {
		// TODO Auto-generated method stub
		return this.teamMembersRepositoriy.findById(id).get();
	}

}
