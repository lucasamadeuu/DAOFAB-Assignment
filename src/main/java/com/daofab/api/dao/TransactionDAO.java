package com.daofab.api.dao;

import com.daofab.api.model.entity.Child;
import com.daofab.api.model.entity.Parent;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TransactionDAO {
    void setParentTransactions(List<Parent> parentTransactions);

    void setChildTransactionsMap(Map<Long, List<Child>> childTransactionsMap);

    List<Parent> getParentTransactions(int page, int size);

    List<Child> getChildTransactions(long parentId);

    Optional<Parent> getParentTransaction(long parentId);

    long getTotalPaidAmountForParentTransaction(long parentId);
}
