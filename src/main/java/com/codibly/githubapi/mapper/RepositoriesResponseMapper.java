package com.codibly.githubapi.mapper;

import com.codibly.githubapi.dto.BranchDto;
import com.codibly.githubapi.dto.RepositoriesResponseDto;
import com.codibly.githubapi.dto.UserRepositoryDto;

import java.util.List;

public class RepositoriesResponseMapper {
    public static RepositoriesResponseDto mapToRepositoriesResponseDto (
            UserRepositoryDto userRepositoryDto, List<BranchDto> branchDtos) {
        return new RepositoriesResponseDto(
                userRepositoryDto.repositoryName(),
                userRepositoryDto.ownerLogin(),
                branchDtos);
    }
}
