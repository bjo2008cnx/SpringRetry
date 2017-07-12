package org.indecomm.springretry;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MyServiceImpl implements MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

    @Override
    public void retryService() {
        logger.info("抛出异常. throw RuntimeException in method retryService()");
        throw new RuntimeException("服务调用失败");
    }

    @Override
    public void retryServiceWithRecovery(String sql) throws SQLException {
        if (StringUtils.isEmpty(sql)) {
            logger.info("抛出SQL异常. throw SQLException in method retryServiceWithRecovery()");
            throw new SQLException("SQL执行失败");
        }
    }

    @Override
    public void recover(SQLException e, String sql) {
        logger.info("恢复..........In recover method");
    }

    @Override
    public void recover(RuntimeException e) {
        logger.info("恢复.No sql..........In recover method");
    }

    @Override
    public void templateRetryService() {
        logger.info("模板重试方法. throw RuntimeException in method templateRetryService()");
        throw new RuntimeException("模板重试方法调用失败");
    }
}
