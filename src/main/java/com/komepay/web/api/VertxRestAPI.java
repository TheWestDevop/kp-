package com.komepay.web.api;

import com.komepay.web.dao.CoinDao;
import com.komepay.web.dao.CoinSettingsDao;
import com.komepay.web.dao.UserDao;
import com.komepay.web.dao.CoinBalanceDao;
import com.komepay.web.models.Coin;
import com.komepay.web.models.CoinBalance;
import com.komepay.web.models.CoinSettings;
import com.komepay.web.models.User;
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





}
