package org.indecomm.springretry;

import java.sql.SQLException;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface MyService {
    
    @Retryable(value = { RuntimeException.class }, maxAttempts = 2, backoff = @Backoff(delay = 5000))
    void retryService();

    @Retryable(value = { SQLException.class }, maxAttempts = 2, backoff = @Backoff(delay = 5000))
    void retryServiceWithRecovery(String sql) throws SQLException;

    @Recover
    void recover(SQLException e, String sql); //处理SQLException

    @Recover
    void recover(RuntimeException e); //处理RuntimeException

    void templateRetryService();
}
