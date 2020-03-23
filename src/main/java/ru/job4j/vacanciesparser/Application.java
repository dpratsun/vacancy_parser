package ru.job4j.vacanciesparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.vacanciesparser.database.connection.SqlConnectionManager;
import ru.job4j.vacanciesparser.factory.SqlRuSiteParserFactory;
import ru.job4j.vacanciesparser.parser.site.SiteParser;
import ru.job4j.vacanciesparser.properties.FileProperties;
import ru.job4j.vacanciesparser.properties.Properties;
import ru.job4j.vacanciesparser.repository.ParseDateRepository;
import ru.job4j.vacanciesparser.repository.SqlParseDateRepository;
import ru.job4j.vacanciesparser.repository.SqlVacancyRepository;
import ru.job4j.vacanciesparser.repository.VacancyRepository;

public class Application {
    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        Properties properties = new FileProperties("app.properties");
        try (var connectionManager = new SqlConnectionManager(properties)) {
            ParseDateRepository parseDateRepository = new SqlParseDateRepository(connectionManager.getConnection());
            VacancyRepository vacancyRepository = new SqlVacancyRepository(connectionManager.getConnection());
            SiteParser sqlRuParser = SqlRuSiteParserFactory
                    .build(parseDateRepository.getLast(), properties.getValue("sqlru.url"));

            var vacancies = sqlRuParser.parse();
            vacancyRepository.store(vacancies);
            parseDateRepository.add();
        } catch (Exception e) {
            LOG.error("Error during parsing of Sql.ru " + e);
        }
    }
}
