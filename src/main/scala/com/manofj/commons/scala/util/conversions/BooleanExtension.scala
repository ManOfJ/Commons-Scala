package com.manofj.commons.scala.util.conversions


/** [[scala.Boolean]] のインスタンスに対して､ユーティリティメソッドを使用可能にする
  *
  * @define p `self`
  * @define true `true`
  * @define false `false`
  * @define option [[scala.Option]]
  * @define none [[scala.None]]
  */
trait BooleanExtension
  extends Any
  with    Extension[ Boolean ]
{

  /** $p が $true の場合のみ指定された関数を実行する
    */
  def foreach( function: => Unit ): Unit = if ( self ) function

  /** $p が $true の場合､ value の値を $option でラップして返す
    * $false の場合は $none を返す
    *
    * @param value $p が $true の場合に参照される値
    */
  def map[ A ]( value: => A ): Option[ A ] = if ( self ) Option( value ) else None

  /** $p が $true の場合､ optValue の値を返す
    * $false の場合は $none を返す
    *
    * @param optValue $p が $true の場合に参照される値
    */
  def flatMap[ A ]( optValue: => Option[ A ] ): Option[ A ] = if ( self ) optValue else None

  /** $p が $false の場合､ falseValue の値を返す
    * $true の場合は trueValue の値を返す
    *
    * @param falseValue $p が $false の場合に参照される値
    * @param trueValue  $p が $true の場合に参照される値
    */
  def fold[ A, B >: A ]( falseValue: => A )( trueValue: => B ): B = if ( self ) trueValue else falseValue


  /** foreach メソッドの記号表記
    */
  def >>( function: => Unit ): Unit = foreach( function )

  /** map メソッドの記号表記
    */
  def <#[ A ]( function: => A ): Option[ A ] = map( function )

  /** flatMap メソッドの記号表記
    */
  def <<#[ A ]( function: => Option[ A ] ): Option[ A ] = flatMap( function )

  /** 三項演算を模した構文を提供するオブジェクトを返す
    * 返されたオブジェクトのメソッドを利用して擬似的な三項演算を行うことが可能
    *
    * @example {{{
    * import com.manofj.commons.scala.util.conversions.Boolean
    * assert( ( true  <<? "true value" <<! "false value" ) == "true value" )
    * }}}
    *
    * @param trueValue $p が $true の場合に返される値
    */
  def ?[ A ]( trueValue: => A ): TernaryExtension[ A ] = new TernaryExtension[ A ]( self, () => trueValue )

}

/** 三項演算を模した構文を提供する
  *
  * @define p1 `self._1`
  * @define p2 `self._2`
  * @define true `true`
  * @define false `false`
  */
final class TernaryExtension[ A ]( private val self: ( Boolean, () => A ) )
  extends AnyVal
{

  /** $p1 が $true であれば $p2 を返す
    * そうでなければ falseValue の値を返す
    *
    * @param falseValue $p1 が $false の場合に返される値
    */
  def ![ B >: A ]( falseValue: => B ): B = if ( self._1 ) self._2() else falseValue

}
