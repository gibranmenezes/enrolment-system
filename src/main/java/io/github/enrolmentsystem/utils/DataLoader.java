package io.github.enrolmentsystem.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.github.enrolmentsystem.domain.user.Role;
import io.github.enrolmentsystem.domain.user.User;
import io.github.enrolmentsystem.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepository;
    

    //'@PostConstruct
    public void populateDataBase() {

        List<User> users = new ArrayList<>();

        var user1 = new User("Maria Silva" , "msilva", "msilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.ADMIN);
        var user2 = new User("João Silva" , "jsilva", "jsilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.INSTRUCTOR);
        var user3 = new User("Carla Silva" , "csilva", "csilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);
        var user4 = new User("Ana Silva" , "asilva", "asilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);
        var user5 = new User("Bruno Silva" , "bsilva", "bsilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);
        var user6 = new User("Daniel Silva" , "dsilva", "dsilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);
        var user7 = new User("Flávia Silva" , "fsilva", "fsilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);
        var user8 = new User("Gilson Silva" , "gsilva", "gsilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);
        var user9 = new User("Helena Silva" , "hsilva", "hsilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);
        var user10 = new User("Luis Silva" , "lsilva", "lsilva@gmail.com", "$2a$12$SChW9RhbUVJt2k4XmLonHOEHdvn0fXZlVmYtDpOXzowPHQNLKDZRm", Role.STUDENT);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

        users.forEach(u -> userRepository.save(u));

      
    }


}
