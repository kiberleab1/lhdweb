package com.lemonde.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lemonde.web.domains.TeamMembers;

public interface TeamMembersRepository extends CrudRepository<TeamMembers,Long> {

}
