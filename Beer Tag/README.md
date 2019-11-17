# BEER TAG APPLICATION 
### (by Yavor Yankov and Tihomira Tacheva)

**BEER TAG** is web application which enables its users to add, remove or edit beer in the application and to manage as well all the beers that they have drank and want to drink, to rate them, to add them tags.

## Getting Started
Anyone who go in to the application can view all of the beers that have been added to the application, as well as view main features of the beers by clicking on the **"Beers"** button.
The main features of a beer are:

- the **name** of the beer;
- how much alcohol it contains **ABV** (alcohol by volume);
- from which **brewery** it is made;
- in which **country**;
- what is the beer’s **style**;
- as well as optional beer’s **descriptions** and its **photo**. 
- Anyone can also see the **average rating** of each beer that registered users have given for it.

##### If the user wants more rights, he needs to register in the application with the button "Register" or if he has already registered, must log in to his account through the button "Login".

Once logged in, each user is already authorized to add new beers, edit and delete existing beers. This can be done by approaching the **"Beers"** page and clicking on the **"Create Beer"** button, or by approaching the specific page of each beer through **"Beer Details"**. Going into each specific page of beer, the registered user has options to modify the data of the same beer by using the **"Update beer"** button or to delete it by **"Delete beer"**.
In addition, he gets the right to add **BEER TAGS** via the **"Add tag"** button, as well as to give the beer rating through the **"Give Rate"** button and in addition to his _own rating_ he could see the _average rating_ that all users have given it by **"Rating"** button. Logged-in users also have an option of marking a beer whether they would like to drink it or they have already drunk it with **"Give Status"** button. Doing the latter, each user is greeted with a specific greeting message indicating whether they want to drink it or have already drunk it.

Each **_registered user_** through the **"Users"** button has access to their own profile information. **_Administrators_** only have access through the **"Users"** button to all _registered users_, including their own account. Each user on his own profile page can find information about himself:
- username;
- email;
- photo;
- also which are the **top 3 most ranked beers** that he has given the highest rating on them.

On the Users page there is as well an **"Update"** button with which he can modify his own data, as well as **"Delete"** that can remove its own account from the application. **Administrators** have the same options and in addition to the common they can access, modify and delete other users accounts from the application's system.

> Every user can log out via the **"Logout"** button.
> The homepage of the application is also accessible on every page via the **"Home"** button.


### Used TECH int the app

BACKEND:

* **JDK** version is 1.8.0_221
* **IntelliJ IDEA**
* **SpringMVC** and **SpringBoot** framework
* **JPA** in the Persistence layer
* **Spring Security** to handle user registration and user roles
* **100%** tests coverage

UI:

* [Spring MVC Framework] with [Thymeleaf] template engine for generating the UI!
* [HTML] - markup language for documents designed to be displayed in a web browser.
* [CSS] - style sheet language used for describing the presentation of a document written in.
* [Bootstrap] - great UI boilerplate for modern web apps
* [jQuery] - duh

DATABASE:

* [SQL] - domain-specific language used in programming and designed for managing data held in a relational database management system (RDBMS)
* [MariaDB] - fork of the MySQL relational database management system (RDBMS).

API DOCUMENTATION:

* [Swagger] - give information for successfully consuming and integrating with an API.

ADDITIONAL:

* [GitLab] - distributed version-control system for tracking changes.
Link to App: https://gitlab.com/yavoryankov83/beer-tag-web-application
* [Trello] - a visual tool for organizing your work. 
Link to App Trello: https://trello.com/b/HSioFcc6/beer-tag-web-application
* **AWS** - deployement of the application code to a virtual machine on AWS.
**LINK TO APPLICATION:** http://beer-tag.ddns.net/

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [SQL]: <https://www.tutorialspoint.com/sql/index.htm>
   [HTML]: <https://www.tutorialspoint.com/html/index.htm>
   [CSS]: <https://getbootstrap.com/docs/3.4/css/>
   [GitLab]: <https://gitlab.com/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Bootstrap]: <https://getbootstrap.com/>
   [jQuery]: <http://jquery.com>
   [Trello]: <https://trello.com/>
   [Swagger]: <https://swagger.io/>
   [Spring MVC Framework]: <https://spring.io/>
   [Thymeleaf]: <https://www.thymeleaf.org/>
   [MariaDB]: <https://mariadb.org/>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
