package com.productrank.api.domain.controller;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.domain.dto.CompanyDto;
import com.productrank.api.domain.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/company")
@Slf4j
public class CompanyController {
    private final CompanyService companyService;


    @GetMapping("/my")
    public ResponseEntity<List<CompanyDto>> getMyCompanyList(@AuthenticationPrincipal SecurityUser user){
        return ResponseEntity.ok(companyService.getCompanyList(user.getUser()));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> enrollCompany(@AuthenticationPrincipal SecurityUser user, @RequestBody CompanyDto dto){
        return ResponseEntity.ok(companyService.enrollCompany(user.getUser(), dto));
    }

    @PutMapping
    public ResponseEntity<CompanyDto> updateCompany(@AuthenticationPrincipal SecurityUser user, @RequestBody CompanyDto dto){
        return ResponseEntity.ok(companyService.updateCompany(user.getUser(), dto));
    }

    @DeleteMapping
    public ResponseEntity<CompanyDto> deleteCompany(@AuthenticationPrincipal SecurityUser user, @RequestBody CompanyDto dto){
        return ResponseEntity.ok(companyService.deleteCompany(user.getUser(), dto));
    }
}
