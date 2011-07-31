class StringSample {
  def hoge = "hoge"
}

object StringSample {
  def one = "one"
  def mirror(str: String) = str
  def hoge2(s: StringSample) = s.hoge + s.hoge
}
