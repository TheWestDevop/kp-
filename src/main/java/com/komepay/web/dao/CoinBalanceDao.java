package com.komepay.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.komepay.web.models.CoinBalance;

public class CoinBalanceDao{

    Connection connect = null;
    PreparedStatement psmt = null;

    public List<CoinBalance> getAllCoinBalance() {
        connect = DBConnection.getConnection();

        String Query = "SELECT * FROM coinbalance ";

        CoinBalance coinbalance;

        List<CoinBalance> coinbalances = new ArrayList<CoinBalance>();

        try {
            psmt = connect.prepareStatement(Query);

            ResultSet res = psmt.executeQuery();

            while (res.next()) {

                coinbalance = new CoinBalance();
                coinbalance.setId(res.getLong(1));
                coinbalance.setCid(res.getInt(2));
                coinbalance.setAmount(res.getDouble(3));
                coinbalances.add(coinbalance);

            }


        } catch (SQLException e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);

        } catch (Exception e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);

        }
        return coinbalances;
    }


    public void save(CoinBalance coinBalance) {

        try {
            connect = DBConnection.createConnection();

            String Query = "INSERT INTO CoinBalance(cid,amount) VALUES (?,?)";

            psmt = connect.prepareStatement(Query);

            psmt.setInt(1, coinBalance.getCid());
            psmt.setDouble(2, coinBalance.getAmount());
            psmt.execute();


        } catch (SQLException e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);

        } catch (Exception e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);

        }

    }


    public void update(CoinBalance coinbalance) {

        try {

            connect = DBConnection.getConnection();

            String Query = "UPDATE `CoinBalance` SET "
                    + "`cid`=?, `amount`= ? WHERE `id`= ?";

            psmt = connect.prepareStatement(Query);

            
            psmt.setInt(1, coinbalance.getCid());
            psmt.setDouble(2, coinbalance.getAmount());
            psmt.setLong(3, coinbalance.getId());

        } catch (SQLException e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);

        } catch (Exception e) {
            Logger.getLogger(CoinDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public void delete(long id){


            try {
                connect = DBConnection.getConnection();

                String Query = "DELETE FROM `CoinBalance` WHERE id = ?";

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