package com.codibly.githubapi.controller;

import com.codibly.githubapi.dto.RepositoriesResponseDto;
import com.codibly.githubapi.service.IGithubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class GithubController {
    private final IGithubService iGithubService;

    public GithubController(IGithubService iGithubService) {
        this.iGithubService = iGithubService;
    }

    @GetMapping("/userRepos")
    public ResponseEntity<List<RepositoriesResponseDto>> getRepositories(@RequestParam String username){
        List<RepositoriesResponseDto> repositories = iGithubService.getRepositories(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repositories);
    }
}
