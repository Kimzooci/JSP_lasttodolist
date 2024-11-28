
package kyungseo.poc.todo.common.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class JdbcConfiguration {

	// private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConfiguration.class);

	@Autowired
	DataSource ds1DataSource;

	/*
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	*/

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(ds1DataSource);
	}

	@PostConstruct
	public void startDBManager() {
		//
	}

}