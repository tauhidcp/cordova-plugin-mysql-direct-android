/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Wait for the deviceready event before using any of Cordova's device APIs.
// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
document.addEventListener('deviceready', onDeviceReady, false);
document.getElementById("SelectButton").addEventListener("click", SelectTable);
document.getElementById("AddButton").addEventListener("click", AddRow);

function onDeviceReady() {
    // Cordova is now initialized. Have fun!

    console.log('Running cordova-' + cordova.platformId + '@' + cordova.version);
    document.getElementById('deviceready').classList.add('ready');
	
	Connect();
}


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

function AddRow(){
		 
	var qry = "insert into prov (id, name) values ('','Coba')";
	
	cordova.plugins.MySQLDirect.execQuery(qry, onSuccess, onError);
	
	function onSuccess(s){
        alert(s);
    }

	function onError(e){
       alert(e);
    }
	
} 