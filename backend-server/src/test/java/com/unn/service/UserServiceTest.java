package com.unn.service;

import com.unn.model.User;
import com.unn.service.impl.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private final Long userTypeId = 0L;
    private final String username = "username";
    private final String password = "password";
    private final String mail = "mail";
    private final User user = new User(userTypeId, username, password, mail);
    
    @Test
    public void createUserTest() {
        userService.clearTable();
        Optional<User> createdUser = userService.createUser(user);
        Assert.assertEquals(createdUser.get().getUsername(), username);
        Assert.assertEquals(createdUser.get().getPassword(), password);
        Assert.assertEquals(createdUser.get().getMail(), mail);
        userService.clearTable();
    }

    @Test
    public void findUserByIdTest() {
        userService.clearTable();
        Optional<User> createdUser = userService.createUser(user);
        Optional<User> retUser = userService.findUser(createdUser.get().getId());
        Assert.assertEquals(retUser.get().getUsername(), username);
        Assert.assertEquals(retUser.get().getPassword(), password);
        Assert.assertEquals(retUser.get().getMail(), mail);
        userService.clearTable();
    }

    @Test
    public void findUserByMailTest() {
        userService.clearTable();
        Optional<User> createdUser = userService.createUser(user);
        Optional<User> retUser = userService.findUser(createdUser.get().getMail());
        Assert.assertEquals(retUser.get().getUsername(), username);
        Assert.assertEquals(retUser.get().getPassword(), password);
        Assert.assertEquals(retUser.get().getMail(), mail);
        userService.clearTable();
    }

    @Test
    public void deleteUserByIdTest() {
        userService.clearTable();
        Optional<User> createdUser = userService.createUser(user);
        userService.deleteUser(createdUser.get().getId());
        Optional<User> retUser = userService.findUser(mail);
        Assert.assertTrue(retUser.isEmpty());
        userService.clearTable();
    }

    @Test
    public void deleteUserByMailTest() {
        userService.clearTable();
        Optional<User> createdUser = userService.createUser(user);
        userService.deleteUser(createdUser.get().getMail());
        Optional<User> retUser = userService.findUser(mail);
        Assert.assertTrue(retUser.isEmpty());
        userService.clearTable();
    }

    @Test
    public void updateUserByMailTest() {
        final String usernameNew = "username1";
        final String passwordNew = "password1";

        userService.clearTable();
        Optional<User> createdUser = userService.createUser(user);
        User modifyUser = createdUser.get();
        modifyUser.setUsername(usernameNew);
        modifyUser.setPassword(passwordNew);
        userService.updateUser(modifyUser);
        Optional<User> retUser = userService.findUser(mail);
        Assert.assertEquals(retUser.get().getUsername(), usernameNew);
        Assert.assertEquals(retUser.get().getPassword(), passwordNew);
        Assert.assertEquals(retUser.get().getMail(), mail);
        userService.clearTable();
    }
}