package com.komepay.web.dao;

import com.komepay.web.models.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileDao {

    Connection connect = null;
    PreparedStatement psmt = null;

    public List<Profile> getAllProfile(){
        connect = DBConnection.getConnection();

        String Query = "SELECT * FROM Profile";

        Profile profile;

        List<Profile> profiles = new ArrayList<Profile>();

        try{
            psmt = connect.prepareStatement(Query);

            ResultSet res = psmt.executeQuery();

            while(res.next()){

                profile =  new Profile();
                profile.setId(res.getLong(1));
                profile.setUid(res.getInt(2));
                profile.setFirstname(res.getString(3));
                profile.setLastname(res.getString(4));
                profile.setEmail(res.getString(5));
                profile.setPhone(res.getString(6));
                profile.setAddress(res.getString(7));
                profile.setAddress2(res.getString(8));
                profile.setCity(res.getString(9));
                profile.setState(res.getString(10));
                profile.setCountry(res.getString(11));
                profile.setDate(res.getString(12));
                profiles.add(profile);

            }

            return profiles;

        } catch(SQLException e){
            Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);
            return null;
        } catch(Exception e){
            Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);
            return null;
        } 

    }


    public void save(Profile profile){

        try{
            connect = DBConnection.createConnection();

            String Query = "INSERT INTO Profile(uid,firstname,lastname,email,phone,address,address2,city,state,country) VALUES (?,?,?,?,?,?,?,?,?,?)";

            psmt = connect.prepareStatement(Query);

            psmt.setInt(1, profile.getUid());
            psmt.setString(2,profile.getFirstname());
            psmt.setString(3, profile.getLastname());
            psmt.setString(4, profile.getEmail());
            psmt.setString(5, profile.getPhone());
            psmt.setString(6, profile.getAddress());
            psmt.setString(7, profile.getAddress2());
            psmt.setString(8, profile.getCity());
            psmt.setString(9, profile.getState());
            psmt.setString(10, profile.getCountry());
            psmt.execute();

            


        }catch(SQLException e){
            Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

        } catch(Exception e){
            Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

        } finally{
            try{
                connect.close();
            }catch(SQLException e){
                Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE,null,e);
            }
        }

       

    }


    public void update(Profile profile){

     try{
        connect = DBConnection.getConnection();

        String Query = "UPDATE `Profile` SET "
                + "`uid`=?,`firstname`=?,`lastname`=?,"
                + "`email`=?, `phone`=? , `address`=?,`address2`=?,`city`=?,`state`=?,`country`=? WHERE `id`= ?";

        psmt = connect.prepareStatement(Query);

        psmt.setInt(1, profile.getUid());
        psmt.setString(2,profile.getFirstname());
        psmt.setString(3, profile.getLastname());
        psmt.setString(4, profile.getEmail());
        psmt.setString(5, profile.getPhone());
        psmt.setString(6, profile.getAddress());
        psmt.setString(7, profile.getAddress2());
        psmt.setString(8, profile.getCity());
        psmt.setString(9, profile.getState());
        psmt.setString(10, profile.getCountry());
        psmt.executeUpdate();

    }catch(SQLException e){
        Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

    } catch(Exception e){
        Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

    }


    }


    public boolean delete(long id) {


        try {
            connect = DBConnection.getConnection();

            String Query = "DELETE FROM `Profile` WHERE id = ?";

            psmt = connect.prepareStatement(Query);

            psmt.setLong(1, id);


            if (psmt.execute()) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);
            return false;
        } catch (Exception e) {
            Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(ProfileDao.class.getName()).log(Level.SEVERE, null, e);
            }
        }

       }
    }
