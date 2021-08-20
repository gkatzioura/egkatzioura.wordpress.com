package com.gkatzioura.r2dbc.repository;

import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.gkatzioura.r2dbc.model.OrderRequest;

public interface OrderRepository extends ReactiveCrudRepository<OrderRequest, UUID> {
}
