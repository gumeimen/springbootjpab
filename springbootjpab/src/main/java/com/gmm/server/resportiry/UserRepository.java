package com.gmm.server.resportiry;

import com.gmm.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**CrudRepository这个也可以换成JpaRepository
 * Created by john on 2017-10-01.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 修改用户利用@Query方式;
     * @param name
     * @param gender
     * @param age
     * @param id
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user as u SET u.NAME= ?1,u.gender= ?2,u.age= ?3 WHERE u.id = ?4",nativeQuery = true)
    public int updateUser(String name,String gender,Integer age,Integer id);
}
