package net.jp.vss.speech.speechlet

import com.amazon.speech.speechlet
import com.amazon.speech.ui.{Card, OutputSpeech, Reprompt}

class VssSpeechletResponse(
  outputSpeech: OutputSpeech = null,
  card: Card = null,
  reprompt: Reprompt = null,
  shouldEndSession: Boolean = false) extends speechlet.SpeechletResponse {
  setOutputSpeech(outputSpeech)
  setCard(card)
  setReprompt(reprompt)
  setNullableShouldEndSession(shouldEndSession)
}
