package com.komepay.web.dao;

import com.komepay.web.models.CoinSettings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoinSettingsDao {

    Connection connect = null;
    PreparedStatement psmt = null;

    public List<CoinSettings> getAllCoinSetting(){
        connect = DBConnection.getConnection();

        String Query = "SELECT * FROM CoinSetting";

        CoinSettings coinSetting;

        List<CoinSettings> coinSettings = new ArrayList<CoinSettings>();

        try{
            psmt = connect.prepareStatement(Query);

            ResultSet res = psmt.executeQuery();

            while(res.next()){

               coinSetting =  new CoinSettings();
                coinSetting.setId(res.getLong(1));
                coinSetting.setCid(res.getInt(2));
                coinSetting.setP2pport(res.getInt(3));
                coinSetting.setImageurl(res.getString(4));
                coinSetting.setBitcoinforum(res.getString(5));
                coinSetting.setCoinmarketcapurl(res.getString(6));
                coinSettings.add(coinSetting);

            }

            return coinSettings;

        } catch(SQLException e){
            Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);
            return null;
        } catch(Exception e){
            Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);
            return null;
        } 

    }


    public String save(CoinSettings coinSetting){

        try{
            connect = DBConnection.createConnection();

            String Query = "INSERT INTO coinSetting(cid,p2pport,imageurl,bitcoinforum,coinMarketcapurl,) VALUES (?,?,?,?,?)";

            psmt = connect.prepareStatement(Query);

            psmt.setInt(1, coinSetting.getCid());
            psmt.setInt(2,coinSetting.getP2pport());
            psmt.setString(3, coinSetting.getImageurl());
            psmt.setString(4, coinSetting.getBitcoinforum());
            psmt.setString(5, coinSetting.getCoinmarketcapurl());
            psmt.execute();




        }catch(SQLException e){
            Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

        } catch(Exception e){
            Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

        }

       return  "Saved";

    }


    public void update(CoinSettings coinSetting){

     try{
        connect = DBConnection.getConnection();

        String Query = "UPDATE `CoinSetting` SET "
                + "`cid`=?,`p2pport`=?,`imageurl`=?,"
                + "`bitcoinforum`=?, `coinmarketcapurl`=?  WHERE `id`= ?";

        psmt = connect.prepareStatement(Query);

         psmt.setInt(1, coinSetting.getCid());
         psmt.setInt(2 ,coinSetting.getP2pport());
         psmt.setString(3, coinSetting.getImageurl());
         psmt.setString(4, coinSetting.getBitcoinforum());
         psmt.setString(5, coinSetting.getCoinmarketcapurl());
         psmt.execute();

    }catch(SQLException e){
        Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

    } catch(Exception e){
        Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

    }


    }


    public boolean delete(long id) {


        try {
            connect = DBConnection.getConnection();

            String Query = "DELETE FROM `CoinSettings` WHERE id = ?";

            psmt = connect.prepareStatement(Query);

            psmt.setLong(1, id);


            if (psmt.execute()) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);
            return false;
        } catch (Exception e) {
            Logger.getLogger(CoinSettingsDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        } 

       }
    }


