package net.jp.vss

import com.amazon.speech.speechlet._
import net.jp.vss.speech.ui.VssPlainTextOutputSpeech
import net.jp.vss.speech.speechlet.VssSpeechletResponse
import org.apache.commons.lang3.builder.ToStringBuilder
import org.slf4j.{Logger, LoggerFactory}

class VssSpeechlet extends Speechlet {

  val LOG : Logger = LoggerFactory.getLogger(this.getClass)

  override def onSessionStarted(request: SessionStartedRequest, session: Session): Unit = {
    logInvocation("onSessionStarted", request, session)
  }

  override def onLaunch(request: LaunchRequest, session: Session): SpeechletResponse = {
    logInvocation("onLaunch", request, session)
    if(session.getUser.getAccessToken == null) {
      new VssSpeechletResponse(
        outputSpeech = new VssPlainTextOutputSpeech("おっと、アカウントリンクが無効なようです。ちょっと確認していただけますか？"),
        shouldEndSession = true
      )
    } else {
      SlackNotification.send(session.getUser.getAccessToken)
      new VssSpeechletResponse(
        outputSpeech = new VssPlainTextOutputSpeech("Slackに通知しました"),
        shouldEndSession = true
      )
    }
  }

  override def onIntent(request: IntentRequest, session: Session): SpeechletResponse = {
    logInvocation("onIntent", request, session)
    if(session.getUser.getAccessToken == null) {
      new VssSpeechletResponse(
        outputSpeech = new VssPlainTextOutputSpeech("あれ？アカウントリンクが無効なようです。ちょっと確認していただけますか？"),
        shouldEndSession = true
      )
    } else {
      SlackNotification.send(session.getUser.getAccessToken)
      new VssSpeechletResponse(
        outputSpeech = new VssPlainTextOutputSpeech("スラックに通知しました"),
        shouldEndSession = true
      )
    }
  }

  override def onSessionEnded(request: SessionEndedRequest, session: Session): Unit = {
    logInvocation("onSessionEnded", request, session)
  }

  private def logInvocation(name: String, request: SpeechletRequest, session: Session): Unit = {
    val sessionStr = ToStringBuilder.reflectionToString(session.getAttributes)
    val userStr = ToStringBuilder.reflectionToString(session.getUser)
    LOG.debug(s"$name user=$userStr session=$sessionStr")
  }
}
