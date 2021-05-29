

````bash
mvn clean package docker:build
````


````bash
docker image ls
````


````bash
docker compose up --build
````

OR

````bash
docker swarm init
docker stack deploy --compose-file docker-compose.yaml batch-process
docker stack services batch-process
docker stack rm batch-process
````