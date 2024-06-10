package com.example.prmproject.Database.SqlServer;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SqlConnection {

    String classes = "net.sourceforge.jtds.jdbc.Driver";
    protected static String ip = "10.0.2.2";
    protected static String port = "1433";
    protected static String db = "PRM";
    protected static String username = "sa";
    protected static String password = "12345";
    boolean connected = false;

    @SuppressLint("NewApi")
    public Connection CONN(){
        Connection con = null;
        String ip = "10.0.2.2", port = "1433", username = "sa", password = "12345", databasename = "PRM";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
        return con;
    }
    public boolean checkConnection(Connection connection){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try{
                if(connection == null){
                    Log.e("error connect to sql server", "error");
                    connected = false;
                }
                else{
                    Log.e("successfully connect to sql server", "connected");
                    connected = true;
                }
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        });
        return connected;
    }
}
