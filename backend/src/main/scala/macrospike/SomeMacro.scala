package macrospike

object SomeMacro {

  import scala.compiletime.*
  import scala.deriving.*
  import scala.quoted.*

  trait SomeLongFieldUpdater[T] {
    def updateSomeLongField(t: T, newLongValue: Long): T
  }

  object SomeLongFieldUpdater {

    // inline def derived[T]: SomeLongFieldUpdater[T] = ${ derivedMacro[T] }
    inline def derived[T](inline code: () => SomeLongFieldUpdater[T]): Unit = ${
      derivedMacro[T]('code)
    }

    def updateSomeLongFieldWrapper[T](
        body: (T, Long) => T
    ): SomeLongFieldUpdater[T] =
      new SomeLongFieldUpdater[T] {
        def updateSomeLongField(t: T, newLongValue: Long): T =
          body(t, newLongValue)
      }

    private def derivedMacro[T: Type](
        code: Expr[() => SomeLongFieldUpdater[T]]
    )(using Quotes): Expr[Unit] = {
      import quotes.reflect.*

      val typeRepr         = TypeRepr.of[T]
      val classSymbol      = typeRepr.typeSymbol
      val copyMethodSymbol = classSymbol.methodMember("copy").head

      /*       code.asTerm.underlyingArgument match {
        case Block(statements, term) =>
          statements.head match {
            case tree: Tree =>
              // report.errorAndAbort(
                // s"tree: ${tree.show(using Printer.TreeStructure)}"
                  report.errorAndAbort(
                    s"tree: ${tree.show(using Printer.TreeStructure)}"
                  )


              )
          }

          // report.errorAndAbort(
          //   s"statement 1: ${term.show(using Printer.TreeStructure)}"
          // )
      }
       */

/*       Block(
        List(
          DefDef(
            "$anonfun",
            List(TermParamClause(Nil)),
            Inferred(),
            Some(
              Apply(
                TypeApply(
                  Select(Ident("SomeLongFieldUpdater"), "updateSomeLongFieldWrapper"),
                  List(TypeIdent("SomeClass"))
                ),
                List(
                  Block(
                    List(
                      DefDef(
                        "$anonfun",
                        List(
                          TermParamClause(
                            List(ValDef("someClass", Inferred(), None), ValDef("newValue", Inferred(), None))
                          )
                        ),
                        Inferred(),
                        Some(
                          Apply(
                            Select(Ident("someClass"), "copy"),
                            List(Select(Ident("someClass"), "copy$default$1"), Ident("newValue"))
                          )
                        )
                      )
                    ),
                    Closure(Ident("$anonfun"), None)
                  )
                )
              )
            )
          )
        ),
        Closure(Ident("$anonfun"), None)
      )
 */
      report.errorAndAbort(
        s"code: ${code.asTerm.underlyingArgument.show(using Printer.TreeStructure)}"
      )

      '{ () }

    }

  }

}
