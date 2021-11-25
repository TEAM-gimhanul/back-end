package com.backend.gimhanul.domain.user.domain.repositories;

import com.backend.gimhanul.domain.user.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	@Query(nativeQuery = true, value = "select * from tbl_user a where a.id != :userId order by RAND() limit 1")
	User getUserRamdom(String userId);
}
