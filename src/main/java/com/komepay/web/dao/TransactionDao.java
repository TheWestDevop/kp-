package com.komepay.web.dao;

import com.komepay.web.models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionDao {

    Connection connect = null;
    PreparedStatement psmt = null;

    public List<Transaction> getAllTransaction(){
        connect = DBConnection.getConnection();

        String Query = "SELECT * FROM Transaction";

        Transaction transaction;

        List<Transaction> transactions = new ArrayList<Transaction>();

        try{
            psmt = connect.prepareStatement(Query);

            ResultSet res = psmt.executeQuery();

            while(res.next()){

                transaction =  new Transaction();
                transaction.setId(res.getLong(1));
                transaction.setDescription(res.getString(2));
                transaction.setCredit(res.getInt(3));
                transaction.setDebit(res.getInt(4));
                transaction.setTax(res.getInt(5));
                transaction.setCommission(res.getInt(6));
                transaction.setDate(res.getString(7));
                transactions.add(transaction);

            }

            return transactions;

        } catch(SQLException e){
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);
            return null;
        } catch(Exception e){
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);
            return null;
        } 

    }


    public String save(Transaction transaction){

        try{
            connect = DBConnection.createConnection();

            String Query = "INSERT INTO transaction(description,credit,debit,tax,commission) VALUES (?,?,?,?,?,?)";

            psmt = connect.prepareStatement(Query);

            psmt.setString(1, transaction.getDescription());
            psmt.setDouble(2,transaction.getCredit());
            psmt.setDouble(3, transaction.getDebit());
            psmt.setDouble(4, transaction.getTax());
            psmt.setDouble(5, transaction.getCommission());
            psmt.execute();




        }catch(SQLException e){
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

        } catch(Exception e){
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

        }

       return  "Saved";

    }


    public void update(Transaction transaction){

     try{
        connect = DBConnection.getConnection();

        String Query = "UPDATE `transaction` SET "
                + "`description`=?,`credit`=?,`debit`=?,"
                + "`tax`=?, `commission`=?  WHERE `id`= ?";

        psmt = connect.prepareStatement(Query);

        psmt.setString(1, transaction.getDescription());
        psmt.setDouble(2,transaction.getCredit());
        psmt.setDouble(3, transaction.getDebit());
        psmt.setDouble(4, transaction.getTax());
        psmt.setDouble(5, transaction.getCommission());
        psmt.setLong(6, transaction.getId());
        psmt.executeUpdate();

    }catch(SQLException e){
        Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, "Error Happened !!!",e);

    } catch(Exception e){
        Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, e.getMessage(),e);

    }


    }


    public boolean delete(long id) {


        try {
            connect = DBConnection.getConnection();

            String Query = "DELETE FROM `Transaction` WHERE id = ?";

            psmt = connect.prepareStatement(Query);

            psmt.setLong(1, id);


            if (psmt.execute()) {
                return true;
            } else {
                return false;
            }


        } catch (SQLException e) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, "Error Happened !!!", e);
            return false;
        } catch (Exception e) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        } 

       }
    }


