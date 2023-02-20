package com.productrank.api.domain.controller;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.domain.dto.CompanyDto;
import com.productrank.api.domain.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/company")
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
}
