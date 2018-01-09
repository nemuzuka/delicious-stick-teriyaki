package net.jp.vss.speech.ui

import com.amazon.speech.ui

class VssPlainTextOutputSpeech(text: String) extends ui.PlainTextOutputSpeech {
  setText(text)
}
