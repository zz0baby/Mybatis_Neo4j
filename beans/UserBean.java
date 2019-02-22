package beans;

//import java.io.Serializable;

public class UserBean {
//	private static final long serialVersionUID = 1L;
	private int id;
	private String released;
	private String tagline;
	private String title;
	private int degree;

	public UserBean() {
	}

	public UserBean(String released, String tagline, String title, int degree) {
		super();
		this.released = released;
		this.tagline = tagline;
		this.title = title;
		this.degree = degree;
	}

	public UserBean(int id, String released, String tagline, String title, int degree) {
		super();
		this.id = id;
		this.released = released;
		this.tagline = tagline;
		this.title = title;
		this.degree = degree;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return "ABean [id=" + id + ", released=" + released + ", tagline=\"" + tagline + "\", title=\"" + title + "\", degree="
				+ degree + "]";
	}

}
