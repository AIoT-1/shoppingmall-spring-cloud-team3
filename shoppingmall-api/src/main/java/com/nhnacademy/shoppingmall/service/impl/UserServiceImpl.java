package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.enums.Auth;
import com.nhnacademy.shoppingmall.exception.user.UserLoginIdDuplicateException;
import com.nhnacademy.shoppingmall.exception.user.UserNotFoundException;
import com.nhnacademy.shoppingmall.exception.user.UserTerminatedException;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDto.Read.Response getUser(Long id) {
        User user = getUserById(id);
        validateUserNotTerminated(user);
        return UserDto.Read.Response.fromEntity(user);
    }

    @Override
    public UserDto.Create.Response createUser(UserDto.Create.Request request) {
        User user = request.toEntity();
        if (userRepository.existsByLoginId(user.getLoginId())) {
            throw new UserLoginIdDuplicateException(request.getLoginId());
        }
        User savedUser = userRepository.save(user);
        return UserDto.Create.Response.fromEntity(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<UserDto.Read.Response> getUserListPageByAuth(Pageable pageable, Auth auth) {
        Page<UserDto.Read.Response> page= userRepository.findPageByAuth(pageable, auth.name())
                .map(UserDto.Read.Response::fromEntity);
        return PageResponseDto.fromPage(page);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto.UserDetails.Response getUserByLoginId(String loginId) {

        return userRepository.findByLoginId(loginId)
                            .map(UserDto.UserDetails.Response::fromEntity)
                            .orElse(null);
    }

    @Override
    public void updateUser(Long id, UserDto.Update.Request request) {
        User user = getUserById(id);
        validateUserNotTerminated(user);
        user.updateUser(request.getName(), request.getPassword(), request.getBirthDate());
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        validateUserNotTerminated(user);
        user.terminate();
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    private void validateUserNotTerminated(User user) {
        if (user.isTerminated()) {
            throw new UserTerminatedException(user.getId());
        }
    }
}
