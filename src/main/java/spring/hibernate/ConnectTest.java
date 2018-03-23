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
//			UserInfo user1 = session.get(UserInfo.class, 3);
//			System.out.println(user1.getName());
//			System.out.println(user1.getGroup().getName());
//			System.out.println(user1);
			UserGroup group = new UserGroup();
//			group.setId(1);
			group.setName("tom1");
//			session.save(group);
			UserInfo user = new UserInfo();
//			session.update(user);
			
			user.setSex(1);
			user.setGroup(group);
			group.getUserSet().add(user);
			session.save(group);
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
