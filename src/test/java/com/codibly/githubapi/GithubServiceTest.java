package com.codibly.githubapi;

import com.codibly.githubapi.dto.RepositoriesResponseDto;
import com.codibly.githubapi.service.impl.GithubServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GithubServiceTest {

    @Autowired
    private GithubServiceImpl githubService;

    @Test
    void validUserReturnsRepositories() {
        String username = "Deslej";

        List<RepositoriesResponseDto> repositories = githubService.getRepositories(username);

        assertThat(repositories)
                .isNotEmpty()
                .allSatisfy(repo -> {
                    assertThat(repo.repositoryName()).isNotBlank();
                    assertThat(repo.ownerLogin()).isEqualToIgnoringCase(username);
                    assertThat(repo.branches()).isNotNull();
                    assertThat(repo.branches())
                            .allSatisfy(branch -> {
                                assertThat(branch.name()).isNotBlank();
                                assertThat(branch.commitSha()).matches("^[a-f0-9]{40}$");
                            });
                });
    }
}
