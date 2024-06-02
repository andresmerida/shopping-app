# shopping-app with Microservices
## We will build simple online shopping application
## We will cover:
- service discovery
- centralized configuration
- distributed tracing
- event driven architecture
- centralized logging
- circuit breaker
- secure microservice using
- keycloak

## Services
- Product Service, create and view products, acts as product catalog)
- Order Service, can order products
- Inventory Service, can check if product is in stock or not
- Notification Service, can send notifications, after order is placed

(Order service, Inventory service and notification service are going to interact with each other. Synchronous and Asynchronous communication)