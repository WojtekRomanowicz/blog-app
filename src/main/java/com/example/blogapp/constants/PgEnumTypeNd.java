package com.example.blogapp.constants;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PgEnumTypeNd {
    Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws SQLException;
}
