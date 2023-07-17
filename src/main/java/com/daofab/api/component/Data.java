package com.daofab.api.component;

import com.daofab.api.dao.TransactionDAO;
import com.daofab.api.model.entity.Child;
import com.daofab.api.model.entity.Parent;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Data {

    @Autowired
    private TransactionDAO transactionDao;

    @PostConstruct
    private void loadData() {
        try {
            loadParentTransactions();
            loadChildTransactions();
        } catch (IOException e) {
            throw new RuntimeException("Exception loading transactions data.", e);
        }
    }

    private void loadParentTransactions() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/Parent.json")) {
            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            ParentTransactionData parentTransactionData = new Gson().fromJson(content, ParentTransactionData.class);
            List<Parent> parentTransactions = parentTransactionData.getData().stream().sorted(Comparator.comparing(Parent::getId)).collect(Collectors.toList());
            transactionDao.setParentTransactions(parentTransactions);
        }
    }

    private void loadChildTransactions() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/Child.json")) {
            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            ChildTransactionData childTransactionData = new Gson().fromJson(content, ChildTransactionData.class);
            Map<Long, List<Child>> childTransactionsMap = childTransactionData.getData().stream().collect(Collectors.groupingBy(Child::getParentId));
            transactionDao.setChildTransactionsMap(childTransactionsMap);
        }
    }

    private static class ParentTransactionData {
        private List<Parent> data;

        public List<Parent> getData() {
            return data;
        }
    }

    private static class ChildTransactionData {
        private List<Child> data;

        public List<Child> getData() {
            return data;
        }
    }
}
