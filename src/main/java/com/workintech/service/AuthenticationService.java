package com.workintech.service;

import com.workintech.entity.Member;
import com.workintech.entity.Role;
import com.workintech.repository.MemberRepository;
import com.workintech.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public Member register(String email, String password){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent()){
            throw new RuntimeException("User with given email already exist! Email: "+email);
        }
        String encodedPassword = passwordEncoder.encode(password);
        List<Role> roleArrayList = new ArrayList<>();
        Optional<Role> roleAdmin = roleRepository.findByAuthority("USER");
        if(roleAdmin.isEmpty()){
            Role roleAdminEntity = new Role();
            roleAdminEntity.setAuthority("USER");
            roleArrayList.add(roleRepository.save(roleAdminEntity));
        }else{
            roleArrayList.add(roleAdmin.get());
        }
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setAuthorities(roleArrayList);
        return memberRepository.save(member);
    }
}
