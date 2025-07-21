package com.codibly.githubapi.dto;

import java.util.List;

public record RepositoriesResponseDto(String repositoryName, String ownerLogin, List<BranchDto> branches) { }
