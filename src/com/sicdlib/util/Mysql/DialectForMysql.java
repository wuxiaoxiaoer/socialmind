package com.sicdlib.util.Mysql;

import org.hibernate.dialect.MySQL5Dialect;

import java.sql.Types;

public class DialectForMysql extends MySQL5Dialect {
    public DialectForMysql() {
        super();
        registerHibernateType(Types.LONGVARCHAR, 65535, "text");
    }
}
