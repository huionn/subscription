package com.test.subscription.service;

import com.test.subscription.model.SubscriptionReq;
import com.test.subscription.model.SubscriptionResp;

public interface SubscriptionService {

	public SubscriptionResp register(SubscriptionReq req);
}
