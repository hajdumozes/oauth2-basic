### Prerequisites

- set up a local keycloak server
    - create realm
    - create user with password
    - create client with authentication and authorization enabled (it will create a client secret)
    - attach the created entities together with assigning
- set keycloak configurations in the `application.yml`

### Environment variables

| Name                | Format | Default value | Comment                                                                                                 |
|---------------------|--------|---------------|---------------------------------------------------------------------------------------------------------|
| `SERVER_PORT`       | string | 8081          |                                                                                                         |
| `CLIENT_ID`         | string |               |                                                                                                         |
| `CLIENT_SECRET`     | string |               | can be claimed after setting the client to have authentication and authorization (making it not public) |
| `INTROSPECTION_URI` | string |               | The pattern is: {server}/realms/{realm}/protocol/openid-connect/token/introspect                        |
| `LOG_LEVEL`         | string | DEBUG         |                                                                                                         |
