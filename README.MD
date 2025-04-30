Clone the project
```
git clone https://github.com/MelArt24/lab-03-starter-project-kotlin.git
```

Navigate to the project directory
```
cd notes-app
```

Build the Docker image
```
docker build -t notes_app .
```

Run the project
```
docker-compose up -d
```

Join the application
```
docker exec -it notes_app /bin/sh
```

Manually run the program inside the container
```
java -jar app.jar
```