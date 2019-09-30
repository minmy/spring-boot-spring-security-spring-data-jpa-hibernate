package com.xinmy.springbootbase.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 *
 * @desc
 */
public class MyPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {
    //
    public static final String PREFIX = "xin_";

    //
    @Override
    public Identifier toPhysicalTableName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
        final Identifier identifier = super.toPhysicalTableName(name, jdbcEnvironment);
        return new Identifier(PREFIX + identifier.getText(), identifier.isQuoted());
    }

}
