package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.NewsServicesLocal;
import entities.News;

@ManagedBean
@ViewScoped
public class NewsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Models
	private News news = new News();
	private List<News> newss;

	// Injection
	@EJB
	private NewsServicesLocal newsServicesLocal;

	public NewsServicesLocal getNewsServicesLocal() {
		return newsServicesLocal;
	}

	public void setNewsServicesLocal(NewsServicesLocal newsServicesLocal) {
		this.newsServicesLocal = newsServicesLocal;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewss() {
		newss = newsServicesLocal.findAllNewss();
		return newss;
	}

	public void setNewss(List<News> newss) {
		this.newss = newss;
	}

	public Boolean doAddNews(News news) {
		return newsServicesLocal.addNews(news);

	}

	public Boolean doDeleteNews(News news) {

		return newsServicesLocal.deleteNews(news);

	}

	public Boolean doUpdateNews(News news) {

		return newsServicesLocal.updateNews(news);
	}

	public Boolean doDeleteNewsByNewsId(Integer newsId) {

		return newsServicesLocal.deleteNewsByNewsId(newsId);
	}

	public News doFindNewsByNewsId(Integer newsId) {
		return newsServicesLocal.findNewsByNewsId(newsId);

	}

	public List<News> doFindAllNewss() {

		return newsServicesLocal.findAllNewss();
	}

	public News doFindNewsByNewsTitle(String title) {
		return newsServicesLocal.findNewsByNewsTitle(title);

	}

}
