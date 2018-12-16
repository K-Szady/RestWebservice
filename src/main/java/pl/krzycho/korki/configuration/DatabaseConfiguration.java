package pl.krzycho.korki.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    Environment environment;

    @Value("${oracle.datasource.url}")
    private String databaseUrl;
    @Value("${oracle.datasource.username}")
    private String username;
    @Value("${oracle.datasource.password}")
    private String pass;
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("oracle.datasource.drivername"));
        ds.setUrl(databaseUrl);
        ds.setUsername(username);
        ds.setPassword(pass);
        return ds;
    }
}
