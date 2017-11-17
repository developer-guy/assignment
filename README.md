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

## Built With


## Authors

* **Batuhan Apaydın** - *Initial work* - [SAHABT](https://github.com/developer-guy)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

