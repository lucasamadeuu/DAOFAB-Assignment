package com.daofab.api.model;

import java.util.Set;
import com.daofab.api.response.ParentResponse;

public interface ParentRepository {
    Set<ParentResponse> getParentTransactions(int page, int size);
}
