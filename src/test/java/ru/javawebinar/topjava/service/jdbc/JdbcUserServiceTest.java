package ru.javawebinar.topjava.service.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.postgresql.util.PSQLException;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Override
    @Before
    public void setup() {
        //Do nothing because we don't use CacheManager & JpaUtil fields in this test class
    }

    @Override
    @Test
    public void createWithException() {
        validateRootCause(PSQLException.class, () -> service.create(new User()));
    }
}