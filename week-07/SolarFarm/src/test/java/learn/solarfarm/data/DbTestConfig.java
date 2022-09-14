package learn.solarfarm.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@ComponentScan
public class DbTestConfig {

    @Bean
    public DataSource getDataSource() {  // <- creates a DataSource object
        MysqlDataSource toReturn = new MysqlDataSource();
        // Url is for the test database.
        toReturn.setUrl("jdbc:mysql://localhost:3306/solar_test");
        toReturn.setUser("root");
        toReturn.setPassword("s8373384");
        return toReturn;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource source) { //<- Where we build our Jdbc template
        return new JdbcTemplate(source);
    }
}
