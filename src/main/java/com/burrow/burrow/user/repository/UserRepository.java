package com.burrow.burrow.user.repository;

import com.burrow.burrow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
