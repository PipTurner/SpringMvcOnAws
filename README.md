# SpringMvcOnAws
A project for a Spring MVC Web application with a MySQL back end that has a WAR that is ready to be deployed to AWS.

This is a fairly basic Spring Web MVC application that allows users to create an account, login, logout and get a quote once they have logged in.

The login is all performed by Spring Security and it ties in with the MySql database to store account credentials. The quotes are also stored in the database and a random one is selected and displayed once the user has logged in.

User the \ReadyToDeployWar folder there is a .war file that is an artifact of this project and is what you need to deploy it to AWS. You can also  
