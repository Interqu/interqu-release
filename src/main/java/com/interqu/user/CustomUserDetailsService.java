package com.interqu.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.interqu.db.RoleRepository;
import com.interqu.db.UserRepository;
import com.interqu.roles.Role;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
            User user = userRepo.findByEmail(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            System.out.println(user);

            return new CustomUserDetails(user,getAuthorities(user.getRoles()));
        } catch (Exception e) {
            // e.printStackTrace();
            System.err.println("User not found: " + username);
        }
        throw new UsernameNotFoundException("User not found");
	}
    private List<GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
