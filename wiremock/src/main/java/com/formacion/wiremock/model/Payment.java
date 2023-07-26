package com.formacion.wiremock.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.iban4j.Iban;


public class Payment {

    public Payment() {
    }

    public Payment(Iban debtor, Iban creditor, BigDecimal amount) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }
    @JsonProperty
    private Iban debtor;
    @JsonProperty
    private Iban creditor;
    private BigDecimal amount;

    public Iban getDebtor() {
        return debtor;
    }

    public void setDebtor(Iban debtor) {
        this.debtor = debtor;
    }

    public Iban getCreditor() {
        return creditor;
    }

    public void setCreditor(Iban creditor) {
        this.creditor = creditor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(debtor, payment.debtor) &&
                Objects.equals(creditor, payment.creditor) &&
                Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debtor, creditor, amount);
    }
    }


