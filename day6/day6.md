# APIをたたく前に
## APIとは

## 非同期処理
APIを使用する場合、以下のような流れで記述しようとすると思います。

1. APIを使用して情報を取得
2. 取得した情報を表示

実際この流れで間違いではありませんが、アプリが堕ちてしまいます。なぜかというと、情報を取得している間はActivityが停止することになってしまい、Androidの仕様上、数秒停止した場合にアプリを強制的に落とされてしまうからです。そして、これまたAndroidの仕様上、メインスレッドではネットへ接続する処理ができません。

これらを解決するために、**表では今まで通りの処理をして、裏では情報の取得を行う**という作業が必要です。

# APIをたたく
**OpenWeatherMap**というAPIを使います。
まずは
https://openweathermap.org/
に登録してAPIキーを入手しましょう。


[![Image from Gyazo](https://i.gyazo.com/6f230ed4abc29db03e33eff0b1016adb.png)](https://gyazo.com/6f230ed4abc29db03e33eff0b1016adb)

