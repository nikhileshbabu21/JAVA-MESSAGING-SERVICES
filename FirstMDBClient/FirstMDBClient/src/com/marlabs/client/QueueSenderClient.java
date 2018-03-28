package com.marlabs.client;


import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;
import com.marlabs.mdb.vo.Employee;
	 
public class QueueSenderClient {
	
    private static final String QUEUE_LOOKUP = "queue/MyQueue";
    private static final String CONNECTION_FACTORY = "ConnectionFactory";
 
    public static void main(String[] args) {
        sendMessageToQueue();
        sendObjectMessageToQueue();
    }
 
    public static void sendMessageToQueue() {
        try {
            Context context = ClientUtility.getInitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context
                    .lookup(CONNECTION_FACTORY);
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
 
            QueueSender sender = session.createSender(queue);
            TextMessage message = session.createTextMessage();
            message.setText("Welcome to EJB3");
            sender.send(message);
            session.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
    public static void sendObjectMessageToQueue() {
        try {
            Context context = ClientUtility.getInitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context
                    .lookup(CONNECTION_FACTORY);
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
 
            QueueSender sender = session.createSender(queue);
            ObjectMessage message = session.createObjectMessage();
            Employee emp = new Employee();
            emp.setDesignation("Tester");
            emp.setSalary(12000);
            emp.setName("MNOP");
            emp.setId(3);
            message.setObject(emp);
            sender.send(message);
            session.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}


