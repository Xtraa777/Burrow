package com.burrow.burrow.user.security;

import com.burrow.burrow.user.entity.User;
import com.burrow.burrow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails getUserDetails(String uid) {
        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found" + uid));
        return new UserDetailsImpl(user);
    }
}
