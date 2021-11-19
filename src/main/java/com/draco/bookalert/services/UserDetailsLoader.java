package com.draco.bookalert.services;

import com.draco.bookalert.models.User;
import com.draco.bookalert.models.UserWithRoles;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

//      The Service
//     We will need to define a service that Spring Security will use to load the authentication and authorization information of users.

    private final UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users =  users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);
    }
}
