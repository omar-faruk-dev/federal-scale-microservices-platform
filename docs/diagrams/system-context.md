# Diagram (Textual)

```text
[Client] -> [Gateway Service] -> [Auth Service]
                        \--> [Resource Service] -> [PostgreSQL]
                                               \-> [Redis]

[Config Service] ---> [Gateway/Auth/Resource] at startup
[Actuator + OTEL metrics/traces] ---> observability backend
```
