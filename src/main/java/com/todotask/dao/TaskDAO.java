package com.todotask.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.todotask.model.Task;

public class TaskDAO {
	
	public void addTask(Task bean) {
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addTask(session,bean);        
        tx.commit();
        session.close();
	}
	
	private void addTask(Session session,Task bean) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setDetail(bean.getDetail());
		task.setSubject(bean.getSubject());
		task.setStatus(bean.getStatus());
		session.save(task);
	}
	
	public List<Task> getTasks() {
		Session session = SessionUtil.getSession();
		System.out.println("test");
		Query query = session.createQuery("from Task");
		List<Task> tasks = query.list();
		session.close();
		return tasks;
	}
	
	public Task getTask(int id) {
		Session session = SessionUtil.getSession();
//		System.out.println("test");
		Query query = session.createQuery("from Task where id = :id");
		query.setInteger("id",id);
		Task task = (Task) query.getSingleResult();
//		List<Task> tasks = query.list();
		session.close();
		return task;
	}
	
	public int deleteTask(int id){
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Task where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id",id);
		int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}
	
	public int updateTask(int id,Task task){
        if(id <=0)  
            return 0;  
        System.out.println("Detail legnt: "+task.getDetail().length()+", Subjec legnt: "+task.getSubject().length());
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "update Task set subject = :subject, detail=:detail, status=:status where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        query.setString("subject",task.getSubject());
        query.setString("detail",task.getDetail());
        query.setString("status",task.getStatus());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}
	
	public int setStatus(int id,Task task){
		 if(id <=0)  
	            return 0;  
	        Session session = SessionUtil.getSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "update Task set status=:status where id = :id";
	        Query query = session.createQuery(hql);
	        query.setInteger("id",id);
	        query.setString("status",task.getStatus());
	        int rowCount = query.executeUpdate();
	        System.out.println("Rows affected: " + rowCount);
	        tx.commit();
	        session.close();
	        return rowCount;
	}

}
