package spring.android.pradeepms.signature;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignatureClient {

	private final String[] auth_keys = { "topic_id", "article_id", "story_id",
			"image_id", "quote_id", "set_id", "query", "name" };

	private final String _sharedsecret;
	private final String _accesskey;
	public SignatureClient(String accesskey, String sharedsecret,
			String server, String version) {
		_accesskey = accesskey;
		_sharedsecret = sharedsecret;
	}

	public String call(String method_name, Map<String, String> params) {

		// Create Signature based on the input params
		String signature = this.create_signature(params);

		return signature.toString();

	}

	private String create_signature(Map params) {
		String strValue = "";
		for (int i = 0; i < auth_keys.length; i++) {
			if (params.containsKey(auth_keys[i])) {
				Object value = (Object) params.get(auth_keys[i]);
				if (value instanceof List) {
					List newval = (List) value;
					Collections.sort(newval);
					for (int j = 0; j < newval.size(); j++) {
						strValue += (String) newval.get(j);
					}

				} else {
					strValue = (String) value;
				}
			}
		}
		return this.getMD5Hash(strValue);
	}

	private String getMD5Hash(String query) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(_accesskey);
		buffer.append(_sharedsecret);
		buffer.append(query);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			StringBuffer result = new StringBuffer();
			byte bbytes[] = md.digest(buffer.toString().getBytes());
			for (int i = 0; i < bbytes.length; i++) {
				byte b = bbytes[i];
				result.append(Integer.toHexString((b & 0xf0) >>> 4));
				result.append(Integer.toHexString(b & 0x0f));
			}
			return result.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.err.println("MD5 does not appear to be supported" + e);
			System.exit(1);
		}
		return "";
	}

}
