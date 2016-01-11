package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.News;

@Local
public interface NewsServicesLocal {

	public Boolean addNews(News news);


	public Boolean deleteNews(News news);

	public Boolean updateNews(News news);

	public Boolean deleteNewsByNewsId(Integer newsId);

	public News findNewsByNewsId(Integer newsId);

	public List<News> findAllNewss();

	public News findNewsByNewsTitle(String title);
}
