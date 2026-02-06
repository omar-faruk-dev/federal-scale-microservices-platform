# Architecture Overview

## System Context
This repository provides a production-style microservices baseline for federal-adjacent SaaS teams targeting AWS-aligned deployments while preserving cloud portability.

## Services
- **config-service**: Centralized configuration through Spring Cloud Config (native backend for local usage).
- **auth-service**: OAuth2/OIDC authorization server issuing JWTs and exposing identity/audit endpoint.
- **resource-service**: Protected CRUD API with RBAC, pagination, PostgreSQL persistence, Redis-backed caching, and audit logging.
- **gateway-service**: Edge API with routing, rate limiting, and Resilience4j circuit breaker fallback.

## Platform Components
- PostgreSQL 16 for durable transactional storage.
- Redis 7 for low-latency cache and gateway rate-limiter state.
- OpenTelemetry/Micrometer tracing and metrics through Spring Actuator endpoints.

## Security Model
- JWT issuance and validation via OAuth2/OIDC flows.
- RBAC through Spring Security method-level annotations.
- Audit logging filter and principal inspection endpoint.
- Secrets managed externally in production (AWS Secrets Manager / SSM Parameter Store recommended).

## Operational Profile Strategy
- `local`: Docker Compose + native config backend.
- `dev`: central config + shared lower environment infrastructure.
- `prod`: immutable container images, managed databases, secret managers, and policy controls.

## AWS-first but Cloud-neutral Mapping
- ECS/EKS deploy targets, ALB/NLB ingress, RDS PostgreSQL, ElastiCache Redis, CloudWatch + OTEL collector.
- Equivalent services on Azure/GCP can be substituted without code changes.
