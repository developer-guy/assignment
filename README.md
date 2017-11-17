![alt text](https://raw.githubusercontent.com/developer-guy/assignment/master/eureka.png)
![alt text](https://raw.githubusercontent.com/developer-guy/assignment/master/docker.png)


# Assignment


## Getting Started
Firstly , you must run maven command inside each project :

    mvn package docker:build 

## Running

To run docker image which was built by maven command :
  
    docker run -p 8080:8080 todo-app -e MONGO_DB='assignment' \ 
    -e MONGO_USER='bapaydin67' \
    -e MONGO_PASSWORD='xxxxxx' \
    -e MONGO_PORT='13046' \
    -e MONGO_HOST='ds113046.mlab.com' \
    --link eureka-server

    docker run -p 8761:8761  eureka-server 
    
Some curl commands :

    9909  curl -X POST -H "Content-Type: application/json" -d '{"number":1}' http://localhost:8080/resource/save
    9910  curl -X GET http://localhost:8080/resource/find/1
    9917  curl -X DELETE http://localhost:8080/resource/delete/0
    9929  curl -X GET http://localhost:8080/resource/max
    9930  curl -X GET http://localhost:8080/resource/min
    9933  curl -X GET http://localhost:8080/resource\?type\=ASC | json_pp
    9935  curl -X GET http://localhost:8080/resource\?type\=DESC | json_pp

## Built With

Maven

## Authors

* **Batuhan Apaydın** - *Initial work* - [SAHABT](https://github.com/developer-guy)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

