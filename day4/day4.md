# お願い

補講の日程決めと生存確認を兼ねてメールの返信をお願いします。

# XML（レイアウト）について

## WidgetsとLayoutについて

Androidには**Widgets**と**Layout**があります。  
Layoutが「枠」で、Widgetsは枠の中の「部品」のようなものです。

ここまで紹介してきたのはWidgetsです。  
Layoutは教える時間があまりないのでここで紹介するにとどめておきます。

時間があるときにいじって遊んでみてください。

## ID

今までコードでwidgetsをコードで操作する時にidというものを使ってきたと思います。  
これはlayoutやwidgetsを識別するためのものです。  
Design側の右上のID欄もしくはText側で

`android:id="@+id/【つけたいid(名前)】`

と書くことで指定できます。

## Layout

Layoutの種類を簡単に紹介します。

- RelativeLayout  
中の要素の位置関係を相対的に決めるlayout
- LinearLayout  
縦か横1列に要素を整列させるlayout
- FrameLayout  
要素を重ねて表示することのできるlayout
- ConstraintLayout  
要素に制約を持たせて位置を決めることのできるlayout

恐らく今まで触ってきたのはConstraintLayoutだと思います。
これらをまとめて**view**といいます。

Layoutは自分でいじってみるのが一番早いと思うので色々試してみてください。

## Layoutのプロパティ

- width/height  
それぞれ幅・高さを表します。  
各viewの大きさを決めることができます。
- match_parent  
親のViewに対していっぱいに、幅・高さを調節することができるプロパティです。 
- wrap_content  
Viewの内容の大きさに合わせて、幅・高さを調節することができるプロパティです。
- dp  
大きさを数字で絶対的に決める時に用いる単位です。Androidの端末には色々な画面サイズがあるので、色々な画面サイズに対応させるためには、相対的に大きさを決めたほうが好ましいです。

## 課題

LinearLayoutを使ってButtonを整列させてみてください。

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