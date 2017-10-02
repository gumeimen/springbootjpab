package com.gmm.server.service;

import com.gmm.server.model.User;
import com.gmm.server.resportiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Created by john on 2017-10-01.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //查询所有用户;
    public Iterable<User> findAll(){
        Iterable<User> userIterable = userRepository.findAll();
        return userIterable;
    }
    //查询单个用户;
    public User findOne(Integer id){
        User user = userRepository.findOne(id);
        return user;
    }

    //添加用户;
    @Transactional
    public User addUser(User user){
        User user1 = userRepository.save(user);
        return user1;
    }

    //删除用户;
    @Transactional
    public void deleteUser(Integer id){

        userRepository.delete(id);
    }

    //修改用户,第一种修改方式;(原始的方法)
    @Transactional
    public void updateUser1(User user) {
        User user1 = userRepository.findOne(user.getId());
        if (user1 != null) {
            if (user.getAge() != null) {
                user1.setAge(user.getAge());
            }
            if (user.getGender() != null) {
                user1.setGender(user.getGender());
            }
            if (user.getName() != null) {
                user1.setName(user.getName());
            }
            userRepository.save(user1);
        }
    }


    //修改用户,第二种修改方式;(使用注解方式)
    @Transactional
    public void updateUser2(Integer id, String name, String gender, Integer age) {
        userRepository.updateUser(name,gender,age,id);
    }

}
