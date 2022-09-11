package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @SuppressWarnings("unchecked")
   @Override
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   @Override
   public User findByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory
              .getCurrentSession()
              .createQuery("FROM User user JOIN FETCH user.car WHERE user.car.model = :model AND user.car.series = :series")
              .setParameter("model", model)
              .setParameter("series", series);
      return query.getSingleResult();
   }

}
