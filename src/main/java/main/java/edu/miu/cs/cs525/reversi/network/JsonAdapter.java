package main.java.edu.miu.cs.cs525.reversi.network;

public class JsonAdapter implements TargetJson {
	private AdapteeJson adapteeJson = new AdapteeJson();

	@Override
	public String JsontoString(String str) {

		if (isJson(str)) {
			return adapteeJson.JsonString(str);
		}

		return str;
	}

	@Override
	public boolean isJson(String jsonInString) {
		return adapteeJson.isJson(jsonInString);
	}
}
