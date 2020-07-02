package br.com.dudzdev.me.util;

import java.util.Optional;
import java.util.function.Supplier;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.DataSourceClosingSpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import liquibase.integration.spring.SpringLiquibase;

public final class SpringLiquibaseUtil {

    private SpringLiquibaseUtil() {
    }

    public static SpringLiquibase createSpringLiquibase(final DataSource liquibaseDatasource, 
                                                        final LiquibaseProperties liquibaseProperties, 
                                                        final DataSource dataSource, 
                                                        final DataSourceProperties dataSourceProperties) {

        SpringLiquibase liquibase;
        final DataSource liquibaseDataSource = getDataSource(liquibaseDatasource, liquibaseProperties, dataSource);

        if (liquibaseDataSource != null) {
            liquibase = new SpringLiquibase();
            liquibase.setDataSource(liquibaseDataSource);
            return liquibase;
        }

        liquibase = new DataSourceClosingSpringLiquibase();
        liquibase.setDataSource(createNewDataSource(liquibaseProperties, dataSourceProperties));

        return liquibase;
    }

    private static DataSource getDataSource(final DataSource liquibaseDataSource, 
                                            final LiquibaseProperties liquibaseProperties, 
                                            final DataSource dataSource) {

        if (liquibaseDataSource != null) {
            return liquibaseDataSource;
        }

        if (liquibaseProperties.getUrl() == null && liquibaseProperties.getUser() == null) {
            return dataSource;
        }

        return null;
    }

    private static DataSource createNewDataSource(final LiquibaseProperties liquibaseProperties, final DataSourceProperties dataSourceProperties) {
        final String url = getProperty(liquibaseProperties::getUrl, dataSourceProperties::determineUrl);
        final String user = getProperty(liquibaseProperties::getUser, dataSourceProperties::determineUsername);
        final String password = getProperty(liquibaseProperties::getPassword, dataSourceProperties::determinePassword);

        return DataSourceBuilder.create().url(url).username(user).password(password).build();
    }

    private static String getProperty(final Supplier<String> property, final Supplier<String> defaultValue) {
        return Optional.of(property).map(Supplier::get).orElseGet(defaultValue);
    }

}
