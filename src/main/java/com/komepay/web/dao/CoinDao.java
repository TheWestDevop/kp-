package com.komepay.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.komepay.web.models.Coin;

public class CoinDao{

    Connection connect = null;
    PreparedStatement psmt = null;

    public List<Coin> getAllCoin() {
        connect = DBConnection.getConnection();

        String Query = "SELECT * FROM coin ";

        Coin coin;

        List<Coin> coins = new ArrayList<Coin>();

        try {
            psmt = connect.prepareStatement(Query);

            ResultSet res = psmt.executeQuery();

            while (res.next()) {

                coin = new Coin();
                coin.setId(res.getLong(1));
                coin.setName(res.getString(2));
                coins.add(coin);

            }


        } catch (SQLException e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);

        } catch (Exception e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);

        }
        return coins;
    }


    public void save(Coin coin) {

        try {
            connect = DBConnection.createConnection();

            String Query = "INSERT INTO Coin(name) VALUES (?)";

            psmt = connect.prepareStatement(Query);

            psmt.setString(1, coin.getName());

            psmt.execute();


        } catch (SQLException e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);

        } catch (Exception e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);

        }

    }


    public void update(Coin coin) {

        try {

            connect = DBConnection.getConnection();

            String Query = "UPDATE `Coin` SET "
                    + "`name`=? WHERE `id`= ?";

            psmt = connect.prepareStatement(Query);

            psmt.setString(1, coin.getName());
            psmt.setLong(2, coin.getId());

        } catch (SQLException e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);

        } catch (Exception e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public void delete(long id){


            try {
                connect = DBConnection.getConnection();

                String Query = "DELETE FROM `Coin` WHERE id = ?";

                psmt = connect.prepareStatement(Query);

                psmt.setLong(1, id);
                psmt.execute();


            } catch (SQLException e) {
                Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);

            } catch (Exception e) {
                Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);

            }

    }

}