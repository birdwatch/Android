# お願い

補講の日程決めと生存確認を兼ねてメールの返信をお願いします。

# XML（レイアウト）について

# ListView

データの一覧を表示したい時、ListViewを使うと簡単に表示することができます。

## Adapter

このListViewを扱うために、Adapterというものを使用します。  
Adapterとは、データを画面上に表示するための橋渡しのようなものです。

Adapterの例を紹介します。

- ArrayAdapter
　　配列やListを1行に1つ表示する時に使う

- SimpleAdapter
　　XMLファイルで定義されたビューを表示する時に使う

- BaseAdapter
　　ArrayAdapter・CursorAdapter・SimpleAdapterのスーパークラス。  
    　　独自のListViewを作りたいとき（例えば画像と文字が一つの枠の中にあるやつ）はこれを使います（ムズイ）


今回は一番簡単なArrayAdapterを使います。

## Code

以下のように書きます。

```java:main.java(一部)

    ListView listView;

    private static final String[] charas = {
            "花", "乃愛", "ひなた"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        // ArrayAdapterのオブジェクト生成、↑のcharasの内容をセット
        // support_simple_spinner_dropdown_itemはAndroid Studioにもともと用意されてるレイアウトファイル
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item ,charas);

        // Adapterの内容をListViewにセット
        listView.setAdapter(arrayAdapter);

    }
```

## XML

<img src = "https://i.imgur.com/T4OE3RR.png">

## 結果

<img src="https://i.imgur.com/TcL2E5r.png">

## 課題

1. 追加ボタンを押したら要素（簡単な数字とか文字（固定））が追加されるlistviewを実装してください。

2. edittextに文字を入力して、追加ボタンを押したらその内容が要素として追加される、  
削除ボタンを押したら一番上の要素が削除されるlistviewを実装してください。（難しいので多分宿題）




# ImageView

画像も簡単に表示することができます。


## 画像表示

画像表示するだけならコードを書かなくてもできます。

(プロジェクト名)\app\src\main\res\drawableに表示したい画像を入れましょう。 （XMLに挿入した画像の左側参照） 
ImageViewというGUI部品をxmlに追加すると表示する画像を選択する画面が出てきます。  
ここで表示したい画像をタップするとxml上にも画像が表示されるようになります。

## Code

コードで画像を表示するには以下のように書きます。


```java: main.java(一部)
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ImageViewのオブジェクトを作成
        // アクテビティのImageViewと紐づけ
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        // ↑にdrawable/hana.pngをセット
        imageView.setImageResource(R.drawable.hana);

    }
    
```

## XML

<img src = "https://i.imgur.com/lW5Fhus.png">

## 結果

<img  src = "https://i.imgur.com/IJQ59EZ.png">


## 課題

1. おみくじアプリを作ってください（占うボタンを押したら占い結果の画像がランダムで出る）

2. 考え中