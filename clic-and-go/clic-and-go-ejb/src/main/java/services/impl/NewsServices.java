package services.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.NewsServicesLocal;
import services.interfaces.NewsServicesRemote;
import entities.News;

/**
 * Session Bean implementation class NewsServices
 */
@Stateless
@LocalBean
public class NewsServices implements NewsServicesRemote, NewsServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public NewsServices() {

	}

	@Override
	public Boolean addNews(News news) {
		Boolean b = false;
		try {
			entityManager.merge(news);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean deleteNews(News news) {
		Boolean b = false;
		try {
			news = findNewsByNewsId(news.getNewsId());
			entityManager.remove(news);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean updateNews(News news) {
		Boolean b = false;
		try {
			entityManager.merge(news);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean deleteNewsByNewsId(Integer newsId) {
		Boolean b = false;
		try {
			entityManager.remove(findNewsByNewsId(newsId));
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public News findNewsByNewsId(Integer newsId) {
		return entityManager.find(News.class, newsId);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> findAllNewss() {
		String jpql = "select s from News s";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public News findNewsByNewsTitle(String title) {
		String jpql = "select s from News s where s.title LIKE :param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", '%' + title + '%');
		System.out.println((News) query.getSingleResult());
		return (News) query.getSingleResult();
	}

}
