package com.manofj.commons.scala.util

import scala.language.implicitConversions


/** オーソドックスな型の [[com.manofj.commons.scala.util.DefaultValue]] 実装オブジェクトを提供する
  */
object DefaultValue {

  implicit def toValue[ A ]( default: DefaultValue[ A ] ): A = default.get


  trait ByteDefault extends DefaultValue[ Byte ] { override final val get: Byte = 0 }
  implicit object Byte extends ByteDefault

  trait ShortDefault extends DefaultValue[ Short ] { override final val get: Short = 0 }
  implicit object Short extends ShortDefault

  trait IntDefault extends DefaultValue[ Int ] { override final val get: Int = 0 }
  implicit object Int extends IntDefault

  trait LongDefault extends DefaultValue[ Long ] { override final val get: Long = 0 }
  implicit object Long extends LongDefault

  trait BigIntDefault extends DefaultValue[ BigInt ] { override final val get: BigInt = math.BigInt( 0 ) }
  implicit object BigInt extends BigIntDefault

  trait FloatDefault extends DefaultValue[ Float ] { override final val get: Float = 0 }
  implicit object Float extends FloatDefault

  trait DoubleDefault extends DefaultValue[ Double ] { override final val get: Double = 0 }
  implicit object Double extends DoubleDefault

  trait BigDecimalDefault extends DefaultValue[ BigDecimal ] { override final val get: BigDecimal = math.BigDecimal( 0 ) }
  implicit object BigDecimal extends BigDecimalDefault

  trait BooleanDefault extends DefaultValue[ Boolean ] { override final val get: Boolean = false }
  implicit object Boolean extends BooleanDefault

  trait CharDefault extends DefaultValue[ Char ] { override final val get: Char = '\u0000' }
  implicit object Char extends CharDefault

  trait StringDefault extends DefaultValue[ String ] { override final val get: String = "" }
  implicit object String extends StringDefault


  def apply[ A ]( implicit default: DefaultValue[ A ] ): DefaultValue[ A ] = default

}

/** 特定の型のデフォルト値を提供する
  */
trait DefaultValue[ A ] { def get: A }
