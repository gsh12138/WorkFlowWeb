package configs;

import Flow.FlowDateInterface;
import helpers.FlowDateImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by tech on 2017/12/19.
 */
@Configuration
@ComponentScan(basePackages = {"repositorys","configs","helpers"},
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
public class RootConfig {


}
