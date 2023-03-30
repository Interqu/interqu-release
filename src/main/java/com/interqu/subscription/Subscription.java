package com.interqu.subscription;

import java.util.Date;
import java.util.HashSet;

public class Subscription {

	public static final String FREE = "FREE_PLAN";
	public static final String TIER_1 = "INTERMEDIATE_PLAN";
	public static final String TIER_2 = "PREMIUM_PLAN";
	
	public static final String MONTHY = "MONTHLY";
	public static final String YEARLY = "YEARLY";
	
	private String subscriptionType;
	private String subscriptionPaymentPlan;
	private Double cost;
	private Date nextRenew;
	private HashSet<Subscription> history = new HashSet<Subscription>();
	
	public Subscription(String subscriptionType, String subscriptionPaymentPlan, Double cost, Date nextRenew,
			HashSet<Subscription> history) {
		super();
		this.subscriptionType = subscriptionType;
		this.subscriptionPaymentPlan = subscriptionPaymentPlan;
		this.cost = cost;
		this.nextRenew = nextRenew;
		this.history = history;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getSubscriptionPaymentPlan() {
		return subscriptionPaymentPlan;
	}

	public void setSubscriptionPaymentPlan(String subscriptionPaymentPlan) {
		this.subscriptionPaymentPlan = subscriptionPaymentPlan;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Date getNextRenew() {
		return nextRenew;
	}

	public void setNextRenew(Date nextRenew) {
		this.nextRenew = nextRenew;
	}

	public HashSet<Subscription> getHistory() {
		return history;
	}

	public void setHistory(HashSet<Subscription> history) {
		this.history = history;
	}	
}
