package Parser;

public class Categorie {

	private String name;
	private String content;

	public Categorie(String name, String content) {
		this.name = name;
		this.content = content.replace("\t", " ");
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

}
