package org.github.ypiel.adopinter.db;

import org.github.ypiel.adopinter.entities.Country;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DerbyLocalInfo {

    private static DerbyLocalInfo instance = null;

    private final static String DERBY_URL = "jdbc:derby:d:/Dev/localInfoDB;create=true";

    private final static String CREATE_COUNTRY_TABLE = "CREATE TABLE country ( " +
            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "name VARCHAR(100) NOT NULL," +
            "is_lahaye SMALLINT NOT NULL," +
            "min_married_year SMALLINT NOT NULL DEFAULT 0," +
            "nb_child_last_year INT NOT NULL DEFAULT 0," +
            "is_open SMALLINT NOT NULL DEFAULT 1," +
            "comment VARCHAR(1024) DEFAULT ''" +
            ")";

    private final static String INSERT_COUNTRY = "INSERT INTO COUNTRY (name, is_lahaye, min_married_year, nb_child_last_year, is_open, comment) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SELECT_ALL_COUNTRIES = "SELECT id, name, is_lahaye, min_married_year, nb_child_last_year, is_open, comment FROM COUNTRY";

    private final Map<String, PreparedStatement> stmts = new HashMap<String, PreparedStatement>();

    private Connection conn;

    private DerbyLocalInfo() {
        createDataBaseIfNeeded(false, 1);
        buildPreparedStatement();
    }

    public final static DerbyLocalInfo getInstance() {
        if (instance == null) {
            instance = new DerbyLocalInfo();
        }

        return instance;
    }

    private void buildPreparedStatement(){
        try {
            stmts.put(INSERT_COUNTRY, conn.prepareStatement(INSERT_COUNTRY));
            stmts.put(SELECT_ALL_COUNTRIES, conn.prepareStatement(SELECT_ALL_COUNTRIES));
        }
        catch (SQLException e){
            System.out.println("Can't prepare statements : "+e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void createDataBaseIfNeeded(boolean create, int i) {
        try {
            if(conn == null) {
                conn = DriverManager.getConnection(DERBY_URL);
            }

            if (create) {
                System.out.println("Create database structure...");
                conn.createStatement().execute(CREATE_COUNTRY_TABLE);
            }

            final ResultSet resultSet = conn.createStatement().executeQuery("select count(*) from country");

        } catch (SQLException e) {
            if(i < 3){
                this.createDataBaseIfNeeded(true, ++i);
            }
            else{
                System.out.println("Can't create database : " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

    public void insertCountry(Country c){
        try {
            PreparedStatement s = stmts.get(INSERT_COUNTRY);
            s.setString(1, c.getName());
            s.setInt(2, c.isLahaye() ? 1 : 0);
            s.setInt(3, c.getNbMarriedYear());
            s.setInt(4, c.getNbChildren());
            s.setInt(5, c.isOpen() ? 1 : 0);
            s.setString(6, c.getComment());
            s.execute();
        }
        catch(Exception e){
            System.out.println("Can't insert country : "+e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Country> getAllCountries(){
        List<Country> cl = new ArrayList<>();
        try{
            PreparedStatement s = stmts.get(SELECT_ALL_COUNTRIES);
            final ResultSet rs = s.executeQuery();
            while(rs.next()){
                Country c = new Country();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setLahaye(rs.getInt(3) == 1);
                c.setNbMarriedYear(rs.getInt(4));
                c.setNbChildren(rs.getInt(5));
                c.setOpen(rs.getInt(6) == 1);
                c.setComment(rs.getString(7));

                cl.add(c);
            }
        }
        catch(Exception e){
            System.out.println("Can't selecgt all countries : "+e.getMessage());
            e.printStackTrace();
        }
        return cl;
    }

}
