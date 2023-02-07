# cordova-plugin-mysql-direct-android
cordova plugin to execute mysql query directly from android app 

#### !! for security reason, execute sql query directly from android app is totally risk. this plugin for education only !!

this plugin implemented from jdbc driver (http://www.java2s.com/Code/Jar/j/Downloadjdbcjar.htm). it allow you to execute mysql query (select/insert/update/delete) from your cordova android app. 

### Tested on:

- NodeJS  	      : 19.4.0
- Cordova 	      : 11.1.0
- cordova-android : 11.0 
- Java 11  
- MariaDB : 10.4.27 (xampp)


### Add MySQL User (MariaDB)

first of all, we need to add a new mysql user who have access to data management over the network. for xampp user go to **http://localhost/phpmyadmin/ - user account - add user account**

- #### Fill the textfield

username : **budi**

for hostname, you may fill with specific ip address but for convenience reason we can fill it with **%**

hostname : **%**

password : use generate button or fill with password you want 

- #### Security Permission

Global privileges : **check all** 



### Install/Uninstall Plugin

install from github repository using this command
```
cordova plugin add https://github.com/tauhidcp/cordova-plugin-mysql-direct-android.git
```
or install from npmjs package using this command 
```
cordova plugin add id.my.tauhidslab.mysqldirect
```

uninstall using this command
```
cordova plugin rm id.my.tauhidslab.mysqldirect
```


### Connect to MySQL Server Database (MariaDB)

- #### Disable Windows Firewall !!

Connect to MySQL server database (MariaDB) using this plugin is very simple. Example code to connet from cordova android app as follow
```
function Connect(){
	
	var host   = "192.168.1.6";
	var user   = "budi";
	var pass   = "fWSs_pwZTFFLxU82";
	var port   = 3306;
	var dbname = "cordova_sql";
	
	cordova.plugins.MySQLDirect.Connect(host, user, pass, port, dbname, onSuccess, onError);
	
	function onSuccess(s){
        document.getElementById("dbinfo").innerText = s;
    }

	function onError(e){
        document.getElementById("dbinfo").innerText = e;
    }
	
}
```


### Get Result From Query (Select)

to handle query result (select), we can use getQuery function. for example we will get the value from prov table as follow   
```
function SelectTable(){
		 
	var qry = "select * from prov";
	
	cordova.plugins.MySQLDirect.getQuery(qry, onSuccess, onError);
	
	function onSuccess(s){
		
		var rs = JSON.parse(s); // parse output to JSON
		
		rs.forEach(function(obj){
			document.getElementById("prov_list").innerHTML += obj['id']+". "+obj['name']+"</br>";  
		});
    }

	function onError(e){
       alert(e);
    }
	
} 
```


### Execute Query (Insert/Update/Delete)

to handle query like insert, update and delete we can use execQuery. for example we will add new record to prov table as follow 
```
function AddRow(){
		 
	var qry = "insert into prov (id, name) values ('','Lombok Utara')";
	
	cordova.plugins.MySQLDirect.execQuery(qry, onSuccess, onError);
	
	function onSuccess(s){
        alert(s);
    }

	function onError(e){
       alert(e);
    }
	
} 
```

