package org.xarch.reliable.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	public static final String topicPayExchange = "pay.exchange";

	public static final String refundQueue = "refund.touser.queue";
	public static final String payToUserQueue = "pay.touser.queue";

	@Bean
	public Queue RefundQueue() {
		// 是否持久化
		boolean durable = false;
		// 仅创建者可以使用的私有队列，断开后自动删除
		boolean exclusive = true;
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = true;
		return new Queue(refundQueue, durable, exclusive, autoDelete);
	}

	@Bean
	public Queue PayToUserQueue() {
		// 是否持久化
		boolean durable = false;
		// 仅创建者可以使用的私有队列，断开后自动删除
		boolean exclusive = true;
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = true;
		return new Queue(payToUserQueue, durable, exclusive, autoDelete);
	}

	@Bean
	public TopicExchange PayExchange() {
		// 是否持久化
		boolean durable = true;
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = true;
		return new TopicExchange(topicPayExchange, durable, autoDelete);
	}

	@Bean
	public Binding binding1(Queue RefundQueue, TopicExchange PayExchange) {
		return BindingBuilder.bind(RefundQueue).to(PayExchange).with("refund.touser.#");
	}
	
	@Bean
	public Binding binding3(Queue PayToUserQueue, TopicExchange PayExchange) {
		return BindingBuilder.bind(PayToUserQueue).to(PayExchange).with("pay.touser.#");
	}

	
}
