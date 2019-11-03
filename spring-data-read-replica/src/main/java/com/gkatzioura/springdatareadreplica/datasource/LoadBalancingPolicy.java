package com.gkatzioura.springdatareadreplica.datasource;

import java.util.Collection;
import javax.sql.DataSource;

public interface LoadBalancingPolicy extends AutoCloseable {

    void init(Collection<DataSource> nodes);

    void onUp(DataSource node);

    void onDown(DataSource node);

    DataSource next();
}
