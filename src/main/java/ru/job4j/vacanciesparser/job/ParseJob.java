package ru.job4j.vacanciesparser.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.job4j.vacanciesparser.database.connection.SqlConnectionManager;
import ru.job4j.vacanciesparser.factory.SiteParserFactory;
import ru.job4j.vacanciesparser.parser.site.SiteParser;
import ru.job4j.vacanciesparser.properties.FileProperties;
import ru.job4j.vacanciesparser.properties.Properties;
import ru.job4j.vacanciesparser.repository.ParseDateRepository;
import ru.job4j.vacanciesparser.repository.SqlParseDateRepository;
import ru.job4j.vacanciesparser.repository.SqlVacancyRepository;
import ru.job4j.vacanciesparser.repository.VacancyRepository;

public class ParseJob implements Job {
    public static final String SOURCE = "source";
    public static final String PROPERTIES = "properties";
    public static final String FACTORY = "factory";

    private static final Logger LOG = LogManager.getLogger(ParseJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("Parse job");

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String source = dataMap.getString(SOURCE);
        Properties properties = (Properties) dataMap.get(PROPERTIES);
        SiteParserFactory factory = (SiteParserFactory) dataMap.get(FACTORY);

        try (var connectionManager = new SqlConnectionManager(properties)) {
            ParseDateRepository parseDateRepository = new SqlParseDateRepository(connectionManager.getConnection());
            VacancyRepository vacancyRepository = new SqlVacancyRepository(connectionManager.getConnection());

            SiteParser siteParser = factory.build(parseDateRepository.getLast(), source);

            LOG.info("Starting parse job for source: " + source);

            var vacancies = siteParser.parse();
            vacancyRepository.store(vacancies);
            parseDateRepository.add();
        } catch (Exception e) {
            LOG.error("Error during parsing of " + source + e);
        }
    }
}
