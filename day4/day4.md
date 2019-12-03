# day4

- お願い

補講の日程決めと生存確認を兼ねてメールの返信をお願いします。

1. ListView

データの一覧を表示したい時、ListViewを使うと簡単に表示することができます。

### Adapter

このListViewを扱うために、Adapterというものを使用します。

Adapterとは、データを画面上に表示するための橋渡しのようなものです。

Adapterの例を紹介します。

- ArrayAdapter
　　配列やListを1行に1つ表示する時に使う
- SimpleAdapter
　　XMLファイルで定義されたビューを表示する時に使う
- BaseAdapter
　　ArrayAdapter・CursorAdapter・SimpleAdapterのスーパークラス。
　　独自のAdapterクラスを定義して、レイアウトを自由にカスタマイズする時に使う

2. ImageView