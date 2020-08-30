package com.abc.bank.repositories;

import com.abc.bank.domain.Customer;
import com.abc.bank.to.LightWeightObjectTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Diluni on 17/12/19.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    //fetching only needed columns
    @Query("SELECT DISTINCT new com.abc.bank.to.LightWeightObjectTO(c.id, c.name, t.transactionType, t.amount) FROM Customer c LEFT JOIN c.accounts a LEFT JOIN a.transactions t WHERE t.createdAt>=:from AND t.createdAt<=:to")
    List<LightWeightObjectTO> findTransactionSummery(@Param("from") Date from, @Param("to") Date to);

}
