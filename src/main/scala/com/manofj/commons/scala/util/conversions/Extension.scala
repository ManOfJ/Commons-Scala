package com.manofj.commons.scala.util.conversions


/** ユーティリティメソッド拡張クラスのベース
  */
trait Extension[ A ]
  extends Any
{

  /** 拡張対象のインスタンス */
  protected def self: A

}
