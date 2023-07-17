package com.daofab.api.model.implement;

import com.daofab.api.dao.TransactionDAO;
import com.daofab.api.model.ParentRepository;
import com.daofab.api.model.entity.Child;
import com.daofab.api.model.entity.Parent;
import com.daofab.api.response.ChildResponse;
import com.daofab.api.response.ParentResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

@Service
public class ParentImplementRepository implements ParentRepository {
    @Autowired
    private TransactionDAO transactionDao;

    @Override
    public Set<ParentResponse> getParentTransactions(int page, int size) {
        Set<ParentResponse> parentTransactionList = new TreeSet<>();

        System.out.println(size);

        try {
            for(Parent parentTransaction : transactionDao.getParentTransactions(page, size)) {
                parentTransactionList.add(
                        new ParentResponse()
                                .setParentId(parentTransaction.getId())
                                .setSender(parentTransaction.getSender())
                                .setReceiver(parentTransaction.getReceiver())
                                .setTotalAmount(parentTransaction.getTotalAmount())
                                .setTotalPaidAmount(transactionDao.getTotalPaidAmountForParentTransaction(parentTransaction.getId()))
                );
            }
        } catch (Exception e) {
            System.out.println("Exception fetching parent transactions." + e);
        }

        return parentTransactionList;
    }

    public Set<ChildResponse> getChildTransactions(long parentId) {
        try {
            Optional<Parent> parent = transactionDao.getParentTransaction(parentId);

            Parent parentTransaction = parent.get();

            Set<ChildResponse> childTransactionList = new TreeSet<>();

            for (Child childTransaction : transactionDao.getChildTransactions(parentTransaction.getId())) {
                childTransactionList.add(
                        new ChildResponse()
                                .setChildId(childTransaction.getId())
                                .setSender(parentTransaction.getSender())
                                .setReceiver(parentTransaction.getReceiver())
                                .setTotalAmount(parentTransaction.getTotalAmount())
                                .setPaidAmount(childTransaction.getPaidAmount())
                );

            }
            return childTransactionList;
        } catch (Exception e) {
            System.out.println("Exception fetching parent transactions." + e);
        }
        return null;
    }

}
