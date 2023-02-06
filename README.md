# cordova-plugin-sql-direct-android
cordova plugin to execute sql query direct from android app 

#### for security reason, execute sql query direct from android app is totally risk. this plugin for education only!

this plugin implemented from mysql connector jdbc (https://dev.mysql.com/downloads/connector/j/). it allow you to execute sql query (select/insert/update/delete) from your cordova android app. 

### Tested on:

- NodeJS  	      : 19.4.0
- Cordova 	      : 11.1.0
- cordova-android : 11.0 
- Java 11  
- MariaDB : 10.4.27 (xampp)

### Add SQL User to SQL Server Database (MariaDB)

first of all, we need to add a new mysql user who have access to data management over the network. for xampp user go to **http://localhost/phpmyadmin/ - user account - add user account**

- #### Fill the textfield

username : **budi**

for hostname, you may fill with specific ip address but for convenience reason we can fill it with **%**

hostname : **%**

password : use generate button or fill with password you want 

- #### Security Permission

Global privileges : **check all** 


### Connect to SQL Server Database (MariaDB)

- #### Disable Windows Firewall

Connect to SQL server database (MariaDB) using this plugin is very simple. 

### Get Result From Query (Select)


### Execute Query (Insert/Update/Delete)


