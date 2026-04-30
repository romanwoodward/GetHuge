## Grails 7.0.11-SNAPSHOT Documentation

- [User Guide](https://grails.apache.org/docs/snapshot/guide/index.html)
- [API Reference](https://grails.apache.org/docs/snapshot/api/index.html)
- [Grails Guides](https://guides.grails.org/index.html)
---

## Feature geb-with-testcontainers documentation

- [Grails Geb Functional Testing for Grails with Testcontainers documentation](https://github.com/apache/grails-geb#readme)

- [https://groovy.apache.org/geb/manual/current/](https://groovy.apache.org/geb/manual/current/)

## Feature testcontainers documentation

- [https://java.testcontainers.org/](https://java.testcontainers.org/)

## Feature spring-boot-devtools documentation

- [Grails SpringBoot Developer Tools documentation](https://docs.spring.io/spring-boot/reference/using/devtools.html)

## Feature views-json documentation

- [Grails JSON Views documentation](https://grails.apache.org/docs/snapshot/guide/theWebLayer.html)

## Feature asset-pipeline-grails documentation

- [Grails Asset Pipeline documentation](https://github.com/wondrify/asset-pipeline#readme)

## Feature scaffolding documentation

- [Grails Scaffolding documentation](https://grails.apache.org/docs/snapshot/guide/scaffolding.html)

## Google OAuth Setup

This app uses the Grails Spring Security OAuth2 plugin plus the Google provider extension.

Set these environment variables before starting the app:

- `GOOGLE_API_KEY`
- `GOOGLE_API_SECRET`

In Google Cloud Console, create an OAuth 2.0 client and add this authorized redirect URI for local development:

- `http://localhost:8080/oauth2/google/callback`

After Google returns to the app, it automatically creates the local user record on first login, stores the linked OAuth identity, and then sends the user to the dashboard.

The login button in the app uses the `google` provider name configured in [grails-app/conf/application.yml](grails-app/conf/application.yml).

