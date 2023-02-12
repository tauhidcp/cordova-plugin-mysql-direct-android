package id.my.tauhidslab.mysqldirect;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement; 
import java.sql.ResultSet;


/**
 * class to execute SQL Query from cordova android app 
 * implemented from mysql connector jdbc (http://www.java2s.com/Code/Jar/j/Downloadjdbcjar.htm)
 * 
 * Ahmad Tauhid, S.Kom (http://www.tauhidslab.my.id/)
 * https://github.com/tauhidcp/cordova-plugin-sql-direct-android.git
 *
 */
 
 
public class MySQLDirect extends CordovaPlugin {
	
	private Connection con;
    private Statement stm;
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        	
		if (action.equals("getQuery")) {
            String message = args.getString(0);
            this.getQuery(message, callbackContext);
            return true;
        }
		
		if (action.equals("execQuery")) {
            String message = args.getString(0);
            this.execQuery(message, callbackContext);
            return true;
        }
		
		if (action.equals("Connect")) {
            String host = args.getString(0);
            String user = args.getString(1);
            String pass = args.getString(2);
            int port = args.getInt(3);
			String dbname = args.getString(4);
			this.Connect(host, user, pass, port, dbname, callbackContext);
            return true;
        }
		
        return false;
    }

    private void execQuery(String message, CallbackContext callbackContext) {
        
		if (this.con != null){
			
			if (message != null && message.length() > 0) {
                            
                                try {

                                    this.stm.execute(message);
                                    callbackContext.success("Done!");

                                } catch(Exception e){
                                    
                                    callbackContext.error(e.toString());
                                }
			} else {
				
				callbackContext.error("don't pass empty string!");
				
			}
		
		} else {
			
            callbackContext.error("you are not connected, login first!");
        }
		
    }
    
    private void getQuery(String message, CallbackContext callbackContext){
		
		if (this.con != null){
			
			if (message != null && message.length() > 0) { 
                            
                            try {
                                
                                String output = "";
                                ResultSet rs = this.stm.executeQuery(message);
								java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                                int columnsNumber = rsmd.getColumnCount();
                            
                                 while (rs.next()) {

                                    output += "{"; 
                                    for (int i = 1; i <= columnsNumber; i++) {
                                        if (i > 1) output += ",";
                                        String columnValue = rs.getString(i);
                                        output += "\""+rsmd.getColumnName(i)+"\"=\""+columnValue+"\"";
                                    }
                                    output += "},";
                                }

								callbackContext.success("["+removeLastChar(output.replace("=",":"))+"]");
                            
                            } catch(Exception e){
                            
                                callbackContext.error(e.toString());
                            
                            }
                            
			} else {
				
				callbackContext.error("don't pass empty string!");
				
			}
		
		} else {
			
            callbackContext.error("you are not connected, login first!");
        }
    }
    
	public static String removeLastChar(String s) {
    return (s == null || s.length() == 0)
      ? null 
      : (s.substring(0, s.length() - 1));
	}
	
	
    private void Connect(String host, String user, String pass, int port, String dbname, CallbackContext callbackContext) {
		
        if (host != null && host.length() > 0 && user != null && user.length() > 0 && dbname != null && dbname.length() > 0) {	
				
                            try {
								
								Class.forName("com.mysql.jdbc.Driver");
								String url = "jdbc:mysql://"+host+":"+port+"/"+dbname;
								this.con = DriverManager.getConnection(url,user,pass);
                                this.stm = this.con.createStatement();
								callbackContext.success("connected!");
                                
                            } catch(Exception e){
                                
                                callbackContext.error(e.toString());
                            }
                            
		
		} else {
			
            callbackContext.error("don't pass empty string!");
        }
    }
     
	
}
