package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.User;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;

    /**
     * 회원가입 (신규 등록)
     *
     * @param command
     * @return
     */
    public User addUser(AddUserCommand command) {

        User user = new User(command);
        userRepository.save(user);
        return user;
    }

    /**
     * 로그인
     *
     * @param userId
     * @param password
     */
    public void loginUser(String userId, String password) {
        userRepository.loginUser(userId, password);

    }
}