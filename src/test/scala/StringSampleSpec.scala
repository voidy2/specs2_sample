import org.specs2.mutable._
import org.specs2.mock._
import StringSample._

class StringSampleSpec extends Specification with Mockito {

  "StringSample" should {
    "one" in new BeforeAndAfter {
      println("処理")
      hogehoge must beEqualTo("one")
    }

    "mirror" in {
      mirror("hoge") must be matching("hog.")
      mirror("hoge") must =~("^ho")
      mirror("fuga") must =~("fuga")
      mirror("fuga") must not endWith("hoge")
      mirror("fuga") must not startWith("hoge")
      mirror("fuga") must not contain("hoge")
      mirror(null) must beNull
      mirror("a b c") must find("(\\S+)").withGroups("a", "b", "c")
    }

    "hoge" in {
      val m = mock[StringSample]
      m.hoge returns "hoge"
      hoge2(m) must be equalTo("hogehoge")
      there was two(m).hoge
    }
  }

  trait BeforeAndAfter extends After {
    lazy val hogehoge = StringSample.one
    println("before")
    def after = println("after")
  }
}

