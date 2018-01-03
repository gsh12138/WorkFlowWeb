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
@Configuration
@EnableJpaRepositories(basePackages = "repositorys.mySqlRepositorys",
        entityManagerFactoryRef = "mysqlEntityMangerFactory",
        transactionManagerRef = "mysqlTransactionManager")
public class MySqlJpaDataConfig {

    private DataSource mysqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/oasystemdev?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("159357");

        return dataSource;
    }

    @Bean(name = "mysqlEntityMangerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emfb=
                new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(mysqlDataSource());
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setPackagesToScan("entitys");
        return emfb;
    }

    private JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);

        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager transactionManager(){
        PlatformTransactionManager transactionManager =
                new JpaTransactionManager(entityManagerFactory().getObject());

        return transactionManager;
    }
}
