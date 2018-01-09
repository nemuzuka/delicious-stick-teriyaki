package net.jp.vss;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * エンドポイント.
 */
public class VssSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
	private static final Set<String> supportedApplicationIds = new HashSet<>();
	static {
		//supportedApplicationIds.add("");
	}

	public VssSpeechletRequestStreamHandler() {
		super(new VssSpeechlet(), supportedApplicationIds);
	}
}
