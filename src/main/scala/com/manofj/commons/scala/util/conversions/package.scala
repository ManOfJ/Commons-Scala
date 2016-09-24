package com.manofj.commons.scala.util

import scala.language.implicitConversions


/** Scala の標準ライブラリに含まれるクラスを暗黙的に変換､拡張するクラスを提供する
  */
package object conversions {

  implicit class Any$[ A <: Any ]( protected val self: A ) extends AnyVal with AnyExtension[ A ]

  implicit class Boolean$( protected val self: Boolean ) extends AnyVal with BooleanExtension

  implicit class Option$[ A ]( protected val self: Option[ A ] ) extends AnyVal with OptionExtension[ A ]

}
