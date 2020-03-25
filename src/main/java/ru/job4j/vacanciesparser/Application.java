package ru.job4j.vacanciesparser;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.vacanciesparser.factory.SqlRuSiteParserFactory;
import ru.job4j.vacanciesparser.job.ParseJob;
import ru.job4j.vacanciesparser.properties.FileProperties;
import ru.job4j.vacanciesparser.properties.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Application {
    private final static String PROPERTIES_FILE = "app.properties";
    private final static String CRON_PROPERTY = "cron.time";
    private final static String SQL_RU_URL_PROPERTY = "sqlru.url";

    public static void main(String[] args) {
        Properties properties = new FileProperties(PROPERTIES_FILE);
        try {
            var parseJob = createJob(properties);
            var parseJobTrigger = createJobTrigger(properties);

            var scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(parseJob, parseJobTrigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JobDetail createJob(Properties properties) {
        var parseJob = newJob(ParseJob.class).build();
        parseJob.getJobDataMap().put(ParseJob.PROPERTIES, properties);
        parseJob.getJobDataMap().put(ParseJob.SOURCE, properties.getValue(SQL_RU_URL_PROPERTY));
        parseJob.getJobDataMap().put(ParseJob.FACTORY, new SqlRuSiteParserFactory());
        return parseJob;
    }

    private static Trigger createJobTrigger(Properties properties) {
        return newTrigger()
                .withSchedule(cronSchedule(properties.getValue(CRON_PROPERTY)))
                .build();
    }
}
