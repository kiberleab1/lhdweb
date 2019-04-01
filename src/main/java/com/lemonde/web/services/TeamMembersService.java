package com.lemonde.web.services;

import java.util.List;

import com.lemonde.web.domains.TeamMembers;

public interface TeamMembersService {
	void save(TeamMembers teamMember);
	Iterable<TeamMembers>findAll();
	void deleteById(long id);
	TeamMembers findById(long id);
}
