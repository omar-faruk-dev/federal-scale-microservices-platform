# federal-scale-microservices-platform

Production-grade Java 21 + Spring Boot microservices reference architecture tailored for U.S. remote engineering interviews and federal-adjacent SaaS delivery.

## What this repository demonstrates

- API gateway pattern with Spring Cloud Gateway.
- OAuth2/OIDC auth service issuing JWTs.
- Config server for centralized environment configuration.
- Domain resource service with CRUD + pagination.
- PostgreSQL persistence and Redis-backed caching/rate limiting.
- OpenTelemetry-compatible tracing + Actuator metrics.
- Circuit breakers, fallback endpoints, and health probes.
- Docker Compose local platform stack.
- GitHub Actions CI pipeline using Maven verify.

## Repository structure

```text
/services
  /gateway-service
  /auth-service
  /resource-service
  /config-service
/docs
  /diagrams
  architecture.md
/scripts
/.github/workflows
/docker-compose.yml
/README.md
```

## Architecture

See [`docs/architecture.md`](docs/architecture.md) for detailed design rationale and AWS-first/cloud-neutral mapping.

## Local startup

### Prerequisites
- Java 21
- Maven 3.9+
- Docker + Docker Compose

### Run locally

```bash
./scripts/bootstrap.sh
```

Or manually:

```bash
mvn -B -ntp clean package
docker compose up --build
```

### Endpoints

- Gateway: `http://localhost:8080`
- Auth: `http://localhost:8081`
- Resource API: `http://localhost:8082/api/resources`
- Config server: `http://localhost:8888`

## Security model

- Auth service runs Spring Authorization Server with OIDC enabled.
- JWT tokens are validated at gateway/resource-service boundaries.
- RBAC enforcement:
  - `ROLE_ADMIN`: mutate resources
  - `ROLE_USER` / `ROLE_ADMIN`: read resources
- Audit logging:
  - `auth-service` principal inspection endpoint
  - `resource-service` request audit filter
- Secrets management (production recommendation):
  - Use externalized secrets with AWS Secrets Manager / AWS SSM Parameter Store.
  - Inject secrets at runtime via environment variables or platform secret mounts.

## Encryption and compliance notes

- TLS termination should be enforced at ingress (ALB/API Gateway/mesh).
- Service-to-service mTLS is recommended in regulated environments.
- At-rest encryption should be enabled for RDS/EBS/ElastiCache equivalents.
- Centralized audit retention should follow NIST/FedRAMP policy baselines.

## OpenAPI

- Auth service: `/swagger-ui/index.html`
- Resource service: `/swagger-ui/index.html`

## CI pipeline

GitHub Actions workflow (`.github/workflows/ci.yml`) performs:

1. Checkout
2. JDK 21 setup with Maven cache
3. `mvn -B -ntp verify`

## Profiles

- `local`: docker-compose defaults + native config server backend.
- `dev`: intended for shared integration environments.
- `prod`: intended for externalized configs, hardened security, and managed infrastructure.

## Future roadmap

- Replace in-memory users/clients with persistent identity store.
- Add Testcontainers-based integration tests for PostgreSQL/Redis.
- Add policy-as-code (OPA) integration and ABAC examples.
- Add Helm/Terraform deployment assets for AWS GovCloud.
- Add centralized OTEL collector and SIEM forwarding templates.
