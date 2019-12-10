# 画面遷移
ついにこの時が来ました。画面遷移をしてみましょう。
画面遷移には**Intent**というものを使います。実はIntentにも種類があるのですが、今回は一番基本的なものを扱います。

## Activity
一回目の授業で勉強したActivityを思い出してみましょう。Activityとは簡単にいうとアプリの画面のことです。そして、今までXMLで製作していた画面がActivityです。

Intentを用いれば、このActivityを切り替えることができます。まずは遷移先のActivityとそれにつけるServiceを作ってみましょう。以下のように作ってください。


![Imgur](https://i.imgur.com/Oby0PRo.png)

![Imgur](https://i.imgur.com/iupYK7j.png)

ここに今までと同じようにアプリを作っていくことで遷移先のアプリを作ることができます。

## Intentの使い方
Intentの大まかな手順は以下のようになっています。

1. Intentオブジェクトを作成
2. 付加情報(アクティビティ間での共有)を設定
3. Activityを起動
   
実際に遷移するときには以下のように書きます。

```java:main.java(一部)
    // Intentオブジェクトを作成
    // 二つ目の引数は遷移先のActivityのclass
    Intent intent = new Intent(this, SubActivity.class);

    // 付加情報を設定
    // intent.putExtra("key", value);

    // Activityを起動
    startActivity(intent);
```

アプリをbuildする前にSubActivityが存在することをアプリ側に教える必要があります。アプリ側の設定をいじるときには**Manifest**をいじります。Manifestでは、回転の可否や起動時のActivityの選択等ができます。

AndroidMainfest.xmlを開いて以下のコードをMainActivityの部分の下に挿入しましょう。

```xml:main.java(一部)
        <activity
            android:name=".SubActivity"
            android:label="@string/app_name" >
        </activity>
```

これを追加することでアプリがSubActivityというServiceを認識することができるようになります。

これでbuildをすることで画面遷移ができるようになるはずです。

## 値渡し
intent間で変数の値を渡すことが可能です。

渡す側
```java:main.java(一部)
    String value = "value";
    // 付加情報を設定
    intent.putExtra("key", value);
```

渡される側
```java:main.java(一部)
    Intent intent = getIntent();
    String someData = intent.getStringExtra("value");
```

## 戻るには
戻るには現在開いているActivityを終了することで戻ることができます。

渡される側
```java:main.java(一部)
    finish();
```

## intentの種類
さっきまで扱っていたのが**明示的intent**というものです。どのように動作するかを指定する**アクション**や、システムによって起動するActivityが自動で選択される**暗黙的intent**というものがあります。

試しにurlを暗黙的intentで開いてみましょう。


```java:main.java(一部)
    // URLの文字列からURIオブジェクトを生成
    Url uri = URI.parse("http://watatentv.com/");
    // アクションを設定
    String action - "android.intent.action.VIEW";
    // 暗黙的intentの生成
    Intent intent = new Intent(action, uri);
    startActivity(intent);
```
これは外部intentを暗黙的intentで開いています。

## 課題
1. 前回製作した占いアプリの占い結果を遷移先で表示してください
2. クイズを出して遷移先で正解不正解を出す→戻るボタンで戻るアプリを作ってください