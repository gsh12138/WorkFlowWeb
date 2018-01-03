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
 * Created by tech on 2017/12/21.
 */

@EnableJpaRepositories(basePackages = "repositorys.oracleRepositorys",
        entityManagerFactoryRef = "oracleEntityMangerFactory",
        transactionManagerRef = "oracleTransactionManager")
public class OracleJpaDataConfig {
    private DataSource oracleDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//192.168.0.103/topprod");
        dataSource.setUsername("tsdb");
        dataSource.setPassword("tsdb");
        return dataSource;
    }

    @Bean(name="oracleEntityMangerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean emfb=
                new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(oracleDataSource());
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setPackagesToScan("entitys");
        return emfb;
    }

    private JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.ORACLE);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager transactionManager(){
        PlatformTransactionManager transactionManager=
                new JpaTransactionManager(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
