package com.app.sampleandroidproject.beans.result;

import java.util.List;

/**
 * Innoz
 * com.iss.innoz.network.entity
 *
 * @Author: xie
 * @Time: 2016/9/28 16:03
 * @Description:
 */


public class HttpResultCityAndSpace {

    private List<CityEntity> city;

    public List<CityEntity> getCity() {
        return city;
    }

    public void setCity(List<CityEntity> city) {
        this.city = city;
    }

    public static class CityEntity {
        private String uuid;
        private String name;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
