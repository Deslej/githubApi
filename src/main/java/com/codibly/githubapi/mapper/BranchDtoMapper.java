package com.codibly.githubapi.mapper;

import com.codibly.githubapi.dto.BranchDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class BranchDtoMapper {
    public static List<BranchDto> mapToBranchDto(JsonNode body){
        List<BranchDto> branchDtos = new ArrayList<>();
        for (JsonNode jsonNode : body) {
            String branchName = jsonNode.get("name").asText();
            String commitSha = jsonNode.get("commit").get("sha").asText();
            branchDtos.add(new BranchDto(branchName,commitSha));
        }
        return branchDtos;
    }
}
