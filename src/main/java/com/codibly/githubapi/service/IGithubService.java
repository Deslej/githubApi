package com.codibly.githubapi.service;

import com.codibly.githubapi.dto.RepositoriesResponseDto;

import java.util.List;

public interface IGithubService {

    /**
     *
     * @param username - Gtihub username
     * @return List of RepositoriesResponseDto representing non-fork repositories
     * with their branches and last commit sha
     */
    List<RepositoriesResponseDto> getRepositories (String username);
}
