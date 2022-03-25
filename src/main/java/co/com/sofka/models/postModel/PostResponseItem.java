package co.com.sofka.models.postModel;

public class PostResponseItem {
	private String name;
	private Integer postId;
	private Integer id;
	private String body;
	private String email;

	public String getName(){
		return name;
	}

	public Integer getPostId(){
		return postId;
	}

	public Integer getId(){
		return id;
	}

	public String getBody(){
		return body;
	}

	public String getEmail(){
		return email;
	}
}
