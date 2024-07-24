package com.green.greengram.user;

import com.green.greengram.entity.User;
import com.green.greengram.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
                                                        /*Entity , PK 타입*/
   List<UserRole> findAllByUser(User User);
                        /*필드명*/
}
