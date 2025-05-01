package org.example;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

public class GitLabRegistryCleaner {
    private static final String GITLAB_API_URL = "https://gitlab";
    private static final String PRIVATE_TOKEN = "";
    private static final String PROJECT_ID = "";

    public static void main(String[] args) throws GitLabApiException {

        try (GitLabApi gitLabApi = new GitLabApi(GITLAB_API_URL, PRIVATE_TOKEN)) {

            var a = gitLabApi.getContainerRegistryApi().getRepositories(PROJECT_ID).stream()
                    .filter(registryRepository -> registryRepository.getName().contains(""))
                    .toList();
            a.forEach(registryRepository -> {
                try {
                    gitLabApi.getContainerRegistryApi().deleteRepository(PROJECT_ID, registryRepository.getId());
                }
                catch (GitLabApiException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}
