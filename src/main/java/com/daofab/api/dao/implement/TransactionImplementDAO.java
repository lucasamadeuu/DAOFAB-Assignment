package com.daofab.api.dao.implement;

import com.daofab.api.dao.TransactionDAO;
import com.daofab.api.model.entity.Child;
import com.daofab.api.model.entity.Parent;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TransactionImplementDAO implements TransactionDAO {
    private List<Parent> parentTransactions;
    private Map<Long, List<Child>> childTransactionsMap;

    @Override
    public void setParentTransactions(List<Parent> parentTransactions) {
        this.parentTransactions = parentTransactions;
    }

    @Override
    public void setChildTransactionsMap(Map<Long, List<Child>> childTransactionsMap) {
        this.childTransactionsMap = childTransactionsMap;
    }

    @Override
    public List<Parent> getParentTransactions(int page, int size) {
        List<Parent> parentTransactionList = new ArrayList<>();

        int i=(page*size);

        while(i<(page*size + size) && i<parentTransactions.size()) {
            parentTransactionList.add(parentTransactions.get(i));
            i++;
        }

        return parentTransactionList;
    }

    @Override
    public List<Child> getChildTransactions(long parentId) {
        return childTransactionsMap.get(parentId) == null ? Collections.emptyList() : childTransactionsMap.get(parentId);
    }

    @Override
    public Optional<Parent> getParentTransaction(long parentId) {
        return parentTransactions.stream().filter(p -> p.getId() == parentId).findFirst();
    }

    @Override
    public long getTotalPaidAmountForParentTransaction(long parentId) {
        List<Child> childTransactions = childTransactionsMap.get(parentId);

        if(childTransactions != null) {
            return childTransactions.stream().mapToLong(child -> child.getPaidAmount()).sum();
        }else {
            return 0;
        }
    }
}
