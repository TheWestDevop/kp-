package com.komepay.web.models;

public class CoinSettings {
    private long id;
    private int cid;
    private int p2pport;
    private String imageurl;
    private String bitcoinforum;
    private String coinmarketcapurl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getP2pport() {
        return p2pport;
    }

    public void setP2pport(int p2pport) {
        this.p2pport = p2pport;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBitcoinforum() {
        return bitcoinforum;
    }

    public void setBitcoinforum(String bitcoinforum) {
        this.bitcoinforum = bitcoinforum;
    }

    public String getCoinmarketcapurl() {
        return coinmarketcapurl;
    }

    public void setCoinmarketcapurl(String coinmarketcapurl) {
        this.coinmarketcapurl = coinmarketcapurl;
    }
}
