package spring.android.pradeepms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DayLifeJSON {
	private Response response;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	static class Response {
		private String code;
		private Payload payload;

		public Payload getPayload() {
			return payload;
		}

		public void setPayload(Payload payload) {
			this.payload = payload;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}
}
