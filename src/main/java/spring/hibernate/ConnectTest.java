package spring.hibernate;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectTest {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session  = sf.openSession();
//		Session session  = sf.getCurrentSession();
		try {
			session.beginTransaction();
			UserInfo user1 = session.get(UserInfo.class, 1);
//			System.out.println(user1);
//			UserInfo user = new UserInfo();
			user1.setAge(22222);
			user1.setName("tom2");
//			user.setId(1);
//			session.update(user);
//			session.save(user);
//			user.setAge(25);
//			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			sf.close();
		}
		
		
	}
}
