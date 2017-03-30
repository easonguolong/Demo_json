package com.gson_jsondemo;

import java.util.List;

/**
 * Created by Administrator on 2017/03/13.
 */

public class ConfigJson {
    private final List<TvConfig> tvs;
    public ConfigJson(List<TvConfig> tvs){
        this.tvs = tvs;
    }
    public List<TvConfig> GetTvs(){
        return tvs;
    }
}
