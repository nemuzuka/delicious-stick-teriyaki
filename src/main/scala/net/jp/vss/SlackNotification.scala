package net.jp.vss

import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization
import org.slf4j.{Logger, LoggerFactory}
import skinny.http.{HTTP, Request}

/**
  * 各種通知.
  */
object SlackNotification {

  val LOG : Logger = LoggerFactory.getLogger(this.getClass)
  val URL = "https://slack.com/api/chat.postMessage"

  /**
    * slack通知.
    * @param token TOKEN
    */
  def send(token:String): Unit = {
    val channel = sys.env.getOrElse("SLACK_CHANNEL", "")
    if(channel.nonEmpty) {
      val msg = sys.env.getOrElse("SLACK_MESSAGE", "今日はお休みします...")
      send(token=token, channel=channel, msg=msg)
    }
  }

  /**
    * 通知送信.
    * TOKENの機能を使用して、引数のメッセージをSlackに表示します.
    * @param token Token
    * @param channel チャンネルID.
    * @param msg メッセージ.
    * @return 正常終了しなかった時のエラーメッセージ _1:HTTPステータスコード _2:エラーbody (正常終了時はNone)
    */
  private[this] def send(token: String, channel: String, msg: String): Option[(Int, String)] = {

    implicit val formats = Serialization.formats(NoTypeHints)
    val jsonStr = Serialization.write(
      Param(channel=channel, text=msg)
    )
    val request = Request(URL).body(
      jsonStr.getBytes(HTTP.DEFAULT_CHARSET), "application/json; charset=utf-8"
    ).header("Authorization", s"Bearer $token")
    val response = HTTP.post(request)
    if (200 <= response.status && response.status <= 399) None else Option((response.status, response.textBody))
  }

  /**
    * リクエスト用パラメータ.
    * @param channel チャンネルID
    * @param text メッセージ
    * @param as_user 差出人を制御 true:tokenを発行したユーザ / false:Botが差出人
    */
  case class Param(channel:String, text:String, as_user:Boolean = true)
}
