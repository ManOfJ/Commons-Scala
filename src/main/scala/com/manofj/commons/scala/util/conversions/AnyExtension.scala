package com.manofj.commons.scala.util.conversions

import scala.language.implicitConversions
import scala.util.Try

import com.manofj.commons.scala.util.DefaultValue


/** あらゆるクラスのインスタンスに対して､ユーティリティメソッドを使用可能にする
  *
  * @define p `self`
  * @define null `null`
  * @define option [[scala.Option]]
  * @define try [[scala.util.Try]]
  */
trait AnyExtension[ A <: Any ]
  extends Any
  with    Extension[ A ]
{

  /** $p が $null であれば `true` を返す
    * そうでなければ `false` を返す
    */
  def isNull: Boolean = Option( self ).isEmpty

  /** $p が $null でなければ `true` を返す
    * そうでなければ `false` を返す
    */
  def nonNull: Boolean = Option( self ).isDefined

  /** 指定された初期化関数を実行後 $p を返す
    *
    * @example {{{
    * import java.sql.Connection
    * import java.sql.DriverManager
    * import com.manofj.commons.scala.util.conversions.AnyExtension.wrap
    *
    * val connection = DriverManager.getConnection( "foo.bar.baz" ) << { c =>
    *   c.setAutoCommit( false )
    *   c.setReadOnly( true )
    * }
    *
    * assert( connection.isInstanceOf[ Connection ] )
    * assert( !connection.getAutoCommit )
    * assert( connection.isReadOnly )
    * }}}
    * @param initializer 初期化関数
    */
  def <<( initializer: A => Unit ): A = { initializer( self ); self }

  /** $p が $null でなければ function を実行する
    *
    * @note これは $option( $p ) foreach function と同等です
    * @param function $p が $null でない場合に実行される関数
    */
  def >>( function: A => Unit ): Unit = Option( self ).foreach( function )

  /** $p を $option でラップし､指定された変換関数で置き換えた値を返す
    *
    * @note これは $option( $p ) map converter と同等です
    * @param converter $p の変換関数
    */
  def <#[ B ]( converter: A => B ): Option[ B ] = Option( self ).map( converter )

  /** $p を $option でラップし､指定された変換関数で置き換えたものを返す
    *
    * @note これは $option( $p ) flatMap converter と同等です
    * @param converter $p の変換関数
    */
  def <<#[ B ]( converter: A => Option[ B ] ): Option[ B ] = Option( self ).flatMap( converter )

  /** $p を指定された変換関数で置き換えた値を返す
    * 変換に失敗した場合は [[com.manofj.commons.scala.util.DefaultValue]] が提供する初期値を返す
    *
    * @note これは $try( converter( $p ) ) getOrElse default.get と同等です
    * @param converter $p の変換関数
    */
  def <%%[ B ]( converter: A => B )
              ( implicit default: DefaultValue[ B ] ): B = Try( converter( self ) ).getOrElse( default.get )

  /** $p を指定された変換関数で置き換えたものを $option でラップして返す
    * 変換に失敗した場合は [[scala.None]] を返す
    *
    * @note これは $try( converter( $p ) ) toOption と同等です
    * @param converter $p の変換関数
    */
  def <%?[ B ]( converter: A => B ): Option[ B ] = Try( converter( self ) ).toOption

  /** $p を $option でラップして返す
    */
  def ? : Option[ A ] = Option( self )

  /** $p が $null であれば指定のデフォルト値を返す
    *
    * @note これは $option( $p ) getOrElse value と同等です
    * @param value $p が $null の場合に参照される初期値
    */
  def ?:[ B >: A ]( value: => B ): B = Option( value ).getOrElse( self )

}
