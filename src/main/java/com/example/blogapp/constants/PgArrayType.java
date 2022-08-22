package com.example.blogapp.constants;

import lombok.SneakyThrows;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

public class PgArrayType<T extends Serializable> implements UserType {

    protected static final int[] SQL_TYPES = {Types.ARRAY};
    private Class<T> typeParameterClass;

    @Override
    public Object assemble(Serializable cached, Object owner) {
        return this.deepCopy(cached);
    }

    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Override
    public Serializable disassemble(Object value) {
        return (T) this.deepCopy(value);
    }

    @Override
    public boolean equals(Object x, Object y) {

        if (x == null) {
            return y == null;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        return null;
    }


    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        if (resultSet.wasNull()) {
            return null;
        }
        if (resultSet.getArray(names[0]) == null) {
            return new String[0];
        }

        Array array = resultSet.getArray(names[0]);
        @SuppressWarnings("unchecked")
        T javaArray = (T) array.getArray();
        return javaArray;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        Connection connection = preparedStatement.getConnection();
        if (value == null) {
            preparedStatement.setNull(index, SQL_TYPES[0]);
        } else {
            @SuppressWarnings("unchecked")
            T castObject = (T) value;
            Array array = connection.createArrayOf(JDBCType.VARCHAR.getName(), (Object[]) castObject);
            preparedStatement.setArray(index, array);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }

    @Override
    public int getSqlType() {
        return 0;
    }

    @Override
    public Class<T> returnedClass() {
        return typeParameterClass;
    }


    public int[] sqlTypes() {
        return new int[]{Types.ARRAY};
    }


}