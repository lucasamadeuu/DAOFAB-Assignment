package com.daofab.api.response;

import java.util.Objects;

public class ChildResponse implements Comparable<ChildResponse>{
    long id;
    String sender;
    String receiver;
    long totalAmount;
    long paidAmount;

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

    public long getPaidAmount() {
        return paidAmount;
    }

    public ChildResponse setChildId(long id) {
        this.id = id;
        return this;
    }

    public ChildResponse setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public ChildResponse setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public ChildResponse setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public ChildResponse setPaidAmount(long paidAmount) {
        this.paidAmount = paidAmount;
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
        ChildResponse other = (ChildResponse) obj;
        return id == other.id;
    }

    @Override
    public int compareTo(ChildResponse o) {
        return this.getId().compareTo(o.getId());
    }
}
