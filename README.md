# SecurityDocker
Test project to learn Spring security and Docker, but also let me test Jenkins.

This app is deployed on heroku under this link https://securitydocker.herokuapp.com
Endpoints:
* /login - to authenicate user
* /loggin-success - when login procedure ended with success
* /login-failure -  when login procedure ended with success
* /api/{name} - e.g /api/James  Prints User name and surname as Json. This endpoint is available only for users with WRITE permissions (ADMIN role)
* /logout - to logout user

App consist simple authentication using spring boot.
Users are stored in H2 database and have different roles and permissions

Users available (username, password, Role):
* James Bond, GoldenEye, ADMIN
* Leonardo DiCaprio, Inception, USER
* Sophie Marceau, TommorowNeverDies, USER

Feel free to drop me a message on bartekkmitaa@gmail.com
