package macrospike

import SomeMacro.SomeLongFieldUpdater

object SomeScalaClass {

  case class SomeClass(
      name: String,
      someLongField: Long
  ) // derives SomeLongFieldUpdater

  val someClass = SomeClass("name1", 1L)
  val newValue = 2L

  val result = SomeLongFieldUpdater.derived[SomeClass](() =>
    SomeLongFieldUpdater.updateSomeLongFieldWrapper[SomeClass](
      (someClass, newValue) => someClass.copy(someLongField = newValue)
    )
  )

}
