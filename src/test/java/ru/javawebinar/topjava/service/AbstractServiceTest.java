package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

import java.util.concurrent.TimeUnit;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",

        @Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
        @ActiveProfiles(resolver = ActiveDbProfileResolver.class)
        abstract public class AbstractServiceTest{
        private static final Logger log=getLogger("result");

        private static final StringBuilder results=new StringBuilder();
        @ClassRule
        public static ExternalResource summary=TimingRules.SUMMARY;

        @Rule
        // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
        public Stopwatch stopwatch=new Stopwatch() {
    @Override
    protected void finished(long nanos, Description description) {
        String result = String.format("%-95s %7d", description.getDisplayName(), TimeUnit.NANOSECONDS.toMillis(nanos));
        results.append(result).append('\n');
        log.info(result + " ms\n");
    }
};


        //    https://dzone.com/articles/applying-new-jdk-11-string-methods
        private static final String DELIM="-".repeat(103);

        @AfterClass
        public static void printResult(){
        log.info("\n" + DELIM +
        "\nTest                                                                                       Duration, ms" +
        "\n" + DELIM + "\n" + results + DELIM + "\n");
        results.setLength(0);
        }
        public Stopwatch stopwatch=TimingRules.STOPWATCH;
        }