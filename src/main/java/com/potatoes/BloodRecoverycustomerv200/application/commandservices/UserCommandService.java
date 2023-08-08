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
     * 회원 가입
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
     * ID 중복 확인
     *
     * @param userId
     * @return
     */
    public boolean isDuplicateId(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user.getUserId().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 닉네임 중복 확인
     *
     * @param nickname
     * @return
     */
    public boolean isDuplicateNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        if (user.getNickname().isEmpty()) {
            return true;
        }
        return false;
    }



    /**
     * 로그인
     *
     * @param userId
     */
    public User loginUser(String userId) {
        User user = userRepository.findByUserId(userId);

        // validation
        if (user.getUserId().equals(null)) {
            try {
                throw new Exception(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

}