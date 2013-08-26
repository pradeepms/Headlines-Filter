package spring.android.pradeepms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {

	private List<Article> article;

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public
	static class Article {

		private String headline;
		private String url;
		private String timestamp;

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getHeadline() {
			return headline;
		}

		public void setHeadline(String headline) {
			this.headline = headline;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}
}