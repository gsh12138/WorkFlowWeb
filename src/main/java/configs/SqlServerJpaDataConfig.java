package configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by tech on 2017/12/19.
 */

@EnableJpaRepositories(basePackages = "repositorys.splserverRepositorys",
        entityManagerFactoryRef = "sqlserverEntityMangerFactory",
        transactionManagerRef = "sqlserverTransactionManager")
public class SqlServerJpaDataConfig {

    public DataSource SqlServerDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://192.168.0.109;databaseName=TJZH");
        dataSource.setUsername("sa");
        dataSource.setPassword("techsem");
        return dataSource;
    }

    @Bean(name = "sqlserverEntityMangerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emfb=
                new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(SqlServerDataSource());
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setPackagesToScan("entitys");
        return emfb;

    }

    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.SQL_SERVER);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean(name = "sqlserverTransactionManager")
    public PlatformTransactionManager transactionManager(){
        PlatformTransactionManager transactionManager =
                new JpaTransactionManager(entityManagerFactory().getObject());
        return transactionManager;

    }
}
