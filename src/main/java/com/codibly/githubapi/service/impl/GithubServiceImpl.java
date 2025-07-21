package com.codibly.githubapi.service.impl;

import com.codibly.githubapi.dto.RepositoriesResponseDto;
import com.codibly.githubapi.dto.UserRepositoryDto;
import com.codibly.githubapi.exception.NotExistingUserException;
import com.codibly.githubapi.mapper.BranchDtoMapper;
import com.codibly.githubapi.mapper.RepositoriesResponseMapper;
import com.codibly.githubapi.mapper.UserRepositoryDtoMapper;
import com.codibly.githubapi.service.IGithubService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class GithubServiceImpl implements IGithubService {

    private final RestTemplate restTemplate = new RestTemplate();
    /**
     * @param username - Github username
     * @return List of RepositoriesResponseDto representing non-fork repositories
     * with their branches and last commit sha
     */
    @Override
    public List<RepositoriesResponseDto> getRepositories(String username) {

        List<UserRepositoryDto> userRepository = getUserRepositories(username);

        return getRepositoriesResponseDto(userRepository);
    }

    private List<UserRepositoryDto> getUserRepositories(String username){
        try {
            String url = String.format(Locale.US, "https://api.github.com/users/%s/repos", username);
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode body = response.getBody();

            return UserRepositoryDtoMapper.mapToUserRepositoryDto(body);
        }catch(HttpClientErrorException e) {
            throw new NotExistingUserException(String.format("User with name %s doesn't exist", username));
        }
    }

    private List<RepositoriesResponseDto> getRepositoriesResponseDto(List<UserRepositoryDto> userRepository){
        List<RepositoriesResponseDto> repositoriesResponseDtos = new ArrayList<>();
        for (UserRepositoryDto userRepositoryDto : userRepository) {
            String url = String.format(Locale.US,
                    "https://api.github.com/repos/%s/%s/branches",
                    userRepositoryDto.ownerLogin(),userRepositoryDto.repositoryName());
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode body = response.getBody();
            repositoriesResponseDtos.add(
                    RepositoriesResponseMapper.mapToRepositoriesResponseDto(
                            userRepositoryDto,BranchDtoMapper.mapToBranchDto(body))
            );
        }

        return repositoriesResponseDtos;
    }

}
