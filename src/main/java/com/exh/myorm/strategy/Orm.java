package com.exh.myorm.strategy;

import java.util.Map;

public interface Orm<T> {
    T orm(Map<String, Object> map);
}
