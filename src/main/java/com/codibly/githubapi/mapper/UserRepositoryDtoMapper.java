package com.codibly.githubapi.mapper;

import com.codibly.githubapi.dto.UserRepositoryDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDtoMapper {

    public static List<UserRepositoryDto> mapToUserRepositoryDto (JsonNode body){
        List<UserRepositoryDto> userRepositoryDtos = new ArrayList<>();
        for (JsonNode jsonNode : body) {
            if(!jsonNode.get("fork").asBoolean()) {
                String repositoryName = jsonNode.get("name").asText();
                String ownerLogin = jsonNode.get("owner").get("login").asText();
                userRepositoryDtos.add(new UserRepositoryDto(ownerLogin, repositoryName));
            }
        }
        return userRepositoryDtos;
    }
}
