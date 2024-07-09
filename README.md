# ActiveMQ Docker Compose Setup

## Overview
This project provides a Docker Compose setup for running Apache ActiveMQ 5.17.1 with Hawtio 2.14.0 console integration.

## Prerequisites
- Docker
- Docker Compose

## Quick Start
1. Clone this repository:
2. Start the ActiveMQ container:

3. Access the Hawtio web console:
Open `http://localhost:8161/hawtio` in your browser
Default credentials: admin / admin

## Configuration

### Environment Variables
Adjust these in `docker-compose.yml`:
- `ACTIVEMQ_ADMIN_LOGIN`: Admin username (default: admin)
- `ACTIVEMQ_ADMIN_PASSWORD`: Admin password (default: admin)
- `ACTIVEMQ_OPTS_MEMORY`: JVM memory settings (default: "-Xms1G -Xmx4G")

### Ports
- 61616: OpenWire
- 8161: Web Console (Hawtio)
- 61613: STOMP
- 1883: MQTT
- 61614: WebSocket

### Volumes
- `./activemq-data:/opt/activemq/data`: Persists ActiveMQ data
- `./config/activemq.xml:/opt/activemq/conf/activemq.xml`: Custom ActiveMQ configuration
- `./jetty-realm.properties:/opt/activemq/conf/jetty-realm.properties`: Authentication configuration
- `./login.config:/opt/activemq/conf/login.config`: JAAS login configuration
- `./users.properties:/opt/activemq/conf/users.properties`: User credentials
- `./groups.properties:/opt/activemq/conf/groups.properties`: User group assignments

## Custom Configuration
1. Modify `config/activemq.xml` for ActiveMQ settings
2. Update `users.properties` and `groups.properties` for user management
3. Adjust `jetty-realm.properties` and `login.config` for authentication settings

## Logging
View logs:


## Stopping the Service
To stop and remove the containers:


## Security Considerations
- Change default passwords in production environments
- Consider enabling SSL/TLS for secure communications
- Regularly update the ActiveMQ image to the latest version

## Troubleshooting
If you encounter issues:
1. Check the logs using `docker-compose logs activemq`
2. Ensure all configuration files are correctly mounted
3. Verify network settings and port availability

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License.
