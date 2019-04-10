package com.komepay.web.dao;

import com.komepay.web.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    Connection connect = null;
    PreparedStatement psmt = null;

    public List<User> getAll(){
        connect = DBConnection.getConnection();

        String Query = "SELECT * FROM user";

        User user;

        List<User> users = new ArrayList<User>();

        try{
            psmt = connect.prepareStatement(Query);

            ResultSet res = psmt.executeQuery();

            while(res.next()){

               user =  new User();
                user.setId(res.getLong(1));
                user.setUsername(res.getString(2));
                user.setPassword(res.getString(3));
                user.setSecret(res.getString(4));
                user.setIsemailverified(res.getInt(5));
                user.setIsphoneverified(res.getInt(6));
                user.setStatus(res.getInt(7));
                user.setDate(res.getString(8));
                users.add(user);

            }

            return users;

        } catch(SQLException e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);
            return null;
        } catch(Exception e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);
            return null;
        } 

    }


    public String save(User user){

        try{
            connect = DBConnection.createConnection();

            String Query = "INSERT INTO user(username,password,secret,isemailverified,isphoneverified,status) VALUES (?,?,?,?,?,?)";

            psmt = connect.prepareStatement(Query);

            psmt.setString(1, user.getUsername());
            psmt.setString(2,user.getPassword());
            psmt.setString(3, user.getSecret());
            psmt.setInt(4, user.getIsemailverified());
            psmt.setInt(5, user.getIsphoneverified());
            psmt.setInt(6, user.getStatus());

            psmt.execute();




        }catch(SQLException e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

        } catch(Exception e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

        } finally{
            try{
                connect.close();
            }catch(SQLException e){
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE,null,e);
            }
        }

       return  "Saved";

    }


    public void update(User user){

     try{
        connect = DBConnection.getConnection();

        String Query = "UPDATE `user` SET "
                + "`username`=?,`password`=?,`secret`=?,"
                + "`isemailverified`=?, `isphoneverified`=? , `status`=? WHERE `id`= ?";

        psmt = connect.prepareStatement(Query);

         psmt.setString(1, user.getUsername());
         psmt.setString(2,user.getPassword());
         psmt.setString(3, user.getSecret());
         psmt.setInt(4, user.getIsemailverified());
         psmt.setInt(5, user.getIsphoneverified());
         psmt.setInt(6, user.getStatus());
         psmt.setLong(7, user.getId());

    }catch(SQLException e){
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

    } catch(Exception e){
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

    }


    }


    public boolean delete(long id) {


        try {
            connect = DBConnection.getConnection();

            String Query = "DELETE FROM `user` WHERE id = ?";

            psmt = connect.prepareStatement(Query);

            psmt.setLong(1, id);


            if (psmt.execute()) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);
            return false;
        } catch (Exception e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
            }
        }

       }
    }
