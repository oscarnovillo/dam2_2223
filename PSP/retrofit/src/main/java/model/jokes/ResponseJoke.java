package model.jokes;

import lombok.Data;


@Data
public class ResponseJoke{
	private final Flags flags;
	private final boolean safe;
	private final int id;
	private final boolean error;
	private final String category;
	private final String type;
	private final String lang;
	private final String joke;
	private final String setup;
	private final String delivery;

	public ResponseJoke(Flags flags, boolean safe, int id, boolean error, String category, String type, String lang, String joke, String setup, String delivery) {
		this.flags = flags;
		this.safe = safe;
		this.id = id;
		this.error = error;
		this.category = category;
		this.type = type;
		this.lang = lang;
		this.joke = joke;
		this.setup = setup;
		this.delivery = delivery;
	}
}
