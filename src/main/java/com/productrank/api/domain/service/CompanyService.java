package com.productrank.api.domain.service;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.config.security.custom.SecurityUserDetailService;
import com.productrank.api.domain.dto.CompanyDto;
import com.productrank.api.domain.dto.UserDto;
import com.productrank.api.domain.entity.Company;
import com.productrank.api.domain.entity.User;
import com.productrank.api.domain.repository.CompanyRepository;
import com.productrank.api.domain.repository.UserRepository;
import com.productrank.api.error.ErrorCode;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    @Transactional
    @Cacheable("users")
    public User getuserByEmail(String email){
        return userRepository.findByEmail(email).get();
    }
    @Transactional
    public CompanyDto enrollCompany(User user, CompanyDto companyDto){
        User userEntity = getuserByEmail(user.getEmail());

        Company company = Company.builder()
                .companyNumber(companyDto.companyNumber())
                .companyDescription(companyDto.companyDescription())
                .companyName(companyDto.companyName())
                .build();
        company.madeBy(userEntity);
        return CompanyDto.from(companyRepository.save(company));
    }

    public List<CompanyDto> getCompanyList(User user) {
        return companyRepository.findByUserId(user.getId())
                .stream().map(CompanyDto::from).collect(Collectors.toList());
    }
}
