### お願い

補講の日程決めと生存確認を兼ねてメールの返信をお願いします。

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

```main.java(一部)
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

xml側はこんな感じです。

<img src = "https://i.imgur.com/T4OE3RR.png">

## 結果

<img src="https://i.imgur.com/TcL2E5r.png">

## 課題

1. 考え中

# ImageView

画像も簡単に表示することができます。

## 課題
1. おみくじアプリを作ってください