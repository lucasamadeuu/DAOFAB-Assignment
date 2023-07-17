package com.daofab.api.response;

import java.util.Objects;

public class ParentResponse implements Comparable<ParentResponse>{
    long id;
    String sender;
    String receiver;
    long totalAmount;
    long totalPaidAmount;

    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public long getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public ParentResponse setParentId(long id) {
        this.id = id;
        return this;
    }

    public ParentResponse setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public ParentResponse setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public ParentResponse setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public ParentResponse setTotalPaidAmount(long totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParentResponse other = (ParentResponse) obj;
        return id == other.id;
    }


    @Override
    public int compareTo(ParentResponse o) {
        return this.getId().compareTo(o.getId());
    }
}
