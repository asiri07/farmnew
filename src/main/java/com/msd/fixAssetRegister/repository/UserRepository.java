package com.msd.fixAssetRegister.repository;



import com.msd.fixAssetRegister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT us FROM User AS us WHERE us.individual = 1 ")
    List<User> findIndividualUser();

}
