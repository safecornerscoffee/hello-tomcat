package com.safecornerscoffee.medium.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeMapper {
    String getCurrentTime();
}
