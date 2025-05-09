package com.Abhijith.EcomStore.Repository;


import com.Abhijith.EcomStore.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

}
