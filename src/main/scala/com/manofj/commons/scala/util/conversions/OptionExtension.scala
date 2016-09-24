package com.manofj.commons.scala.util.conversions


/** $option のインスタンスに対して､ユーティリティメソッドを使用可能にする
  *
  * @define p `self`
  * @define option [[scala.Option]]
  */
trait OptionExtension[ A ]
  extends Any
  with    Extension[ Option[ A ] ]
{

  /** $p isEmpty が `true` を返す場合は value の値を $option でラップして返す
    * そうでなければ $p をそのまま返す
    *
    * @example {{{
    * import com.manofj.commons.scala.util.conversions.OptionExtension.wrap
    *
    * val numberOpt =
    *   Option( "foo" )
    *     .filter( _.forall( _.isDigit ) )
    *     .recover( "-1" )
    *     .map( _.toInt )
    *
    * assert( numberOpt.contains( -1 ) )
    * }}}
    *
    * @param value $p が空だった場合に参照される値
    */
  def recover( value: => A ): Option[ A ] = if ( self.isEmpty ) Option( value ) else self


  /** foreach メソッドの記号表記
    */
  def >>( function: A => Unit ): Unit = self.foreach( function )

  /** map メソッドの記号表記
    */
  def <#[ B ]( function: A => B ): Option[ B ] = self.map( function )

  /** flatMap メソッドの記号表記
    */
  def <<#[ B ]( function: A => Option[ B ] ): Option[ B ] = self.flatMap( function )

}
