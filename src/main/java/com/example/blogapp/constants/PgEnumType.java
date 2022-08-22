package com.example.blogapp.constants;
import lombok.SneakyThrows;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class PgEnumType implements EnhancedUserType, ParameterizedType, PgEnumTypeNd {
    private Class<Enum> enumClass;
    @SneakyThrows
    public void setParameterValues(Properties parameters) {
        String enumClassName =
                parameters.getProperty("enumClassName");
        try {
            enumClass = (Class<Enum>) Class.forName(enumClassName);
        } catch (ClassNotFoundException cnfe) {
            throw new Exception("Enum class not found", cnfe);
        }
    }
    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }
    public Object deepCopy(Object value) {
        return value;
    }
    public Serializable disassemble(Object value) {
        return (Enum) value;
    }
    public boolean equals(Object x, Object y) {
        return x == y;
    }
    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        return null;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws SQLException {

        Object object = rs.getObject(names[0]);

        if (rs.wasNull()) {
            return null;
        }
        if (object instanceof PGobject) {
            PGobject pg = (PGobject) object;
            return Enum.valueOf(enumClass, pg.getValue());
        }
        if (object instanceof String) {
            return Enum.valueOf(enumClass,
                    String.valueOf(object));
        }
        return null;
    }
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws
            SQLException {

        if (value == null) {
            st.setNull(index, Types.VARCHAR);
        } else {
            st.setObject(index, ((Enum) value),
                    Types.OTHER);
        }
    }
    public boolean isMutable() {
        return false;
    }
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }

    @Override
    public int getSqlType() {
        return 0;
    }

    public Class returnedClass() {
        return enumClass;
    }
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }
    public Object fromXMLString(String xmlValue) {
        return Enum.valueOf(enumClass, xmlValue);
    }
    public String objectToSQLString(Object value) {
        return "\"" + ((Enum) value).name() + "\"";

    }
    public String toXMLString(Object value) {
        return ((Enum) value).name();
    }

    @Override
    public String toSqlLiteral(Object value) {
        return null;
    }

    @Override
    public String toString(Object value) {
        return null;
    }

    @Override
    public Object fromStringValue(CharSequence sequence) {
        return null;
    }
}
