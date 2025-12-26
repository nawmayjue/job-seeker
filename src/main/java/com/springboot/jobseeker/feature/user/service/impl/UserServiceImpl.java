package com.springboot.jobseeker.feature.user.service.impl;

import com.springboot.jobseeker.feature.role.dto.RoleResponse;
import com.springboot.jobseeker.feature.skillcategory.dto.SkillCategoryResponse;
import com.springboot.jobseeker.feature.user.dto.UserRegisterRequest;
import com.springboot.jobseeker.feature.user.dto.UserResponse;
import com.springboot.jobseeker.feature.user.repository.jpa.UserJpaRepository;
import com.springboot.jobseeker.feature.user.service.UserService;
import com.springboot.jobseeker.feature.user.service.UserInfoDetails;
import com.springboot.jobseeker.shared.data.model.SkillCategory;
import com.springboot.jobseeker.shared.data.model.User;
import com.springboot.jobseeker.shared.exception.BadRequestException;
import com.springboot.jobseeker.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userJpaRepository.findByLoginUsername(username);
        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
    public String addUser(UserRegisterRequest userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        User user = User.builder()
                .fullName(userInfo.getFullName())
                .loginUsername(userInfo.getLoginUsername())
                .password(userInfo.getPassword())
                .loginEmail(userInfo.getLoginEmail())
                .build();

        userJpaRepository.save(user);
        return "User Added Successfully";
    }

    @Override
    public UserResponse retrieveUserById(Long id) {
        User user = userJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill Category Not Found."));

//        RoleResponse role = roleJpaRepository.findById(user.getRole().getId());
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .loginUsername(user.getLoginUsername())
                .loginEmail(user.getLoginEmail())
//                .roleResponse(role)
                .build();
    }

    @Override
    public List<UserResponse> retrieveAllUsers() {
        List<User> users = userJpaRepository.findAll();
        return users.stream()
                .filter(user ->
                        user.getDeletedAt() == null &&
                                user.getDeletedBy() == null
                )
                .map(user -> UserResponse.builder()
                                .id(user.getId())
                                .fullName(user.getFullName())
                                .loginUsername(user.getLoginUsername())
                                .loginEmail(user.getLoginEmail()).build()
//                .roleResponse(role)
                )
                .toList();
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userJpaRepository.existsById(id)){
            throw new RuntimeException("User with id " + id + " doesn't exist");
        }

        userJpaRepository.deleteById(id);
    }
}
