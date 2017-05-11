package kpi.ipt.organizer.mail.config;

import kpi.ipt.organizer.mail.MailingConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(messageConverter());

        return factory;
    }

    @Bean
    public Queue mailingQueue() {
        return new Queue(MailingConstants.QUEUE_NAME, true, false, false, null);
    }

    @Bean
    public Binding mailingBinding() {
        return BindingBuilder.bind(mailingQueue())
                .to(mailingExchange())
                .with(MailingConstants.ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Exchange mailingExchange() {
        DirectExchange mailingExchange = new DirectExchange(MailingConstants.EXCHANGE_NAME, true, false, null);
        mailingExchange.setDelayed(true);

        return mailingExchange;
    }
}
