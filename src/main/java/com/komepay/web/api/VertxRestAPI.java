package com.komepay.web.api;

import com.komepay.web.dao.CoinDao;
import com.komepay.web.dao.CoinSettingsDao;
import com.komepay.web.dao.ProfileDao;
import com.komepay.web.dao.TransactionDao;
import com.komepay.web.dao.UserDao;
import com.komepay.web.dao.CoinBalanceDao;
import com.komepay.web.models.Coin;
import com.komepay.web.models.CoinBalance;
import com.komepay.web.models.CoinSettings;
import com.komepay.web.models.User;
import com.komepay.web.models.Transaction;
import com.komepay.web.models.Profile;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;


public class VertxRestAPI extends AbstractVerticle {

    UserDao userDao = new UserDao();
    CoinDao coinDao = new CoinDao();
    CoinBalanceDao coinBalanceDao = new CoinBalanceDao();
    CoinSettingsDao coinSettingDao = new CoinSettingsDao();
    TransactionDao transactionDao =  new TransactionDao();
    ProfileDao profileDao = new ProfileDao();

    @Override
    public void start(Future fuc){



        Router router = Router.router(vertx);
        

         router.get("/api/users").handler(rc -> {
             HttpServerResponse response = rc.response();
             response
                     .putHeader("content-type", "application/json; charset=utf-8")
                     .end(Json.encodePrettily(userDao.getAll()));});
         router.post("/api/users").handler(this::toSaveUser);
         router.put("/api/users").handler(this::toUpdateUser);
         router.delete("/api/users").handler(this::toDeleteUser);


        //Coin API
        router.get("/api/coin").handler(rc -> {
            HttpServerResponse response = rc.response();
            response
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(coinDao.getAllCoin()));});
        router.post("/api/coin").handler(this::toSaveCoin);
        router.put("/api/coin").handler(this::toUpdateCoin);
        router.delete("/api/coin").handler(this::toDeleteCoin);


        //CoinBalance
        router.get("/api/coinbalance").handler(rc -> {
            HttpServerResponse response = rc.response();
            response
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(coinBalanceDao.getAllCoinBalance()));});
        router.post("/api/coinbalance").handler(this::toSaveCoinBalance);
        router.put("/api/coinbalance").handler(this::toUpdateCoinBalance);
        router.delete("/api/coinbalance").handler(this::toDeleteCoinBalance);

        //CoinSettings
        router.get("/api/coinsettings").handler(rc -> {
            HttpServerResponse response = rc.response();
            response
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(coinSettingDao.getAllCoinSetting()));});
        router.post("/api/coinsettings").handler(this::toSaveCoinSetting);
        router.put("/api/coinsettings").handler(this::toUpdateCoinSetting);
        router.delete("/api/coinsettings").handler(this::toDeleteCoinSetting);
        //Transaction
        router.get("/api/transaction").handler(rc -> {
            HttpServerResponse response = rc.response();
            response
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(transactionDao.getAllTransaction()));});
        router.post("/api/transaction").handler(this::toSaveTransaction);
        router.put("/api/transaction").handler(this::toUpdateTransaction);
        router.delete("/api/transaction").handler(this::toDeleteTransaction);
        //Profile
        router.get("/api/profile").handler(rc -> {
            HttpServerResponse response = rc.response();
            response
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(profileDao.getAllProfile()));});
        router.post("/api/profile").handler(this::toSaveProfile);
        router.put("/api/profile").handler(this::toUpdateProfile);
        router.delete("/api/profile").handler(this::toDeleteProfile);
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8282,result -> {
                    if (result.succeeded()){
                        fuc.complete();
                    }else {
                        fuc.fail(result.cause());
                    }
                });



    }
 //User
    private void  toSaveUser(RoutingContext routingContext){

        String username = routingContext.request().getParam("username");
        String password = routingContext.request().getParam("password");
        String secret = routingContext.request().getParam("secret");
        int isemailvalid = Integer.valueOf(routingContext.request().getParam("isemailvalid"));
        int isphonevalid = Integer.valueOf(routingContext.request().getParam("isphonevalid"));
        int status = Integer.valueOf(routingContext.request().getParam("status"));


        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setSecret(secret);
        user.setIsemailverified(isemailvalid);
        user.setIsphoneverified(isphonevalid);
        user.setStatus(status);

        userDao.save(user);

        System.out.print(user);

        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(userDao.getAll()));

    }
    private void  toUpdateUser(RoutingContext routingContext){

        String username = routingContext.request().getParam("username");
        String password = routingContext.request().getParam("password");
        String secret = routingContext.request().getParam("secret");
        int isemailvalid = Integer.valueOf(routingContext.request().getParam("isemailvalid"));
        int isphonevalid = Integer.valueOf(routingContext.request().getParam("isphonevalid"));
        int status = Integer.valueOf(routingContext.request().getParam("status"));
        long id =  Integer.valueOf(routingContext.request().getParam("id"));

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setSecret(secret);
        user.setIsemailverified(isemailvalid);
        user.setIsphoneverified(isphonevalid);
        user.setStatus(status);
        user.setId(id);

        userDao.update(user);

        System.out.print(user);

        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(userDao.getAll()));

    }
    private void  toDeleteUser(RoutingContext routingContext){

        long id =  Integer.valueOf(routingContext.request().getParam("id"));

        userDao.delete(id);
        
        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(userDao.getAll()));

    }

//Coin    
private void  toSaveCoin(RoutingContext routingContext){

    String name = routingContext.request().getParam("name");


    Coin coin = new Coin();

    coin.setName(name);


    coinDao.save(coin);

    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinDao.getAllCoin()));

}
private void  toUpdateCoin(RoutingContext routingContext){

    String name = routingContext.request().getParam("name");
    long id =  Integer.valueOf(routingContext.request().getParam("id"));

    Coin coin = new Coin();

    coin.setName(name);
    coin.setId(id);

    coinDao.update(coin);


    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinDao.getAllCoin()));

}
private void  toDeleteCoin(RoutingContext routingContext){

    long id =  Integer.valueOf(routingContext.request().getParam("id"));

    coinDao.delete(id);
    
    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinDao.getAllCoin()));

}

//CoinBalance
private void  toSaveCoinBalance(RoutingContext routingContext){

    int cid = Integer.parseInt(routingContext.request().getParam("cid"));
    int amount = Integer.parseInt(routingContext.request().getParam("amount"));

    CoinBalance coinbalance = new CoinBalance();

    coinbalance.setCid(cid);
    coinbalance.setAmount(amount);


    coinBalanceDao.save(coinbalance);

    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinBalanceDao.getAllCoinBalance()));

}
private void  toUpdateCoinBalance(RoutingContext routingContext){

    int cid = Integer.parseInt(routingContext.request().getParam("cid"));
    double amount =  Integer.valueOf(routingContext.request().getParam("amount"));
    long id =  Integer.valueOf(routingContext.request().getParam("id"));

    CoinBalance coinBalance = new CoinBalance();

    coinBalance.setCid(cid);
    coinBalance.setId(id);
    coinBalance.setAmount(amount);
 
    coinBalanceDao.update(coinBalance);


    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinBalanceDao.getAllCoinBalance()));

}
private void  toDeleteCoinBalance(RoutingContext routingContext){

    long id =  Integer.valueOf(routingContext.request().getParam("id"));

    coinBalanceDao.delete(id);
    
    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinBalanceDao.getAllCoinBalance()));

}


//CoinSettings
private void  toSaveCoinSetting(RoutingContext routingContext){
    int cid = Integer.parseInt(routingContext.request().getParam("cid"));
    int p2pport =  Integer.valueOf(routingContext.request().getParam("p2pport"));
    String imageurl = routingContext.request().getParam("imageurl");
    String bitcoinforum = routingContext.request().getParam("bitcoinforum");
    String coinmarketcapurl = routingContext.request().getParam("coinmarketcapurl");

    CoinSettings coinSetting = new CoinSettings();

    coinSetting.setCid(cid);
    coinSetting.setP2pport(p2pport);
    coinSetting.setImageurl(imageurl);
    coinSetting.setBitcoinforum(bitcoinforum);
    coinSetting.setCoinmarketcapurl(coinmarketcapurl);

    coinSettingDao.save(coinSetting);

    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinBalanceDao.getAllCoinBalance()));

}
private void  toUpdateCoinSetting(RoutingContext routingContext){

    int cid = Integer.parseInt(routingContext.request().getParam("cid"));
    int p2pport =  Integer.valueOf(routingContext.request().getParam("p2pport"));
    String imageurl = routingContext.request().getParam("imageurl");
    String bitcoinforum = routingContext.request().getParam("bitcoinforum");
    String coinmarketcapurl = routingContext.request().getParam("coinmarketcapurl");

    CoinSettings coinSetting = new CoinSettings();

    coinSetting.setCid(cid);
    coinSetting.setP2pport(p2pport);
    coinSetting.setImageurl(imageurl);
    coinSetting.setBitcoinforum(bitcoinforum);
    coinSetting.setCoinmarketcapurl(coinmarketcapurl);

    coinSettingDao.update(coinSetting);


    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinBalanceDao.getAllCoinBalance()));

}
private void  toDeleteCoinSetting(RoutingContext routingContext){

    long id =  Integer.valueOf(routingContext.request().getParam("id"));

    coinSettingDao.delete(id);
    
    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(coinSettingDao.getAllCoinSetting()));

}

//Transaction

    private void  toSaveTransaction(RoutingContext routingContext){

        String description = routingContext.request().getParam("ddescription");
        double credit = Integer.valueOf(routingContext.request().getParam("credit"));
        double debit = Integer.valueOf(routingContext.request().getParam("debit"));
        double  tax = Integer.valueOf(routingContext.request().getParam("tax"));
        double commission  = Integer.valueOf(routingContext.request().getParam("commission"));


        Transaction transaction =  new Transaction();
        
        transaction.setDescription(description);
        transaction.setCredit(credit);
        transaction.setDebit(debit);
        transaction.setTax(tax);
        transaction.setCommission(commission);
      

        transactionDao.save(transaction);

        System.out.print(transaction);

        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(transactionDao.getAllTransaction()));

    }
    private void  toUpdateTransaction(RoutingContext routingContext){
        String description = routingContext.request().getParam("ddescription");
        double credit = Integer.valueOf(routingContext.request().getParam("credit"));
        double debit = Integer.valueOf(routingContext.request().getParam("debit"));
        double  tax = Integer.valueOf(routingContext.request().getParam("tax"));
        double commission  = Integer.valueOf(routingContext.request().getParam("commission"));


        Transaction transaction =  new Transaction();
        
        transaction.setDescription(description);
        transaction.setCredit(credit);
        transaction.setDebit(debit);
        transaction.setTax(tax);
        transaction.setCommission(commission);
      

        transactionDao.update(transaction);

        System.out.print(transaction);

        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(transactionDao.getAllTransaction()));

    }
    private void  toDeleteTransaction(RoutingContext routingContext){

        long id =  Integer.valueOf(routingContext.request().getParam("id"));

        transactionDao.delete(id);
        
        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(transactionDao.getAllTransaction()));

    }

//Profile
private void  toSaveProfile(RoutingContext routingContext){
    int uid = Integer.valueOf(routingContext.request().getParam("uid"));
    String firstname = routingContext.request().getParam("firstname");
    String lastname = routingContext.request().getParam("lastname");
    String email = routingContext.request().getParam("email");
    String phone = routingContext.request().getParam("phone");
    String address = routingContext.request().getParam("address");
    String address2 = routingContext.request().getParam("address2");
    String city = routingContext.request().getParam("city");
    String state = routingContext.request().getParam("state");
    String country = routingContext.request().getParam("country");


    Profile profile =  new Profile();

    profile.setUid(uid);
    profile.setFirstname(firstname);
    profile.setLastname(lastname);
    profile.setEmail(email);
    profile.setPhone(phone);
    profile.setAddress(address);
    profile.setAddress2(address2);
    profile.setCity(city);
    profile.setState(state);
    profile.setCountry(country);
    

    profileDao.save(profile);

    System.out.print(profile);

    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(profileDao.getAllProfile()));

}
private void  toUpdateProfile(RoutingContext routingContext){
     
    int uid = Integer.valueOf(routingContext.request().getParam("uid"));
    String firstname = routingContext.request().getParam("firstname");
    String lastname = routingContext.request().getParam("lastname");
    String email = routingContext.request().getParam("email");
    String phone = routingContext.request().getParam("phone");
    String address = routingContext.request().getParam("address");
    String address2 = routingContext.request().getParam("address2");
    String city = routingContext.request().getParam("city");
    String state = routingContext.request().getParam("state");
    String country = routingContext.request().getParam("country");
    long id =  Integer.valueOf(routingContext.request().getParam("id"));

    Profile profile =  new Profile();

    profile.setId(id);
    profile.setUid(uid);
    profile.setFirstname(firstname);
    profile.setLastname(lastname);
    profile.setEmail(email);
    profile.setPhone(phone);
    profile.setAddress(address);
    profile.setAddress2(address2);
    profile.setCity(city);
    profile.setState(state);
    profile.setCountry(country);
    

    profileDao.update(profile);

    System.out.print(profile);

    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(profileDao.getAllProfile()));

}
private void  toDeleteProfile(RoutingContext routingContext){

    long id =  Integer.valueOf(routingContext.request().getParam("id"));

    profileDao.delete(id);
    
    routingContext.response()
            .setStatusCode(201)
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(profileDao.getAllProfile()));

}



}
