# APIをたたく前に
## APIとは

## 非同期処理
APIを使用する場合、以下のような流れで記述しようとすると思います。

1. APIを使用して情報を取得
2. 取得した情報を表示

実際この流れで間違いではありませんが、アプリが堕ちてしまいます。なぜかというと、情報を取得している間はActivityが停止することになってしまい、Androidの仕様上、数秒停止した場合にアプリを強制的に落とされてしまうからです。そして、これまたAndroidの仕様上、メインスレッドではネットへ接続する処理ができません。

これらを解決するために、**表では今まで通りの処理をして、裏では情報の取得を行う**という作業が必要です。

書き方は以下のようになっています。


```java
import android.os.AsyncTask;

// params, progress, resultは任意の型に置き換えられる
public class MyAsyncTask extends AsyncTask<Params, Progress, Result> {

    @Override
    protected void onPreExecute() {
        //バックグラウンド処理開始前にUIスレッドで実行される。
        //ダイアログの生成などを行う。
    }

    @Override
    protected Result doInBackground(Params... params) {
        //バックグラウンドで処理させる内容をここで記述。
        //AsyncTaskを使うにあたって、このメソッドの中身は必ず記述しなければいけない。
    }

    @Override
    protected void onProgressUpdate(Progress... values) {
        //doInBackgroundの実行中にUIスレッドで実行される。
        //引数のvaluesを使ってプログレスバーの更新などをする際は、ここに記述する。
    }

    @Override
    protected void onPostExecute(Result result) {
        //doInBackgroundが終了するとUIスレッドで実行される。
        //ダイアログの消去などを行う。
        //doInBackgroundの結果を画面表示に反映させる処理もここに記述。
    }
}
```

# APIをたたこう
**OpenWeatherMap**というAPIを使います。
まずは
https://openweathermap.org/
に登録してAPIキーを入手しましょう。

アプリでネットワーク接続を有効にするためにAndroidManifest.xmlに以下の内容を付け足しています。


```xml
    <uses-permission android:name="android.permission.INTERNET" />
```

OpenWeatherMapではAPIキーを使ってアクセスし、JSON形式のデータを取得します。中身は下の図のようになっています。左のパラメータをキーとして、右の値を取得します。サンプルコードを参考に見てみてください。

[![Image from Gyazo](https://i.gyazo.com/6f230ed4abc29db03e33eff0b1016adb.png)](https://gyazo.com/6f230ed4abc29db03e33eff0b1016adb)

## 課題
1. 自分の表示させたい情報を表示させてみましょう
2. 色々加工してみてください（ListViewで全国の天気出すとか）