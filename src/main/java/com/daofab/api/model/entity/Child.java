package com.daofab.api.model.entity;

public class Child {
    private Long id;
    private Long parentId;
    private Long paidAmount;

    // Constructors, getters, and setters
    public Child() {
    }

    public Child(Long id, Long parentId, Long paidAmount) {
        this.id = id;
        this.parentId = parentId;
        this.paidAmount = paidAmount;
    }

    public Long getId() {
        return id;
    }

    public Child setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Child setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getPaidAmount() {
        return paidAmount;
    }

    public Child setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", paidAmount=" + paidAmount +
                '}';
    }
}
