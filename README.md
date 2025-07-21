## githubApi

A simple Spring Boot application that exposes a REST endpoint to list all **non-forked** GitHub repositories of a given user. For each repository, the API returns:

- Repository name
- Owner login
- List of branches with branch name and last commit SHA

## API Endpoints

### GET `/api/userRepos/{username}`

Returns a list of non-forked repositories for the specified GitHub user.

**Example response for an existing user:**

```json
[
  {
    "repositoryName": "Bulls-Cows-Projekt-Python",
    "ownerLogin": "Deslej",
    "branches": [
      {
        "name": "main",
        "commitSha": "34106f9291b310a552115680f614ec970ff730e6"
      }
    ]
  },
  {
    "repositoryName": "carclub",
    "ownerLogin": "Deslej",
    "branches": [
      {
        "name": "master",
        "commitSha": "abb135511a88392fcf38550806c99d034a30209e"
      }
    ]
  }
]
```
**Example response for a non-existing user:**

```json
{
  "status": 404,
  "message": "User with name eslej doesn't exist"
}
```
